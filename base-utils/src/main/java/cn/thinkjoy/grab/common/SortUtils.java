package cn.thinkjoy.grab.common;

/**
 * Created by admin on 2016/2/23.
 */
public class SortUtils {
    //基数排序的基数
    private int radix=10;

    /**
     * 基数排序
     * @param array
     * @param radix
     */
    public void radixSort(int[] array,int radix){
        setRadix(10);
    }
    /**
     * 基数排序
     * @param array
     */
    public void radixSort(int[] array) {
        // 数组的第一维表示可能的余数0-radix，第二维表示array中的等于该余数的元素
        // 如：十进制123的个位为3，则bucket[3][] = {123}
        int[][] bucket = new int[radix][array.length];
        int distance = getDistance(array); // 表示最大的数有多少位
        int temp = 1;
        int round = 1; // 控制键值排序依据在哪一位
        while (round <= distance) {
            // 用来计数：数组counter[i]用来表示该位是i的数的个数
            int[] counter = new int[radix];
            // 将array中元素分布填充到bucket中，并进行计数
            for (int i = 0; i < array.length; i++) {
                int which = (array[i] / temp) % radix;
                bucket[which][counter[which]] = array[i];
                counter[which]++;
            }
            int index = 0;
            // 根据bucket中收集到的array中的元素，根据统计计数，在array中重新排列
            for (int i = 0; i < radix; i++) {
                if (counter[i] != 0)
                    for (int j = 0; j < counter[i]; j++) {
                        array[index] = bucket[i][j];
                        index++;
                    }
                counter[i] = 0;
            }
            temp *= radix;
            round++;
        }
    }

    private int getDistance(int[] array) {
        int max = computeMax(array);
        int digits = 0;
        int temp = max / radix;
        while(temp != 0) {
            digits++;
            temp = temp / radix;
        }
        return digits + 1;
    }

    private int computeMax(int[] array) {
        int max = array[0];
        for(int i=1; i<array.length; i++) {
            if(array[i]>max) {
                max = array[i];
            }
        }
        return max;
    }

    private void setRadix(int radix) {
        this.radix = radix;
    }
}
