# Spring Boot介绍

## Spring Boot和Sping MVC的关系

Spring Boot是Spring MVC的升级版，两者没有必然的联系；在学习Spring Boot之前没有必要拥有Spring MVC的基础

## Spring Boot的特点

1. 化繁为简，简化配置

   最大的特点就是可以让我们抛弃那些繁琐的xml配置

2. 备受关注，是下一代框架

   google在Spring Boot和Spring MVC在搜索量上的一个统计：

![Image text](../images/search.png)

3. 微服务的入门级微框架

   Spring家族为微服务架构提供了一整套的组件，统称为Spring Cloud，而要使用Spring Cloud的话又必须先学会使用Spring Boot。Spring Cloud是建立在Spring Boot的基础之上

   **疑问：** 是不是要学完Spring Cloud之后才能开发出东西呢？

   **答案：** 当然不是这样的

## 内容介绍

1. 第一个Spring Boot程序

   即Spring Boot版本的hello word

2. 自定义属性配置

   即如何在项目中自定义自己的配置

3. Controller的使用

4. spring-data-jpa

   关于数据库的操作

5. 事务管理

6. 以用户信息系统为例子，贯穿课程

## 需要具备必要的前置知识

1. 利用maven构建项目
2. Spring注解
3. RESTful API

# 第一个Spring Boot应用

## 创建与解读

创建Spring Boot应用的方法有很多，只推荐一种：使用IDEA来进行创建

创建步骤如下：

- Create New Project 
- Spring Initializr
- 输入Group、Artifact等信息
- Spring Boot Version选择1.4.1；有很多组件可以选择，目前只选择web
- 选择路径(注意：千万不要在路径中出现中文)

- 构建完成之后，删除无用的文件：mvmw、mvmw.cmd、.mvn

pom.xml文件解读：

- spring-boot-starter-web：作为web项目必须要引入的依赖
- spring-boot-starter-test：作为单元测试的时候要用到的

工程解读：

- 在GirlApplication上有个注解：@SpringBootApplication，要想启动项目的话，在启动类上必须要有这个注解

- application.properties：spring boot的配置文件，在resources目录下

- test：单元测试的目录

## 启动Spring Boot应用的3种方式

- 使用IDEA启动项目，直接运行GirlApplication这个类即可

  启动成功，打印日志信息(部分)：

  ```java
  Registering beans for JMX exposure on startup
  Tomcat started on port(s): 8080 (http)
  Started GirlApplication in 5.824 seconds (JVM running for 6.269)
  ```

  启动成功，访问web ui：http://127.0.0.1:8080/

- 进入到项目所对应的目录，运行如下命令：

  ```shell
  mvn spring-boot:run
  ```

  启动成功，访问web ui：http://127.0.0.1:8080/

- 在项目的对应目录下，先编译项目：

  ```
  mvn install
  ```

  进入到target目录下，运行如下命令：

  ```shell
  java -jar demo-0.0.1.SNAPSHOT.jar
  ```

  启动成功，访问web ui：http://127.0.0.1:8080/

**注意：** 对应的工程代码在HelloController.java中

# Spring Boot项目属性配置

## 使用yml配置而不是properties配置

修改application.properties文件如下：

```xml
server.port=8081
server.context-path=/girl
```

修改过后的web ui访问地址：http://127.0.0.1:8081/girl/hello

推荐使用另外一种的配置文件application.yml，相同的配置在yml文件中是这样的：

```yaml
server:
  port: 8082
  context-path: /girl
```

属性与值之间必须得有空格，否则会有问题

## yml配置初使用

修改properties.yml：

```yaml
server:
  port: 8080
  context-path: /girl
cupSize: B
```

通过Value注解，把配置文件中的内容给注入到cupSize这个属性中来了：

```java
@RestController
public class HelloController {

    @Value("${cupSize}")
    private String cupSize;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return cupSize;
    }
}
```

访问http://127.0.0.1:8080/girl/hello，页面返回B

