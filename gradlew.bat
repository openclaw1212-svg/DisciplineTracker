@echo off
set DIR=%~dp0
set CLASSPATH=%DIR%gradle\wrapper\gradle-wrapper.jar
java -Xms64m -Xmx512m -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
