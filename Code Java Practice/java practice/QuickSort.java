public class QuickSort {
    public void sort(int[] array) {
        quickSort(array, 0, array.length-1);
    }
    
    
    public void quickSort (int[] array, int l, int r) {
        if (l > r) return;
        int pivot = partition(array, l, r);
        quickSort(array, l, pivot -1);
        quickSort(array, pivot + 1, r);
    }
    
    
    private int partition(int[] array, int l, int r) {
        // use last element as pivot
        int pivot = array[r];
        int right = r - 1;
        while ( l <= right ) {
            if (array[l] < pivot) {
                l++;
            }
            else if (array[right] > pivot) {
                right--;
            }
            else {
                int temp = array[l];
                array[l] = array[right];
                array[right] = temp;
                l++;
                right--;
            }
        }
    
        int temp = array[r];
        array[r] = array[l];
        array[l] = temp;
        return l;
    }
    
    
    
}