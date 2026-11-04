# Java String 정리

## 1. String이란?

`String`은 Java에서 문자열을 표현하는 참조형 클래스

```java
String str = "Java";
String str2 = new String("Java");
```

- `String`은 기본형(primitive type)이 아닌 참조형(reference type)
- `String` 객체는 불변(Immutable) 객체
- 문자열 리터럴은 String Pool을 통해 관리됨
- 문자열 내용 비교에는 일반적으로 `equals()`를 사용함

## 2. `==`와 `equals()`의 차이

### `==` 연산자

`==`는 기본형에서는 값을 비교하고, 참조형에서는 두 변수가 같은 객체를 참조하는지 비교함

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b); // false
```

`a`와 `b`는 서로 다른 객체를 참조하므로 `false`가 출력됨

### `equals()` 메서드

`equals()`는 객체의 동등성을 비교하는 메서드

`String` 클래스는 `equals()`를 재정의하여 문자열의 내용을 비교함

```java
System.out.println(a.equals(b)); // true
```

- 두 문자열의 내용이 `"Java"`로 같기 때문에 `true`가 출력됨
- 핵심 : `==`는 참조 동일성, String의 `equals()`는 문자열 내용의 동등성을 비교
- 단, 모든 클래스의 `equals()`가 내용을 비교하는 것은 아님
- 재정의하지 않은 클래스는 기본적으로 `Object.equals()`의 참조 비교 동작을 따름

## 3. String Pool

String Pool은 동일한 문자열 리터럴을 공유할 수 있도록 관리하는 공간

```java
String a = "Java";
String b = "Java";
String c = new String("Java");

System.out.println(a == b); // true
System.out.println(a == c); // false
```

- `a`와 `b`는 String Pool의 동일한 문자열 객체를 참조함
- `c`는 `new`를 사용하여 별도의 String 객체를 생성함
- 따라서 `a == b`는 `true`, `a == c`는 `false`

### `intern()` 메서드

`intern()`은 해당 문자열과 내용이 같은 String Pool의 정규화된 참조를 반환함

```java
String a = "Java";
String b = new String("Java");

String c = b.intern();

System.out.println(a == c); // true
```

- `c`는 String Pool에 존재하는 `"Java"` 객체를 참조하므로 `a == c`는 `true`

## 4. String의 불변성 (Immutable)

`String` 객체는 생성된 이후 내부 문자열 내용을 변경할 수 없음

```java
String str = "Java";
str.concat("Script");
System.out.println(str); // Java
```

- `concat()`은 기존 객체를 수정하지 않고 새로운 문자열을 반환함
- 반환값을 저장하지 않았기 때문에 `str`은 그대로 `"Java"`를 참조함

```java
str = str.concat("Script");
System.out.println(str); // JavaScript
```
- 위 코드는 기존 String 객체를 수정한 것이 아니라 `str`이 새로운 String 객체를 참조하도록 재대입한 것

### String이 불변성의 장점
- 여러 곳에서 문자열 객체를 안전하게 공유할 수 있음
- String Pool을 통한 객체 재사용이 가능함
- 문자열 내용이 바뀌지 않으므로 해시값을 안정적으로 사용할 수 있음

## 5. String과 StringBuilder의 차이
`StringBuilder`는 문자열 내용을 변경할 수 있는 가변(Mutable) 클래스

```java
StringBuilder a = new StringBuilder("Java");
StringBuilder b = a;

b.append(" Practice");

System.out.println(a);      // Java Practice
System.out.println(a == b); // true
```

- `a`와 `b`는 동일한 StringBuilder 객체를 참조함
- 따라서 `b.append()`로 객체를 변경하면 `a`에서도 변경된 내용을 확인할 수 있음

### StringBuilder의 `equals()`

```java
StringBuilder a = new StringBuilder("Java");
StringBuilder b = new StringBuilder("Java");

System.out.println(a == b);      // false
System.out.println(a.equals(b)); // false
```

- `StringBuilder`는 `String`과 달리 `equals()`를 문자열 내용 비교용으로 재정의하지 않음
- 따라서 별개의 StringBuilder 객체를 비교하면 `equals()`도 `false`를 반환함
- 내용을 비교하려면 문자열로 변환하여 비교할 수 있음
- `StringBuilder`는 `equals()`를 재정의하지 않아 참조를 비교하므로, 내용 비교 시 `toString().equals()` 등을 사용해야 함

```java
System.out.println(
    a.toString().equals(b.toString())
); // true
```

| 구분         | String      | StringBuilder |
|------------|-------------|---------------|
| 변경 가능 여부   | 불변          | 가변            |
| 문자열 추가     | 새 문자열 반환    | 기존 객체 수정      |
| `equals()` | 문자열 내용 비교   | 참조 비교         |
| 주요 용도      | 일반적인 문자열 처리 | 반복적인 문자열 수정   |

### 성능 차이

- `String`은 불변 객체이므로 문자열 연결 시 새로운 객체가 생성될 수 있음
- `StringBuilder`는 내부 버퍼를 수정하여 반복적인 문자열 연결에 유리함
- 단순한 문자열 처리에는 `String`, 반복적인 문자열 수정에는 `StringBuilder`가 적합함

## 6. null과 NullPointerException

`null`은 참조 변수가 객체를 참조하지 않는 상태를 나타내는 값

```java
String a = null;
System.out.println(a == null); // true
```

### NullPointerException

`null`인 참조 변수를 통해 인스턴스 메서드를 호출하면 `NullPointerException`이 발생함

```java
String a = null;

System.out.println(a.equals("Java"));
// NullPointerException
```

- `a`가 객체를 참조하지 않으므로 `equals()`를 호출할 수 없음

### 안전한 문자열 비교

```java
String a = null;
System.out.println("Java".equals(a)); // false
```
- 문자열 리터럴처럼 null이 아닌 객체에서 `equals()`를 호출하면, 인자가 null이어도 정상적으로 비교할 수 있음

## 7. Objects.equals()

`Objects.equals()`는 null을 안전하게 처리하면서 두 객체의 동등성을 비교하는 메서드

```java
import java.util.Objects;

String a = null;
String b = "Java";

System.out.println(Objects.equals(a, b));       // false
System.out.println(Objects.equals(null, null)); // true
```

동작 방식은 다음과 같음

- 두 참조가 동일하면 `true`를 반환함
  - 둘 다 null인 경우도 포함됨
- 한쪽만 null이면 `false`를 반환함
- 둘 다 null이 아니면 첫 번째 객체의 `equals()`를 사용하여 비교함

`Objects.equals()`를 사용하면 null 여부를 직접 검사하지 않고도 안전하게 동등성을 비교할 수 있음

## 8. 핵심 요약

| 개념                     | 핵심 내용                             |
|------------------------|-----------------------------------|
| `==`                   | 기본형의 값 또는 참조형의 참조 동일성 비교          |
| `String.equals()`      | 문자열 내용 비교                         |
| String Pool            | 문자열 리터럴 및 intern된 문자열 객체의 참조를 재사용 |
| `intern()`             | String Pool의 정규화된 참조 반환           |
| Immutable              | 객체 생성 후 내부 상태를 변경할 수 없음           |
| StringBuilder          | 기존 객체의 문자열 내용을 수정할 수 있음           |
| `null`                 | 객체를 참조하지 않는 상태                    |
| `NullPointerException` | null 참조를 잘못 사용할 때 발생              |
| `Objects.equals()`     | null을 안전하게 처리하는 동등성 비교            |
