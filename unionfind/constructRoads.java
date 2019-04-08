import java.util.*;
//time: ncurRoads + klogk-  k is newRoadsConstruct
//Note that Union operation takes essentially constant time[1] when UnionFind is implemented with both path compression and union by rank.
//amazon OA: construct roads between cities
//assume currently available roads does not conform cycles. if so, what to do? - doesn't matter, won't union it
public class MinCostConstructRoads {
    public static void main (String[] args) {
        MinCostConstructRoads test = new MinCostConstructRoads();
        List<List<Integer>> curRoads = new ArrayList<>();
        curRoads.add(Arrays.asList(1,4));
        curRoads.add(Arrays.asList(4,5));
        curRoads.add(Arrays.asList(1,5));
        List<List<Integer>> newRoadsCost = new ArrayList<>();
        newRoadsCost.add(Arrays.asList(1,2,5));
        newRoadsCost.add(Arrays.asList(1,3,10));
        newRoadsCost.add(Arrays.asList(1,6,2));
        newRoadsCost.add(Arrays.asList(5,6,5));
        newRoadsCost.add(Arrays.asList(3,6,6));
        newRoadsCost.add(Arrays.asList(2,3,3));
        int cost = test.lowestCost(6,3,curRoads,4,newRoadsCost);
        System.out.println(cost);
    }

    public int lowestCost(int nCities, int ncurRoads, List<List<Integer>> curRoads,
                          int newRoadsConstruct, List<List<Integer>> newRoadsCost) {

        //corner cases: there're only 1 or 0 cities
        if (nCities < 2) {//|| ncurRoads >= nCities - 1 should not be added
            return 0;
        }
        //sort all connections according to their costs in ascending order
        Collections.sort(newRoadsCost, (a, b) -> a.get(2) - b.get(2));

        int countBlocks = nCities;//count the number of separate blocks, until it becomes 1.
        //build union find
        uf mst = new uf(nCities);
        //traverse all available roads first
        for (List<Integer> curRoad : curRoads) {
            int root1 = mst.find(curRoad.get(0));
            int root2 = mst.find(curRoad.get(1));
            if (root1 != root2) {//city1 and city2 are not connected, union them
                mst.union(root1, root2);
                countBlocks--;
            }
        }
        if (countBlocks == 1) return 0;// n cities are already all connected by current roads

        int totalCost = 0;
        //traverse the ordered future roads to connect the blocks
        for (List<Integer> newRoad : newRoadsCost) {
            int root1 = mst.find(newRoad.get(0));
            int root2 = mst.find(newRoad.get(1));
            if (root1 != root2) {// not connected, union them and count the total cost
                mst.union(root1, root2);
                countBlocks--;
                totalCost += newRoad.get(2);
                System.out.println("building road between " + newRoad.get(0) + " and " + newRoad.get(1));
            }
        }
        //must guarantee final total block is 1- all connected, or there'll be isolation(return -1, no answer)
        return countBlocks == 1 ? totalCost : -1;
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

----------------------
//union find by checking number of nodes connected to the root
public class MinCostConnectCity {
    public int minCostConnectCities(int cities, int roadsNum, int[][] roads, int k, int[][] addPath) {
        if(cities == 0) return 0;
        UnionFind uf = new UnionFind(cities + 1);
        for(int[] road : roads) {
            uf.connect(road[0], road[1]);
        }

        Arrays.sort(addPath, (a, b) -> a[2] - b[2]);
        int res = 0;
        for(int i = 0; i < k; i ++) {
            int[] path = addPath[i];
            int a = path[0], b = path[1], cost = path[2];
            if(uf.connect(a, b)) {
                res += cost;
            }

            if(uf.findConnectNodes(a) == cities) {
                return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] roads = new int[][]{{1,4}, {4,5}, {2,3}};
        int[][] addPaths = new int[][]{{1,2,5}, {1,3,10}, {1,6,2}, {5,6,5}};
        int k = 4, cities = 6;
        MinCostConnectCity mcc = new MinCostConnectCity();
        System.out.println(mcc.minCostConnectCities(cities, roads.length, roads, k, addPaths));
    }
}


class UnionFind {
    int[] ids;
    int[] nums;

    public UnionFind(int n) {
        this.ids = new int[n];
        this.nums = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            nums[i] = 1;
        }
    }

    public boolean connect(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if(pi != pj) {
            if(nums[pi] >= nums[pj]) {
                ids[pj] = pi;
                nums[pi] += nums[pj];
            }
            else {
                ids[pi] = pj;
                nums[pj] += nums[pi];
            }
            return true;
        }
        return false;
    }

    public int find(int n) {
        while (ids[n] != n) {
            ids[n] = ids[ids[n]];
            n = ids[n];
        }
        return n;
    }
    public int findConnectNodes(int n) {
        return nums[find(n)];
    }
}
