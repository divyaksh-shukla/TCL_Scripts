import java.io.*;
import java.util.*;
import java.math.*;

class RSA {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader (new FileReader("message.txt"));

		BigInteger a = new BigInteger(11, 1, new Random());
		BigInteger b = new BigInteger(11, 1, new Random());
		//BigInteger a = BigInteger.probablePrime(5, new Random());
		//BigInteger b = BigInteger.probablePrime(5, new Random());
		BigInteger X = BigInteger.ZERO;
		BigInteger Y = BigInteger.ZERO;
		BigInteger N;
		BigInteger phi;
		BigInteger k = BigInteger.valueOf(2);

		System.out.println("a  :" + a);
		System.out.println("b  :" + b);

		N = a.multiply(b);
		phi = a.subtract(BigInteger.ONE).multiply(b.subtract(BigInteger.ONE));

		System.out.println("N  :" + N);
		System.out.println("phi:" + phi);

		for (BigInteger i = BigInteger.valueOf(2) ; !(i.equals(phi)) ; i = i.add(BigInteger.ONE)) {
			if (i.gcd(phi).equals(BigInteger.ONE)) {
				X = new BigInteger(i.toString());
				break;
			}
			//System.out.println(i);
		}

		// System.out.println("X is generated...");

		for (BigInteger i = BigInteger.ONE ;; i = i.add(BigInteger.ONE)) {
			if(X.multiply(i).remainder(phi).equals(BigInteger.ONE)) {
				Y = new BigInteger(i.toString());
				break;
			}
			//System.out.println(i);
		}

		// Y =

		System.out.println("Public Key:\t" + X + "\t," + N);
		System.out.println("Private Key:\t" + Y + "\t," + N);
		String message, line;
		StringBuilder lines = new StringBuilder();

		while((line = br.readLine()) != null) {
			lines.append(line);
		}
		message = lines.toString();
		System.out.println("Message length:" + message.length());

		BigInteger[] msg = new BigInteger[message.length()];
		BigInteger[] encrypt = new BigInteger[message.length()];
		BigInteger[] decrypt = new BigInteger[message.length()];

		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			msg[i.intValue()] = BigInteger.valueOf((long) message.charAt(i.intValue()));
		}

		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			encrypt[i.intValue()] = msg[i.intValue()].modPow(X,N);
		}

		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			decrypt[i.intValue()] = encrypt[i.intValue()].modPow(Y,N);
		}

		/*System.out.println("\nEncoded Numbers:");
		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			System.out.print(msg[i.intValue()] + ",");
		}

		System.out.println("\nEncrypted Numbers:");
		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			System.out.print(encrypt[i.intValue()] + ",");
		}

		System.out.println("\nDecrypted Numbers:");
		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			System.out.print(decrypt[i.intValue()] + ",");
		}*/
		System.out.println("\n\nDecrypted Message:");
		for(BigInteger i = BigInteger.ZERO ; !(i.equals(BigInteger.valueOf(message.length()))) ; i = i.add(BigInteger.ONE)) {
			System.out.print((char)(decrypt[i.intValue()].intValue()));
		}
		System.out.println();
	}
}
