# website - 极客人网站 

Geekren的网站

## 开发方法

### 代码位置

请将网站资源全部放在src/main/webapp目录下

### 启动 & 调试

执行以下命令后在http://localhost:8080/website/可以看到网站的效果

以下命令是开发模式(响应代码变化)
```bash
mvn jetty:run
```

以下命令是本地模式(不响应代码变化)
``` bash
mvn clean package cargo:run
```

## Licence

GPLv3.0
