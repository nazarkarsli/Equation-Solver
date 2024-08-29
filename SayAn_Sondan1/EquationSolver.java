package SayAn_Sondan1;

import java.util.Scanner;

public class EquationSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Denklem sayısını girin (n): ");
        int n = scanner.nextInt();
        double[][] A = new double[n][n];
        double[] B = new double[n];

        // Katsayıları ve sonuçları kullanıcıdan al
        System.out.println("Denklem katsayılarını girin:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("A[" + (i+1) + "][" + (j+1) + "]: ");
                A[i][j] = scanner.nextDouble();
            }
            System.out.print("B[" + (i+1) + "]: ");
            B[i] = scanner.nextDouble();
        }
        double[] D = forwardSubstitution(A, B);


        boolean isUpperTriangular = isUpperTriangular(A);

        if (isUpperTriangular) {
            System.out.println("Üst Üçgen (U):");
        } else {
            System.out.println("Alt Üçgen (L):");
        }
        printMatrix(A);
        double[] X = backwardSubstitution(A, D);

        // Sonuçları yazdır
        System.out.println("Bilinmeyenlerin değerleri (x):");
        for (int i = 0; i < n; i++) {
            System.out.println("x[" + (i+1) + "]: " + X[i]);
        }
    }
    public static double[] forwardSubstitution(double[][] A, double[] B) {
        int n = A.length;
        double[] D = new double[n];

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += A[i][j] * D[j];
            }
            D[i] = B[i] - sum;
        }
        return D;
    }
    public static double[] backwardSubstitution(double[][] A, double[] D) {
        int n = A.length;
        double[] X = new double[n];

        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * X[j];
            }
            X[i] = (D[i] - sum) / A[i][i];
        }
        return X;
    }
    public static boolean isUpperTriangular(double[][] A) {
        int n = A.length;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void printMatrix(double[][] mat) {
        for (double[] row : mat) {
            for (double val : row) {
                System.out.print(String.format("%.6f", val) + " ");
            }
            System.out.println();
        }
    }
}
