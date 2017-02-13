#! /bin/sh

echo $1

if [ ! -n "$1" ]; then
    echo "必须指定一个apk"
    exit -1
fi
src=$1
if [ ! -f ${src} ]; then
    echo "文件不存在"
    exit -1
fi

filename=${src##*/}
fname_nosuffix=${filename%%.*}

if [ ! -n "$2" ]; then
    echo "目标目录不存在"
    exit -1
fi
dst=$2

if [ ! -n "$3" ]; then
    echo "必须指定标识文件"
    exit -1
fi
key=$3
if [ ! -f ${key} ];then
    echo "key 不存在"
    exit -1
fi


if [ ! -d "./target" ];then
    echo "目标目录不存在";
    mkdir -p "target"
fi
echo "-=----------------------"

./apktool/apktool d -f -o ${dst}/${fname_nosuffix} $1


cp -Rf ${key} ${dst}/${fname_nosuffix}/assets/

./apktool/apktool b ${dst}/${fname_nosuffix}

jarsigner -verbose -storepass android -keystore Z970B.keystore -signedjar ./target/${filename} ${dst}/${fname_nosuffix}/dist/${filename} androiddebugkey

echo ===================
echo #?
echo ===================

#cp -rf $2/dist/*.apk ./target/

rm -rf ${dst}/${fname_nosuffix}



