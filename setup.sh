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

if [ ! -e ../non-redist ]; then
	mkdir ../non-redist
fi

if [ ! -e ../non-redist/mcp811.zip ]; then
	echo Please download mcp811.zip and place it in the ../non-redist folder.
	exit 1
fi

if [ ! -e ../non-redist/minecraft_server.1.7.2.jar ]; then
	echo Please download minecraft_server.1.7.2.jar and place it in the ../non-redist folder.
	exit 1
fi

pushd ../non-redist
cp minecraft_server.1.7.2.jar minecraft_server.1.7.2.core.jar
popd

#if [ `md5sum ../non-redist/mcp811.zip | cut '-d ' -f1` != 2d7a759309b5cc10ca29caa0b10f3bfc ]; then
#	echo mcp811.zip md5 not what was expected
#fi

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
		mv ../mcp/jars/{saves,config,options.txt} "$SAVESDIR"
	fi
	rm -rf ../mcp
fi

mkdir ../mcp
cp ../non-redist/mcp811.zip ../mcp
cd ../mcp
unzip mcp811.zip
cp ../non-redist/minecraft_server.1.7.2.core.jar jars/minecraft_server.1.7.2.jar
mv conf/patches/Start.java .
rm -rf conf
cp -rf "$GITDIR/conf" .
mv Start.java conf/patches

# hack for cygwin/msys
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
	mv "$SAVESDIR"/{saves,config,options.txt} jars
	rmdir "$SAVESDIR"
fi

cp -rf src src-base
cd src
BASEEDITS="$GITDIR/baseedits.patch"
if [ -e "$BASEEDITS" ]; then
	patch -p1 -u < "$BASEEDITS"

	# msys patch converts touched files to unix line endings,
	# which is undesirable.

	# there doesn't seem to be a reliably way to detect which files have
	# unix line endings, so convert ALL the files to unix line endings
	# and back...

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
