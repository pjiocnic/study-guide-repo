#!/bin/bash

# Configuration
JAR_PATH="/opt/email-batch/spring-email-batch-0.0.1-SNAPSHOT.jar"
JAVA_BIN="/usr/bin/java"

# Run the app
$JAVA_BIN -jar "$JAR_PATH"
