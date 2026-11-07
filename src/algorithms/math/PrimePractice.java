package algorithms.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimePractice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		boolean isPrime = true;

		// TODO 1: 1은 소수가 아님
		// TODO 2: 2부터 √N까지 나누어지는지 검사
		// TODO 3: 나누어떨어지면 isPrime = false
		if (n == 1) {
			isPrime = false;
		} else {
			int root = (int) Math.sqrt(n);
			int i = 2;
			while (i <= root) {
				if (n % i == 0) {
					isPrime = false;
					break;
				}
				i++;
			}
		}

		System.out.println(isPrime ? "Prime" : "Not Prime");
	}
}
