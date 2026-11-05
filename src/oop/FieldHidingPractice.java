
package oop;

public class FieldHidingPractice {

	// 실험 A: 기본적인 필드 숨김
	static class ParentA {
		int value = 10;
	}

	static class ChildA extends ParentA {
		int value = 20;
	}


	// 실험 B: 필드 숨김과 메서드 오버라이딩
	static class ParentB {
		int value = 10;

		int getValue() {
			return value;
		}
	}

	static class ChildB extends ParentB {
		int value = 20;

		@Override
		int getValue() {
			return value;
		}
	}


	// 실험 C: 업캐스팅과 필드 수정
	static class ParentC {
		int value = 10;
	}

	static class ChildC extends ParentC {
		int value = 20;
	}


	// 실험 D: instanceof와 안전한 다운캐스팅
	static class ParentD {
		void hello() {
			System.out.print("Parent");
		}
	}

	static class ChildD extends ParentD {
		@Override
		void hello() {
			System.out.print("Child");
		}

		void childOnly() {
			System.out.print("Only");
		}
	}


	// 실험 E: 잘못된 다운캐스팅
	static class ParentE {
	}

	static class ChildE extends ParentE {
	}

	public static void main(String[] args) {

		// 실험 A
		System.out.print("A: ");

		ParentA a = new ChildA();
		System.out.println(a.value);


		// 실험 B
		System.out.print("B: ");

		ParentB b = new ChildB();

		System.out.println(
			b.value + " " + b.getValue()
		);


		// 실험 C
		System.out.print("C: ");

		ParentC c = new ChildC();
		c.value = 30;

		System.out.println(
			c.value + " " + ((ChildC) c).value
		);


		// 실험 D
		System.out.print("D: ");

		ParentD d = new ChildD();

		if (d instanceof ChildD child) {
			child.hello();
			child.childOnly();
		}

		System.out.println();


		// 실험 E
		System.out.print("E: ");

		ParentE e = new ParentE();

		try {
			ChildE child = (ChildE) e;
			System.out.println("Success");
		} catch (ClassCastException ex) {
			System.out.println("ClassCastException");
		}

	}
}
