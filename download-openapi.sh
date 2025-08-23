#!/bin/bash

# Descargar la especificación OpenAPI
curl -o openapi-spec.json http://localhost:8080/v3/api-docs

echo "OpenAPI specification has been downloaded to openapi-spec.json" 