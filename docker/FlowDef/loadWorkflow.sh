#!/bin/bash

find "/app/flowDef" -type f -name "*.json" | while read -r file; do
  echo "POSTing $file"
  curl -s -o /dev/null -w "HTTP %{http_code} - $file\n" \
    -X PUT "http://localhost:9090/api/metadata/workflow" \
    -H "accept: */*" \
    -H "Content-Type: application/json" \
    --data-binary @"$file"
done
