#!/bin/bash

GITDIR="`pwd`"

IS_WINDOWS=
if uname | grep CYGWIN; then
	IS_WINDOWS=cygwin
else
	if uname | grep MINGW32; then
		IS_WINDOWS=msys
	fi
fi

IS_HD2U=
if dos2unix --version 2>&1 | head -n1 | grep hd2u; then
	IS_HD2U=yes
fi
	

if [ -e ../non-redist/mcp811.zip ]; then
#	if [ `md5sum ../non-redist/mcp811.zip | cut '-d ' -f1` != 2d7a759309b5cc10ca29caa0b10f3bfc ]; then
#		echo mcp811.zip md5 not what was expected
#	fi
	SAVESDIR=
	if [ -e ../mcp ]; then
		echo -n 'Deleting ../mcp folder. Enter 'yes' to confirm: '
		read confirm
		if [ "$confirm" != 'yes' ]; then
			echo Exiting.
			exit 1
		fi
		if [ -e ../mcp/jars/saves ]; then
			SAVESDIR="`pwd`/../temp-setup-saves"
			mkdir "$SAVESDIR"
			mv "../mcp/jars/saves"/{saves,config} "$SAVESDIR"
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
	cmd "/c decompile.bat < NUL
" || ./decompile.sh < /dev/null

	if [ ! -e src/minecraft ]; then
		echo Decompile failed. Exiting.
		exit 1
	fi

	if [ ! -z "$SAVESDIR" ]; then
		if [ ! -e jars ]; then
			mkdir jars
		fi
		mv "$SAVESDIR/*" jars
	fi

	cp -rf src src-base
	cd src
	BASEEDITS="$GITDIR/baseedits.patch"
	if [ -e "$BASEEDITS" ]; then
		patch -p1 -u < "$BASEEDITS"

		# msys patch converts touched files to unix line endings,
		# which is undesirable, so use MCP's patch if possible

		# AND. cygwin and msys use different incompatible versions of dos2unix!
		if [ ! -z $IS_WINDOWS ]; then
			if [ ! -z $IS_HD2U ]; then
				find -type f | xargs dos2unix --d2u
				find -type f | xargs dos2unix --u2d
			else
				find -type f | xargs dos2unix
				find -type f | xargs unix2dos
			fi
		fi
	fi
else
	if [ ! -e ../non-redist ]; then
		mkdir ../non-redist
	fi
	echo Please download mcp811.zip and place it in the ../non-redist folder.
fi