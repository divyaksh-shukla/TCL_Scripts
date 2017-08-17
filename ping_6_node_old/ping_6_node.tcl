set ns [new Simulator]
set f [open ping_6_node.tr w]
set nf [open ping_6_node.nam w]

$ns trace-all $f
$ns namtrace-all $nf
$ns color 1 "Blue"
$ns color 2 "Green"
$ns color 3 "Red"
$ns color 4 "Yellow"

proc finish {} {
	global ns f nf
	$ns flush-trace
	close $f
	close $nf
#	exec nam ping_6_node.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns duplex-link $n0 $n2 2.00Mb 10ms DropTail
$ns duplex-link $n1 $n2 2.00Mb 10ms DropTail
$ns duplex-link $n2 $n3 2.00Mb 10ms DropTail
$ns duplex-link $n3 $n4 2.00Mb 10ms DropTail
$ns duplex-link $n3 $n5 2.00Mb 10ms DropTail

$ns duplex-link-op $n0 $n2 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up
$ns duplex-link-op $n2 $n3 orient right
$ns duplex-link-op $n3 $n4 orient right-up
$ns duplex-link-op $n3 $n5 orient right-down

$ns queue-limit $n2 $n3 10

set ping0 [new Agent/Ping]
set ping1 [new Agent/Ping]
set ping4 [new Agent/Ping]
set ping5 [new Agent/Ping]

$ping0 set class_ 1
$ping1 set class_ 2
$ping4 set class_ 3
$ping5 set class_ 4

$ns attach-agent $n0 $ping0
$ns attach-agent $n1 $ping1
$ns attach-agent $n4 $ping4
$ns attach-agent $n5 $ping5

$ns connect $ping0 $ping5
$ns connect $ping1 $ping4
$ns connect $ping4 $ping0
$ns connect $ping5 $ping1

proc sendPingPacket {} {
	global ns ping0 ping1 ping4 ping5
	set pinginterval 0.01
	set now [$ns now]

	$ns at [expr $now + $pinginterval] "$ping0 send"
	$ns at [expr $now + $pinginterval] "$ping1 send"
	$ns at [expr $now + $pinginterval] "$ping4 send"
	$ns at [expr $now + $pinginterval] "$ping5 send"
	$ns at [expr $now + $pinginterval] "sendPingPacket"
}

set seq 1

Agent/Ping instproc recv {from rtt} {
	global seq
	$self instvar node_
	puts "Node [$node_ id] received ping answer from $from with round trip time $rtt ms"
}

$ns at 0.01 "sendPingPacket"

$ns rtmodel-at 3.0 down $n2 $n3
$ns rtmodel-at 5.0 up $n2 $n3

$ns at 10.0 "finish"

$ns run
