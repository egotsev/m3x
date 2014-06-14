package net.yaht.rmi.m3x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	static Double[][] A, B, C;

	public static void main(String[] args) throws Exception {
		CLOptions options = new CLOptions(args);
		if (!options.isQuiet()) {
			System.out.println("Generating matrices...");
		}
		generateMatrices(options.getM(), options.getN(), options.getK());
		if (!options.isQuiet()) {
			System.out.println("Matrices generated. Initializing threads...");
		}
		
		ExecutorService threadPool = Executors.newFixedThreadPool(options.getThreadNumber());
		List<Callable<Double>> callables = new ArrayList<>(A.length * B[0].length);
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				callables.add(new MatrixMultiplicationStep(i, j, A, B, C));
			}
		}
		if (!options.isQuiet()) {
			System.out.println("Begining multiplication.");
		}
		long begin = new Date().getTime();
		threadPool.invokeAll(callables);
		threadPool.shutdown();
		threadPool.awaitTermination(30, TimeUnit.SECONDS);
		long end = new Date().getTime();
		System.out.println(options.getThreadNumber() + " thread(s) with result time " + (end - begin));
	}

	private static void generateMatrices(int m, int n, int k) {
		A = new Double[m][n];
		B = new Double[n][k];
		C = new Double[m][k];
		fillMatrix(A, m, n);
		fillMatrix(B, n, k);
	}

	private static void fillMatrix(Double[][] matrix, int n, int m) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = Math.random();
			}
		}
	}
}
