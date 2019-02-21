package trial;

import java.util.Arrays;

public class printIfBlock {
	//print all combinations of if block given n -> similar to add parenthesis
	//add indent before each inner if block
	//use int[] res to add results -> 0: left,"if{" 1: right"}". size is 2*n including all candidates in the final result
	public static void printIfBlocks(int n) {
		int[] res = new int[2 * n];
		combinationRec(n, n, 0, res, n);
	}
	public static void main(String[] args) {
		//printIfBlock mytest = new printIfBlock();
		printIfBlock.printIfBlocks(3);
	}
	//left, right respectively represents how many left/right candidates left to be added
	//level represents index of res
	private static void combinationRec(int left, int right, int level, int[] res, int n) {
		//base: reached leaf node, print out the res(should contains 2*n candidates now)
		if (level == 2*n) {
			System.out.println(Arrays.toString(res));
			printRes(res);
			return;
		}
		//recursion rule: for each node, 2 choices go deeper, but check left/right first to see if you can go deeper
		//dont need to undo "add" here because it'll be replaced directly by new candidate
		if (left > 0) {//still have left remaining, can continue add
			res[level] = 0;
			combinationRec(left - 1, right, level + 1, res, n);
		}
		if (right > left) {//must have left first then you can have right
			res[level] = 1;
			combinationRec(left, right - 1, level + 1, res, n);
		}
	}
	//difficulty: find out how to automatically add 2 indents before each inner if{} block
	//rule: keep heading var indicating how many indents to be added for next candidate printing
	//for left"if{" -> print out first then add heading by 1; for right"}" -> subtract 1 from heading first then print it out
	private static void printRes(int[] res) {
		int heading = 0;
		for (int i = 0; i < res.length; i++) {
			if (res[i] == 0){//left
				printSpace(heading);
				System.out.println("if {");
				heading += 2;
			}else {//right
				heading -= 2;
				printSpace(heading);
				System.out.println("}");
			}
		}
	}
	private static void printSpace(int heading) {
		for (int i = 0; i < heading; i++){
			System.out.print(" ");
		}
	}
}
