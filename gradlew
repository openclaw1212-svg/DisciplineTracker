#!/usr/bin/env sh
set -e
APP_HOME=$(cd "${0%/*}"; pwd -P)
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
java -Xms64m -Xmx512m -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