再加入age这个值，使用方式如上，详细代码在HelloController.java中，页面返回B18

## 在配置中再使用当前配置

修改properties.yml：

```yaml
server:
  port: 8080
  context-path: /girl
cupSize: B
age: 18
content: "cupSize: ${cupSize}, age: ${age}"
```

对应HelloController.java：

```java
@RestController
public class HelloController {
    @Value("${content}")
    private String content;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return content;
    }
}
```

页面返回：cupSize: B, age: 18

## 如何把配置写到类中去(把配置进行分组，推荐使用的方式)

**疑问：**  这样写起来很累，一个个去写属性，如果属性很多的话是不是需要一个一个去写呢？

**解答：** 有办法只写一次就行，在实际生产中代码编写所推荐的方式

修改properties.yml：

```yaml
server:
  port: 8080
  context-path: /girl
girl:
  cupSize: B
  age: 18
```

对应HelloController.java：

```java
@RestController
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }
}

```

对应GirlProperties.java：

- 获取前缀是girl的配置，这样子就不用每一次都写一次 `@Value` 的注解了
- 使用 `@ConfigurationProperties` 这个注解就可以把对应的girl的前缀下面的属性都给映射过来，与yml文件相对应
- 值得的注意是必须加上 `@Component` 这个注解，不然 `private GirlProperties girlProperties;`  会报错，报错内容为： `Could not autowire. No beans of 'GirlProperties' type found.`
- `@Component、@Service、@Controller`等这些注解加上以后，被注解的类都会纳入到Spring中进行管理

```java
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {

    private String cupSize;

    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```

## 多环境配置

**疑问：** 如果随着时间的推移，cupSize不想要B了，想要F了怎么办

**答案：** 直接将对应的值由B改为F就行

**疑问：** 如果在家是B，出门是F怎么办？

使用上述的方式肯定是不行的；作为一名优秀的工程师，不能把时间浪费在频繁修改配置上

创建dev和prod的对应yml文件：

application-dev.yml：

```yaml
server:
  port: 8080
  context-path: /girl
girl:
  cupSize: B
  age: 18
```

application-prod.yml：

```yaml
server:
  port: 8081
  context-path: /girl
girl:
  cupSize: F
  age: 18
```

application.yml：

```yaml
spring:
  profiles:
    active: dev
```

2种方式进行多环境配置：

- 修改对应application.yml文件中active的值：prod或者dev

- 编译运行，直接使用命令进行指定：

  ```shell
  mvn install
  java -jar demo-0.0.1.SNAPSHOT.jar --spring.profiles.active=prod
  ```

## 总结

属性配置

- 使用`@Value`注解来实现配置文件的注入

- 随着配置文件内容的增多，建议对配置的内容做一个分组

  使用到`@Component`和`@ConfigurationProperties`来注入配置

- 多环境配置，针对多环境来使用配置文件，还有更优雅的方式来解决，日后再说

# Controller的使用

## 常见注解

控制器主要是用来接收用户端的请求的

| @Conrtoller         | 处理http请求，不加这个注解浏览器是无法访问到的               |
| ------------------- | ------------------------------------------------------------ |
| **@RestController** | **Spring4之后新加的注解，其实是一个组合注解，等同于@Controller+@ResponseBody** |
| **@RequestMapping** | **配置url映射，希望用户通过某个url访问到我们写的方法**       |

## @Controller使用

### 代码

HelloController.java:

```java
@Controller
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }
}
```

### 报错

只加Controller会报错，信息如下：

```java
org.thymeleaf.exceptions.TemplateInputException: Error resolving template "B", template might not exist or might not be accessible by any of the configured Template Resolvers
```

原因：必须配合模板一起使用，**不需要做深入了解**

### 解决办法

配合模板引擎一起使用

