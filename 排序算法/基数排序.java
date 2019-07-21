
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序
 */
public class Demo6 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(sort(new int[]{892, 896, 199, 322, 72, 653})));

    }

    public static int[] sort(int[] arr) {
        int length = arr.length;

        //最大值
        int max = arr[0];
        for (int i = 0; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //当前排序位置
        int location = 1;
        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        //长度为10，装入余数0-9的数据
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList());
        }

        while (true) {
            //判断是否排完
            Integer pow = (int) (Math.pow(10, (location - 1)));
            if (max < pow) {
                break;
            }

            //数据入桶
            for (int i = 0; i < length; i++) {
                int number = (arr[i] / pow) % 10;
                bucketList.get(number).add(arr[i]);
            }
            //写会数组
            int nn = 0;
            for (int i = 0; i < 10; i++) {
                int size = bucketList.get(i).size();
                for (int ii = 0; ii < size; ii++) {
                    arr[nn++] = bucketList.get(i).get(ii);
                }

                bucketList.get(i).clear();
            }
            location++;
        }
        return arr;
    }

}
