import java.util.Arrays;

/**
 * 快速排序
 */
public class Demo9 {

    public static void main(String[] args) {

        int[] arr = new int[]{0, 8, 1, 6, 2, 3, 9, 7, 5};
        sort(arr);
        //
        System.out.println(Arrays.toString(arr));
    }


    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        int partition = partition(arr, startIndex, endIndex);
        sort(arr, startIndex, partition - 1);
        sort(arr, partition + 1, endIndex);

    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                mark++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }
}
