#!/usr/bin/env bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $SCRIPT_DIR

set -x

__memory=128

#__gc_type=UseSerialGC
#__gc_type=UseParallelGC
#__gc_type=UseConcMarkSweepGC
__gc_type=UseG1GC
__log_path_prefix=gclog/$__gc_type

if [[ ! -e $__log_path_prefix ]]; then
    mkdir $__log_path_prefix
fi

for xx in $(seq 1 6)
do
    __log_suffix=$__memory-$__gc_type

    java  -XX:+$__gc_type  -Xmx${__memory}m    -XX:+PrintGCDetails -XX:+PrintGCTimeStamps \
        -Xloggc:./$__log_path_prefix/log-$__log_suffix \
        GCLogAnalysis   > $__log_path_prefix/run-log-$__log_suffix 2>&1

    __memory=$(expr $__memory \* 2)
done


