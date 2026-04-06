#!/usr/bin/env sh
##############################################################################
## Gradle start up script for UN*X
##############################################################################
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`
GRADLE_OPTS="${GRADLE_OPTS:-"-Dfile.encoding=UTF-8"}"
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Resolve links
PRG="$0"
PRGDIR=`dirname "$PRG"`
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
exec "$JAVACMD" "${JVM_OPTS[@]}" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
