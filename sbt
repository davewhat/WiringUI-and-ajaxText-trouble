java -Xmx1024M -Xss4M -XX:+CMSClassUnloadingEnabled -jar `dirname $0`/sbt-launcher.jar "$@"