- 在pom.xml中添加相关依赖：

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  ```

- resources\templates\index.html

  ```html
  <h1>hello Spring Boot!</h1>
  ```

- HelloController.java

  ```java
  @Controller
  public class HelloController {
      @Autowired
      private GirlProperties girlProperties;
  
      @RequestMapping(value = "/hello", method = RequestMethod.GET)
      public String say() {
          return "index";
      }
  }
  ```

- 启动项目，访问web ui：http://127.0.0.1:8080/girl/hello，页面上打印出：hello Spring Boot

模板生效，类似于jsp，只是用的thymeleaf模板引擎，是spring官方的一个模板

因为现在的开发方式基本都是前后端分离的，我们作为后端只需要提供rest接口，返回一些json格式给前端就行了

不用再使用模板的方式了，使用模板这种方式会给性能上带来很大的损耗，因此这块**不推荐大家使用**

## 注解@Controller和@ResponseBody配合使用

如上文所说，`@Controller`和`@ResponseBody`配合使用的效果等同于`@RestController`

HelloController.java：

```java
@Controller
@ResponseBody
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }
}
```

页面返回B

## @RequsetMapping使用

### 2个url访问同个方法

现在又有一个新的**需求** ：希望用户访问hello或者是hi，访问任何一个都可以访问到say方法

这就涉及到`@RequsetMapping`这个注解了，映射url，可以在value中写成集合的形式

HelloController.java：

```java
@RestController
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }
}
```

访问页面：http://127.0.0.1:8080/girl/hello和http://127.0.0.1:8080/girl/hi，页面上都返回B

### 给类指定url

`@RequestMapping`可以给整个类指定一个url，HelloController.java：

```java
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }
}
```

访问：http://127.0.0.1:8080/girl/hello/say，页面返回B

### 常见请求方式

#### POST请求使用

使用其它的请求方式，常用的是GET和POST；换成POST，通过web访问就失效了

需要使用postman这个工具进行测试：

![Image text](../images/POST请求.png)

#### 是否可以把请求方式给去除

尝试将`method = RequestMethod.XXX`移除，发现不管GET还是POST都会请求到

但是**并不推荐这种做法** ，因为GET和POST是适用于不同的业务场景或是处理方式上的，为了安全起见我们还是需要自己去指定的

## 如何处理url中的参数

### 常见处理参数的注解

| @PathVariable     | 获取url中的数据      |
| ----------------- | -------------------- |
| **@RequestParam** | **获取请求参数的值** |
| **@GetMapping**   | **组合注解**         |

### @PathVariable的使用

HelloController.java：

```java
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/say/{id}", method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id) {
        return "id: " + id;
    }
}
```

访问：http://127.0.0.1:8080/girl/hello/say/23，页面返回id：23

也可以调换id的位置，如下：

```java
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/{id}/say", method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id) {
        return "id: " + id;
    }
}
```

访问：http://127.0.0.1:8080/girl/hello/100/say，页面返回id：100

### @RequestParam的使用

#### RequestParam的初步使用

HelloController.java：

```java
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say(@RequestParam("id") Integer myId) {
        return "id: " + myId;
    }
}
```

访问方式：http://127.0.0.1:8080/girl/hello/say?id=111(与上述的访问方式不同)，页面返回111

**代码中注意的点：** url中的id对应`@RequestParam`中传入的参数，后面`Integer myId`为自己设置的变量

#### RequestParam其它的一些特性

现在有个需求：给id设置一个默认值，不希望获取到的id为空

HelloController.java：

```java
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say(@RequestParam(value = "id", defaultValue = "0") Integer myId) {
        return "id: " + myId;
    }
}
```

required表示是否必须传；defaultValue不许传int类型的，会报错

访问方式：http://127.0.0.1:8080/girl/hello/say?id=，页面返回0

访问方式：http://127.0.0.1:8080/girl/hello/say?id=888，页面返回888

### 简化RequestMapping，使用@GetMapping注解

`@GetMapping`是组合注解，还有`PostMapping`、`PutMapping`等类似的组合注解；使用这类注解之后，就可以简化书写，节省一定的时间，推荐这种写法

HelloController.java：

```java
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @GetMapping(value = "/say")
    public String say(@RequestParam(value = "id", defaultValue = "0") Integer myId) {
        return "id: " + myId;
    }
}
```

访问方式：http://127.0.0.1:8080/girl/hello/say?id=666，页面返回666

# Spring Boot数据库操作

## Spring Data Jpa简介

- JPA(Java Persistence API)定义了一系列对象持久化的标准，目前实现这一规范的产品有Hibernate、TopLink等

- JPA只是一个标准，并不是组件、系统

- Spring Data Jpa就是对Hibernate的一个整合



## RESTful API设计

实现这5个接口，并且把实现后的结果在数据库中呈现出来

| 请求类型 | 请求路径  | 功能               |
| -------- | --------- | ------------------ |
| GET      | /girls    | 获取女生列表       |
| POST     | /girls    | 创建一个女生       |
| GET      | /girls/id | 通过id查询一个女生 |
| PUT      | /girls/id | 通过id更新一个女生 |
| DELETE   | /girls/id | 通过id删除一个女生 |

## 实战

### 前置准备

在pom.xml中引入依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

修改application.yml：

```yaml
spring:
  profiles:
    active: dev
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/dbgirl
    username: root
    password: zhaotao88016476
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
```

其中：

- create代表在运行的时候会自动帮我们创建一个表

- show-sql 表示能看到sql语句，方便我们在开发的时候调试

在Navicat fo MySQL工具中，创建对应的数据库，如下图所示：

![Image text](../images/create database.png)

创建Girl.java：

```java
@Entity
public class Girl {

