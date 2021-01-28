package com.example.basics.swing;

import java.util.Scanner;

public class tuxing {
    public static void main(String[] args) {
        chengfabiao();
        System.out.println();
        sanjiaoxing();
        System.out.println();
        sanjiaoxing2();
        System.out.println();
        changfangxin();
        System.out.println();
        dengyaosanjiaoxing();
        System.out.println();
        lingxing();
        System.out.println();
        lingxing2();
        System.out.println();
        lingxing3();
        System.out.println();
        lingxing4();
        System.out.println();
        pingxingsibianxing();
        System.out.println();
        what();
    }


    public static void lingxing() {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 4 - i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        //下半部分
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < i + 1; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 4 - i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void lingxing4() {

        //下半部分
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 5 - i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 4 - i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void lingxing2() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * (5 - i) - 1; j++) {
                System.out.print(5 - i);
            }
            System.out.println();
        }
    }

    public static void lingxing3() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (j == 1 || j == 2 * i - 1) {
                    System.out.print("*");//上半截
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * (5 - i) - 1; j++) {
                if (j == 1 || j == 2 * (5 - i) - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void sanjiaoxing() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void sanjiaoxing2() {
        for (int i = 0; i < 10; i++) {
            for (int j = 10; j > i; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }


    }

    public static void chengfabiao() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j < i + 1; j++) {
                System.out.print(j + " x " + i + " = " + i * j+" ");
            }
            System.out.println();
        }
    }

    public static void dengyaosanjiaoxing() {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 4 - i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void changfangxin() {
        for (int j = 0; j < 4; j++) {//外层循环控制行数
            for (int i = 0; i < 5; i++) {//内层循环控制列数
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void pingxingsibianxing() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void scanner() {
        String messge;
        int age;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a line of text !");
        messge = scan.nextLine();
        System.out.println("You entered: " + messge);
        System.out.println("Enter a line of age !");
        age = scan.nextInt();
        System.out.println("You entered: " + age);
    }

    /**
     * 若随意输出一个n,输出打印n行
     */
    public static void what2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            //输出n-i个空格
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            //输出2*i-1个星
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            //换行
            System.out.println();
        }
    }

    /**
     * 若根据用户输入的数字来打印菱形的行数
     *
     * @param size
     */
    public static void printHollowRhombus(int size) {
        if (size % 2 == 0) {
            size++;// 计算菱形大小
        }
        for (int i = 0; i < size / 2 + 1; i++) {
            for (int j = size / 2 + 1; j > i + 1; j--) {
                System.out.print("  ");// 输出左上角位置的空白
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                if (j == 0 || j == 2 * i) {
                    System.out.print("* ");// 输出菱形上半部边缘
                } else {
                    System.out.print("  ");// 输出菱形上半部空心
                }
            }
            System.out.println("");        //换行
        }
        for (int i = size / 2 + 1; i < size; i++) {
            for (int j = 0; j < i - size / 2; j++) {
                System.out.print("  ");// 输出菱形左下角空白
            }
            for (int j = 0; j < 2 * size - 1 - 2 * i; j++) {
                if (j == 0 || j == 2 * (size - i - 1)) {
                    System.out.print("* ");// 输出菱形下半部边缘
                } else {
                    System.out.print("  ");// 输出菱形下半部空心
                }
            }
            System.out.println("");        //换行
        }
    }

    public void method() {
        int sumi = 0, sumj = 0;
        for (int i = 0, j = 0; j < 10; i++, j++) {
            sumi += i;
            sumj += j;
            System.out.println("sumi= " + sumi + "  sumj= " + sumj);
        }
        System.out.println("sumi= " + sumi + "  sumj= " + sumj);
    }

    public static void what() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入菱形的行数：");
        int i = scanner.nextInt();
    }

    public static void method2() {
        int x = 20;
        out:
        for (int i = 2; i < 10; i++) {
//            System.out.println("i = " + i );
            while (x < 1000) {
//                System.out.println("i = " + i +"   "+x);
                if (i * x >= 80) {
                    System.out.println("i = " + i + "   " + x + "   " + i * x);
                    break out;
                } else {
                    x += 5;
                    System.out.println("i = " + i + "   " + x);
                }
            }
        }
        System.out.println("  x= " + x);
    }
}

