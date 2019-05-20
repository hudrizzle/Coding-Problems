//O(n^2) time:  brute force: for each histogram, geenrate from center to outer
public class Solution {
    public int maxTrapped(int[] array) {
        if (array == null || array.length < 3) return 0;
        int waterTrapped = 0;
        for (int i = 0; i < array.length; i++) {
            int left = i - 1, right = i + 1;
            int maxHeiL = 0, maxHeiR = 0;
            while (left >= 0) {
                maxHeiL = Math.max(maxHeiL, array[left--]);
            }
            while (right < array.length) {
                maxHeiR = Math.max(maxHeiR, array[right++]);
            }
            int ith = Math.min(maxHeiL, maxHeiR) - array[i];
            waterTrapped += (ith > 0 ? ith : 0);
        }
        return waterTrapped;
    }
}

//improve brute force: for each histogram, geenrate from center to outer -> duplicated searching
//only search maxHeight so far once from left and right -> DP
//Time: O(n), space: O(n)
public class Solution {
    public int maxTrapped(int[] array) {
        if (array == null || array.length < 3) return 0;
        int waterTrapped = 0;
        int[] maxHeiL = new int[array.length];
        int[] maxHeiR = new int[array.length];
        maxHeiL[0] = array[0];
        maxHeiR[array.length - 1] = array[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            maxHeiL[i] = Math.max(maxHeiL[i - 1], array[i]);
        }
        for (int i = array.length - 2; i >= 0; i--) {
            maxHeiR[i] = Math.max(maxHeiR[i + 1], array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            int ith = Math.min(maxHeiL[i], maxHeiR[i]) - array[i];
            waterTrapped += (ith > 0 ? ith : 0);
        }
        return waterTrapped;
    }
}

//Greedy: 2 pointers L,R move from 2 sides until met each other: calculate ith water trapped
//Time:O(N), SPACE: O(1).
public class Solution {
    public int maxTrapped(int[] array) {
        if (array == null || array.length < 3) return 0;
        int waterTrapped = 0;
        int l = 0, r = array.length - 1;
        int maxL = array[0], maxR = array[array.length - 1];
        //last point where L, R meet must be a high point, would not trap water
        while(l < r) {
            maxL = Math.max(array[l], maxL);
            maxR = Math.max(array[r], maxR);
            if (maxL <= maxR) {
                int curwater = Math.min(maxL, maxR) - array[l];
                waterTrapped += (curwater > 0 ? curwater : 0);
                l++;
            }else {
                int curwater = Math.min(maxL, maxR) - array[r];
                waterTrapped += (curwater > 0 ? curwater : 0);
                r--;
            }
        }
        return waterTrapped;
    }
}
