#!/bin/bash

docker run -v tb_server:/var/lib/postgresql/data --env POSTGRES_USER=root --env POSTGRES_PASSWORD=123456 -p 5432:5432 postgres:13
