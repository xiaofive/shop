#!/usr/bin/env bash
echo '==================1.开发环境准备================================'
echo '1请确保安装并java8, git, maven并设置好环境变量'
echo '2请确保安装并docker,docker-compose并设置好环境变量'

read -r -p "开发环境准备好了吗? [Y/n] " envConfirm
case $envConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac
echo '==================2.拉取最新代码==============================='
git pull
echo '==================3.清理当前脚本启动的容器和产生的镜像=========='
docker stop zuul-app consumer-app nc-app nacos-app shop-app es-app
docker rm zuul-app consumer-app nc-app nacos-app shop-app es-app
docker image rm smart-wms/server-jyzt-zuul smart-wms/server-jyzt-consumer smart-wms/server-jyzt-nc smart-wms/server-jyzt-nacos smart-wms/server-jyzt-shop smart-wms/server-jyzt-es

echo '==================4.maven打包,构建服务image====================='
mvn clean

if [ ! -n $1 ]; then
    mvn package -P dev
else
    mvn package -P $1
fi

if [ "$?" -ne 0 ]; then
    ehco 打包失败
    exit 1
fi

#mvn package -P dev

echo '==================5.启动 jyzt-cloud ============================'
cd docker

docker-compose up -d
docker-compose logs -f

#cd -
