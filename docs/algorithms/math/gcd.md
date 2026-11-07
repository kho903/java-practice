# 최대공약수(GCD)와 유클리드 호제법

## 1. 최대공약수(GCD)

두 정수를 모두 나누어떨어지게 하는 가장 큰 양의 정수

- GCD(Greatest Common Divisor)
- 예: GCD(12, 18) = 6

## 2. 유클리드 호제법(Euclidean Algorithm)

두 정수의 나머지를 반복적으로 계산하여 최대공약수를 구하는 알고리즘

- GCD(a, b) = GCD(b, a % b)
- 나머지가 0이 될 때까지 반복
- 반복 종료 후 마지막으로 0이 아닌 값이 최대공약수

예: GCD(48, 18)

```text
48 % 18 = 12
18 % 12 = 6
12 % 6 = 0

GCD = 6
```

## 3. Java 구현

```java
package algorithms.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GcdPractice {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        System.out.println(a);
    }
}
```

- `BufferedReader`로 한 줄 단위 입력 처리
- `StringTokenizer`로 공백 기준 문자열 분리
- `Integer.parseInt()`로 문자열을 정수로 변환
- `b`가 0이 될 때까지 나머지 연산 반복
- 반복 종료 후 `a`가 최대공약수

## 4. 시간 및 공간 복잡도

- 시간 복잡도: O(log min(A, B))
- 공간 복잡도: O(1)
- 모든 약수를 직접 확인하는 방식보다 효율적
- A, B가 양의 정수인 경우 기준

## 5. 핵심 요약

- 유클리드 호제법은 `%` 연산을 활용
- GCD(a, b) = GCD(b, a % b)
- 최대공약수는 최소공배수(LCM) 계산에도 활용 가능
- 알고리즘 문제에서는 `BufferedReader`와 `StringTokenizer`를 활용해 빠르게 입력 처리 가능
