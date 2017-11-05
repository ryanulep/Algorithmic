public class BubbleSort {
    private int [] array;
    private int size;
    private int i_index;
    private int j_index;
    private int optimizer;
    private boolean is_sorted;

    public BubbleSort() {
        array = null;
        size = 0;
    }
    
    public BubbleSort(int[] n) {
        size = n.length;
        array = new int[size];
        i_index = 0;
        j_index = 0;
        optimizer = 0;
        for (int i = 0; i < size; i++) {
            array[i] = n[i];
        }
    }

    public void Print() {
        System.out.print("\t\t");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.print("i: " + i_index + "\tj: " + j_index);
        System.out.println();
    }
    
    public void Sort() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public void Next() {
        is_sorted = true;
        if (i_index == array.length) { return; }        
        if (j_index == array.length-1) { 
            i_index++;
            j_index = 0;
        }

        // Swap conditions
        if (array[j_index] > array[j_index+1]) {
            int temp = array[j_index];
            array[j_index] = array[j_index+1];
            array[j_index+1] = temp;
            is_sorted = false;
            optimizer = 0;
        }      

        // If BubbleSort has not swapped anything in length-1 attempts,
        // then it is has finished.
        if (optimizer == array.length-1 && is_sorted) { return; }

        Print();
        optimizer++;
        j_index++;
    }

    public static void main(String[] args) {
        int[] array = new int[5];
        array[0] = 5;
        array[1] = 4;
        array[2] = 3;
        array[3] = 2;
        array[4] = 1;
    
        BubbleSort bs = new BubbleSort(array);

        bs.Print();
        for (int i = 0; i < 30; i++) {
            bs.Next();
        }
    }
}