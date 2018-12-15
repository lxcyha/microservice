#!/usr/bin/env bash
docker run -d --net=host \
 mesosphere/marathon:v1.5.2 \
 --master zk://192.168.1.16:2181/mesos \
 --zk zk://192.168.1.16:2181/marathon