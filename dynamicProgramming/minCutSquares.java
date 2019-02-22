package trial;
//time: O(n^0.5)?   space:O(n)
public class cutMinSquares {
	public static void main(String[] args) {
		int ans = minCutSquares(4);
		System.out.println(ans);
	}
	public static int minCutSquares(int n) {
		int[] M = new int[n + 1];//from M[0] to M[n]
		//base
		M[0] = 0;
		M[1] = 1;
		//M[i] = min(1+ M[i - j^2]) for all j from 1 to j^2 <= i
		for (int i = 2; i <= n; i++){
			M[i] = i;//set a max value, i number of 1 sum up to i
			for (int j = 1; j * j <= i; j++){
				M[i] = Math.min(M[i], 1 + M[i - j * j]);
			}
		}
		return M[n];
	}
}
