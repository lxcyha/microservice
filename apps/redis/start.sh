#!/usr/bin/env bash
cur_dir=`pwd`
docker stop redis
docker rm redis
docker run -idt --name redis -v ${cur_dir}/log:/log -v ${cur_dir}/redis.conf:/etc/redis/redis_default.conf -v ${cur_dir}/data:/data -p 6379:6379  redis:latest redis-server /etc/redis/redis_default.conf
