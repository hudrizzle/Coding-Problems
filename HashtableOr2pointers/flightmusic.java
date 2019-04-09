import java.util.Arrays;
//return a pair of movie indices which sum up <= fightDuration - 30
//doesn't matter if contains duplicates duration
//only note to return the one with longest duration if tie
public class flightmusic {
    public static void main(String[] args) {
        flightmusic test = new flightmusic();
        int[] res1 = test.getMovieIdes(new int[]{1,3, 4, 6, 7}, 38);
        int[] res2 = test.getMovieIdes(new int[]{1,2,4,7,6,4}, 38);
        System.out.println("first movie: " + res1[0] +" second movie: " + res1[1]);
        System.out.println("first movie: " + res2[0] +" second movie: " + res2[1]);
    }
    public int[] getMovieIdes(int[] movieDurations, int flightDuration) {
        Pair[] pairs = createPairs(movieDurations);
        Arrays.sort(pairs, (a, b) -> a.movieDuration - b.movieDuration);
        int[] res = new int[]{-1, -1};
        int i = 0; int j = pairs.length - 1;
        int durationSum = 0;
        while(i < j) {
            int curDuration = pairs[i].movieDuration + pairs[j].movieDuration;
            if (curDuration == flightDuration - 30){
                return new int[]{pairs[i].index, pairs[j].index};
            }else if (curDuration > flightDuration - 30) {
                j--;
            }else {//curDuration < flightDuration - 30, also valid but should optimize
                if (curDuration > durationSum){//update res and old duration
                    durationSum = curDuration;
                    res = new int[] {pairs[i].index, pairs[j].index};
                }
                i++;
            }
        }
        return res;//if all combination > fightDuration - 30, not found, return {-1,-1}
    }

    private Pair[] createPairs(int[] movieDurations) {
        Pair[] pairs = new Pair[movieDurations.length];
        for(int i = 0; i < movieDurations.length; i ++) {
            pairs[i] = new Pair(i, movieDurations[i]);
        }
        return pairs;
    }

}

class Pair {
    int index;
    int movieDuration;

    public Pair(int index, int movieDuration) {
        this.index = index;
        this.movieDuration = movieDuration;
    }
}
