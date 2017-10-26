import java.math.BigInteger;
import java.util.Scanner;

public class RSAED {
  public RSAED() {}
  
  private static boolean isPrime(int num) { 
    if (num < 2) return false;
    if (num == 2) return true;
    if (num % 2 == 0) return false;
    for (int i = 3; i * i <= num; i += 2)
      if (num % i == 0) return false;
    return true;
  }
  
  public static int findGCD(int n1, int n2) { 
    if (n2 == 0)
      return n1;
    return findGCD(n2, n1 % n2);
  }
  
  public static void main(String[] args) { 
    Scanner sc = new java.util.Scanner(System.in);
    int k = 0,m = 0,n = 0;
    

    System.out.print("Enter the value of a: ");
    int i = sc.nextInt();
    System.out.print("Enter the value of b: ");
    int j = sc.nextInt();
    
    System.out.println("Enter message:");
    
    String str1 = sc.next();
    str1 = str1 + sc.nextLine();
    
    char[] data = str1.toCharArray();
    
    for (int z : data)
      System.out.print(z + " ");
    k = i * j;
    for (int z : data)
      if (z > k) {
        System.out.println("Message cannot be encrypted using the given values of 'a' and 'b'");
        System.exit(0);
      }
    if ((isPrime(i)) && (isPrime(j)))
    {
      int z = 3;
      for (;;) {
        if (findGCD(z, (i - 1) * (j - 1)) == 1) {
          m = z;
          break;
        }
        
        z++;
      }
      z = 1;
      for (;;) {
        if (m * z % ((i - 1) * (j - 1)) == 1) {
          n = z;
          break;
        }
        
        z++;
      }
    }
    else{
      System.out.println("The entered value(s) not prime");
      System.exit(0);
    }
    
    System.out.println("\nx=" + m + "\ty=" + n);
    System.out.println("Public key:{" + m + "," + k + "}\nPrivate Key:{" + n + "," + k + "}");
    
    System.out.print("Encrypted data: ");
    String str2 = "";
    for (int x : data)
    {
      BigInteger c = new BigInteger(String.valueOf(x)).pow(m).mod(new BigInteger(String.valueOf(k)));
      System.out.print(c.intValue() + " ");
      str2 = str2 + Character.toString((char)((c).pow(n).mod(new BigInteger(String.valueOf(k))).intValue()));
    }
    
    System.out.println("\nDecrypted data: " + str2);
  }
}
