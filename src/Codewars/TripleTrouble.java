package Codewars;

public class TripleTrouble {
    public static void main(String[] args) {
        fun(451999L, 41177722899L);
    }

    public static int fun(long num1, long num2) {
        long mNum1 = num1;
        long mNum2 = num2;

        boolean flag1 = false;
        boolean flag2 = false;

        long x = 0;
        long xx = 0;
        long xxx = 0;

        while ((!flag1 && !flag2) || mNum1 != 0) {
            x = mNum1 % 10;
            xx = mNum1 % 10;
            xxx = mNum1 % 10;

            mNum1 /= 10;

        }

        if (x == xx) {
            flag1 = true;
        }

        if (flag1 && xx == xxx) {
            flag2 = true;
        }

        if (flag1 && flag2) {

        }

        return 0;
    }
}
