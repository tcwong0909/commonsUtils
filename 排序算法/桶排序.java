import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 桶排序
 */
public class Demo7 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(get(new int[]{500, 6123, 1700, 10, 9999})));

    }

    public static int[] get(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        int length = arr.length;
        //最大最小值
        for (int i = 0; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            } else if (min > arr[i]) {
                min = arr[i];
            }
        }
        //最大和最小值 差值
        int diff = max - min;
        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            bucketList.add(new ArrayList<>());
        }
        //每个桶的存数区间
        float section = (float) diff / (float) (length - 1);
        //数据入桶
        for (int i = 0; i < length; i++) {
            //桶下表
            int num = (int) (arr[i] / section) - 1;
            if (num < 0) {
                num = 0;
            }
            bucketList.get(num).add(arr[i]);
        }
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }
        int index = 0;
        for (ArrayList<Integer> arrayList : bucketList) {
            for (Integer integer : arrayList) {
                arr[index] = integer;
                index++;
            }
        }
        return arr;
    }

}
