#!/bin/bash

GITDIR="`pwd`"
BASEEDITS="$GITDIR/baseedits.patch"
TEMPDIR="../mcp/src-temp-update-baseedits"

cd ../mcp

if [ -e "$BASEEDITS" ]; then
	cp -rf "$BASEEDITS" "$BASEEDITS.old"
fi
diff -r -U 3 src-base src > "$BASEEDITS"
