package net.yaht.rmi.m3x;

public class Matrix<T> {

	private T[][] matrix;
	
	public Matrix(int rows, int columns) {
		// TODO Auto-generated constructor stub
	}
	
	public Matrix(T[][] matrix) {
		this.matrix = matrix;
	}
	
	public T get(int i, int j) {
		return matrix[i][j];
	}
	
	public void set(int i, int j, T d) {
		matrix[i][j] = d;
	}
}
