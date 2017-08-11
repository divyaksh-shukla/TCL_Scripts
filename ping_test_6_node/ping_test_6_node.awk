BEGIN{
	drops = 0;
}
{
	if(($1=="d")) { drops++; }
}
END{
	printf("Dropped: %d \n ", drops);
}
