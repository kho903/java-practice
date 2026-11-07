
package algorithms.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrefixSumPractice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in)
		);

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + 1];
		long[] prefix = new long[n + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + arr[i];
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < q; i++) {

			st = new StringTokenizer(br.readLine());

			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			sb.append(prefix[r] - prefix[l - 1]).append("\n");
		}

		System.out.print(sb);
	}
}
