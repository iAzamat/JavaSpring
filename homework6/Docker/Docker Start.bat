@echo off
docker build -t taskdb_img .
docker run --name taskdb-container -p 3306:3306 -d taskdb_img
pause