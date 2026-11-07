# 누적합(Prefix Sum)

## 1. 핵심 개념

배열의 처음부터 특정 위치까지의 합을 미리 계산하여 저장하는 알고리즘

- 구간 합을 여러 번 계산하는 문제에서 활용
- 누적합 배열을 한 번 생성하면 각 구간 합을 O(1)에 계산 가능
- 1-based 인덱싱을 사용하면 구간 합 계산이 편리함

## 2. 누적합 계산


```java
long[] prefix = new long[n + 1];

for (int i = 1; i <= n; i++) {
    prefix[i] = prefix[i - 1] + arr[i];
}
```

- `prefix[0] = 0`
- `prefix[i] = prefix[i - 1] + arr[i]`
- 누적합이 int 범위를 초과할 수 있으므로 long 사용

## 3. 구간 합 계산

L번째부터 R번째까지의 합을 계산하는 공식

```text
sum(L, R) = prefix[R] - prefix[L - 1]
```

```java
long sum = prefix[r] - prefix[l - 1];
```

- `prefix[R]`에서 L 이전까지의 합을 제거
- 구간 합 하나를 O(1)에 계산
- `L - 1` 인덱스 처리에 주의

## 4. 시간 및 공간 복잡도

| 구분         | 시간 복잡도   |
|------------|----------|
| 누적합 배열 생성  | O(N)     |
| 구간 합 한 번   | O(1)     |
| Q개 구간 합 계산 | O(N + Q) |
| 매번 직접 계산   | O(NQ)    |

- 누적합 배열의 공간 복잡도: O(N)
- 출력 결과를 StringBuilder에 모으면 출력 버퍼 공간도 필요

## 5. 핵심 요약

- 누적합은 반복적인 구간 합 계산을 최적화하는 기법
- prefix[i] = prefix[i - 1] + arr[i]
- sum(L, R) = prefix[R] - prefix[L - 1]
- 정수 합의 오버플로를 고려하여 long 사용
