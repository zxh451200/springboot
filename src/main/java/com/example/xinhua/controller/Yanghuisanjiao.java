public class Main {
    //    杨辉三角
    public static void main(String[] args) {
        print(10);
    }

    /**
     * @param i 行坐标
     * @param j 列坐标
     * @return
     */
    public static int sanjiao(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        }
        return sanjiao(i - 1, j - 1) + sanjiao(i - 1, j);
    }

    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", sanjiao(i, j));
            }
            System.out.println();
        }
    }

    public static void printSpace(int n, int m) {
        int num = (n - 1 - m) * 2;
        for (int i = 0; i < num; i++) {
            System.out.print(" ");
        }
    }
}
