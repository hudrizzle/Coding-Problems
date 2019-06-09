/**通过判断mid和target是否处于翻转的同一边，来决定搜索的方向
如果在同一边，按照正常的二分搜索方向
如果在翻转的不同边，反转二分搜索的方向
如果 > 最后一个数:在左边, 否则在右边*/

public class Solution {
  public int search(int[] array, int target) {
    if (array == null || array.length == 0) return -1;
    int l = 0, r = array.length - 1, div = array[array.length - 1];
    //div is used to determine mid is on left or right part
    while (l + 1 < r) {
        int mid = l + (r - l) / 2;
        //found the target
        if (array[mid] == target) return mid;

        //mid and target are on the same side: do as binary search
        if (array[mid] > div && target > div || array[mid] <= div && target <= div) {
            if (array[mid] < target) l = mid + 1;
            else r = mid - 1;
        }else {//mid and target on the different side
            if (array[mid] <= div) r = mid - 1;
            else l = mid + 1;
        }
    }
    if (array[l] == target) return l;
    if (array[r] == target) return r;
    return -1;
  }
}
