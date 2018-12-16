#!/usr/bin/env bash
mvn package
docker build -t lxcyha/course-edge-service:latest .
docker push lxcyha/course-edge-service:latest