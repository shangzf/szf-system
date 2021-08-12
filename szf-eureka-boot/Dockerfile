FROM openjdk:8u212-jre-alpine3.9
# 作者信息
MAINTAINER shangzf Docker szf-eureka-boot
# 修改源
RUN echo "http://mirrors.aliyun.com/alpine/latest-stable/main/" > /etc/apk/repositories && echo "http://mirrors.aliyun.com/alpine/latest-stable/community/" >> /etc/apk/repositories
# 安装需要的软件，解决时区问题
RUN apk --update add curl bash tzdata && rm -rf /var/cache/apk/
#修改镜像为东八区时间
ENV TZ Asia/Shanghai

COPY szf-eureka-boot.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]