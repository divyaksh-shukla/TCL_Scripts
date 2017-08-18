puts $argc
puts $argv
for {set i 0} { $i < $argc } { incr i } {
	puts "[lindex $argv $i]"
}
