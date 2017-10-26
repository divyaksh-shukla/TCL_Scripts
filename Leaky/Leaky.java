import java.util.*;
public class Leaky
{
	public static void main(String args[])
	{
		Scanner my = new Scanner(System.in);
		int no_groups,bucket_size;
		System.out.println("Enter the bucket size:");
		bucket_size = my.nextInt();
		System.out.println("Enter no of groups:");
		no_groups = my.nextInt();
		int no_packets[] = new int[no_groups+10];
		int in_bw[] = new int[no_groups+10];
		int out_bw,reqd_bw=0,tot_packets=0;
		for(int i=0;i<no_groups;i++)
		{
			System.out.println("Enter the no of packets for group "+(i+1)+" ");
			no_packets[i] = my.nextInt();
			System.out.println("Enter the input bandwidth for the group "+(i+1)+" ");
			in_bw[i] = my.nextInt();
			if((tot_packets+no_packets[i])<=bucket_size)
				tot_packets += no_packets[i];
			else {
				do {
					System.out.println("Bucket Overflow!");
					System.out.println("Enter the value less than "+(bucket_size-tot_packets));
					no_packets[i] = my.nextInt();
				}while((tot_packets+no_packets[i])>bucket_size);
				tot_packets += no_packets[i];
			}
			reqd_bw += (no_packets[i]*in_bw[i]);
		}
		System.out.println("The total required bandwidth is:"+reqd_bw);
		System.out.println("Enter the output bandwith:");
		out_bw = my.nextInt();
		int temp = reqd_bw;
		int rem_pkts = tot_packets;
		int pkts,k=1;
		for(int i=0;(out_bw <= temp) && (rem_pkts>0) && (i<=no_groups);i++)
		{
			pkts = no_packets[i];
			while(in_bw[i] <= out_bw && pkts>0 && out_bw<=temp) {
			System.out.println("Packet of "+(i+1)+" sent");
			System.out.println((--rem_pkts)+" packets remaining");
			System.out.println("Remaining bandwidth:"+(temp-=out_bw));
			pkts--;
			}
			if((out_bw > temp) && (rem_pkts>0))
				System.out.println(rem_pkts +" packet(s) discarded due to insufficient bandwidth"); 
		}	
		if(out_bw>reqd_bw)
			System.out.println("All packets are transmitted");
	}
}
