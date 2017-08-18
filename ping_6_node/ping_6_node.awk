#!/usr/bin/awk -f

BEGIN {
	drop = 0;
}
{
	if($1 == "d") { drop++; }
}
END {
	printf("No. of packets dropped %d\n", drop);
}
