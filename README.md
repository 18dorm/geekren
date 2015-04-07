# 极客人

## 技术栈

- 前端：bootstrap jquery ...
- java spring hibernate springmvc ...
- mysql

## 开发环境

- java 1.8
- maven 3

## 使用方法

若需要抓astaxie的github数据，请执行以下语句。程序会将抓取结果打印在控制台
```bash
$ mvn clean package exec:java -Dexec.mainClass="com.dorm18.geekren.github.pages.User" -Dexec.args="astaxie"
```
## api

```bash
https://api.github.com/users/rosicky1985
https://api.github.com/repos/18dorm/18dorm.github.io
https://api.github.com/repos/rosicky1985/spider/stats/participation
https://api.github.com/repos/18dorm/18dorm.github.io/stats/contributors

## 如何抓取用户贡献的仓库

- http://stackoverflow.com/questions/20714593/github-api-repositories-contributed-to
- http://stackoverflow.com/questions/24006586/githubapi-get-repositories-a-user-has-ever-committed-in
- http://stackoverflow.com/questions/21322778/how-do-i-get-a-list-of-all-the-github-projects-ive-contributed-to-in-the-last-y?lq=1

## References

- https://www.githubarchive.org/

## Licence

GPLv3.0
