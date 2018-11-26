#!/usr/bin/env bash
cd ../
mvn package

cd deploy
docker build -t user-service:latest .