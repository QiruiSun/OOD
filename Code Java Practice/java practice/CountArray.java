public class CountArray {
	

	public int[] run(int[] nums) {
		int[] count = new int[nums.length];
		int[] indexArray = buildIndex(nums);
		int[] helper = new int[nums.length];
		
		helper(count, indexArray, helper, 0, nums.length-1, nums);
		
		return count;
	}

	private void helper(int[] count, int[] indexArray, int[] helper, int left, int right, int[] nums) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		helper(count, indexArray, helper, left, mid, nums);
		helper(count, indexArray, helper, mid+1, right, nums);
		merge(count, indexArray, helper, left, right, nums);
	}

	private void merge(int[] count, int[] indexArray, int[] helper, int left, int right, int[] nums) {
		for (int i = left; i <= right; i++) {
			helper[i] = indexArray[i];
		}

		int l = left;
		int mid = left + (right - left) / 2;
		int r = mid + 1;
		int curr = l;
		while (l <= mid) {
			if (r > right || nums[helper[l]] <= nums[helper[r]]) {
				count[helper[l]] += r - mid - 1;
				indexArray[curr++] = helper[l++];
			} else {
				indexArray[curr++] = helper[r--];
			}
		}
	}

	private int[] buildIndex(int[] nums) {
		int[] index = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			index[i] = i;
		}
		return index;
	}
}





// 4,1,2,3


// 0,1,2,3

// count 0, 1, 2, 3


// indexArray 0, 1, 2, 3
// 		   1  0  2  3
		   

// 		   1, 4, 2, 3


// helper     1  0  2  3

// left 0, right 1
// 4, 1


// 1, 4 








