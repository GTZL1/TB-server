#!/bin/bash

docker run --env POSTGRES_USER=root --env POSTGRES_PASSWORD=123456 -p 5432:5432 postgres:13.0
