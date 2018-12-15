#!/usr/bin/env bash
mvn package
docker build -t lxcyha/api-gateway-zuul:latest .
docker push lxcyha/api-gateway-zuul:latest