#!/usr/bin/env bash
./gradlew clean build
java -jar ./build/libs/rest-service-0.0.1-SNAPSHOT.jar &
cd ./person-app/
npm install
npm start

