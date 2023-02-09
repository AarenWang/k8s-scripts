Kubernetes 官方文档 Task部分学习记录
https://kubernetes.io/docs/tasks/


## 第一个任务  Pod直接使用卷 挂载
https://github.com/AarenWang/k8s-scripts/blob/master/task/1-pod-with-mount-volume.yaml
   
执行yaml文件
```
kubectl create  -f 1-pod-with-mount-volume.yaml  -n task
```
查看写入文件系统内容
```
kubectl exec -p time-show-app  cat /data/app/time.txt   -n task
kubectl exec -p time-show-app  ls /data/app/time.txt   -n task  #疑问 为啥 ls 后面不能加 -l
```
删除pod
```
kubectl delete  -f 1-pod-with-mount-volume.yaml  -n task
```

## 第二个任务 学习PV PVC使用
官方文档  
[概念](https://kubernetes.io/docs/concepts/storage/persistent-volumes/)    
[实战](https://kubernetes.io/docs/tasks/configure-pod-container/configure-persistent-volume-storage/)  
[设计指导](https://github.com/kubernetes/community/blob/master/contributors/design-proposals/storage/persistent-storage.md)

一 为什么要有持久卷

二 持久卷和持久卷申明关系

三  静态和动态PV

四 例子

  [pv  yaml](https://github.com/AarenWang/k8s-scripts/blob/master/task/2-pv-volume.yaml)  
  [pvc yaml](https://github.com/AarenWang/k8s-scripts/blob/master/task/2-pvc-claim.yaml) 

[nginx挂载持久卷](
https://github.com/AarenWang/k8s-scripts/blob/master/task/2-nginx-with-pv.yaml)

 进入nginx pod 查看挂载文件
 ```
kubectl -n task  exec -it nginx-pv-pod   -- /bin/bash
```

查看ningx html文件目录
```
ls /usr/share/nginx/html/  # 为空
```

在Pod所在Node节点机器上  /mnt/data目录下 创建文件 index.html 
再回到pod目录/usr/share/nginx/html/可以看到文件内容
```
root@nginx-pv-pod:/# ls /usr/share/nginx/html/
index.html
```

在Pod里安装curl查看文件 http服务器内容
```
apt-get update
apt-get install curl
curl localhost
```

持久卷访问控制
```pv.beta.kubernetes.io/gid```

```
kind: PersistentVolume
apiVersion: v1
metadata:
  name: pv1
  annotations:
    pv.beta.kubernetes.io/gid: "1234"
```
POD上只有相同的groupd ID才能访问到持久卷


## 第三个任务 学习prjected volume使用
把configMap secret downward称为 **projected volume**
serviceAccountToken也可以被注入 但是不是卷类型
-- serviceAccountToken volumes can be projected.
-- serviceAccountToken is not a volume type

另外configMap和secret可以通过环境变量注入

创建secret

```
echo -n "admin" > ./username.txt
echo -n "1f2d1e2e67df" > ./password.txt
kubectl create secret generic user --from-file=./username.txt  -n task
kubectl create secret generic pass --from-file=./password.txt  -n task
```

执行yaml文件
```
kubectl apply -f 3-projected-volume.yaml  -n task
```

watch Pod
```
kubectl get --watch pod projected-volume  -n task
```

另外终端执行 确认挂载到secret
```
kubectl -n task exec -it projected-volume -- /bin/sh   
ls /projected-volume/ #可以看到
cat /projected-volume/password.txt  #可以看到password.txt内容

```


## 第四个任务 单实例有状态mysql部署

部署 
```
kubectl create -f 4-statefull-one-install-mysql.yaml  -n task
kubectl create -f 4-statefull-one-install-pv.yaml -n task
```

查看
```
kubectl describe deployment mysql -n task
kubectl get pods -l app=mysql -n task
kubectl describe pvc mysql-pv-claim   -n task
```

使用mysql客户端访问mysql服务
```
kubectl -n task run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword

```

删除部署
```
kubectl delete -f 4-statefull-one-install-mysql.yaml  -n task
kubectl delete -f 4-statefull-one-install-pv.yaml -n task
```

## 第五个任务 master-slave架构有状态(statefullset)mysql部署
准备文件目录
```
sudo mkdir /mnt/mysql-statefullset-data
sudo chown AarenWang:AarenWang /mnt/mysql-statefullset-data

sudo mkdir /mnt/mysql-statefullset-data-1
sudo chown AarenWang:AarenWang /mnt/mysql-statefullset-data-1

sudo mkdir /mnt/mysql-statefullset-data-2
sudo chown AarenWang:AarenWang /mnt/mysql-statefullset-data-2
```

启动部署
```
kubectl create -f 5-master-slave-mysql-pv.yaml  -n task
kubectl create -f 5-master-slave-mysql-configmap.yaml  -n task
kubectl create -f 5-master-slave-mysql-service.yaml  -n task
kubectl create -f 5-master-slave-mysql-stateful.yaml  -n task
```

使用mysql客户端验证
连接master实例创建表格，插入数据
```
kubectl -n task run mysql-client --image=mysql:5.7 -i --rm --restart=Never --\
  mysql -h mysql <<EOF
CREATE DATABASE test;
CREATE TABLE test.messages (message VARCHAR(250));
INSERT INTO test.messages VALUES ('hello');
EOF
```

连接slave实例 查询刚插入数据
kubectl -n task run mysql-client --image=mysql:5.7 -i -t --rm --restart=Never --\
  mysql -h mysql-read -e "SELECT * FROM test.messages"


删除部署

```
kubectl delete -f 5-master-slave-mysql-configmap.yaml  -n task
kubectl delete -f 5-master-slave-mysql-service.yaml  -n task
kubectl delete -f 5-master-slave-mysql-stateful.yaml  -n task
kubectl delete -f 5-master-slave-mysql-pv.yaml  -n task
```

## 第六个任务 部署zookeeper
[参考文档](https://www.jianshu.com/p/2633b95c244c)
节点亲和性知识参考这两篇 https://cizixs.com/2017/05/17/kubernetes-scheulder-affinity/  
https://www.jianshu.com/p/102c4df69af9
```

sudo mkdir /mnt/zookeeper-data
sudo chown AarenWang:AarenWang /mnt/zookeeper-data
```

## 第一个任务 CRD使用
