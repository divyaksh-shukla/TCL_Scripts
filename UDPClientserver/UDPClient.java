import java.io.*;
import java.net.*;

public class UDPClient {
	public static void main(String args[]) throws IOException {
		byte buf[] = new byte[1024];
		DatagramSocket ds = new DatagramSocket(2000);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		ds.receive(dp);

		String str2 = new String(dp.getData(), 0, dp.getLength());

		System.out.println("Server : " + str2);

		ds.close();
	}
}
