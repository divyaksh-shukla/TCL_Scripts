BEGIN{
	drops = 0;
}
{
	if(($1=="d")) { drops++; }
}
END{
	printf("Number of dropped packets: %d \n ", drops);
}
