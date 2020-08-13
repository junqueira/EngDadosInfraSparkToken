#!/bin/bash

az login \
    --service-principal \
    --username  ${STORAGE_USER} \
    --password ${STORAGE_PASSWORD} \
    --tenant ${STORAGE_TENANT}

az dls fs upload \
    --account datalakexpidevbrz \
    --source-path $SOURCE_PATH \
    --destination-path $DLS_DESTINATION_PATH \
    --overwrite

# az dls fs delete --account datalakexpidevbrz --path "/file_source/jars"
# az dls fs list   --account datalakexpidevbrz --path "/file_source/jars"

JOB_NAME="SPARK"_`date +\%Y\%m\%d\%H\%M\%S`
curl -s -k -H 'Content-Type: application/json' -X POST \
      -d '{
            "name": "'$JOB_NAME'",
            "numExecutors": 3,
            "file": "'$LOCAL_JAR'",
            "className": "'$CLASS'",
            "args": [
                "encrypto"
			],
            "conf": {
                "spark.driver.memory":"2G",
                "spark.executor.memory":"2G",
                "spark.kubernetes.namespace": "spark",
                "spark.executor.cores":"2",
                "spark.kubernetes.driver.request.cores":"400m",
                "spark.kubernetes.executor.request.cores":"400m",      
                "spark.kubernetes.container.image.pullPolicy":"Always",
                "spark.kubernetes.executor.deleteOnTermination":"true",
                "spark.jars.packages": "io.delta:delta-core_2.11:0.6.1",
                "spark.delta.logStore.class":"org.apache.spark.sql.delta.storage.AzureLogStore",
                "spark.kubernetes.container.image":"bigdataxpdev.azurecr.io/xp-spark-kubernetes:v2.4.5"
            }
          }' "http://localhost:8998/batches" | jq