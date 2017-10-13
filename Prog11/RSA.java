import java.util.Scanner;

public class RSA {
	String msg, emsg, dmsg;
	int a, b, n, m, x, y, t;
	int enc[], dec[];

	Scanner sc = new Scanner(System.in);

	public void getData() {
		System.out.print("Enter the string : ");
		msg = sc.next();

		System.out.print("Enter values for a nad b : ");
		a = sc.nextInt();
		b = sc.nextInt();
	}

	public void init() {
		n = a * b;
		t = (a - 1) * (b - 1);
		x = computeX();
		y = computeY();
		enc = new int[msg.length()];
		dec = new int[msg.length()];
	}

	public int gcd(int a, int b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

	public int computeX() {
		for(int i = 2; i < n; i++) {
			if(gcd(i, n) == 1) {
				x = i;
			}
		}
	}

	public int computeY() {
		for(int i = 1; i < n; i++) {
			if((x * i) % n == 1) {
				y = i;
			}
		}
	}

	public void encrypt() {
		char c;
		int asc;

		for(int i = 0; i < msg.length(); i++) {
			c = msg.charAt(i);
			asc = (int)c - 96;

			enc[i] = Math.pow(asc, x) % n;

			c = (char)(enc[i] + 96);
			emsg += Character.toString(c);
		}

		System.out.print("Encrypted message : ");
		for(int i = 0; i < msg.length(); i++) {
			System.out.print(emsg.charAt(i));
		}
	}

	public void decrypt() {
		char c;
		int asc;

		for(int i = 0; i < emsg.length(); i++) {
			dec[i] = Math.pow(enc[i], y) % n;

			c = (char)(enc[
		}
	}
}

public class MainClass {
	public static void main(String args[]) {
		RSA ob = new RSA();

		ob.getData();
	}
}
