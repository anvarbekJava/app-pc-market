package uz.pdp.apppcmarket.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ixtiyoriy 5ta son kiriting: ");
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int f = in.nextInt();
        int max, min;
        if ((a > b) && (a > c) && (a > d) && (a > f)) {
            max = a;
            System.out.println("1-son eng katta " + max);
        } else if ((b > a) && (b > c) && (b > d) && (b > f)) {
            max = b;
            System.out.println("2-son eng katta " + max);
        } else if ((c > a) && (c > b) && (c > d) && (c > f)) {
            max = c;
            System.out.println("3-son eng katta " + max);
        } else if ((d > a) && (d > b) && (d > c) && (d > f)) {
            max = d;
            System.out.println("4-son eng katta " + max);
        } else if ((f > a) && (f > b) && (f > c) && (f > d)) {
            max = f;
            System.out.println("5-son eng katta " + max);
        }

        if ((a < b) && (a < c) && (a < d) && (a < f)) {
            min = a;
            System.out.println("1-som eng kichik"+min);
        } else if ((b < a) && (b < c) && (b < d) && (b < f)) {
            min = b;
            System.out.println("2-son eng kichik"+min);
        } else if ((c < a) && (c < b) && (c < d) && (c < f)) {
            min = c;
            System.out.println("3-son eng kichik"+min);
        } else if ((d < a) && (d < b) && (d < c) && (d < f)) {
            min = d;
            System.out.println("4-son eng kichik"+min);
        } else if ((f < a) && (f < b) && (f < c) && (f < d)) {
            min = f;
            System.out.println("5-son eng kichik"+min);
        }
    }
}
