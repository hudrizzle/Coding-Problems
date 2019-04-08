/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        List<Connection> res = new ArrayList<Connection>();
        //corner cases 
        if (connections == null || connections.size() == 0) {
            return res;
        }
        //sort all connections according to their costs in ascending order
        Collections.sort(connections, new Comparator<Connection>(){
            @Override
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) return a.cost - b.cost;
                else if (!a.city1.equals(b.city1)) return a.city1.compareTo(b.city1);
                else return a.city2.compareTo(b.city2);
            }
        });
        //covert cities to their code starting from 0
        Map<String, Integer> hash = new HashMap<>();
        int citycount = 0;
        for (Connection con : connections) {
            if (!hash.containsKey(con.city1)) hash.put(con.city1, citycount++);
            if (!hash.containsKey(con.city2)) hash.put(con.city2, citycount++);
        }
        //build union find 
        uf mst = new uf(citycount);
        for (Connection con : connections) {
            int id1 = hash.get(con.city1);
            int id2 = hash.get(con.city2);
            int root1 = mst.find(id1);
            int root2 = mst.find(id2);
            if (root1 != root2) {
                mst.union(root1, root2);//not connected 
                res.add(con);
            }
        }
        return res.size() == citycount - 1 ? res : new ArrayList<Connection>();
    }
    //given input n cities, build UnionFind graph of n vertices from 1 to n
    class uf{
        int[] p;
        int[] rank;
        public uf(int n) {
            this.p = new int[n + 1];
            this.rank = new int[n + 1];
            //initial parent is itself, initial rank is 1
            for (int i = 0; i < n + 1; i++) {
                p[i] = i;
                rank[i] = 1;
            }
        }
        //return the root of vertex i
        public int find(int i){
            if (p[i] == i) return i;
            p[i] = find(p[i]);
            return p[i];
        }
        //connect i, j blocks together according to rank
        public void union(int i, int j) {
            int ranki = rank[i];
            int rankj = rank[j];
            if (ranki < rankj) p[i] = j;
            else if (ranki > rankj) p[j] = i;
            else {//ranks are equal, update one of the rank
                p[i] = j;
                rank[j]++;
            }            
        }
    }
}
