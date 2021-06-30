package processor;

import java.util.Scanner;

class Matrix {
    private int rows;
    private int cols;
    private double[][] matrix;
    public Matrix(int rows, int cols) throws Exception {
        if (rows < 1 || cols < 1) {
            throw new Exception("Rows and columns must be positive");
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }

    public static Matrix transposeSideDiagonal(Matrix first) throws Exception {
        Matrix result = new Matrix(first.rows, first.cols);
        for (int i = 0; i < first.rows; i++) {
            for (int j = 0; j < first.cols; j++) {
                result.matrix[i][j] = first.matrix[first.rows - j - 1][first.cols - i - 1];
            }
        }
        return result;
    }

    public static Matrix transposeVertical(Matrix first) throws Exception {
        Matrix result = new Matrix(first.rows, first.cols);
        for (int i = 0; i < first.rows; i++) {
            for (int j = 0; j < first.cols; j++) {
                result.matrix[i][j] = first.matrix[i][first.cols - j - 1];
            }
        }
        return result;
    }

    public static Matrix transposeHorizontal(Matrix first) throws Exception {
        Matrix result = new Matrix(first.rows, first.cols);
        for (int i = 0; i < first.rows; i++) {
            for (int j = 0; j < first.cols; j++) {
                result.matrix[i][j] = first.matrix[first.rows - i - 1][j];
            }
        }
        return result;
    }

    public void setElement(int i, int j, double element) throws Exception {
        if (i < 0 || j < 0 || i >= rows || j >= cols) {
            throw new Exception("Index out of bounds!");
        }
        matrix[i][j] = element;
    }
    public double getElement(int i, int j) throws Exception {
        if (i < 0 || j < 0 || i >= rows || j >= cols) {
            throw new Exception("Index out of bounds!");
        }
        return matrix[i][j];
    }
    public void sum(Matrix second) throws Exception { // += operator
        if (this.rows != second.rows || this.cols != second.cols) {
            throw new Exception("ERROR");
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.setElement(i, j, this.getElement(i, j) + second.getElement(i, j));
            }
        }
    }
    public static Matrix sum(Matrix first, Matrix second) throws Exception { // + operator
        if (first.rows != second.rows || first.cols != second.cols) {
            throw new Exception("ERROR");
        }
        Matrix result = new Matrix(first.rows, first.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.setElement(i, j, first.getElement(i, j) + second.getElement(i, j));
            }
        }
        return result;
    }
    public void printMatrix() {
         for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static Matrix transposeMainDiagonal(Matrix first) throws Exception {
        Matrix result = new Matrix(first.cols, first.rows);
        for (int i = 0; i < first.rows; i++) {
            for (int j = 0; j < first.cols; j++) {
                result.matrix[j][i] = first.matrix[i][j];
            }
        }
        return result;
    }
    private static double multiply(double[] first, double[] second) {
        double res = 0;
        for (int i = 0; i < first.length; i++) {
            res += first[i] * second[i];
        }
        return res;
    }
    public static Matrix multiply(Matrix first, Matrix second) throws Exception {
        if (first.cols != second.rows) {
            throw new Exception("The operation cannot be performed.");
        }
        Matrix result = new Matrix(first.rows, second.cols);
        second = transposeMainDiagonal(second);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.matrix[i][j] = multiply(first.matrix[i], second.matrix[j]);
            }
        }
        return result;
    }
    public Matrix scalarMultiply(double factor) throws Exception {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.matrix[i][j] = factor * this.matrix[i][j];
            }
        }
        return result;
    }
}
public class Main {
    private final static Scanner sc = new Scanner(System.in);
    private static Matrix readMatrix(String name) throws Exception {
        System.out.printf("Enter size of %smatrix: ", name);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        System.out.printf("Enter %s matrix:%n", name);
        Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setElement(i, j, sc.nextDouble());
            }
        }
        return matrix;
    }
    private static Matrix twoMatrices(int option) throws Exception {
        Matrix first = readMatrix("first ");
        Matrix second = readMatrix("second ");
        switch (option) {
            case 1:
                return Matrix.sum(first, second);
            case 3:
                return Matrix.multiply(first, second);
            default:
                return null;
        }
    }
    private static Matrix singleMatrix(int option) throws Exception {
        Matrix first;
        switch (option) {
            case 2:
                System.out.println("Enter constant: ");
                first = readMatrix("");
                return first.scalarMultiply(sc.nextDouble());
            case 4:
                System.out.println("1. Main diagonal\n" +
                        "2. Side diagonal\n" +
                        "3. Vertical line\n" +
                        "4. Horizontal line");
                System.out.println("Your choice: ");
                int choice = sc.nextInt();
                first = readMatrix("");
                switch (choice) {
                    case 1:
                        return Matrix.transposeMainDiagonal(first);
                    case 2:
                        return Matrix.transposeSideDiagonal(first);
                    case 3:
                        return Matrix.transposeVertical(first);
                    case 4:
                        return Matrix.transposeHorizontal(first);
                    default:
                        return null;
                }
            default:
                return null;

        }
    }

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("1. Add matrices\n" +
                        "2. Multiply matrix by a constant\n" +
                        "3. Multiply matrices\n" +
                        "4. Transpose matrix\n" +
                        "0. Exit");
                System.out.println("Your choice: ");
                int choice = sc.nextInt();
                boolean end = false;
                Matrix result = null;
                switch (choice) {
                    case 1:
                    case 3:
                        result = twoMatrices(choice);
                        break;
                    case 2:
                    case 4:
                        result = singleMatrix(choice);
                        break;
                    case 0:
                        end = true;
                        break;
                    default:
                        break;
                }
                if (end) {
                    break;
                } else if (result != null) {
                    System.out.println("The result is:");
                    result.printMatrix();

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
