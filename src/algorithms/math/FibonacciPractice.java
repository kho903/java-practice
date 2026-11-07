package algorithms.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciPractice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		System.out.println(fibonacci(n));
	}

	static long fibonacci(int n) {

		// TODO 1: 종료 조건 구현
		// F(0) = 0
		// F(1) = 1
		if (n == 0) return 0;
		if (n == 1) return 1;

		return fibonacci(n - 1) + fibonacci(n - 2);
	}
}
