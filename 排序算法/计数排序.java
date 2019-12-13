/**
 * 计数排序
 */
public class Demo8 {

    public static void main(String[] args) {


    }

    public static int[] get(int[] arr) {
        int max = arr[0];
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        int[] countArr = new int[max + 1];
        for (int i = 0; i < length; i++) {
            countArr[arr[i]]++;
            arr[i] = 0;
        }

        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] > 0) {
                arr[index++] = i;
            }
        }
        return arr;
    }

    //稳定排序
    public static int[] get1(int[] arr) {
        //找出数组中的最大值
        int max = arr[0];
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        //初始化计数数组
        int[] countArr = new int[max + 1];
        //计数
        for (int i = 0; i < length; i++) {
            countArr[arr[i]]++;
        }
        //顺序累加

        for (int i = 1; i < max + 1; i++) {
            countArr[i] = countArr[i - 1] + countArr[i];
        }
        int[] sortedArr = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            sortedArr[countArr[arr[i]] - 1] = arr[i];
            countArr[arr[i]]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArr[i];
        }

        return arr;
    }
}
