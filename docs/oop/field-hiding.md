# Java 필드 숨김과 타입 캐스팅 정리

## 1. 필드 숨김(Field Hiding)

자식 클래스가 부모 클래스와 동일한 이름의 필드를 선언하여 부모 필드를 숨기는 것

```java
class Parent {
	int value = 10;
}

class Child extends Parent {
	int value = 20;
}

Parent p = new Child();

System.out.println(p.value);           // 10
System.out.println(((Child) p).value); // 20
```

- 필드는 참조 변수의 선언 타입을 기준으로 접근함
- 부모 필드와 자식 필드는 각각 독립적으로 존재함
- 메서드 오버라이딩과 달리 동적 바인딩이 적용되지 않음

## 2. 필드 숨김과 메서드 오버라이딩

```java
class Parent {
    int value = 10;

    int getValue() {
        return value;
    }
}

class Child extends Parent {
    int value = 20;

    @Override
    int getValue() {
        return value;
    }
}

Parent p = new Child();

System.out.println(p.value);      // 10
System.out.println(p.getValue()); // 20
```

| 구분    | 필드           | 오버라이딩된 인스턴스 메서드 |
|-------|--------------|-----------------|
| 접근 기준 | 참조 변수의 선언 타입 | 실제 객체 타입        |
| 바인딩   | 정적 바인딩       | 동적 바인딩          |

## 3. 업캐스팅(Upcasting)

자식 타입의 객체를 부모 타입의 참조 변수로 다루는 것

```java
Parent p = new Child();
```
- 상속 관계에서 자동 형변환 가능
- 부모 타입에 정의된 멤버를 기준으로 접근 가능 여부 결정
- 실제 객체 타입은 변경되지 않음
- 오버라이딩된 인스턴스 메서드는 자식 구현이 실행될 수 있음

## 4. 다운캐스팅(Downcasting)

부모 타입의 참조를 자식 타입으로 변환하는 것

```java
Parent p = new Child();
Child c = (Child) p;
```

- 명시적 형변환 필요
- 실제 객체가 대상 자식 타입으로 변환 가능한 경우에만 정상 실행
- 잘못된 다운캐스팅 시 `ClassCastException` 발생
- 형변환해도 새로운 객체가 생성되지는 않음
- 다운캐스팅은 객체 자체를 변경하지 않고 참조의 타입만 변환함

## 5. instanceof


참조 대상 객체가 특정 타입과 호환되는지 확인하는 연산자

```java
Parent p = new Child();

if (p instanceof Child c) {
	System.out.println(c.value);
}
```

- 다운캐스팅 전 타입 확인에 활용 가능
- `null instanceof 타입`은 항상 `false`
- Java 16부터 패턴 매칭을 정식 지원
- 조건이 true인 경우 패턴 변수 사용 가능

## 6. ClassCastException

실제 객체를 호환되지 않는 타입으로 형변환할 때 발생하는 런타임 예외

```java
Parent p = new Parent();
Child c = (Child) p; // ClassCastException
```

- 컴파일 가능한 참조형 형변환이라도 실제 객체 타입이 호환되지 않으면 실행 시 예외 발생
- 실제 객체 타입을 기준으로 형변환 가능 여부 판단
- `instanceof`를 활용하여 잘못된 형변환 방지 가능

## 7. 핵심 요약

| 개념                 | 핵심 내용                    |
|--------------------|--------------------------|
| Field Hiding       | 참조 변수의 선언 타입을 기준으로 필드 접근 |
| Method Overriding  | 실제 객체 타입을 기준으로 메서드 구현 선택 |
| Upcasting          | 자식 타입에서 부모 타입으로 변환       |
| Downcasting        | 부모 타입 참조를 자식 타입으로 명시적 변환 |
| instanceof         | 객체의 타입 호환성 확인            |
| ClassCastException | 호환되지 않는 타입으로 형변환 시 발생    |

**핵심: 필드는 선언 타입, 오버라이딩된 메서드는 실제 객체 타입을 기준으로 동작**