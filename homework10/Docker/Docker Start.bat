@echo off
docker build -t spring-test_img .
docker run --name spring-test-container -p 3306:3306 -d spring-test_img
pause