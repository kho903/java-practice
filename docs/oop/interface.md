# Java 인터페이스와 추상 클래스 정리

## 1. 인터페이스(Interface)

클래스가 구현해야 할 기능의 규약을 정의하는 타입

```java
interface Animal {
    void sound();
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Dog");
    }
}
```

- `implements` 키워드로 인터페이스 구현
- 인터페이스의 추상 메서드는 암묵적으로 `public abstract`
- `default`, `static`, `private` 메서드는 구현을 가질 수 있음
- 구현 메서드의 접근 범위를 좁힐 수 없음
- 여러 인터페이스를 동시에 구현 가능
- 인터페이스 자체는 직접 인스턴스화할 수 없음

## 2. default 메서드

인터페이스에서 기본 구현을 제공하는 인스턴스 메서드

```java
interface Greeting {
    default String greet() {
        return "Hello";
    }
}

class User implements Greeting {
}

class Admin implements Greeting {
    @Override
    public String greet() {
        return "Admin";
    }
}
```

- 구현 클래스에서 오버라이딩하지 않아도 사용 가능
- 필요한 경우 오버라이딩하여 동작 변경 가능
- 일반적인 인스턴스 메서드처럼 동적 바인딩 적용

## 3. 다중 인터페이스의 default 메서드 충돌

서로 다른 인터페이스에 동일한 default 메서드가 존재하면 충돌 가능

```java
interface A {
    default String name() {
        return "A";
    }
}

interface B {
    default String name() {
        return "B";
    }
}

class C implements A, B {
    @Override
    public String name() {
        return A.super.name() + B.super.name();
    }
}
```

- 동일한 default 메서드가 충돌하면 구현 클래스에서 해결 필요
- `인터페이스명.super.method()`로 특정 인터페이스의 기본 구현 호출 가능
- 클래스에서 상속받은 구체적인 메서드가 인터페이스의 default 메서드보다 우선함

## 4. 추상 클래스 (Abstract Class)

공통 필드와 메서드를 정의하고 하위 클래스에 일부 구현을 위임할 수 있는 클래스

```java
abstract class Parent {
	Parent() {
		System.out.print("P");
	}

	abstract void run();
}

class Child extends Parent {
	Child() {
		System.out.print("C");
	}

	@Override
	void run() {
		System.out.print("Run");
	}
}
```

```java
Parent p = new Child(); // PC
p.run();                // Run
```

- `abstract` 키워드 사용
- 직접 인스턴스 생성 불가능
- 생성자, 필드, 일반 메서드 선언 가능
- 추상 메서드 없이도 추상 클래스 선언 가능
- 구체 클래스는 상속받은 추상 메서드를 구현해야 함

## 5. 인터페이스의 필드와 static 메서드

```java
interface Config {
    int VALUE = 10;

    static String label() {
        return "Interface";
    }
}

class Impl implements Config {
    int VALUE = 20;
}

System.out.println(Config.VALUE); // 10
System.out.println(new Impl().VALUE); // 20
System.out.println(Config.label()); // Interface
```

- 인터페이스 필드는 암묵적으로 `public static final`
- 인터페이스의 static 메서드는 구현 클래스에 상속되지 않음
- static 메서드는 인터페이스 이름으로 호출
- 구현 클래스에서 같은 이름의 필드를 선언하면 필드 숨김 발생

## 6. 인터페이스와 추상 클래스 비교

| 구분       | 인터페이스                          | 추상 클래스                    |
|----------|--------------------------------|---------------------------|
| 키워드      | `interface`                    | `abstract class`          |
| 사용       | `implements`                   | `extends`                 |
| 다중 구현/상속 | 여러 인터페이스 구현 가능                 | 클래스 다중 상속 불가능             |
| 생성자      | 선언 불가능                         | 선언 가능                     |
| 인스턴스 필드  | 선언 불가능                         | 선언 가능                     |
| 구현된 메서드  | default, static, private 메서드 등 | 일반 인스턴스 메서드, static 메서드 등 |
| 추상 메서드   | 선언 가능                          | 선언 가능                     |

## 7. 핵심 요약

| 개념                    | 핵심 내용                           |
|-----------------------|---------------------------------|
| Interface             | 구현해야 할 기능의 규약 정의                |
| `public abstract`     | 인터페이스 추상 메서드의 암묵적 제어자           |
| default               | 인터페이스에서 기본 메서드 구현 제공            |
| 다중 구현                 | default 메서드 충돌 시 명시적 해결 필요      |
| Abstract Class        | 공통 상태와 동작을 정의하는 추상 클래스          |
| `public static final` | 인터페이스 필드의 암묵적 제어자               |
| static 메서드            | 인터페이스 이름으로 호출하며 구현 클래스에 상속되지 않음 |

- 핵심: 인터페이스는 기능의 규약, 추상 클래스는 공통 상태와 구현을 공유하는 데 주로 사용
