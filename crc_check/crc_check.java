import java.io.*;

class CRC16 {

	public void computecrc(int gen[], int rem[]) {
		int current = 0;
		while(true) {
			for (int i = 0; i < gen.length; i++) {
				rem [current + 1] = (rem [current + 1] ^ gen [i]);
			}
			while (rem [current] == 0 && current != rem.length - 1) {
				current++;
			}
			if((rem.length - current) < gen.length) {
				break;
			}
		}

		return rem;
	}
	public static void main(String args[]) {

		String value;
		int msg[], app_msg[], gen[], rem[];
		int i;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the message text:");
		value = scanner.next();
		
		msg = new int(value.length());
		for(i = 0; i < value.length(); i++) {

			msg[i] = Character.getNumericValue(value.charAt());
		}
		
	}

}
