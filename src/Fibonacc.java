class Fibonacci {
    public static void printFibonacci(int n) {
        if (n <= 0) return;
        int a = 0, b = 1;
        System.out.print(a);
        for (int i = 1; i < n; i++) {
            System.out.print(" " + b);
            int temp = a + b;
            a = b;
            b = temp;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Fibonacci series up to " + n + " terms:");
        printFibonacci(n);
    }
}
