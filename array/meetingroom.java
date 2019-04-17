//O(nlogn)
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null) return false;
        Collections.sort(intervals, (a,b) -> a.start - b.start);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < intervals.get(i - 1).end) return false;
        }
        return true;
    }
}
