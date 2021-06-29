package processor;

import java.util.Scanner;

class Matrix {
    private int rows;
    private int cols;
    private int[][] matrix;
    public Matrix(int rows, int cols) throws Exception {
        if (rows < 1 || cols < 1) {
            throw new Exception("Rows and columns must be positive");
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }
    public void setElement(int i, int j, int element) throws Exception {
        if (i < 0 || j < 0 || i >= rows || j >= cols) {
            throw new Exception("Index out of bounds!");
        }
        matrix[i][j] = element;
    }
    public int getElement(int i, int j) throws Exception {
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
    public Matrix scalarMultiply(int factor) throws Exception {
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
    private static Matrix readMatrix() throws Exception {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setElement(i, j, sc.nextInt());
            }
        }
        return matrix;
    }
    public static void main(String[] args) {
        try {
            Matrix first = readMatrix();
//            Matrix second = readMatrix();
//            Matrix sum = Matrix.sum(first, second);
//            sum.printMatrix();
            Matrix scalarResult = first.scalarMultiply(sc.nextInt());
            scalarResult.printMatrix();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
