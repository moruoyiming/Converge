package com.algorithm.demo.array;

public class Q12_两个大数相加 {


    public static void main(String[] args) {
        String numA = "5135156864146198510515713546981304";
        String numB = "14105283157813257031975091759832782750923";
        String what = addStrings(numA, numB);
        System.out.println(what);
        System.out.println(numB);
    }

    public static String addStrings(String numA, String numB) {
        StringBuilder res = new StringBuilder();
        int i = numA.length() - 1;
        int j = numB.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int number1 = i >= 0 ? numA.charAt(i) - '0' : 0;
            int number2 = j >= 0 ? numB.charAt(j) - '0' : 0;
            int tmp = number1 + number2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) res.append(1);
        return res.reverse().toString();
    }
}
