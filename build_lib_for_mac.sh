#!/bin/bash
echo "Build libcocoainput for macOS"
[ -d "src/main/resources/assets/darwin" ] && mkdir src/main/resources/assets/darwin
cd libcocoainput/libcocoainput
make && make install
