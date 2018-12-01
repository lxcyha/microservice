# 查看jar包详细内容
jar -tf xxx.jar
# 重启单个微服务
docker-compose up -d xxx.service   
# 微服务 docker push 到docker hub
docker tag xxx:version username/xxx:version 
docker push username/xxx:version
# 微服务 docker push 到私有仓库
修改dockerfile 的 From localhost:5000/[projectName]/xxx
docker tag xxx:version localhost:5000/[projectName]/xxx:version 
docker push localhost:5000/[projectName]/xxx:version