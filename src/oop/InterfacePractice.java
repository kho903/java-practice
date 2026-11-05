
package oop;

public class InterfacePractice {

	// 실험 A: 인터페이스 구현과 public abstract
	interface AnimalA {
		void sound();
	}

	static class DogA implements AnimalA {
		@Override
		public void sound() {
			System.out.print("Dog");
		}
	}

	// 실험 B: default 메서드와 오버라이딩
	interface GreetingB {
		default String greet() {
			return "Hello";
		}
	}

	static class UserB implements GreetingB {
	}

	static class AdminB implements GreetingB {
		@Override
		public String greet() {
			return "Admin";
		}
	}

	// 실험 C: 다중 인터페이스의 default 메서드 충돌
	interface LeftC {
		default String name() {
			return "L";
		}
	}

	interface RightC {
		default String name() {
			return "R";
		}
	}

	static class BothC implements LeftC, RightC {
		@Override
		public String name() {
			return LeftC.super.name()
				+ RightC.super.name();
		}
	}

	// 실험 D: 추상 클래스와 생성자
	static abstract class ParentD {
		ParentD() {
			System.out.print("P");
		}

		abstract void run();

		void hello() {
			System.out.print("Hello");
		}
	}

	static class ChildD extends ParentD {
		ChildD() {
			System.out.print("C");
		}

		@Override
		void run() {
			System.out.print("Run");
		}
	}

	// 실험 E: 인터페이스 상수와 static 메서드
	interface ConfigE {
		int VALUE = 10;

		static String label() {
			return "Interface";
		}
	}

	static class ImplE implements ConfigE {
		int VALUE = 20;

		static String label() {
			return "Class";
		}
	}

	public static void main(String[] args) {

		// 실험 A
		System.out.print("A: ");

		AnimalA a = new DogA();
		a.sound();
		System.out.println();

		// 실험 B
		System.out.print("B: ");

		GreetingB b1 = new UserB();
		GreetingB b2 = new AdminB();

		System.out.println(
			b1.greet() + " " + b2.greet()
		);

		// 실험 C
		System.out.print("C: ");

		BothC c = new BothC();
		System.out.println(c.name());

		// 실험 D
		System.out.print("D: ");

		ParentD d = new ChildD();
		d.run();
		System.out.println();

		// 실험 E
		System.out.print("E: ");

		ImplE e = new ImplE();

		System.out.println(
			ConfigE.VALUE + " " +
				e.VALUE + " " +
				ConfigE.label() + " " +
				ImplE.label()
		);
	}
}
