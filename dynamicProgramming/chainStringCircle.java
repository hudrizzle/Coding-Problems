//if the given string array can be chained together-> T/F
//SWAP, similar to string permutation since: given strings are all used; check certain permutation of them can satisfy 
//O(n!)

public boolean chainCircle (String[] words) {
	if (words == null || words.length == 0) return false;
	return checkStringCircle(words, 1);//index can start from anywhere since final result can be a circle. here choose 1 to avoid outOfBound ERROR
}
private boolean checkStringCircle(String[] words, int index) {
	//base
	if (index == words.length){//remember to check if the first word can be chained with the last word
		return canChain(words[index - 1], words[0]);
	}
	//induction rule: try swapping starting from index with the indices behind it
	for (int i = index; i < words.length; i++){
		if (canChain(words[index - 1], words[i])) {//if word at index i can be added to the tail of word at index - 1, then swap.
			swap(words, i, index);
			if (checkStringCircle(words, index + 1)) return true;//if it returns T from deepest all way up, then it should be valid
			swap(words, i, index);//swap back to original to try another one
		}
	}
	return false;
}
private boolean canChain(String str1, String str2) {
	return str1.charAt(str1.length() - 1) == str2.charAt(0);
}
private void swap(String[] words, int i, int j) {
	String temp = words[i];
	words[i] = words[j];
	words[j] = temp;
}
