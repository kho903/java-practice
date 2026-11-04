
package oop;

public class ConstructorPractice {

	// 실험 A: 부모 생성자와 자식 생성자
	static class ParentA {
		ParentA() {
			System.out.print("P");
		}
	}

	static class ChildA extends ParentA {
		ChildA() {
			System.out.print("C");
		}
	}

	// 실험 B: this()와 super()
	static class ParentB {
		ParentB() {
			System.out.print("A");
		}
	}

	static class ChildB extends ParentB {
		ChildB() {
			this(10);
			System.out.print("B");
		}

		ChildB(int n) {
			super();
			System.out.print(n);
		}
	}

	// 실험 C: 생성자에서 오버라이딩 메서드 호출
	static class ParentC {
		ParentC() {
			show();
		}

		void show() {
			System.out.print("Parent");
		}
	}

	static class ChildC extends ParentC {
		int value = 7;

		@Override
		void show() {
			System.out.print(value);
		}
	}

	// 실험 D: static 필드와 인스턴스 필드 초기화 비교
	static class ParentD {
		ParentD() {
			show();
		}

		void show() {
			System.out.print("Parent");
		}
	}

	static class ChildD extends ParentD {
		static int staticValue = 7;
		int instanceValue = 10;

		@Override
		void show() {
			System.out.print(staticValue + " " + instanceValue);
		}
	}

	// 실험 E: 명시적 super()와 암묵적 super()
	static class ParentE {
		ParentE() {
			System.out.print("P");
		}
	}

	static class ChildE1 extends ParentE {
		ChildE1() {
			super();
			System.out.print("C1");
		}
	}

	static class ChildE2 extends ParentE {
		ChildE2() {
			// super()를 명시하지 않음
			System.out.print("C2");
		}
	}

	public static void main(String[] args) {

		System.out.print("A: ");
		new ChildA();
		System.out.println();

		System.out.print("B: ");
		new ChildB();
		System.out.println();

		System.out.print("C: ");
		new ChildC();
		System.out.println();

		System.out.print("D: ");
		new ChildD();
		System.out.println();

		System.out.print("E-1: ");
		new ChildE1();
		System.out.println();

		System.out.print("E-2: ");
		new ChildE2();
		System.out.println();
	}
}
