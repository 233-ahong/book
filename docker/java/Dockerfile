# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER ah

EXPOSE 8080

# 挂载目录
VOLUME /home/ruoyi
# 创建目录
RUN mkdir -p /home/ruoyi
# 指定路径
WORKDIR /home/ruoyi
# 复制jar文件到路径
COPY ./book-admin.jar /home/ruoyi/book-admin.jar
# 启动认证服务
ENTRYPOINT ["java","-jar","book-admin.jar"]
