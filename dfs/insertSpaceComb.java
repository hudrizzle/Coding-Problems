package trial;

public class insertComb {
	public static void insertionCombinations(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		combinationRec(arr, 0, sb, arr.length);
	}
	public static void main(String[] args) {
		String str = "ABCD";
		insertionCombinations(str);
	}
	private static void combinationRec(char[] arr, int level, StringBuilder prefix, int n) {
		//base: when level == n-1 can add last letter and then print, dont need to wait to level n
		if (level == n - 1) {
			prefix.append(arr[arr.length - 1]);
			printRes(prefix.toString());
			prefix.deleteCharAt(prefix.length() - 1);
			return;
		}
		//recursion rule: each node 2 choices-> add _ after this letter or not
		//not append _:
		prefix.append(arr[level]);
		combinationRec(arr, level + 1, prefix, n);
		//append _ after the letter:
		prefix.append("_");
		combinationRec(arr, level + 1, prefix, n);
		//remember to remove the added chars before return to higher level
		prefix.deleteCharAt(prefix.length() - 1);
		prefix.deleteCharAt(prefix.length() - 1);
	}
	private static void printRes(String output) {
		System.out.println(output);
	}
}
