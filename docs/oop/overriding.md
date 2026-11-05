# Java 오버로딩과 오버라이딩 정리

## 1. 오버로딩(Overloading)

같은 이름의 메서드를 매개변수의 개수, 타입, 순서를 다르게 하여 정의하는 것

```java
void print(int n) {
    System.out.println("int");
}

void print(String s) {
    System.out.println("String");
}
```

- 메서드 이름은 같고 매개변수 목록은 달라야 함
- 반환 타입만 다르게 하는 것은 오버로딩 불가능
- 컴파일 시점에 메서드 선택

## 2. 오버라이딩(Overriding)

자식 클래스가 부모 클래스의 인스턴스 메서드를 재정의하는 것

```java
class Parent {
	void hello() {
		System.out.println("Parent");
	}
}

class Child extends Parent {
	@Override
	void hello() {
		System.out.println("Child");
	}
}

Parent p = new Child();
p.hello(); // Child
```

- 상속 관계에서 적용됨
- 메서드 이름과 매개변수 목록이 동일해야 함
- 접근 제어자의 범위를 부모 메서드보다 좁힐 수 없음
- `private`, `final`, `static` 메서드는 오버라이딩 불가능
- 실제 객체 타입에 따라 실행할 메서드가 결정됨
- `@Override`를 통해 오버라이딩 여부를 컴파일 시점에 검증 가능

## 3. 정적 바인딩과 동적 바인딩

### 정적 바인딩(Static Binding)

컴파일 시점에 호출할 메서드가 결정되는 방식

- 메서드 오버로딩
- static 메서드 숨김

### 동적 바인딩(Dynamic Binding)

실행 시점에 실제 객체 타입을 기준으로 호출할 메서드 구현이 결정되는 방식

- 오버라이딩 가능한 인스턴스 메서드 호출에 적용
- Java 다형성의 핵심 원리

## 4. 오버로딩과 오버라이딩의 동시 적용

```java
class Parent {
    void print(Object o) {
        System.out.println("P-Object");
    }

    void print(String s) {
        System.out.println("P-String");
    }
}

class Child extends Parent {
    @Override
    void print(Object o) {
        System.out.println("C-Object");
    }
}

Parent p = new Child();

p.print("Java"); // P-String

Object obj = "Java";
p.print(obj);    // C-Object
```

- 오버로딩은 참조 변수와 인자의 컴파일 시점 타입을 기준으로 결정됨
- 오버라이딩은 실제 객체 타입을 기준으로 실행할 구현이 결정됨
- 따라서 인자의 선언 타입에 따라 결과가 달라질 수 있음

## 5. super 키워드

`super`는 자식 클래스에서 부모 클래스의 멤버에 접근할 때 사용하는 키워드

```java
@Override
String getName() {
    return super.getName() + "Child";
}
```

- `super.method()`로 부모 클래스의 메서드 구현 호출 가능
- `super.field`로 부모 클래스의 필드 접근 가능
- `super()`는 부모 생성자 호출에 사용

## 6. static 메서드 숨김 (Method Hiding)

static 메서드는 오버라이딩되지 않고 메서드 숨김이 적용됨

```java
class Parent {
    static void hello() {
        System.out.println("P");
    }
}

class Child extends Parent {
    static void hello() {
        System.out.println("C");
    }
}

Parent p = new Child();

p.hello();            // P
Parent.hello();       // P
Child.hello();        // C
```

- static 메서드는 참조 변수의 선언 타입을 기준으로 선택됨
- 인스턴스 메서드는 오버라이딩 시 실제 객체 타입을 기준으로 실행됨
- static 메서드는 클래스 이름을 통해 호출하는 것이 권장됨

## 7. 핵심 요약

| 구분         | 오버로딩                  | 오버라이딩           |
|------------|-----------------------|-----------------|
| 목적         | 같은 이름의 메서드를 여러 형태로 정의 | 부모 메서드의 구현 재정의  |
| 매개변수       | 목록이 달라야 함             | 동일해야 함          |
| 상속 관계      | 불필요                   | 필요              |
| 메서드 선택     | 컴파일 시점에 시그니처 결정       | 실행 시점에 실제 구현 결정 |
| static 메서드 | 오버로딩 가능               | 오버라이딩 불가능       |

- 핵심 : 오버로딩은 컴파일 시점의 타입, 오버라이딩은 실제 객체 타입을 기준으로 이해
