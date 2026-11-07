package algorithms.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciMemoPractice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		long[] memo = new long[n + 1];

		System.out.println(fibonacci(n, memo));
	}

	static long fibonacci(int n, long[] memo) {
		if (n <= 1)
			return n;
		if (memo[n] != 0)
			return memo[n];
		memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
		return memo[n];
	}
}
