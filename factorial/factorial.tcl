proc fact {a} {
	set fact 1
	for {set i 1} {$i <= $a} {incr i} {
		set fact [expr $i * $fact]
	}
	puts $fact
}

puts "Enter a number:"
gets stdin a
fact $a
