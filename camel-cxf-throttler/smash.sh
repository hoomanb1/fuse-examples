#!/bin/bash
for i in {1..10000}
do
   curl -H "Accept: application/json" -H "Content-type: application/json" -X GET  http://localhost:9002/route/services/user/123
done
