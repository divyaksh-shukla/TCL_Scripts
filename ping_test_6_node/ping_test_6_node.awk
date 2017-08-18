BEGIN {
	droppedPackets = 0;
}
{
	if($1 == "d"){ droppedPackets++; }
}
END {
	printf("No. of dropped packets = %d \n\n", droppedPackets);
}
