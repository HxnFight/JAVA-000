#!/usr/bin/env bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $SCRIPT_DIR

set -x

__memory=128

for xx in $(seq 1 5)
do
    __memory=$(expr $__memory \* 2)
    java  -Xmx${__memory}m    -XX:+PrintGCDetails -XX:+PrintGCDateStamps   GCLogAnalysis  > gc-log-$__memory  2>&1
done


