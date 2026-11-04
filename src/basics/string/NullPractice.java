package basics.string;

import java.util.Objects;

public class NullPractice {
	public static void main(String[] args) {
		String a = null;
		String b = "Java";

		System.out.println(a == null);
		System.out.println(b.equals(a));
		System.out.println(Objects.equals(a, b));
		System.out.println(a.equals(b));
	}
}
