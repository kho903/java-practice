
package oop;

public class OverridingPractice {

	// 실험 A: 기본적인 메서드 오버라이딩
	static class ParentA {
		void hello() {
			System.out.print("Parent");
		}
	}

	static class ChildA extends ParentA {
		@Override
		void hello() {
			System.out.print("Child");
		}
	}

	// 실험 B: 오버로딩과 오버라이딩
	static class ParentB {
		void print(Object obj) {
			System.out.print("P-Object");
		}

		void print(String str) {
			System.out.print("P-String");
		}
	}

	static class ChildB extends ParentB {
		@Override
		void print(Object obj) {
			System.out.print("C-Object");
		}

		void print(Integer num) {
			System.out.print("C-Integer");
		}
	}

	// 실험 C: super를 이용한 부모 메서드 호출
	static class ParentC {
		String getName() {
			return "Parent";
		}
	}

	static class ChildC extends ParentC {
		@Override
		String getName() {
			return super.getName() + "Child";
		}
	}

	// 실험 D: static 메서드와 인스턴스 메서드
	static class ParentD {
		static void staticMethod() {
			System.out.print("P");
		}

		void instanceMethod() {
			System.out.print("P");
		}
	}

	static class ChildD extends ParentD {
		static void staticMethod() {
			System.out.print("C");
		}

		@Override
		void instanceMethod() {
			System.out.print("C");
		}
	}

	public static void main(String[] args) {

		System.out.print("A: ");
		ParentA a = new ChildA();
		a.hello();
		System.out.println();

		System.out.print("B-1: ");
		ParentB b = new ChildB();
		b.print("Java");
		System.out.println();

		System.out.print("B-2: ");
		Object obj = "Java";
		b.print(obj);
		System.out.println();

		System.out.print("C: ");
		ChildC c = new ChildC();
		System.out.println(c.getName());

		System.out.print("D-1: ");
		ParentD d = new ChildD();
		d.staticMethod();
		System.out.println();

		System.out.print("D-2: ");
		d.instanceMethod();
		System.out.println();
	}
}
