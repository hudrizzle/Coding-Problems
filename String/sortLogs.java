class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                String[] arr1 = a.split(" ", 2);//split to {id, val}
                String[] arr2 = b.split(" ", 2);
                boolean isDigit1 = Character.isDigit(arr1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(arr2[1].charAt(0));
                if (!isDigit1 && !isDigit2) {//both are letter logs
                    int cmp = arr1[1].compareTo(arr2[1]);
                    if (cmp == 0){//tie, would decide by id
                        return arr1[0].compareTo(arr2[0]);
                    }
                    return cmp;
                }
                //one of them is digit or both of them are digits
                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            }
        });
        return logs;
    }
}
