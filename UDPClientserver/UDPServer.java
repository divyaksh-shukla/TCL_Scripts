import java.io.*;
import java.net.*;

public class UDPServer {
	public static void main(String args[]) throws IOException {
		byte addr[] = {10,2,20,64};
		InetAddress clientIP = InetAddress.getByAddress();
		System.out.println("Client IP : " + clientIP);

		int clientPort = 2000;
		byte buf[] = new byte[1024];
		
		DatagramSocket ds = new DatagramSocket();
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));

		System.out.println("Server is running...");

		String str1 = new String(br.readLine());

		buf = str1.getBytes();

		DatagramPacket packet = new DatagramPacket(buf, str1.length(), clientIP, clientPort);

		ds.send(packet);
		br.close();
	}
}
