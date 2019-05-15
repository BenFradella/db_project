#!/bin/bash

javac Menu.java && \
java -d64 -cp "mariadb-java-client-2.4.1.jar:." Menu
