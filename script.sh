#!/bin/bash

# Script to automate TCL and AWK scripts for multiple values of bandwidth

if [ $# -eq 4 ]; then
	prog=$1
	init=$2
	step=$3
	finish=$4

	cd $prog

	if [ $(echo "$init < $finish" | bc) -eq 1 -a $(temp=$(echo "$init + $step" | bc); echo "$temp <= $finish" | bc) -eq 1 ]; then
		while [ $(echo "$init <= $finish" | bc) -eq 1 ] ; do
			ns $prog.tcl $init
			awk -f $prog.awk $prog.tr
			init=$(echo "$init + $step" | bc)
		done
	fi
else
	echo "USAGE : script.sh PROG_NAME INIT_VALUE STEP_VALUE FINISH_VALUE"
fi
