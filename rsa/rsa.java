import java.util.*;
import java.io.*;
public class rsa {
	static long gcd(long m,long n){ 
		while(n!=0){
			long r=m%n;
			m=n;
			n=r;
		}
		return m;
	}
	public static void main(String args[])
	{
		long p=0,q=0,n=0,e=0,d=0,phi=0;
		long nummes[]=new long[100];
		long encrypted[]=new long[100];
		long decrypted[]=new long[100];
		int i=0,j=0,nofelem=0;
		Scanner sc=new Scanner(System.in);
		String message ;
		System.out.println("Enter the Message tobe encrypted:");
		message= sc.nextLine();
		System.out.println("Enter value of p and q:");
		p=sc.nextInt();
		q=sc.nextInt();
		n=p*q;
		phi=(p-1)*(q-1);
		for(i=2;i<phi;i++)
			if(gcd(i,phi)==1) break;
		e=i;
		System.out.println("X:" + e);
		for(i=1;i<phi;i++)
			if((e*i)%phi==1)
				break;
		d=i;
		System.out.println("Y:" + d);
		for(i=0;i<message.length();i++)
		{
			char c = message.charAt(i);
			long a =(long)c;
			nummes[i]=c-96;
			System.out.print(nummes[i] + ",");
		}
		System.out.println();
		nofelem=message.length();
		for(i=0;i<nofelem;i++)
		{
			encrypted[i]=1;
			encrypted[i] =((long)Math.pow(nummes[i], e))%n;
		}
		System.out.print("\nEncrypted message:");
		for(i=0;i<nofelem;i++)
		{
			System.out.print((char)(encrypted[i] + 96));
			//System.out.print((char)(encrypted[i]+96));
		}

		System.out.println();
		for(i=0;i<nofelem;i++){ 
			decrypted[i]=((long)Math.pow(encrypted[i], d))%n;
		}
		System.out.print("\nDecrypted message:");
		for(i=0;i<nofelem;i++)
			System.out.print((char)(decrypted[i] + 96));

		System.out.println();
		return;
	}
}
