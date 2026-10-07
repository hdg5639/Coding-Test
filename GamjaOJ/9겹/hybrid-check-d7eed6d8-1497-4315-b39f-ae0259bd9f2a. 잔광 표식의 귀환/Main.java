import java.io.*;
import java.util.*;

public class Main {
    static int B;
    static int[] head, nxt, to, wt;

    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        B = nextInt(in);
        int M = nextInt(in), K = nextInt(in), S = nextInt(in), T = nextInt(in);
        long[] G = new long[K], C = new long[K];
        for (int i = 0; i < K; i++) { G[i] = nextLong(in); C[i] = nextLong(in); }

        head = new int[B + 1];
        Arrays.fill(head, -1);
        nxt = new int[2 * M]; to = new int[2 * M]; wt = new int[2 * M];
        int ec = 0;
        for (int e = 0; e < M; e++) {
            int u = nextInt(in), v = nextInt(in), w = nextInt(in);
            to[ec] = v; wt[ec] = w; nxt[ec] = head[u]; head[u] = ec++;
            to[ec] = u; wt[ec] = w; nxt[ec] = head[v]; head[v] = ec++;
        }
        int[] p = new int[K];
        long[] x = new long[K], y = new long[K];
        for (int i = 0; i < K; i++) { p[i] = nextInt(in); x[i] = nextInt(in); y[i] = nextInt(in); }
        long[][] A = new long[K][K];
        for (int i = 0; i < K; i++) for (int j = 0; j < K; j++) A[i][j] = nextLong(in);

        // 거리: dS[i] = d(S, p_i), dT[i] = d(p_i, T), dP[j][i] = d(p_j, p_i)
        long[] dS = new long[K], dT = new long[K];
        long[][] dP = new long[K][K];
        long[] distS = dijkstra(S);
        for (int i = 0; i < K; i++) dS[i] = distS[p[i]];
        HashMap<Integer, long[]> cache = new HashMap<>();
        for (int j = 0; j < K; j++) {
            long[] d = cache.get(p[j]);
            if (d == null) { d = dijkstra(p[j]); cache.put(p[j], d); }
            for (int i = 0; i < K; i++) dP[j][i] = d[p[i]];
            dT[j] = d[T];
        }

        int full = (1 << K) - 1;
        // add[mask][i] = sum_{v in mask} A[v][i]
        long[][] add = new long[1 << K][K];
        for (int mask = 1; mask <= full; mask++) {
            int low = Integer.numberOfTrailingZeros(mask);
            int prev = mask & (mask - 1);
            for (int i = 0; i < K; i++) add[mask][i] = add[prev][i] + A[low][i];
        }

        final long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[1 << K][K];
        for (long[] r : dp) Arrays.fill(r, INF);

        for (int i = 0; i < K; i++) {
            long r = visit(dS[i], 0, i, x, y, G, C, add);
            if (r < dp[1 << i][i]) dp[1 << i][i] = r;
        }
        for (int mask = 1; mask <= full; mask++) {
            for (int j = 0; j < K; j++) {
                long t = dp[mask][j];
                if (t >= INF) continue;
                for (int i = 0; i < K; i++) {
                    if ((mask >> i & 1) != 0) continue;
                    long r = visit(t + dP[j][i], mask, i, x, y, G, C, add);
                    int nm = mask | (1 << i);
                    if (r < dp[nm][i]) dp[nm][i] = r;
                }
            }
        }

        long ans = INF;
        for (int i = 0; i < K; i++) {
            if (dp[full][i] < INF) ans = Math.min(ans, dp[full][i] + dT[i]);
        }
        System.out.println(ans >= 4000000000L ? -1 : ans);
    }

    // p_i에 시각 a로 도착, 직전 방문 집합 mask에서 표식 i를 방문하고 p_i로 돌아온 시각 (불가능하면 INF)
    static long visit(long a, int mask, int i, long[] x, long[] y, long[] G, long[] C, long[][] add) {
        final long INF = Long.MAX_VALUE / 4;
        if (a >= INF) return INF;
        long t1 = a + x[i];
        if (t1 >= G[i] + add[mask][i]) return INF;
        long t2 = t1 + y[i];
        if (t2 >= C[i] + add[mask][i]) return INF;
        int nm = mask | (1 << i);
        long t3 = t2 + y[i];
        if (t3 >= G[i] + add[nm][i]) return INF;
        return t3 + x[i];
    }

    static long[] dijkstra(int src) {
        long[] dist = new long[B + 1];
        Arrays.fill(dist, Long.MAX_VALUE / 4);
        dist[src] = 0;
        long[] heap = new long[nxt.length + 2];
        int size = 0;
        heap[size++] = ((long) 0 << 18) | src;
        while (size > 0) {
            long top = heap[0];
            long last = heap[--size];
            // sift down
            int idx = 0;
            while (true) {
                int l = 2 * idx + 1;
                if (l >= size) break;
                int r = l + 1;
                int c = (r < size && heap[r] < heap[l]) ? r : l;
                if (heap[c] < last) { heap[idx] = heap[c]; idx = c; } else break;
            }
            if (size > 0) heap[idx] = last;

            long d = top >>> 18;
            int u = (int) (top & ((1 << 18) - 1));
            if (d > dist[u]) continue;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                long nd = d + wt[e];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    long key = (nd << 18) | v;
                    int k = size++;
                    while (k > 0) {
                        int par = (k - 1) >> 1;
                        if (heap[par] > key) { heap[k] = heap[par]; k = par; } else break;
                    }
                    heap[k] = key;
                }
            }
        }
        return dist;
    }

    private static int nextInt(DataInputStream in) throws IOException {
        return (int) nextLong(in);
    }

    private static long nextLong(DataInputStream in) throws IOException {
        int b = in.read();
        while (b != '-' && (b < '0' || b > '9')) b = in.read();
        boolean neg = false;
        if (b == '-') { neg = true; b = in.read(); }
        long r = 0;
        while (b >= '0' && b <= '9') { r = r * 10 + (b - '0'); b = in.read(); }
        return neg ? -r : r;
    }
}