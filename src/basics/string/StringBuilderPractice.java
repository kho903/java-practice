package basics.string;

public class StringBuilderPractice {
	public static void main(String[] args) {
		StringBuilder a = new StringBuilder("Java");
		StringBuilder b = a;

		b.append(" Practice");
		System.out.println(a);
		System.out.println(a == b);

		StringBuilder c = new StringBuilder("Java Practice");

		System.out.println(a == c);
		System.out.println(a.equals(c));
	}
}
