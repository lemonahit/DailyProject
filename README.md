# DailyProject

## Spring

### Spring IOC

#### 接口及面向接口编程

相关例子在com.zhaotao.ioc.interface中

当然，从这个例子中还看不出面向接口编程的好处，该例子仅仅是对接口、接口实现类的一个初步了解与使用

#### IOC
**控制反转：** 控制权的转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护：

- 当我们需要一个对象的时候，不是我们自己去程序中去new一个对象；而是由外部容器去创建的，我们向外部容器去申请一个对象然后拿过来使用

**DI：** 依赖注入，是控制反转的一种实现方式

**IOC目的：** 创建对象并且组装对象之间的关系

- IOC容器在创建的时候会初始化一系列的对象

  同时会把对象之间的依赖关系通过注入的方式给组织起来
  - 也就是说当一个类A中持有另外一个类B,当实例化的时候，需要实例化出A和B两个对象，然后把B的对象赋值给A
    **这就是IOC容器负责对象组装的一个功能**

![Spring IOC](..\images\Spring IOC.png)

从上到下来说：

- 业务对象进入Spring容器，通过配置的元数据生产出符合我们需要的对象
-   在使用这些对象的时候直接去Spring容器里面拿出对象来用就行了

#### Spring的Bean配置

那么在Spring容器中，这种对象（也就是Bean）是怎样来进行配置的？

**注：** 在IOC容器中，把所有的对象都称之为Bean

刚才定义了接口类和接口实现类，我们的使用方法是在Main方法中是去new了一个接口实现类，然后调用方法将结果打印在控制台上

那么如果由Spring的Bean来管理的话，这块该怎么配置呢？有2种方式：

- 基于XML的配置

- 注解

##### Bean容器初始化过程
- 涉及到2个基础的包：org.springframework.beans & org.springframework.contex

- 初始化ApplicationContext的3种方式：

  加载本地文件，即指定到具体磁盘目录上的文件

  Classpath，即工程中的相关路径

  Web应用中依赖servlet**或**Listener来初始化ApplicationContext

#### Spring的注入

- 设值注入

- 构造注入

### Sping Bean装配

一切把配置在IOC容器里面的实例或者是对象都称为Bean

#### Bean配置项

- Id

  在整个IOC容器中Bean的唯一标识

- Class

  具体要实例化的哪一个类

- Scope

  具体的范围，也就是作用域

- Constructor arguments

  构造器的参数（构造注入中用到了该参数）

- Properties

  属性（设值注入中用到了该参数）

- Autowiring mode

  自动装配的模式

- lazy-initialization mode

  懒加载模式

- Initialization/destruction method

  初始化/销毁方法

实际上Bean的配置型不止这么多，如果我们想从Bean容器中得到某个实例，有两种方式：

- 一种是通过Id来获取，这就需要配置Id

- 一种是根据Bean的类型来获取，只需要配置Class就可以了

#### Bean作用域

- singleton：单例，指一个Bean容器中只存在一份

- prototype：每次请求（每次使用）都会去创建新的实例（每次向IOC容器去请求要得到一个对象的时候，都会去创建一个新的实例，重新去new一次）

  destroy方式不生效（当这次请求完成后，这个实例就不再被使用了，也会被垃圾回收器回收）

- request：每次http请求创建一个实例且仅在当前request内有效

  http请求创建所创建的对象实例生命周期在一个request内，只要是在当前这个request的范围内，在任何的时间任何的地方去调用，或者说是去IOC容器中申请这个对象，都会是同一个实例；**当另外一个http请求过来的时候会去创建另外一个实例**

- session：原理同上，每次http请求创建，当前session内有效

- global session

  基于portlet的web中有效（portlet定义了global session）

  如果是在单独的web应用中，globalsession的作用域和session一样

  spring对portlet也提供了它自己的支持

#### Bean生命周期

声明周期分为四个部分：

- 定义

  就是在spring对应的xml文件中配置的

- 初始化

  当IOC容器在启动的时候（即context.start），去加载并配置相关属性，并初始化生成bean的实例

- 使用

  在实际的开发中，从bean容器中取出对应对象的实例，然后去使用

- 销毁

  在Bean容器停止的时候，去销毁由当前Bean容器创建的所有的实例

##### 初始化和销毁的3种配置方式

1. xml文件配置init-method的方式
2. 初始化org.springframework.beans.factory.InitializingBean接口覆盖afterPropertiesSet方法的方式
3. 全局默认初始化、销毁方法的配置的方式

