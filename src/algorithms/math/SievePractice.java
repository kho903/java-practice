
package algorithms.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SievePractice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in)
		);

		int n = Integer.parseInt(br.readLine());

		boolean[] isComposite = new boolean[n + 1];

		// TODO 1: 2부터 √N까지 반복
		// TODO 2: 이미 합성수인 숫자는 건너뛰기
		// TODO 3: i * i부터 i의 배수들을 true로 표시

		for (int i = 2; (long) i * i <= n; i++) {
			if (isComposite[i]) continue;
			for (int j  = i * i; j <= n; j += i) {
				isComposite[j] = true;
			}
		}

		// 소수 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 2; i <= n; i++) {
			if (!isComposite[i]) {
				if (!sb.isEmpty()) {
					sb.append(" ");
				}
				sb.append(i);
			}
		}

		System.out.println(sb);
	}
}
