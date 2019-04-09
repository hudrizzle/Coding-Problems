import java.util.*;

//since sorting array will remove original index information, must record <index, number> if use 2 pointers
//can use hashmap- more clear; also can use 2 pointers but note the duplicates
public class TwosumAllPair {
    public static void main(String[] args) {
        TwosumAllPair test = new TwosumAllPair();
        List<List<Integer>> res1 = test.allPairs(new int[]{1,2,2,4}, 6);
        List<List<Integer>> res2 = test.allPairshash(new int[]{1,2,2,4}, 6);
        System.out.println(res1);
        System.out.println(res2);
    }
    class Pair {
        int value;
        int index;
        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
        int getValue() {
            return this.value;
        }
    }
    public List<List<Integer>> allPairs(int[] numbers, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //assume numbers not null, length > 2
        Pair[] number = new Pair[numbers.length];
        for(int i=0;i<numbers.length;i++) {
            number[i] = new Pair(numbers[i], i);
        }
        Arrays.sort(number, (a, b) -> a.value - b.value);
        int L=0, R = numbers.length-1;
        while(L < R) {
            if(number[L].getValue() + number[R].getValue() == target) {
                res.add(new ArrayList<>(Arrays.asList(number[L].index, number[R].index)));
                int i = L + 1;
                while (i < R && number[i].getValue() == number[L].getValue()) {
                    res.add(new ArrayList<>(Arrays.asList(number[i].index, number[R].index)));
                    i++;
                }
                R--;
            }else if(number[L].getValue() + number[R].getValue() < target) {
                L++;
            } else {
                R--;
            }
        }
        return res;
    }
    //use hashtable for 2sum. since need to return index, hashset is not enough to use. also duplicates, indices stored in list
    public List<List<Integer>> allPairshash(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //corner case
        if (array == null || array.length < 2) {
            return res;
        }
        //map records integers and their indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        //traverse array
        for (int i = 0; i < array.length; i++) {
            List<Integer> indices = map.get(target - array[i]);
            if (indices != null){//found at least 1 number to match, add to res
                for (Integer index : indices) res.add(new ArrayList<>(Arrays.asList(index, i)));
            }
            //also add each number into map and their indices for further matching.(will happen first)
            if (map.get(array[i]) == null) map.put(array[i], new ArrayList<>());
            map.get(array[i]).add(i);
        }
        return res;
    }
}
