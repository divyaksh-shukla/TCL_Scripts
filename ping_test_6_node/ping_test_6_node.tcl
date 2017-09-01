set ns [new Simulator]
set tr [open ping_test_6_node.tr w]
set nf [open ping_test_6_node.nam w]

$ns trace-all $tr
$ns namtrace-all $nf

$ns color 1 "Blue"
$ns color 2 "Green"
$ns color 3 "Red"
$ns color 4 "Yellow"

proc finish {} {

	global ns nf tr
	$ns flush-trace
	close $tr
	close $nf
	# exec nam ping_test_6_node.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

# puts "nodes declared"

$ns duplex-link $n0 $n2 [lindex $argv 0]Mb 10ms DropTail
$ns duplex-link $n1 $n2 [lindex $argv 0]Mb 10ms DropTail
$ns duplex-link $n3 $n4 [lindex $argv 0]Mb 10ms DropTail
$ns duplex-link $n3 $n5 [lindex $argv 0]Mb 10ms DropTail
$ns duplex-link $n3 $n2 [lindex $argv 0]Mb 10ms DropTail

$ns queue-limit $n2 $n3 10

$ns duplex-link-op $n0 $n2 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up
$ns duplex-link-op $n3 $n4 orient right-up
$ns duplex-link-op $n3 $n5 orient right-down
$ns duplex-link-op $n3 $n2 orient left

# puts "nodes initialized"

set ping0 [new Agent/Ping]
set ping1 [new Agent/Ping]
set ping4 [new Agent/Ping]
set ping5 [new Agent/Ping]

# puts "pings declared"

$ping0 set class_ 1
$ping1 set class_ 2
$ping4 set class_ 3
$ping5 set class_ 4

$ns attach-agent $n0 $ping0
$ns attach-agent $n1 $ping1
$ns attach-agent $n4 $ping4
$ns attach-agent $n5 $ping5

# puts "nodes attached to the pings"

$ns connect $ping0 $ping5
$ns connect $ping0 $ping4
$ns connect $ping1 $ping5
$ns connect $ping1 $ping4

proc sendPingPacket {} {
	global ns ping0 ping1 ping4 ping5
	set now [$ns now]
	set pinginterval 0.01
	$ns at [expr $now + $pinginterval] "$ping0 send"
	$ns at [expr $now + $pinginterval] "$ping1 send"
	$ns at [expr $now + $pinginterval] "$ping4 send"
	$ns at [expr $now + $pinginterval] "$ping5 send"
	$ns at [expr $now + $pinginterval] "sendPingPacket"
}

Agent/Ping instproc recv { from rtt } {
	$self instvar node_
	# puts "ping from $from to [$node_ id] with rtt=$rtt"
}

$ns at 0.01 "sendPingPacket"
$ns rtmodel-at 3.0 down $n2 $n3
$ns rtmodel-at 5.0 up $n2 $n3
$ns at 10.0 "finish"

$ns run