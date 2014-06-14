package net.yaht.rmi.m3x;

import java.util.concurrent.Callable;

public class MatrixMultiplicationStep implements Callable<Double> {

	int row, column, length;
	Double[][] matrixA, matrixB, matrixC;

	public MatrixMultiplicationStep(int row, int column, Double[][] matrixA,
			Double[][] matrixB, Double[][] matrixC) {
		this.row = row;
		this.column = column;
		this.length = matrixB.length;
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
	}

	@Override
	public Double call() throws Exception {
		matrixC[row][column] = 0d;
		for (int i = 0; i < length; i++) {
			matrixC[row][column] += (matrixA[row][i] * matrixB[i][column]);
		}
		return matrixC[row][column];
	}

}
