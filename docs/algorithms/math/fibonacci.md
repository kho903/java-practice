# 피보나치 수열과 동적 계획법(DP)

## 1. 피보나치 수열(Fibonacci)

앞의 두 항을 더하여 다음 항을 만드는 수열

- F(0) = 0, F(1) = 1
- F(N) = F(N-1) + F(N-2)
- 예: 0, 1, 1, 2, 3, 5, 8, 13

## 2. 단순 재귀(Recursion)

함수 내부에서 자기 자신을 호출하여 문제를 해결하는 방식

```java
static long fibonacci(int n) {
	if (n <= 1)
		return n;

	return fibonacci(n - 1) + fibonacci(n - 2);
}
```

- 종료 조건(Base Case) 필수
- 동일한 피보나치 수를 중복 계산
- 시간 복잡도: O(2^N)
- 공간 복잡도: O(N), 재귀 호출 스택

## 3. 메모이제이션(Memoization, Top-Down)

한 번 계산한 결과를 저장하고 재사용하는 방식

```java
static long fibonacci(int n, long[] memo) {
	if (n <= 1)
		return n;

	if (memo[n] != 0)
		return memo[n];

	memo[n] = fibonacci(n - 1, memo)
		+ fibonacci(n - 2, memo);

	return memo[n];
}
```

- 재귀 호출과 배열을 함께 사용
- 이미 계산한 값을 재사용하여 중복 계산 방지
- `memo[n] == 0`을 미계산 상태로 활용
- 시간 복잡도: O(N)
- 공간 복잡도: O(N)

## 4. 반복문 DP(Tabulation, Bottom-Up)

작은 문제부터 차례대로 계산하여 큰 문제를 해결하는 방식

```java
static long fibonacci(int n) {
	if (n <= 1)
		return n;

	long[] dp = new long[n + 1];
	dp[0] = 0;
	dp[1] = 1;

	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}

	return dp[n];
}
```

- 반복문을 이용하여 작은 값부터 순서대로 계산
- 재귀 호출 스택을 사용하지 않음
- 시간 복잡도: O(N)
- 공간 복잡도: O(N)
- 직전 두 값만 저장하면 공간 복잡도 O(1)로 최적화 가능

## 5. 구현 방식 비교

| 구분     | 단순 재귀  | Top-Down | Bottom-Up |
|--------|--------|----------|-----------|
| 방식     | 재귀     | 재귀 + 캐싱  | 반복문       |
| 중복 계산  | 발생     | 없음       | 없음        |
| 시간 복잡도 | O(2^N) | O(N)     | O(N)      |
| 공간 복잡도 | O(N)   | O(N)     | O(N)      |

## 6. 핵심 요약

- 재귀는 종료 조건과 재귀 호출로 구성
- 메모이제이션은 계산한 결과를 저장하여 중복 계산 방지
- Top-Down은 큰 문제에서 작은 문제로 내려가는 방식
- Bottom-Up은 작은 문제부터 차례대로 계산하는 방식
- DP는 중복되는 부분 문제의 결과를 재사용하여 효율성을 높이는 기법
- 피보나치 F(90)까지는 Java long으로 표현 가능
