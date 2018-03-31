#/bin/bash
#ident "%W%"

PIDFILE="/var/run/application.pid"
if [-r ${PIDFILE}] ;
then
  PID='cat ${PIDFILE}'
  if [${PID} != ""]
  then
    kill -9 ${PID}
  fi
rm ${PIDFILE}
else
  echo "SVC has not yet been started"
fi