    @Id
    @GeneratedValue
    private Integer id;

    private String cupSize;

    private Integer age;

    public Girl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```

- ` @Enity`注解表示该类对应数据库中的一个表

- 该类为数据库对应的类，类中的属性会被映射成数据库的字段

- `@Id`代表该字段为主键

  `@GeneratedValue`代表自增

运行程序，发现在dbgirl中多了一张表girl，表中有3个字段；对应的表和字段，都是通过我们刚刚所写的类给一一映射过去的



**接下来进行相关的测试：** 

在girl表中加入1条数据，再度运行程序，发现数据没有了，原因在于**ddl-auto: create** ，我们通过打印的日志：

```java
Hibernate: drop table if exists girl
Hibernate: create table girl (id integer not null auto_increment, age integer, cup_size varchar(255), primary key (id))
```

每次都会依次执行这两条语句，这也就不难解释为什么在插入1条数据之后，每次重新运行程序之后表中的数据都会被清空

ddl-auto中有许多属性可以填，例如：

- update

  第一次运行的时候会去创建对应的表结构，后面多次运行，数据就不会消失

- create-drop：应用停下来的时候，就会帮我们把表删除

- none：什么都不做

- validate：验证类里面的属性是否和表结构一致，不一致的话会报错

### 获取女生列表(GET请求)

GirlRepository.java：

```java
public interface GirlRepository extends JpaRepository<Girl, Integer>{
}
```

JpaRepository<Girl, Integer> 中参数的意思：类名，ID类型



GirlController.java：

```java
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;
	
   /**
     * 查询所有女生列表
     * @return
     */
    @GetMa
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }
}
```



运行项目，使用Postman验证数据是否可以全部获取到：

![Image text](../images/获取女生列表.png)

### 新增一个女生(POST请求)

GirlController.java中添加：

```java
/**
  * 添加一个女生
  * @param cupSize
  * @param age
  * @return
  */
@PostMapping(value = "/girls")
public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                    @RequestParam("age") Integer age) {
    Girl girl = new Girl();
    girl.setCupSize(cupSize);
    girl.setAge(age);

    return girlRepository.save(girl);
}
```

运行项目，使用Postman进行数据添加：

![Image text](../images/新增一个女生.png)

查看数据库数据是否添加成功：

![Image text](../images/新增一个女生数据库验证.png)

### 通过Id查询一个女生(GET请求)

GirlController.java中添加：

```java
/**
  * 查询一个女生
  * @param id
  * @return
  */
