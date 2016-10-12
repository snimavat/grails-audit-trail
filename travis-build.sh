#!/bin/bash

rm -rf *.zip

./grailsw refresh-dependencies --non-interactive
./grailsw test-app --non-interactive
./grailsw package-plugin --non-interactive
./grailsw maven-install --non-interactive


