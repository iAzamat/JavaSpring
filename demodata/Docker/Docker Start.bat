@echo off
docker build -t booksdb .
docker run --name booksdb -p 5432:5432 -d booksdb
pause