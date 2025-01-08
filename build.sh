#!/bin/bash

cd client && npm run clean && npm run build && cd ../ && pwd;
cd tictactoe && ./mvnw clean && ./mvnw install;


