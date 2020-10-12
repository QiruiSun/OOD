public class NumMatrix {
    private int[][] matrix;
    private int[][] memo;
    
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;    
        this.memo = new int[matrix.length][matrix.length];
        preCompute();
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        
        int upper = Math.min(row1, row2);
        int lower = Math.max(row1, row2);
        int left = Math.min(col1, col2);
        int right = Math.max(col1, col2);
        
		int subRes = left == 0 ? memo[upper][right] : memo[upper][right] - memo[upper][left - 1];
		int res = 0;
		if (lower < matrix.length - 1) {
			int toSubstract = left == 0 ? memo[lower+1][right] : memo[lower+1][right] - memo[lower+1][left - 1];
			res = subRes - toSubstract;
		} else {
			res = subRes;
		}
        
        
        // int res = lower == matrix.length - 1 ? subRes : subRes - toSubstract;
        return res;
    }
    
    private void preCompute() {
        for (int i = memo.length - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = 0; j < memo[0].length; j++) {
                sum += matrix[i][j];
                memo[i][j] = sum;
                if (i + 1 <= memo.length - 1) {
                    memo[i][j] += memo[i+1][j];
                }
            }
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */


