import java.util.*;

public class UserSolution {
    static final int INF = Integer.MAX_VALUE / 4;
    static final int MAXR = 4001;
    static final int REQ = 0, RESP = 1;
    static final int EV_DONE = 0, EV_ARRIVE = 1, EV_CREATE = 2, EV_TIMEOUT = 3;

    int N, H;
    int[] policy, timeout, maxRetry, cap, proc;
    int[][] hop; long[][] lat;
    int[][] adj, adjW; boolean[][] adjOk; int[] deg;
    int[][] nxt;

    // router runtime
    int[] busy;                 // 처리 중 패킷 id (0이면 유휴)
    int[][] q; int[] qHead, qSize;
    int[] total, lost;

    // request
    boolean[] reqExists;
    long[] reqStart; int[] reqSrc, reqDst, reqRes, reqStatus, reqAttempt;
    int[][] attemptFirstHop;
    boolean[] destSeen; int[] lastRespFirstHop;

    // packets
    int pCnt;
    int[] pType, pReq, pTarget, pHops, pExclude, pAttempt;
    boolean[] pDecided;

    // event heap: (time, key)  key = type<<50 | id<<3 | attempt
    long[] hT = new long[1 << 16], hK = new long[1 << 16]; int hN;
    long now;

    public void init(int N, int M, int H, int[] routers, int[] links, int[] staticNext) {
        this.N = N; this.H = H;
        policy = new int[N + 1]; timeout = new int[N + 1]; maxRetry = new int[N + 1];
        cap = new int[N + 1]; proc = new int[N + 1];
        for (int u = 1; u <= N; u++) {
            int b = 5 * (u - 1);
            policy[u] = routers[b]; timeout[u] = routers[b + 1]; maxRetry[u] = routers[b + 2];
            cap[u] = routers[b + 3]; proc[u] = routers[b + 4];
        }
        deg = new int[N + 1];
        adj = new int[N + 1][12]; adjW = new int[N + 1][12]; adjOk = new boolean[N + 1][12];
        hop = new int[N + 1][N + 1]; lat = new long[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(hop[i], INF); Arrays.fill(lat[i], Long.MAX_VALUE / 4);
            hop[i][i] = 0; lat[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            int u = links[5 * i], v = links[5 * i + 1], w = links[5 * i + 2];
            addEdge(u, v, w, links[5 * i + 3] == 1);
            addEdge(v, u, w, links[5 * i + 4] == 1);
            hop[u][v] = hop[v][u] = 1;
            lat[u][v] = lat[v][u] = w;
        }
        // Floyd-Warshall (hop, latency 각각 독립 최단거리)
        for (int k = 1; k <= N; k++) for (int i = 1; i <= N; i++) {
            int hik = hop[i][k]; long lik = lat[i][k];
            for (int j = 1; j <= N; j++) {
                if (hik + hop[k][j] < hop[i][j]) hop[i][j] = hik + hop[k][j];
                if (lik + lat[k][j] < lat[i][j]) lat[i][j] = lik + lat[k][j];
            }
        }
        nxt = new int[N + 1][N + 1];
        for (int u = 1; u <= N; u++) for (int d = 1; d <= N; d++)
            nxt[u][d] = staticNext[(u - 1) * N + (d - 1)];

        busy = new int[N + 1];
        q = new int[N + 1][]; qHead = new int[N + 1]; qSize = new int[N + 1];
        for (int u = 1; u <= N; u++) q[u] = new int[Math.max(1, cap[u])];
        total = new int[N + 1]; lost = new int[N + 1];

        reqExists = new boolean[MAXR]; reqStart = new long[MAXR];
        reqSrc = new int[MAXR]; reqDst = new int[MAXR]; reqRes = new int[MAXR];
        reqStatus = new int[MAXR]; reqAttempt = new int[MAXR];
        attemptFirstHop = new int[MAXR][5];
        destSeen = new boolean[MAXR]; lastRespFirstHop = new int[MAXR];

        pCnt = 0;
        int pc = 1 << 12;
        pType = new int[pc]; pReq = new int[pc]; pTarget = new int[pc]; pHops = new int[pc];
        pExclude = new int[pc]; pAttempt = new int[pc]; pDecided = new boolean[pc];
        hN = 0;
        now = -1;
    }

    void addEdge(int u, int v, int w, boolean ok) {
        int k = deg[u]++;
        adj[u][k] = v; adjW[u][k] = w; adjOk[u][k] = ok;
    }

    public void addRequest(int id, long startTime, int source, int destination, int executionResult) {
        reqExists[id] = true; reqStart[id] = startTime;
        reqSrc[id] = source; reqDst[id] = destination; reqRes[id] = executionResult;
        reqStatus[id] = 0; reqAttempt[id] = 0;
        Arrays.fill(attemptFirstHop[id], 0);
        destSeen[id] = false; lastRespFirstHop[id] = 0;
        push(startTime, ((long) EV_CREATE << 50) | ((long) id << 3));
    }

    public void advance(long time) {
        while (hN > 0 && hT[0] <= time) {
            long t = hT[0], key = hK[0];
            pop();
            now = t;
            int type = (int) (key >>> 50);
            int id = (int) ((key >>> 3) & ((1L << 47) - 1));
            int att = (int) (key & 7);
            if (type == EV_DONE) onDone(id, t);
            else if (type == EV_ARRIVE) arrive(id, pTarget2[id], t);
            else if (type == EV_CREATE) onCreate(id, t);
            else onTimeout(id, att, t);
        }
        now = time;
    }

    // 링크 이동 중인 패킷의 도착 라우터
    int[] pTarget2 = new int[1 << 12];
    // 처리 중 라우터 (완료 이벤트용)
    int[] pAt = new int[1 << 12];

    int newPacket(int type, int req, int target, int exclude, int attempt) {
        int id = ++pCnt;
        if (id >= pType.length) grow();
        pType[id] = type; pReq[id] = req; pTarget[id] = target; pHops[id] = H;
        pExclude[id] = exclude; pAttempt[id] = attempt; pDecided[id] = false;
        return id;
    }

    void grow() {
        int n = pType.length * 2;
        pType = Arrays.copyOf(pType, n); pReq = Arrays.copyOf(pReq, n);
        pTarget = Arrays.copyOf(pTarget, n); pHops = Arrays.copyOf(pHops, n);
        pExclude = Arrays.copyOf(pExclude, n); pAttempt = Arrays.copyOf(pAttempt, n);
        pDecided = Arrays.copyOf(pDecided, n);
        pTarget2 = Arrays.copyOf(pTarget2, n); pAt = Arrays.copyOf(pAt, n);
    }

    // 패킷이 라우터 u에 도착 (로컬 도착 포함)
    void arrive(int p, int u, long t) {
        if (busy[u] == 0) {
            startProc(p, u, t);
        } else if (qSize[u] < cap[u]) {
            q[u][(qHead[u] + qSize[u]) % q[u].length] = p;
            qSize[u]++;
        } // else 폐기
    }

    void startProc(int p, int u, long t) {
        busy[u] = p; pAt[p] = u;
        push(t + proc[u], ((long) EV_DONE << 50) | ((long) p << 3));
    }

    void onDone(int p, long t) {
        int u = pAt[p];
        busy[u] = 0;                                   // (1) 처리 중 해제
        int r = pReq[p];
        if (pType[p] == REQ && u == pTarget[p]) {      // (2) 목적지 REQUEST
            int ex = destSeen[r] ? lastRespFirstHop[r] : 0;
            destSeen[r] = true;
            int rp = newPacket(RESP, r, reqSrc[r], ex, 0);
            lastRespFirstHop[r] = forward(rp, u, t);
        } else if (pType[p] == RESP && u == pTarget[p]) { // 송신자 RESPONSE
            if (reqStatus[r] <= 2) reqStatus[r] = reqRes[r] == 1 ? 3 : 4;
        } else {
            int fh = forward(p, u, t);
            if (fh >= 0 && pType[p] == REQ) attemptFirstHop[r][pAttempt[p]] = fh;
        }
        if (qSize[u] > 0) {                             // (3) 다음 패킷 시작
            int np = q[u][qHead[u]];
            qHead[u] = (qHead[u] + 1) % q[u].length; qSize[u]--;
            startProc(np, u, t);
        }
    }

    // 다음 홉 결정 및 전송.
    // 첫 결정이면 선택된 홉(없으면 0)을 반환, 첫 결정이 아니면 -1.
    int forward(int p, int u, long t) {
        boolean first = !pDecided[p];
        pDecided[p] = true;
        if (pHops[p] == 0) return first ? 0 : -1;
        int d = pTarget[p];
        int pol = policy[u];
        int ex = first ? pExclude[p] : 0;
        int chosenIdx = -1;

        if (pol == 3) {
            int s = nxt[u][d];
            if (s != 0) for (int k = 0; k < deg[u]; k++) if (adj[u][k] == s) { chosenIdx = k; break; }
            // 유일 후보이므로 제외 규칙을 적용해도 그대로 사용
        } else {
            int cnt = 0; boolean exIn = false;
            for (int k = 0; k < deg[u]; k++) {
                int v = adj[u][k];
                if (hop[v][d] >= INF) continue;
                cnt++;
                if (v == ex) exIn = true;
            }
            boolean skipEx = exIn && cnt > 1;
            long b0 = 0, b1 = 0, b2 = 0; int bv = 0;
            for (int k = 0; k < deg[u]; k++) {
                int v = adj[u][k];
                if (hop[v][d] >= INF) continue;
                if (skipEx && v == ex) continue;
                long L = adjW[u][k] + lat[v][d], Hp = 1 + hop[v][d];
                long c0, c1, c2;
                if (pol == 0) { c0 = Hp; c1 = L; c2 = 0; }
                else if (pol == 1) { c0 = L; c1 = Hp; c2 = 0; }
                else { c0 = (busy[v] != 0 ? 1 : 0) + qSize[v]; c1 = L; c2 = Hp; }
                if (chosenIdx < 0 || c0 < b0 || (c0 == b0 && (c1 < b1 || (c1 == b1 && (c2 < b2 || (c2 == b2 && v < bv)))))) {
                    chosenIdx = k; b0 = c0; b1 = c1; b2 = c2; bv = v;
                }
            }
        }
        if (chosenIdx < 0) return first ? 0 : -1;       // 후보 없음 → 폐기
        int v = adj[u][chosenIdx];
        if (adjOk[u][chosenIdx]) {
            pHops[p]--;
            pTarget2[p] = v;
            push(t + adjW[u][chosenIdx], ((long) EV_ARRIVE << 50) | ((long) p << 3));
        } // 방향 금지 → 폐기 (홉 번호는 기록)
        return first ? v : -1;
    }

    void onCreate(int r, long t) {
        int s = reqSrc[r];
        reqStatus[r] = 1; reqAttempt[r] = 0; total[s]++;
        startAttempt(r, 0, 0, t);
    }

    void startAttempt(int r, int a, int ex, long t) {
        int s = reqSrc[r];
        push(t + timeout[s], ((long) EV_TIMEOUT << 50) | ((long) r << 3) | a);
        int p = newPacket(REQ, r, reqDst[r], ex, a);
        arrive(p, s, t);
    }

    void onTimeout(int r, int a, long t) {
        if (reqStatus[r] >= 3) return;
        int s = reqSrc[r];
        if (a < maxRetry[s]) {
            reqStatus[r] = 2; reqAttempt[r] = a + 1;
            startAttempt(r, a + 1, attemptFirstHop[r][a], t);
        } else {
            reqStatus[r] = 5; lost[s]++;
        }
    }

    public int getStatus(int requestId) { return reqStatus[requestId]; }

    public int[] getLossRouters(int K, int P) {
        int c = 0; int[] tmp = new int[N];
        for (int u = 1; u <= N; u++)
            if (total[u] >= K && 100L * lost[u] >= (long) P * total[u]) tmp[c++] = u;
        return Arrays.copyOf(tmp, c);
    }

    public int[] getRouterState(int u) {
        return new int[]{busy[u] != 0 ? 1 : 0, qSize[u], total[u], lost[u]};
    }

    // ---- min-heap ----
    boolean less(int i, int j) { return hT[i] < hT[j] || (hT[i] == hT[j] && hK[i] < hK[j]); }
    void swap(int i, int j) { long a = hT[i]; hT[i] = hT[j]; hT[j] = a; a = hK[i]; hK[i] = hK[j]; hK[j] = a; }
    void push(long t, long k) {
        if (hN == hT.length) { hT = Arrays.copyOf(hT, hN * 2); hK = Arrays.copyOf(hK, hN * 2); }
        int i = hN++; hT[i] = t; hK[i] = k;
        while (i > 0) { int p = (i - 1) >> 1; if (less(i, p)) { swap(i, p); i = p; } else break; }
    }
    void pop() {
        hN--; hT[0] = hT[hN]; hK[0] = hK[hN];
        int i = 0;
        while (true) {
            int l = 2 * i + 1, r = l + 1, m = i;
            if (l < hN && less(l, m)) m = l;
            if (r < hN && less(r, m)) m = r;
            if (m == i) break; swap(i, m); i = m;
        }
    }
}