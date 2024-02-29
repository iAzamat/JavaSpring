@echo off
docker volume create grafana-storage
docker volume inspect grafana-storage
pause