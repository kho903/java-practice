# Java 생성자(Constructor) 정리

## 1. 생성자(Constructor)란?

생성자는 객체 생성 시 호출되어 객체의 초기화를 담당하는 특수한 구성 요소

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }
}

Person p = new Person("Kim");
```

- 생성자의 이름은 클래스 이름과 동일함
- 반환 타입을 선언하지 않음
- `new`를 통해 객체를 생성할 때 생성자가 호출됨
- 생성자 오버로딩을 통해 여러 초기화 방식 제공 가능
- 생성자는 상속되지 않으며 오버라이딩할 수 없음

### 기본 생성자(Default Constructor)

클래스에 생성자를 하나도 선언하지 않으면 컴파일러가 기본 생성자를 자동으로 제공함

```java
class Person {
    String name;
}

// 컴파일러가 암묵적으로 기본 생성자를 제공
Person p = new Person();
```

- 기본 생성자는 매개변수가 없는 생성자
- 생성자를 하나라도 직접 선언하면 기본 생성자가 자동 생성되지 않음
- 직접 선언한 매개변수 없는 생성자와 컴파일러가 제공하는 기본 생성자는 구분됨

## 2. 부모 생성자와 자식 생성자

자식 객체 생성 시 부모 생성자가 먼저 실행됨

```java
class Parent {
	Parent() {
		System.out.print("P");
	}
}

class Child extends Parent {
	Child() {
		System.out.print("C");
	}
}
```

```java
new Child(); // PC
```

### 실행 순서

1. `new Child()` 호출
2. 부모 생성자 `Parent()` 실행 -> P 출력
3. 자식 생성자 `Child()` 실행 -> C 출력
4. 최종 결과 -> PC

- 자식 생성자는 부모 생성자를 호출함
- 부모 생성자는 자식 생성자 본문보다 먼저 실행됨
- 부모 생성자는 상속되는 것이 아니라 호출되는 것
- 명시적인 생성자 호출이 없으면 일반적으로 `super()`가 암묵적으로 추가됨

## 3. this()와 super()

### this()

`this()`는 같은 클래스의 다른 생성자를 호출하는 구문

### super()

`super()`는 부모 클래스의 생성자를 호출하는 구문

```java
class Parent {
	Parent() {
		System.out.print("A");
	}
}

class Child extends Parent {
	Child() {
		this(10);
		System.out.print("B");
	}

	Child(int n) {
		super();
		System.out.print(n);
	}
}
```

```java
new Child(); // A10B
```

### 실행 순서

1. `Child()` 생성자 호출
2. `this(10)`으로 `Child(int n)` 생성자 호출
3. `super()`로 부모 생성자 호출 -> A 출력
4. `Child(int n)`의 나머지 부분 실행 -> 10 출력
5. `Child()`로 돌아와 나머지 부분 실행 -> B 출력
6. 최종 결과 -> A10B

### 주의사항
- Java 21에서는 명시적인 `this()` 또는 `super()` 호출이 생성자의 첫 번째 문장에 위치해야 함
- 하나의 생성자에서 `this()`와 `super()`를 동시에 직접 호출할 수 없음
- `this()`로 다른 생성자를 호출하면 해당 생성자 호출 경로를 통해 부모 생성자가 실행됨
- `super()`는 부모 생성자를, `super.메서드명()`은 부모 클래스의 메서드를 호출함

## 4. 부모 생성자의 암묵적 호출

자식 생성자에서 `super()`를 명시하지 않아도 적절한 경우 컴파일러가 자동으로 추가함

```java
class Parent {
    Parent() {
        System.out.print("P");
    }
}

class Child extends Parent {
    Child() {
        System.out.print("C");
    }
}
```

위 `Child()` 생성자는 사실상 다음 코드와 동일한 동작

```java
Child() {
    super();
    System.out.print("C");
}
```

- 명시적 `super()`와 암묵적 `super()`는 동일한 부모 생성자를 호출할 수 있음
- 부모 클래스에 접근 가능한 매개변수 없는 생성자가 없으면 컴파일 오류가 발생할 수 있음

### 부모 생성자에 매개변수가 있는 경우

```java
class Parent {
    Parent(int n) {
        System.out.println(n);
    }
}

class Child extends Parent {
    Child() {
        super(10);
    }
}
```
- 부모 클래스에 `Parent(int n)`만 존재하면 기본 생성자가 자동 생성되지 않음
- 자식 생성자에서 `super(10)`처럼 적절한 부모 생성자를 명시적으로 호출해야 함
- `super()`를 암묵적으로 호출하려 하면 컴파일 오류 발생

## 5. 생성자와 오버라이딩

부모 생성자에서 오버라이딩 가능한 인스턴스 메서드를 호출하면 실제 객체의 메서드가 실행됨

```java
class Parent {
    Parent() {
        show();
    }

    void show() {
        System.out.print("Parent");
    }
}

class Child extends Parent {
    int value = 7;

