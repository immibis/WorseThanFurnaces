#!/bin/bash

GITDIR="`pwd`"

if [ -e ../non-redist/mcp811.zip ]; then
#	if [ `md5sum ../non-redist/mcp811.zip | cut '-d ' -f1` != 2d7a759309b5cc10ca29caa0b10f3bfc ]; then
#		echo mcp811.zip md5 not what was expected
#	fi
	if [ -e ../mcp ]; then
		echo -n 'Deleting ../mcp folder. Enter 'yes' to confirm: '
		read confirm
		if [ "$confirm" != 'yes' ]; then
			echo Exiting.
			exit 1
		fi
		rm -rf ../mcp
	fi
	mkdir ../mcp
	cp ../non-redist/mcp811.zip ../mcp
	cd ../mcp
	unzip mcp811.zip
	mv conf/patches/Start.java .
	rm -rf conf
	cp -rf "$GITDIR/conf" .
	mv Start.java conf/patches

	# hack for cygwin
	cmd "/c decompile.bat
" || ./decompile.sh

	cp -rf src src-base
	cd src
	BASEEDITS="$GITDIR/baseedits.patch"
	if [ -e "$BASEEDITS" ]; then
		patch -p1 -u < "$BASEEDITS"
	fi
else
	if [ ! -e ../non-redist ]; then
		mkdir ../non-redist
	fi
	echo Please download mcp811.zip and place it in the ../non-redist folder.
fi