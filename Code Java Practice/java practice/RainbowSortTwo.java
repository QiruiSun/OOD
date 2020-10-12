public class RainbowSortTwo {
    public int[] rainbowSortII(int[] array) {
        // Write your solution here
        if (array.length == 0) {
          return array;
        }
    
        int[] count = new int[4];
        for (int i = 0; i < array.length; i++) {
          if (array[i] == 0) {
            count[0]++;
          }
          else if (array[i] == 1) {
            count[1]++;
          }
          else if (array[i] == 2) {
            count[2]++;
          }
          else {
            count[3]++;
          }
        }
    
        int[] output = new int[array.length];
    
        int k = 0;
        for (int i = 0; i < count.length; i ++) {
            while (count[i] > 0) {
                output[k++] = i;
                count[i]--;
            }
        }

        for (int x = 0; x < output.length; x++) {
            System.out.println(output[x]);
        }

        return output;
    }
    
      // private void swap(int a1, int a2, int[] array) {
      //   int temp = array[a1];
      //   array[a1] = array[a2];
      //   array[a2] = temp;
      // }

    public int[] rainbowSortIII(int[] array, int k) {
      // Write your solution here
  
      int[] pointers = new int[k];
  
      while (pointers[k-1] < array.length) {
        int value = array[pointers[k-1]];
  
        for (int i=k-1; i > value-1; i--) {
          swap(pointers[i], pointers[i-1], array);
        }
  
        for (int i=k-1; i >= value-1; i--) {
          pointers[i]++;
        }
      }
      return array;
    }
    
    private void swap(int a, int b, int[] array) {
      int temp = array[a];
      array[a] = array[b];
      array[b] = temp;
    }
}

abstract class D {
  public abstract int lol(int a);
}