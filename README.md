# DailyProject
## Spring
### Spring IOC
- 接口及面向接口编程
- IOC理解</br>
  控制反转；控制权的转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护</br>
  当我们需要一个对象的时候，不是我们自己去程序中去new一个对象；而是由外部容器去创建的，我们向外部容器去申请一个对象然后拿过来使用
  
  DI：依赖注入，是控制反转的一种实现方式

  IOC目的：创建对象并且组装对象之间的关系</br>
  1.IOC容器在创建的时候会初始化一系列的对象</br>
	2.同时会把对象之间的依赖关系通过注入的方式给组织起来</br>
  
  也就是说当一个类A中持有另外一个类B,当实例化的时候，需要实例化出A和B两个对象，然后把B的对象赋值给A</br>
	这就是IOC容器负责对象组装的一个功能</br>
- Spring的Bean配置
- Spring的注入
  - 设值注入
  - 构造注入
