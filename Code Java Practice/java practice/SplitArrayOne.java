import java.util.List;
import java.util.ArrayList;

class SplitArrayOne {
    public List<List<Integer>> splitArray( int[] input, int k) {
        List<Integer> res = new ArrayList<Integer>();
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
    
        helper(input, res, sum/k, 0, 0, k);
        
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        for (int i = 0; i < res.size(); i ++) {
            int start = 0;
            if (i > 0) {
                start = res.get(i-1) + 1;
            }
            List<Integer> curr = new ArrayList<Integer>();

            for ( int j = start; j < res.get(i) + 1; j ++) {
                curr.add(input[j]);
            }
            output.add(curr);
        }
        return output;
    }
    
    public boolean helper(int[] array, List<Integer> output, int target, int lvl, int start, int maxsize) {
        // base case
        if (lvl == maxsize || start == array.length) {
            return lvl == maxsize && start == array.length;
        }
    
        int currSum = 0;
        for (int i = start; i < array.length; i++) {
            currSum += array[i];
            if (currSum == target) {
                output.add(i);
                if (helper(array, output, target, lvl + 1, i+1, maxsize)) {
                    return true;
                }
                output.remove(output.size() - 1);
             }
        }
        return false;
    }
    
}