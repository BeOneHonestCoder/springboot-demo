#/bin/bash
#ident "%W%"

usage()
{
  echo "******"
  exit 1
}

if ["$1" = ""]; then
  usage
fi

JAR="DEMO.jar"
export JAVA_HOME=/jdk/1.6
export RUN_ARGS=" -Denv=${1} -Dspring.profiles.active=${1}"

pushd /var

${JAVA_HOME}/bin/java -jar ${RUN_ARGS} ${JAR}&

popd
