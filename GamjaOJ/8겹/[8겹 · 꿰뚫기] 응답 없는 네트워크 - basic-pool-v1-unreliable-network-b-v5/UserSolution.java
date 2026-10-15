import java.util.*;

public class UserSolution {
    static final int INF = 1000000000;
    int n, limit;
    int[] policy, timeout, retry, capacity, processing, total, lost;
    int[][] hop, latency, weight, fixed;
    boolean[][] allowed;
    ArrayList<Integer>[] adj;
    ArrayDeque<Packet>[] waiting;
    boolean[] busy;
    Request[] requests;
    PriorityQueue<Event> events;
    long packetSeq;

    static class Request {
        int id, source, destination, result, status, attempt, responseFirst;
        int[] first = new int[5];
        Request(int id, int s, int d, int result) {
            this.id=id; source=s; destination=d; this.result=result;
        }
    }
    static class Packet {
        long id;
        Request request;
        int attempt, destination, remaining, exclude;
        boolean response, first=true;
        Packet(long id, Request r, int a, boolean response, int remaining, int exclude) {
            this.id=id; request=r; attempt=a; this.response=response;
            destination=response?r.source:r.destination;
            this.remaining=remaining; this.exclude=exclude;
        }
    }
    static class Event implements Comparable<Event> {
        long time, key;
        int type, router, attempt;
        Request request;
        Packet packet;
        Event(long t, int type, long key, int router, Request r, int a, Packet p) {
            time=t; this.type=type; this.key=key; this.router=router;
            request=r; attempt=a; packet=p;
        }
        public int compareTo(Event e) {
            int c=Long.compare(time,e.time);
            if(c!=0) return c;
            c=Integer.compare(type,e.type);
            if(c!=0) return c;
            c=Long.compare(key,e.key);
            return c!=0?c:Integer.compare(attempt,e.attempt);
        }
    }
    @SuppressWarnings("unchecked")
    public void init(int N, int M, int H, int[] routers, int[] links, int[] staticNext) {
        n=N; limit=H; packetSeq=0;
        policy=new int[n+1]; timeout=new int[n+1]; retry=new int[n+1];
        capacity=new int[n+1]; processing=new int[n+1];
        total=new int[n+1]; lost=new int[n+1]; busy=new boolean[n+1];
        hop=new int[n+1][n+1]; latency=new int[n+1][n+1];
        weight=new int[n+1][n+1]; fixed=new int[n+1][n+1];
        allowed=new boolean[n+1][n+1];
        adj=new ArrayList[n+1]; waiting=new ArrayDeque[n+1];
        requests=new Request[4001]; events=new PriorityQueue<>();
        for(int u=1;u<=n;u++) {
            int b=5*(u-1);
            policy[u]=routers[b]; timeout[u]=routers[b+1]; retry[u]=routers[b+2];
            capacity[u]=routers[b+3]; processing[u]=routers[b+4];
            adj[u]=new ArrayList<>(); waiting[u]=new ArrayDeque<>();
            Arrays.fill(hop[u],INF); Arrays.fill(latency[u],INF);
            hop[u][u]=latency[u][u]=0;
            for(int d=1;d<=n;d++) fixed[u][d]=staticNext[(u-1)*n+d-1];
        }
        for(int i=0;i<M;i++) {
            int b=i*5,u=links[b],v=links[b+1],w=links[b+2];
            adj[u].add(v); adj[v].add(u);
            hop[u][v]=hop[v][u]=1;
            latency[u][v]=latency[v][u]=weight[u][v]=weight[v][u]=w;
            allowed[u][v]=links[b+3]==1; allowed[v][u]=links[b+4]==1;
        }
        for(int k=1;k<=n;k++) for(int u=1;u<=n;u++) for(int d=1;d<=n;d++) {
            hop[u][d]=Math.min(hop[u][d],hop[u][k]+hop[k][d]);
            latency[u][d]=Math.min(latency[u][d],latency[u][k]+latency[k][d]);
        }
    }
    public void addRequest(int requestId,long startTime,int source,int destination,int executionResult) {
        Request r=new Request(requestId,source,destination,executionResult);
        requests[requestId]=r;
        events.add(new Event(startTime,2,requestId,source,r,0,null));
    }
    void startAttempt(Request r,int a,long t) {
        int exclude=a==0?0:r.first[a-1];
        r.attempt=a; r.status=a==0?1:2;
        if(a==0) total[r.source]++;
        Packet p=new Packet(++packetSeq,r,a,false,limit,exclude);
        arrive(p,r.source,t);
        events.add(new Event(t+timeout[r.source],3,r.id,0,r,a,null));
    }
    void begin(Packet p,int u,long t) {
        busy[u]=true;
        events.add(new Event(t+processing[u],0,p.id,u,null,0,p));
    }
    void arrive(Packet p,int u,long t) {
        if(!busy[u]) begin(p,u,t);
        else if(waiting[u].size()<capacity[u]) waiting[u].addLast(p);
    }
    int choose(int u,int d,int exclude) {
        if(policy[u]==3) return fixed[u][d];
        boolean alternative=false;
        for(int v:adj[u]) if(hop[v][d]<INF && v!=exclude) { alternative=true; break; }
        int best=0,ba=INF,bb=INF,bc=INF;
        for(int v:adj[u]) {
            if(hop[v][d]==INF || (alternative && v==exclude)) continue;
            int h=1+hop[v][d],l=weight[u][v]+latency[v][d];
            int a,b,c;
            if(policy[u]==0) { a=h; b=l; c=0; }
            else if(policy[u]==1) { a=l; b=h; c=0; }
            else { a=waiting[v].size()+(busy[v]?1:0); b=l; c=h; }
            if(best==0 || a<ba || (a==ba && (b<bb || (b==bb && (c<bc || (c==bc && v<best)))))) {
                best=v; ba=a; bb=b; bc=c;
            }
        }
        return best;
    }
    void forward(Packet p,int u,long t) {
        if(p.remaining==0) return;
        int v=choose(u,p.destination,p.first?p.exclude:0);
        if(v==0) return;
        if(p.first) {
            p.first=false;
            if(p.response) p.request.responseFirst=v;
            else p.request.first[p.attempt]=v;
        }
        if(!allowed[u][v]) return;
        p.remaining--;
        events.add(new Event(t+weight[u][v],1,p.id,v,null,0,p));
    }
    void complete(Packet p,int u,long t) {
        busy[u]=false;
        Request r=p.request;
        if(u!=p.destination) forward(p,u,t);
        else if(p.response) {
            if(r.status==1 || r.status==2) r.status=r.result==1?3:4;
        } else {
            int exclude=r.responseFirst;
            r.responseFirst=0;
            Packet response=new Packet(++packetSeq,r,0,true,limit,exclude);
            forward(response,u,t);
        }
        if(!waiting[u].isEmpty()) begin(waiting[u].removeFirst(),u,t);
    }
    public void advance(long time) {
        while(!events.isEmpty() && events.peek().time<=time) {
            Event e=events.poll();
            if(e.type==0) complete(e.packet,e.router,e.time);
            else if(e.type==1) arrive(e.packet,e.router,e.time);
            else if(e.type==2) startAttempt(e.request,0,e.time);
            else {
                Request r=e.request;
                if((r.status!=1 && r.status!=2) || r.attempt!=e.attempt) continue;
                if(e.attempt<retry[r.source]) startAttempt(r,e.attempt+1,e.time);
                else { r.status=5; lost[r.source]++; }
            }
        }
    }
    public int getStatus(int requestId) { return requests[requestId].status; }
    public int[] getLossRouters(int K,int P) {
        int[] answer=new int[n]; int count=0;
        for(int u=1;u<=n;u++) if(total[u]>=K && 100L*lost[u]>=(long)P*total[u]) answer[count++]=u;
        return Arrays.copyOf(answer,count);
    }
    public int[] getRouterState(int router) {
        return new int[]{busy[router]?1:0,waiting[router].size(),total[router],lost[router]};
    }
}
