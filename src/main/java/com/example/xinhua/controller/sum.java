import java.util.Arrays;
import java.util.HashMap;

public class qiuhe {
    //   求两数之和并返回索引值
    public static void main(String[] args) {

        int[] num = {3, 4, 5, 6, 3, 32};

        System.out.println(Arrays.toString(youhuaSum(num, 10)));
    }

    /**
     * @param num    数组
     * @param target 目标值
     * @return
     */
    public static int[] sum(int[] num, int target) {
        int[] res = new int[2];
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] + num[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public static int[] youhuaSum(int[] num, int target) {
        HashMap<Integer, Integer> store = new HashMap<>(num.length, 1);
        int[] res = new int[2];
        for (int i = 0; i < num.length; i++) {
            int chazhi = target - num[i];
            Integer getIndex = store.get(chazhi);
            if (null != getIndex) {
                res[0] = getIndex;
                res[1] = i;
                break;
            } else {
                store.put(num[i], i);
            }

        }
        return res;
    }


}