    @Override
    void show() {
        System.out.print(value);
    }
}
```

```java
new Child(); // 0
```

### 실행 순서

1. `Child` 객체 생성 시작
2. 인스턴스 필드에 기본값 할당 -> `value = 0`
3. 부모 생성자 `Parent()` 실행
4. 부모 생성자 내부에서 `show()` 호출
5. 실제 객체 타입인 `Child`의 오버라이딩된 `show()` 실행
6. 아직 `value = 7` 초기화 전이므로 0 출력
7. 부모 생성자 종료 후 자식 필드 `value = 7` 초기화
8. 자식 생성 완료

### 핵심 개념

- 오버라이딩된 인스턴스 메서드는 실제 객체 타입을 기준으로 실행됨
- 자식의 인스턴스 필드 초기화는 부모 생성자 실행 이후에 진행됨
- 부모 생성자 실행 중에는 자식 필드가 아직 기본값을 가지고 있을 수 있음
- 생성자에서 오버라이딩 가능한 메서드를 호출하는 패턴은 초기화되지 않은 상태에 접근할 위험이 있어 지양하는 것이 좋음

## 6. static 필드와 인스턴스 필드 초기화

`static` 필드와 인스턴스 필드는 초기화 시점이 다름

```java
class Parent {
    Parent() {
        show();
    }

    void show() {
        System.out.print("Parent");
    }
}

class Child extends Parent {
    static int staticValue = 7;
    int instanceValue = 10;

    @Override
    void show() {
        System.out.print(
            staticValue + " " + instanceValue
        );
    }
}
```

```java
new Child(); // 7 0
```

### 실행 순서

1. `Child` 클래스 초기화 시 static 필드 초기화
    - `staticValue = 7`
2. 객체 생성 시 인스턴스 필드에 기본값 할당
    - `instanceValue = 0`
3. 부모 생성자 실행
4. 부모 생성자에서 `show()` 호출
5. 자식의 오버라이딩된 메서드 실행
    - `staticValue = 7`
    - `instanceValue = 0`
6. 최종 출력 → 7 0
7. 부모 생성자 종료 후 `instanceValue = 10` 초기화

### static과 인스턴스 필드 비교
| 구분      | static 필드   | 인스턴스 필드                    |
|---------|-------------|----------------------------|
| 소속      | 클래스         | 객체                         |
| 초기화 시점  | 클래스 초기화 시점  | 객체 생성 과정                   |
| 공유 여부   | 모든 인스턴스가 공유 | 객체마다 별도 보유                 |
| 기본값     | 타입에 따른 기본값  | 타입에 따른 기본값                 |
| 명시적 초기화 | 클래스 초기화 과정  | 부모 생성자 실행 후 해당 클래스의 초기화 과정 |

- static 필드는 클래스 단위로 관리됨
- static 필드의 명시적 초기화는 클래스 초기화 과정에서 실행됨
- 인스턴스 필드는 객체마다 생성됨
- 인스턴스 필드의 명시적 초기화는 상위 클래스 생성자 완료 후, 해당 클래스 생성자 본문 실행 전에 수행됨
- 자식 클래스의 인스턴스 필드 초기화는 부모 생성자가 완료된 후 수행됨

## 7. Java 객체 초기화 순서

일반적인 상속 구조에서 자식 객체 생성 시 초기화 순서

1. 필요한 클래스 초기화 수행
2. 객체 메모리 할당 및 모든 인스턴스 필드에 기본값 설정
3. `Child()`에서 `Parent()` 생성자 호출
4. `Parent()`에서 `Object()` 생성자 호출 및 완료
5. `Parent`의 인스턴스 필드 초기화 및 초기화 블록 실행
6. `Parent` 생성자 본문 실행
7. `Child`의 인스턴스 필드 초기화 및 초기화 블록 실행
8. `Child` 생성자 본문 실행
9. 객체 생성 완료

- 클래스 초기화와 객체 초기화는 서로 다른 과정
- 인스턴스 필드에는 명시적 초기화 전에 타입별 기본값이 설정됨
- 부모 클래스의 초기화가 완료된 후 자식 클래스의 명시적 필드 초기화가 진행됨
- 위 순서는 일반적인 생성자 호출 흐름을 단순화한 예시

### 필드 기본값

| 자료형              | 기본값      |
|------------------|----------|
| int, short, byte | 0        |
| long             | 0L       |
| float            | 0.0f     |
| double           | 0.0d     |
| char             | '\u0000' |
| boolean          | false    |
| 참조형              | null     |

- 인스턴스 필드는 명시적으로 초기화하지 않아도 타입별 기본값이 설정됨
- 지역 변수는 자동으로 기본값이 할당되지 않으므로 사용 전에 초기화해야 함

## 8. 핵심 요약

| 개념                  | 핵심 내용                                                |
|---------------------|------------------------------------------------------|
| Constructor         | 객체 생성 시 초기화를 담당하는 특수 구성 요소                           |
| Default Constructor | 생성자를 선언하지 않았을 때 컴파일러가 제공하는 기본 생성자                    |
| `this()`            | 같은 클래스의 다른 생성자 호출                                    |
| `super()`           | 부모 클래스의 생성자 호출                                       |
| 암묵적 `super()`       | 명시적 생성자 호출이 없는 경우 자동 추가되며, 적절한 부모 생성자가 없으면 컴파일 오류 발생 || 부모·자식 생성자           | 부모 생성자 본문이 자식 생성자 본문보다 먼저 실행됨     |
| Overriding          | 실제 객체 타입을 기준으로 인스턴스 메서드 실행                           |
| static 필드           | 클래스 초기화 과정에서 명시적 초기화 수행                              |
| 인스턴스 필드             | 객체별로 생성되며 타입에 따른 기본값 설정                              |
| 생성자와 오버라이딩          | 자식 필드 초기화 전에 오버라이딩 메서드가 실행될 수 있음                     |
