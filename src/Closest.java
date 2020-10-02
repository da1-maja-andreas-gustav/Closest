// Version: 20200917
// Handin done by:
//   <201905796> <Maja Vonge Cornils>
//   <202004107> <A. Malthe Henriksen>
//   <202007490> <Gustav Burchardt>
// Contributions:
//   <A. Malthe Henriksen> <Programmed the exercise>

import java.io.*;
import java.util.*;
public class Closest {
    public int computeClosest(ArrayList<Integer> input) {
        // Sort list
        Collections.sort(input);

        // Since inputs are between -1,000,000,000 and 1,000,000,000
        // will the shortest path be at least 2,000,000,000 so we may put it as a sentinel
        int closest = 2000000000;
        // Loop through each element.
        for (int i = 0; i < input.size(); i++) {
            // Get element i and i+1
            int first = input.get(i);
            int second = i != input.size()-1 ? input.get(i+1) : 2000000000;

            // Get distance
            int distance = Math.abs(first-second);
            // Compare distance
            closest = Math.min(distance, closest);
        }
        // Return shortest
        return closest;
    }

    public static void testAll() {
        clearTerminal();
        testSinglePair();
        testSinglePairInv();
        test1();
        test2();
        // testQuadraticTime();  // This is confusing, so don't run it by default
    }

    public static void testSinglePair() {
        int[] input = { 3, 5 };
        int correctAnswer = 2;

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : input) list.add(i);

        int output = new Closest().computeClosest(list);

        if (output != correctAnswer)
            outputFail("testSinglePair",
                    "Expected output " + correctAnswer +
                            " but got " + output);
        else
            outputPass("testSinglePair");
    }

    public static void testSinglePairInv() {
        int[] input = { 7, 4 };
        int correctAnswer = 3;

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : input) list.add(i);

        int output = new Closest().computeClosest(list);

        if (output != correctAnswer)
            outputFail("testSinglePairInv",
                    "Expected output " + correctAnswer +
                            " but got " + output);
        else
            outputPass("testSinglePairInv");
    }

    public static void test1() {
        int[] input = { 95, 66, 82, 63, 78, 37, 100, 96, 98, 17, 13, 7, 28, 74, 73, 77, 41, 25, 53, 93 };
        int correctAnswer = 96 - 95;

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : input) list.add(i);

        int output = new Closest().computeClosest(list);

        if (output != correctAnswer)
            outputFail("test1",
                    "Expected output " + correctAnswer +
                            " but got " + output);
        else
            outputPass("test1");
    }

    public static void test2() {
        int[] input = { 275, 938, 8, 77, 649, 803, 500, 823, 519, 711, 422, 227, 235, 655, 373 };
        int correctAnswer = 655 - 649;

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : input) list.add(i);

        int output = new Closest().computeClosest(list);

        if (output != correctAnswer)
            outputFail("test2",
                    "Expected output " + correctAnswer +
                            " but got " + output);
        else
            outputPass("test2");
    }

    public static void testQuadraticTime() {
        int n1 = 1000;
        int n2 = 10000;
        long t11 = timeTest(n1);
        long t12 = timeTest(n1);
        long t13 = timeTest(n1);
        long t14 = timeTest(n1);
        long t15 = timeTest(n1);
        long t1 = Math.min(Math.min(Math.min(Math.min(t11, t12), t13), t14), t15);
        System.out.println("");
        System.out.println("Testing time complexity.");
        System.out.println("Elapsed time on input size n1 = " + n1 + ": t1 = " + t1);
        long t21 = timeTest(n2);
        long t22 = timeTest(n2);
        long t23 = timeTest(n2);
        long t2 = Math.min(Math.min(t21, t22), t23);
        System.out.println("Elapsed time on input size n2 = " + n2 + ": t2 = " + t2);
        double slowdown = ((double) t2) / t1;
        double linearSlowdown = ((double) n2) / n1;
        System.out.println("Expected slowdown (for linear time): n2 / n1 = " + linearSlowdown);
        System.out.println("Measured slowdown: t2 / t1 = " + slowdown);
        if (slowdown > Math.pow(linearSlowdown, 1.2))
            System.out.println("That's not linear time!");
        else
            System.out.println("OK.");
    }

    private static long timeTest(int n) {
        long t1 = System.nanoTime();
        ArrayList<Integer> list = new ArrayList<Integer>(n);
        for (int i = 0; i < n; ++i)
            list.add((int) (i * 982451653L % 413158511L));
        new Closest().computeClosest(list);
        long t2 = System.nanoTime();
        // For debugging, output the individual times:
        // System.out.println(n + " " + (t2 - t1));
        return t2 - t1;
    }

    private static void clearTerminal() {
        System.out.print('\u000C');
    }

    private static void outputPass(String testName) {
        System.out.println("[Pass " + testName + "]");
    }

    private static void outputFail(String testName, String message) {
        System.out.println("[FAIL " + testName + "] " + message);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t = 0; t < testcases; ++t) {
            int n = sc.nextInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i) list.add(sc.nextInt());
            System.out.println(new Closest().computeClosest(list));
        }
    }
}