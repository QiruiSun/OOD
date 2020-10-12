public class SearchRoateArray {
    public int search(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0) return -1;
        if (array[0] == target) return 0;
        int pivot = findRotate(array);

        System.out.println(pivot);
        if (target > array[pivot]) return -1;
        if (target == array[pivot]) return pivot;

        if (target >= array[0]) {
            return bSearch(0, pivot, array, target);
        }
        else {
            return bSearch(pivot, array.length - 1, array, target);
        }
    }
    
    private int findRotate(int[] array) {
        int left = 0, right = array.length - 1;

        if (array[left] < array[right])
            return 0;

        while (left <= right) {
            int pivot = (left + right) / 2;
            if (array[pivot] > array[pivot + 1]) return pivot;
            else {
            if (array[pivot] == array[left] && array[pivot] == array[right]) {
                right--;
            }
            else if (array[pivot] < array[left]) {
                right = pivot - 1;
            }
            else {
                left = pivot + 1;
            }
            }
        }
        return 0;
    }
    
    private int bSearch(int left, int right, int[] array, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] > target) {
            right = mid - 1;
            }
            else {
            left = mid + 1;
            }
        }
        return - 1;
    }
}