#!/usr/bin/awk -f

BEGIN {
	cbrPkts = 0;
	tcpPkts = 0;
}
{
	if (($1=="d")&&($5=="cbr")) { cbrPkts++; }
	if (($1=="d")&&($5=="tcp")) { tcpPkts++; }
}
END {
	printf("No. of cbr packets dropped %d \n", cbrPkts)
	printf("No. of tcp packets dropped %d \n", tcpPkts)
}
