
/**
 * class Interval {
 *   public int start;
 *   public int end;
 *   public Interval(int start, int end) {
 *     this.start = start;
 *     this.end = end;
 *   }
 * }
 */
//keep track of last Interval in res, do one of those:
//1.last.end < cur.start, add cur Interval, update last = cur
//2.last.end >= cur.start, only update last.end to max
class Solution {
  public int length(List<Interval> intervals) {
    List<Interval> res = new ArrayList<>();
    if (intervals == null || intervals.size() == 0) return 0;
    Collections.sort(intervals, new Comparator<Interval>(){
      @Override
      public int compare(Interval a, Interval b) {
        return a.start - b.start;
      }
    });
    Interval last = null;
    for (Interval interval : intervals) {
      if (last == null || last.end < interval.start) {//add new interval to res list
        res.add(interval);
        last = interval;
      }else {//only update current last interval end time
        last.end = Math.max(interval.end, last.end);
      }
    }
    //claculate how many hours in total
    int total = 0;
    for (Interval in : res) {
      total += in.end - in.start;
    }
    return total;
  }
}

//improve to O1 space but still Onlogn time
public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return intervals;
        }
        
        Comparator<Interval> comparator = new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                if (a.start == b.start) {
                    return a.end - b.end;
                }
                return a.start - b.start;
            }
        };
        Collections.sort(intervals, comparator);

        // Merge
        int last = -1;
        int index = 0;
        for (Interval item : intervals) {
            if (last == -1 || last < item.start) {
                intervals.get(index).start = item.start;
                intervals.get(index).end = item.end;
                last = item.end;
                index++;
            } else {
                last = Math.max(last, item.end);
                intervals.get(index - 1).end = last;
            }
        }
        
        return intervals.subList(0, index);
    }
}
