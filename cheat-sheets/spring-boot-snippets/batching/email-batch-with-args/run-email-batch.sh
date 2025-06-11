#!/bin/bash

# USAGE:
# ./run-email-batch.sh <tableName> <batchStart> <batchEnd>
# Example:
# 0 * * * * /opt/email-batch/run-email-batch.sh recipients 1 100

TABLE_NAME=$1
BATCH_START=$2
BATCH_END=$3

TIMESTAMP=$(date '+%Y%m%d_%H%M%S')
LOG_FILE="app_${TIMESTAMP}.log"

JAR_PATH="/opt/email-batch/email-batch.jar"
TEMPLATE_PATH="/opt/email-batch/email.ftl"
CONFIG_PATH="/opt/email-batch/config.properties"

java -Dtemplate.path=${TEMPLATE_PATH}      -Dconfig.path=${CONFIG_PATH}      -jar ${JAR_PATH}      ${TABLE_NAME} ${BATCH_START} ${BATCH_END}      >> "${LOG_FILE}" 2>&1
