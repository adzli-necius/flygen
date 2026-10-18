@echo off
setlocal

set "JAR=%~dp0flygen-1.0.0.jar"

if not exist "%JAR%" (
    echo FlyGen JAR not found: %JAR%
    echo Keep flygen.cmd beside flygen-1.0.0.jar.
    exit /b 1
)

java -jar "%JAR%" %*