@GetMapping(value = "/girls/{id}")
public Girl girlFindOne(@PathVariable("id") Integer id) {
    return girlRepository.findOne(id);
}
```

运行项目，使用Postman进行查询：

![Image text](../images/查询一个女生.png)

### 通过Id更新一个女生(PUT请求)

GirlController.java中添加：

```java
/**
  * 更新
  * @param id
  * @param cupSize
  * @param age
  * @return
  */
@PutMapping(value = "/girls/{id}")
public Girl girlUpdate(@PathVariable("id") Integer id,
                       @RequestParam("cupSize") String cupSize,
                       @RequestParam("age") Integer age) {
    Girl girl = new Girl();
    girl.setId(id);
    girl.setCupSize(cupSize);
    girl.setAge(age);

    return girlRepository.save(girl);
}
```

save方法传入Girl对象，然后会根据我们所设置的主键：Id，去对应的数据中进行更新

运行项目，使用Postman进行数据更新：

![Image text](../images/更新数据.png)

**注意：** 这里得使用`x-www-form-urlencoded`，而不是使用`form-data`

查看数据库数据是否更新成功：

![Image text](../images/更新数据数据库验证.png)



### 通过Id删除一个女生(DELETE请求)

GirlController.java中添加：

```java
/**
  * 删除
  * @param id
  */
@DeleteMapping(value = "/girls/{id}")
public void girlDelete(@PathVariable("id") Integer id) {
    girlRepository.delete(id);
}
```

运行项目，使用Postman进行数据删除：

![Image text](../images/删除数据.png)

**注意：** DELETE请求不会返回任何东西

查看数据库数据是否删除成功：

![Image text](../images/删除数据数据库验证.png)

### 查询的拓展 --- 通过年龄来进行查询

GirlRepository.java：

```java
public interface GirlRepository extends JpaRepository<Girl, Integer>{

    // 通过年龄来查询
    public List<Girl> findByAge(Integer age);
}
```

GirlController.java中添加：

```java
/**
  * 通过年龄查询
  * @param age
  * @return
  */
@GetMapping(value = "/girls/age/{age}")
public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
    return girlRepository.findByAge(age);
}
```

运行项目，使用Postman进行数据查询：

![Image text](../images/通过年龄查询.png)

# 事务管理

## 普通的数据插入

GirlService.java：

```java
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(20);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }
}
```

GirlController.java中添加：

```java
@Autowired
private GirlService girlService;

@PostMapping(value = "/girls/two")
public void girlTwo() {
    girlService.insertTwo();
}
```

运行项目，使用Postman进行数据插入：

![Image text](../images/事务-插入数据.png)

查看数据库，验证数据是否插入成功：

![Image text](../images/事务-插入数据数据库验证.png)

## 场景模拟

现在需要有如下场景：

- B这条数据插入失败，插入失败之后A这条也不能插入

- 即两条数据要么同时成功，要么同时失败

前置准备：

- 修改girl表结构，如下图所示：

  ![Image text](../images/girl表结构修改.png)

- 这样修改的原因：将cupSize的长度作为限制条件，使得有插入失败的场景

GirlService.java：

```java
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("BBBB");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }
}
```

使用Postman进行数据插入：

![Image text](../images/事务-报错信息.png)

**有报错信息，说明有数据插入失败**

去查看数据进行验证：

![Image text](../images/事务-插入数据1条.png)

发现BBBB插入失败，但是A已经插入进来了，不是我们的期望结果

## @Transactional注解的使用

对于上述情况的解决方案：

- 加上`@Transactional`注解

GirlService.java：

```java
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("BBBB");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }
}
```

重新运行项目，和上述操作一样，发现当BBBB这条记录因不符合规则而插入失败时，A这条记录也没有插入成功

插入BBBB这条记录失败时候，相应的报错信息如下所示：

```java
com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column 'cup_size' at row 1
```

