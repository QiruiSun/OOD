public class EnlosingBlackPixels {

	public int minArea(int[][] image, int x, int y) {
		// Write your solution here
		if (image == null || image.length == 0 || image[0].length == 0) {
		  return 0;
		}
	
		int minX = rowBinaryS(image, 0, x, 1);
		int maxX = rowBinaryS(image, x, image.length - 1, 0) - 1;
	
		int minY = colBinaryS(image, 0, y, 1);
		int maxY = colBinaryS(image, y, image[0].length - 1, 0) -1;
	
		return (maxX - minX + 1) * (maxY - minY + 1);
	  }
	
	  private int rowBinaryS(int[][] image, int left, int right, int target) {
		while (left < right) {
		  int mid = left + (right - left) / 2;
		  boolean midHasOne = rowHasT(image, mid, target);
		  if (midHasOne) {
			right = mid;
		  } else {
			left = mid + 1;
		  }
		}
		return rowHasT(image, left, 1) && target == 0  ? left + 1 : left;
	  }
	
	  private int colBinaryS(int[][] image, int left, int right, int target) {
		while (left < right) {
		  int mid = left + (right - left) / 2;
		  boolean midHasOne = colHasT(image, mid, target);
		  if (midHasOne) {
			right = mid;
		  } else {
			left = mid + 1;
		  }
		}
		return colHasT(image, left, 1) && target == 0 ? left + 1 : left;
	  }
	
	  private boolean colHasT(int[][] image, int col, int target) {
		boolean found = false;
		for (int i = 0; i < image.length; i++) {
		  if (image[i][col] == 1) {
			found = true;
			break;
		  }
		}
		return target == 1 ? found : !found;
	  }
	
	   private boolean rowHasT(int[][] image, int row, int target) {
		boolean found = false;
		for (int i = 0; i < image[0].length; i++) {
		  if (image[row][i] == 1) {
			found = true;
			break;
		  }
		}
		return target == 1 ? found : !found;
	  }
}