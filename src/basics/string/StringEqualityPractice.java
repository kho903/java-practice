package basics.string;

public class StringEqualityPractice {

	public static void main(String[] args) {
		String a = "Java";
		String b = "Java";
		String c = new String("Java");

		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(a.equals(c));

		String d = new String("Java");
		System.out.println(c == d);
		System.out.println(c.equals(d));

		String e = c.intern();
		System.out.println(a == e);

		String f = "Hello";
		f.concat(" World");
		System.out.println(f);
	}
}
