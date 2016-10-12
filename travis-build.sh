#!/bin/bash

rm -rf *.zip

./grailsw refresh-dependencies --non-interactive
./grailsw test-app --non-interactive
./grailsw package-plugin --non-interactive
./grailsw maven-install --non-interactive

filename=$(find . -name "grails-*.zip" | head -1)
filename=$(basename $filename)
plugin=${filename:7}
plugin=${plugin/.zip/}
plugin=${plugin/-SNAPSHOT/}
version="${plugin#*-}";
plugin=${plugin/"-$version"/}

echo "Publishing plugin grails-spring-security-core with version $version"