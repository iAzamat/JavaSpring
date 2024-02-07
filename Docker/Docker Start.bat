@echo off
docker build -t postges_db .
docker run --name mynewdb-container -p 5432:5432 -d postges_db
pause