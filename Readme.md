## 依赖关系
- house-common 定义了数据model和公共代码
- house-biz 负责数据访问层和业务实现Service
- house-web 负责定义启动类，Controller，模板引擎

- 依赖关系：house-web -> house-biz -> house-common