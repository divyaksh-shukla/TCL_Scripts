import java.io.*;
import java.net.*;
import java.util.*;

class UDPComm{
	public static void main(String args[]) throws IOException{
		UDPThreadServer udps = new UDPThreadServer(args[0], args[1], args[2], args[3]);
		UDPThreadClient udpc = new UDPThreadClient();

		try {
			udps.start();
			udpc.start();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}

class UDPThreadServer extends Thread {
	byte addr[];
	UDPThreadServer (String ip1, String ip2, String ip3, String ip4) {

		addr = new byte[4];
		Integer addrValues[] = new Integer[4];

		addrValues[0] = Integer.parseInt(ip1);
		addrValues[1] = Integer.parseInt(ip2);
		addrValues[2] = Integer.parseInt(ip3);
		addrValues[3] = Integer.parseInt(ip4);

		int i;
		for(i = 0; i < 4;i++) {
			addr[i] = addrValues[i].byteValue();
		}
		
	}
	public void run(){
		try{
		InetAddress clientIP = InetAddress.getByAddress(addr);
		System.out.println("Client IP : " + clientIP);
		int clientPort = 2000;
		byte buf[] = new byte[1024];
		while(true) {
		
			DatagramSocket ds = new DatagramSocket();
			BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));

			//System.out.println("Server is running...");

			String str1 = new String(br.readLine());

			if(str1.startsWith("Server")){
				ds.close();
				continue;
			}

			buf = str1.getBytes();

			DatagramPacket packet = new DatagramPacket(buf, str1.length(), clientIP, clientPort);

			ds.send(packet);
			//br.close();
			ds.close();
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

class UDPThreadClient extends Thread {
	byte buf[];
	UDPThreadClient () {
		buf = new byte[1024];
	}
	public void run() {
		try {
		while(true){
		DatagramSocket ds = new DatagramSocket(2000);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		ds.receive(dp);

		String str2 = new String(dp.getData(), 0, dp.getLength());

		System.out.println("Server : " + str2);

		ds.close();
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
