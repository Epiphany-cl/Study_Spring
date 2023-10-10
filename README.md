# Spring 学习

---

<!-- TOC -->
* [Spring 学习](#spring-学习)
    * [IOC 控制反转](#ioc-控制反转)
      * [一、IOC入门案例](#一ioc入门案例)
        * [1. 创建依赖](#1-创建依赖)
        * [2. 创建一个类](#2-创建一个类)
        * [3. 创建applicationContext.xml 并配置bean](#3-创建applicationcontextxml-并配置bean)
        * [4.在测试类进行测试IOC](#4在测试类进行测试ioc)
      * [二、依赖注入之setter注入](#二依赖注入之setter注入)
        * [1. 创建一个含有属性的类](#1-创建一个含有属性的类)
        * [2. 配置Bean](#2-配置bean)
        * [3. 测试](#3-测试)
      * [三、依赖注入之构造器注入](#三依赖注入之构造器注入)
      * [四、类型属性赋值](#四类型属性赋值)
        * [1. 引用外部已声明的bean](#1-引用外部已声明的bean)
        * [2. 内部bean](#2-内部bean)
        * [3. 级联属性赋值](#3-级联属性赋值)
      * [五、为集合类型属性赋值](#五为集合类型属性赋值)
        * [1. 数组](#1-数组)
        * [2. List、Set集合](#2-listset集合)
        * [3. Map](#3-map)
        * [4. 集合类型的bean](#4-集合类型的bean)
        * [5. p命名空间](#5-p命名空间)
        * [6. 引入外部属性文件](#6-引入外部属性文件)
        * [7. FactoryBean](#7-factorybean)
      * [六、基于注解XML的自动装配](#六基于注解xml的自动装配)
      * [七、基于注解管理bean](#七基于注解管理bean)
        * [扫描组件](#扫描组件)
          * [1. 最基本的扫描方式](#1-最基本的扫描方式)
          * [2. 指定要排除的组件](#2-指定要排除的组件)
          * [3. 仅扫描指定组件](#3-仅扫描指定组件)
      * [八、基于注解的自动装配](#八基于注解的自动装配)
        * [@Autowired注解](#autowired注解)
        * [@Autowired工作流程](#autowired工作流程)
      * [九、特殊值处理](#九特殊值处理)
        * [1. null值](#1-null值)
        * [2. XML实体 `<` `&`](#2-xml实体--)
      * [十、bean的生命周期](#十bean的生命周期)
        * [1. initMethod()和destroyMethod()方法](#1-initmethod和destroymethod方法)
        * [2. bean的后置处理器](#2-bean的后置处理器)
        * [`<bean>`标签属性](#bean标签属性)
    * [AOP 面向切面编程(Aspect Oriented Programming)](#aop-面向切面编程aspect-oriented-programming)
      * [一、 AOP概念与相关术语](#一-aop概念与相关术语)
        * [1. 概述](#1-概述)
        * [2. 相关概念](#2-相关概念)
        * [3.作用](#3作用)
      * [二、 基于注解的AOP](#二-基于注解的aop)
        * [1. 技术说明](#1-技术说明)
        * [2，准备工作](#2准备工作)
        * [3，创建切片类并配置](#3创建切片类并配置)
        * [4，测试类](#4测试类)
        * [4，各种通知](#4各种通知)
        * [5，切入表达式](#5切入表达式)
        * [6，重用切入点表达式](#6重用切入点表达式)
        * [7，切面优先级](#7切面优先级)
      * [三、 基于XML的AOP(了解)](#三-基于xml的aop了解)
      * [四、 声明式事务](#四-声明式事务)
        * [1. JdbcTemplate](#1-jdbctemplate)
        * [2. 声明式事务](#2-声明式事务)
          * [2.1. 编程式事务](#21-编程式事务)
          * [2.2. 声明式事务](#22-声明式事务)
        * [3. 基于注解的声明式事务](#3-基于注解的声明式事务)
          * [3.1 准备工作](#31-准备工作)
          * [3.2 测试无事务情况](#32-测试无事务情况)
          * [3.3 测试无事务情况](#33-测试无事务情况)
          * [3.4 @Transactional注解标识的位置](#34-transactional注解标识的位置)
          * [3.5 事务属性：只读](#35-事务属性只读)
          * [3.6 事务属性：超时](#36-事务属性超时)
          * [3.7 事务属性：回滚策略](#37-事务属性回滚策略)
          * [3.8 事务属性：隔离级别](#38-事务属性隔离级别)
          * [3.9 事务属性：事务传播行为](#39-事务属性事务传播行为)
        * [4. 基于XML的声明式事务](#4-基于xml的声明式事务)
<!-- TOC -->

---

### IOC 控制反转

#### 一、IOC入门案例

##### 1. 创建依赖

```xml
<!-- 基于Maven依赖传递性，导入spring-context依赖即可导入当前所需所有jar包 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.1</version>
</dependency>
```

##### 2. 创建一个类

```java
public class HelloWorld {
    public void show(){
        System.out.println("Hello World");
    }
}
```

##### 3. 创建applicationContext.xml 并配置bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
>
    <bean id="helloWorld" class="HelloWorld"/>
</beans>
```

##### 4.在测试类进行测试IOC

```java
public class MyTest {
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld helloWorld = applicationContext.getBean(HelloWorld.class);
        helloWorld.show(); //输出 Hello World
    }
}
```

#### 二、依赖注入之setter注入

##### 1. 创建一个含有属性的类
```java
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    
    //get、set、构造器 略
}
```

##### 2. 配置Bean
```xml
    <bean class="Student" id="student">
        <property name="id" value="1001"/>
        <property name="name" value="小明"/>
        <property name="age" value="18"/>
        <property name="sex" value="男"/>
    </bean>
```

##### 3. 测试
```java
public class StudentTest {
    @Test
    public void testStudent() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = applicationContext.getBean(Student.class);
        System.out.println(student); //输出 Student{id=1001, name='小明', age=18, sex='男'}
    }
}
```

#### 三、依赖注入之构造器注入

```xml
    <bean class="Student" id="student">
        <constructor-arg name="name" value="小红"/>
        <constructor-arg name="id" value="1002"/>
        <constructor-arg name="sex" value="女"/>
        <constructor-arg name="age" value="17"/>
    </bean>
```
constructor-arg 的 name 属性可以省略 但是字段顺序必须和构造器一致

除此之外还可以用 index 属性，但是感觉多此一举，聊胜于无


#### 四、类型属性赋值

##### 1. 引用外部已声明的bean

`ref` 属性: 引用IOC容器中某个bean的id，将所对应的bean为属性赋值

```xml
    <bean class="Student" id="student">
        <!-- ref 属性 引用IOC容器中某个bean的id，将所对应的bean为属性赋值-->
        <property name="clazz" ref="clazz"/>
        <property name="sex" value="男"/>
        <property name="age" value="18"/>
        <property name="name" value="小明"/>
        <property name="id" value="121"/>
    </bean>
    <bean class="Clazz" id="clazz">
        <property name="className" value="财运滚滚"/>
        <property name="clazzId" value="1001"/>
    </bean>
```

##### 2. 内部bean

```xml
<bean id="studentFour" class="Student">
    <property name="id" value="1004"/>
    <property name="name" value="赵六"/>
    <property name="age" value="26"/>
    <property name="sex" value="女"/>
    <property name="clazz">
        <!-- 在一个bean中再声明一个bean就是内部bean -->
        <!-- 内部bean只能用于给属性赋值，不能在外部通过IOC容器获取，因此可以省略id属性 -->
        <bean id="clazzInner" class="Clazz">
            <property name="clazzId" value="2222"/>
            <property name="clazzName" value="远大前程班"/>
        </bean>
    </property>
</bean>
```

##### 3. 级联属性赋值

```xml
 <bean id="studentFour" class="Student">
        <property name="id" value="1004"/>
        <property name="name" value="赵六"/>
        <property name="age" value="26"/>
        <property name="sex" value="女"/>
        <!-- 一定先引用某个bean为属性赋值，才可以使用级联方式更新属性 -->
        <property name="clazz" ref="clazzOne"/>
        <property name="clazz.clazzId" value="3333"/>
        <property name="clazz.clazzName" value="最强王者班"/>
    </bean>
```

#### 五、为集合类型属性赋值

##### 1. 数组

```xml
    <bean id="studentFour" class="Student">
        <property name="id" value="1004"/>
        <property name="name" value="赵六"/>
        <property name="age" value="26"/>
        <property name="sex" value="女"/>
        <!-- ref属性：引用IOC容器中某个bean的id，将所对应的bean为属性赋值 -->
        <property name="clazz" ref="clazzOne"/>
        <property name="hobbies">
            <array>
                <value>抽烟</value>
                <value>喝酒</value>
                <value>烫头</value>
            </array>
        </property>
    </bean>
```

##### 2. List、Set集合

```xml
    <bean id="clazzTwo" class="Clazz">
        <property name="clazzId" value="4444"/>
        <property name="clazzName" value="Javaee0222"/>
        <property name="students">
            <list><!--array、set标签也行,比较宽容-->
                <ref bean="studentOne"/>
                <ref bean="studentTwo"/>
                <ref bean="studentThree"/>
            </list>
        </property>
    </bean>
```
若为Set集合类型属性赋值，只需要将其中的list标签改为set标签即可

##### 3. Map

```xml
    <property name="teacherMap">
        <map>
            <entry>
                <key>
                    <value>10010</value>
                </key>
                <ref bean="teacherOne"/>
            </entry>
            <entry>
                <key>
                    <value>10086</value>
                </key>
                <ref bean="teacherTwo"/>
            </entry>
        </map>
    </property>
    <!--或者-->
    <property name="teacherMap">
        <map>
            <entry key="10010" value-ref="teacherOne"/>
            <entry key="10086" value-ref="teacherTwo"/>
        </map>
    </property>
    <!--#{}调用对象的属性-->
    <property name="teacherMap">
        <map>
            <entry key="#{teacherOne.id}" value-ref="teacherOne"/>
            <entry key="#{teacherTwo.id}" value-ref="teacherTwo"/>
        </map>
    </property>
```
##### 4. 集合类型的bean

使用前需要映入util的命名空间

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/util
       https://www.springframework.org/schema/util/spring-util.xsd"
>
```

```xml
    <!--list集合类型的bean-->
    <util:list id="students">
        <ref bean="studentOne"></ref>
        <ref bean="studentTwo"></ref>
        <ref bean="studentThree"></ref>
    </util:list>
    <!--map集合类型的bean-->
    <util:map id="teacherMap">
        <entry key="10010" value-ref="teacherOne"/>
        <entry key="10086" value-ref="teacherTwo"/>
    </util:map>
```

##### 5. p命名空间

```xml
<bean id="studentSix" class="Student"
      p:id="1006" 
      p:name="小明" 
      p:clazz-ref="clazzOne" 
      p:teacherMap-ref="teacherMap"/>
```

##### 6. 引入外部属性文件

引入数属性文件

```xml
    <!--声明数据源-->
    <context:property-placeholder location="classpath:jdbc.properties"/>
```

配置bean

```xml
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}"/>
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
```

测试

```java
public class JDBCTest {
    @Test
    public void testDataSource() throws SQLException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = ac.getBean(DataSource.class); Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
```

##### 7. FactoryBean

```java
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }


    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
```

```java
public class UserFactoryBeanTest {

    ApplicationContext applicationContext;

    @Before
    public void inti(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test(){
        User user = (User)applicationContext.getBean("user");
        System.out.println(user);
    }
}
```

#### 六、基于注解XML的自动装配

使用bean标签的autowire属性设置自动装配效果
- 自动装配方式：byType

  - byType：根据类型匹配IOC容器中的某个兼容类型的bean，为属性自动赋值
  - 若在IOC中，没有任何一个兼容类型的bean能够为属性赋值，则该属性不装配，即值为默认值 null
  - 若在IOC中，有多个兼容类型的bean能够为属性赋值，则抛出异常 NoUniqueBeanDefinitionException

```xml
<bean id="userController" class="controller.UserController" autowire="byType"/>
<bean id="userService" class="service.impl.UserServiceImpl" autowire="byType"/>
<bean id="userDao" class="UserDaoImpl"/>
```

- 自动装配方式：byName

  - byName：将自动装配的属性的属性名，作为bean的id在IOC容器中匹配相对应的bean进行赋值


#### 七、基于注解管理bean

标识组件的常用注解

- @Component：将类标识为普通组件  
- @Controller：将类标识为控制层组件  
- @Service：将类标识为业务层组件 
- @Repository：将类标识为持久层组件

##### 扫描组件

###### 1. 最基本的扫描方式

```xml
    <contex:component-scan base-package="controller,dao,service"/>
```
###### 2. 指定要排除的组件

```xml
    <contex:component-scan base-package="controller,dao,service">
        <!-- context:exclude-filter标签：指定排除规则 -->
        <!--
        type：设置排除或包含的依据
        type="annotation"，根据注解排除，expression中设置要排除的注解的全类名
        type="assignable"，根据类型排除，expression中设置要排除的类型的全类名
        -->
        <contex:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        <contex:exclude-filter type="assignable" expression="controller.UserController"/>
    </contex:component-scan>
```

###### 3. 仅扫描指定组件

```xml
    <contex:component-scan base-package="controller,service,dao" use-default-filters="false">
        <!-- context:include-filter标签：指定在原有扫描规则的基础上追加的规则 -->
        <!-- use-default-filters属性：取值false表示关闭默认扫描规则 -->
        <!-- 此时必须设置use-default-filters="false"，因为默认规则即扫描指定包下所有类 -->
        <!--
        type：设置排除或包含的依据
        type="annotation"，根据注解，expression中设置要过滤的注解的全类名
        type="assignable"，根据类型，expression中设置要过滤的类型的全类名
        -->
        <contex:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        <contex:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
        <contex:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
    </contex:component-scan>
```

#### 八、基于注解的自动装配

##### @Autowired注解

在成员变量上直接标记@Autowired注解即可完成自动装配，不需要提供setXxx()方法。以后我们在项目中的正式用法就是这样。

##### @Autowired工作流程

- 首先根据所需要的组件类型到IOC容器中查找
  - 能够找到唯一的bean：直接执行装配 
  - 如果完全找不到匹配这个类型的bean：装配失败
  - 和所需类型匹配的bean不止一个 
    - 没有@Qualifier注解：根据@Autowired标记位置成员变量的变量名作为bean的id进行匹配
      - 能够找到：执行装配
      - 找不到：装配失败
    - 使用@Qualifier注解：根据@Qualifier注解中指定的名称作为bean的id进行匹配
        - 能够找到：执行装配
        - 找不到：装配失败

```java
@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public void show(){
        userService.show();
    }
}
```

@Autowired中有属性required，默认值为true，因此在自动装配无法找到相应的bean时，会装配失败
可以将属性required的值设置为false，则表示能装就装，装不上就不装，此时自动装配的属性为默认值
但是实际开发时，基本上所有需要装配组件的地方都是必须装配的，用不上这个属性。

```java
@Autowired(required = false) //当required为false时 装配不上将不会抛异常
```


#### 九、特殊值处理

##### 1. null值

```xml
<property name="name">
    <null />
</property>
```
##### 2. XML实体 `<` `&`

用`&lt; &amp;`代替

```xml
<!-- 小于号在XML文档中用来定义标签的开始，不能随便使用 -->
<!-- 解决方案一：使用XML实体来代替 &lt; &amp; -->
<property name="expression" value="a &lt; b"/>
```

```xml
<property name="expression">
<!-- 解决方案二：使用CDATA节 -->
<!-- CDATA中的C代表Character，是文本、字符的含义，CDATA就表示纯文本数据 -->
<!-- XML解析器看到CDATA节就知道这里是纯文本，就不会当作XML标签或属性来解析 -->
<!-- 所以CDATA节中写什么符号都随意 -->
<value><![CDATA[a < b]]></value>
</property>
```

#### 十、bean的生命周期

- bean对象创建（调用无参构造器） 给bean对象设置属性
- bean对象初始化之前操作（由bean的前置处理器负责） 
- bean对象初始化（需在配置bean时指定初始化方法）
- bean对象初始化之后操作（由bean的后置处理器负责） 
- bean对象就绪可以使用
- bean对象销毁（需在配置bean时指定销毁方法） IOC容器关闭

##### 1. initMethod()和destroyMethod()方法

```xml
    <!-- 使用init-method属性指定初始化方法 -->
    <!-- 使用destroy-method属性指定销毁方法,singleton才会生效,prototype无效 -->
    <bean class="User" 
          scope="prototype" 
          init-method="initMethod" 
          destroy-method="destroyMethod">
        <property name="id" value="1001"></property>
        <property name="username" value="admin"></property>
        <property name="password" value="123456"></property>
        <property name="age" value="23"></property>
    </bean>
```
需要提前在对象中写initMethod()和destroyMethod()方法

##### 2. bean的后置处理器

bean的后置处理器会在生命周期的初始化前后添加额外的操作，需要实现BeanPostProcessor接口，且配置到IOC容器中，需要注意的是，bean后置处理器不是单独针对某一个bean生效，而是针对IOC容器中所有bean都会执行

创建bean的后置处理器：

```java
public class MyBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("☆☆☆" + beanName + " = " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("★★★" + beanName + " = " + bean);
        return bean;
    }
}
```

在IOC容器中配置:

```xml
    <!-- bean的后置处理器要放入IOC容器才能生效 -->
    <bean id="myBeanProcessor" class="MyBeanProcessor"/>
```

---
##### `<bean>`标签属性

1. scope("singleton|prototype")

- singleton: 单例模式(默认)
- prototype: 原型模式

| 取值            | 含义                      | 创建对象时机  |
|:--------------|-------------------------|---------|
| singleton(默认) | 在IOC容器中，这个bean的对象始终为单实例 | IOC初始化时 |
| prototype     | 这个bean在IOC容器中有多个实例      | 获得bean时 |

如果是在WebApplicationContext环境中还会有另外两个域(不常用)

- request 一次请求有效
- session 一次会话有效


### AOP 面向切面编程(Aspect Oriented Programming)

#### 一、 AOP概念与相关术语

##### 1. 概述

AOP（Aspect Oriented Programming）是一种设计思想，
是软件设计领域中的面向切面编程，它是面向对象编程的一种补充和完善，
它以通过预编译方式和运行期动态代理方式实现在不修改源代码的情况下给程序动态统一添加额外功能的一种技术。

##### 2. 相关概念

- Target(目标对象)

要被增强的对象，一般是业务逻辑类的对象。--TeamService

-  Proxy（代理）

一个类被 AOP 织入增强后，就产生一个结果代理类。 --ProxyTeamService--动态生成

-  Aspect(切面)


表示增强的功能，就是一些代码完成的某个功能，非业务功能。是切入点和通知的结合。--服务性代码：日志 权限 事务

-  JoinPoint(连接点)

所谓连接点是指那些被拦截到的点。在Spring中,这些点指的是方法（一般是类中的业务方法）,因为Spring只支持方法类型的连接点。 --add()

-  PointCut(切入点)

切入点指声明的一个或多个连接点的集合。通过切入点指定一组方法。
被标记为 final 的方法是不能作为连接点与切入点的。因为最终的是不能被修改的，不能被增强的。

-  Advice(通知/增强)

所谓通知是指拦截到 Joinpoint 之后所要做的事情就是通知。通知定义了增强代码切入到目标代码的时间点，是目标方法执行之前执行，还是之后执行等。通知类型不同，切入时间不同。
通知的类型：前置通知,后置通知,异常通知,最终通知,环绕通知。
切入点定义切入的位置，通知定义切入的时间。

-   Weaving(织入).

是指把增强应用到目标对象来创建新的代理对象的过程。 spring 采用动态代理织入，而 AspectJ 采用编译期织入和类装载期织入。

> 切面的三个关键因素：
>
> 1、切面的功能--切面能干啥
>
> 2、切面的执行位置--使用Pointcut表示切面执行的位置
>
> 3、切面的执行时间--使用Advice表示时间，在目标方法之前还是之后执行。

##### 3.作用

- 简化代码：把方法中固定位置的重复的代码抽取出来，让被抽取的方法更专注于自己的核心功能，提高内聚性。
- 代码增强：把特定的功能封装到切面类中，看哪里有需要，就往上套，被套用了切面逻辑的方法就被切面给增强了。

#### 二、 基于注解的AOP

##### 1. 技术说明

![image.png](https://s2.loli.net/2023/10/07/qICv1JaRe7TZFKm.png)

- 动态代理（InvocationHandler）：JDK原生的实现方式，需要被代理的目标类必须实现接口。因为这个技术要求代理对象和目标对象实现同样的接口（兄弟两个拜把子模式）。
- cglib：通过继承被代理的目标类（认干爹模式）实现代理，所以不需要目标类实现接口。
- AspectJ：本质上是静态代理，将代理逻辑“织入”被代理的目标类编译得到的字节码文件，所以最终效果是动态的。weaver就是织入器。Spring只是借用了AspectJ中的注解。

##### 2，准备工作

- ① 添加依赖

在之前的基础上添加以下依赖即可:

```xml
<!-- spring-aspects会帮我们传递过来aspectjweaver -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>5.3.1</version>
</dependency>
```

- ② 准备被代理的资源

Calculator接口:

```java
public interface Calculator {
    int add(int i, int j);
    int sub(int i, int j);
    int mul(int i, int j);
    int div(int i, int j);
}
```

Calculator的实现类CalculatorPureImpl:

```java
@Component
public class CalculatorPureImpl implements Calculator {

    @Override
    public int add(int i, int j) {
        int result = i + j;
        System.out.println("方法内部 result = " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result = i - j;
        System.out.println("方法内部 result = " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i * j;
        System.out.println("方法内部 result = " + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        int result = i / j;
        System.out.println("方法内部 result = " + result);
        return result;
    }
}
```

##### 3，创建切片类并配置

```java
@Aspect //表示这个类是一个切面类@Aspect
@Component //注解保证这个切面类能够放入IOC容器@Component
public class LogAspect {


    @Before("execution(* cl.CalculatorPureImpl.* (..))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Logger-->前置通知，方法名：" + methodName + "，参数：" + args);
    }


    @After("execution(* cl.CalculatorPureImpl.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名：" + methodName);
    }


    @AfterReturning(value = "execution(* cl.CalculatorPureImpl.*(..))", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名：" + methodName + "，结 果：" + result);
    }


    @AfterThrowing(value = "execution(* cl.CalculatorPureImpl.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名：" + methodName + "，异常：" + ex);
    }


    @Around("execution(* cl.CalculatorPureImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            System.out.println("环绕通知-->目标对象方法执行之前");
            //目标对象（连接点）方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->目标对象方法返回值之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->目标对象方法出现异常时");
        } finally {
            System.out.println("环绕通知-->目标对象方法执行完毕");
        }
        return result;
    }
}
```

在spring配置类中的配置

```xml
<contex:component-scan base-package="xxx"/>
<aop:aspectj-autoproxy/>
```

##### 4，测试类

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AopTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void name() {
        int add = calculator.add(1, 2);
    }
}
```

> 再spring项目中用test需要导入spring-test依赖
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.3.1</version>
</dependency>
```

##### 4，各种通知

- 前置通知：使用@Before注解标识，在被代理的目标方法前执行
- 返回通知：使用@AfterReturning注解标识，在被代理的目标方法成功结束后执行（寿终正寝）
- 异常通知：使用@AfterThrowing注解标识，在被代理的目标方法异常结束后执行（死于非命）
- 后置通知：使用@After注解标识，在被代理的目标方法最终结束后执行（盖棺定论）
- 环绕通知：使用@Around注解标识，使用try...catch...finally结构围绕整个被代理的目标方法，包括上面四种通知对应的所有位置

各种通知的执行顺序:

> Spring版本5.2.x以前： 
>  - 前置通知 
>  - 目标操作 
>  - 后置通知 
>  - 返回通知或异常通知 
>
> Spring版本5.2.x以后： 
>  - 前置通知
>  - 目标操作
>  - 返回通知或异常通知 
>  - 后置通知

##### 5，切入表达式

![image.png](https://s2.loli.net/2023/10/07/jlVRoJeH7viky19.png)


- 包名部分
  - 用`*`号代替包的某一层,表示该层任意,如:`*.Hello`匹配`com.Hello`,不匹配`com.softeem.Hello`
  - 用`*..`表示包名任意,包的层次深度任意

- 类名部分
  - 用`*`表示类名任意
  - 可以用`*`代替类名的一部分,如:`*Service`匹配所有名称以`Service`结尾的类或接口

- 方法名部分
  - 用`*`表示方法名任意
  - 可以用`*`代替方法名的一部分,如:`*Operation`匹配所有方法名以`Operation`结尾的方法

- 方法参数列表部分
  - 用`(..)`表示参数列表任意
  - 用`(int,..)`表示参数列表以一个`int`类型的参数开头
  - 基本数据类型和对应的包装类型是不一样的,切入点表达式中使用 `int` 和实际方法中 `Integer` 是不匹配的

- 方法返回值部分
  - 如果想明确指定一个返回值类型,那么必须同时写明权限修饰符,如:`execution(public int _.._Service.* *(.., int))`
  - 只写返回值类型without修饰符是错误的,如:`execution(* int _.._Service.*(.., int))` 

##### 6，重用切入点表达式

① 声明

```java
@Pointcut("execution(* cl.CalculatorPureImpl.* (..))")
public void pointCut() {}
```

② 在用一个切面中使用

```java
@Before("pointCut()")
public void beforeMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    System.out.println("Logger-->前置通知，方法名：" + methodName + "，参数：" + args);
}
```

③ 在不用切面使用需要写全类名

```java
@Before("cl.LogAspect.pointCut()")
public void beforeMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    System.out.println("Logger-->前置通知，方法名：" + methodName + "，参数：" + args);
}
```

##### 7，切面优先级

相同目标方法上同时存在多个切面时，切面的优先级控制切面的内外嵌套顺序。 

- 优先级高的切面：外面
- 优先级低的切面：里面

使用@Order注解可以控制切面的优先级：
- @Order(较小的数)：优先级高
- @Order(较大的数)：优先级低

#### 三、 基于XML的AOP(了解)

```xml
<contex:component-scan base-package="cl"/>
<bean id="loggerAspect" class="cl.LoggerAspect"/>
<aop:config>
    <!--配置切面类-->
    <aop:aspect ref="loggerAspect">
        <aop:pointcut id="pointCut" expression="execution(* cl.CalculatorPureImpl.*(..))"/>
        <aop:before method="beforeMethod" pointcut-ref="pointCut"/>
        <aop:after method="afterMethod" pointcut-ref="pointCut"/>
        <aop:after-returning method="afterReturningMethod" returning="result" pointcut-ref="pointCut"/>
        <aop:after-throwing method="afterThrowingMethod" throwing="ex" pointcut-ref="pointCut"/>
        <aop:around method="aroundMethod" pointcut-ref="pointCut"/>
    </aop:aspect>
</aop:config>
```

```java
public class LoggerAspect {
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Logger-->前置通知，方法名：" + methodName + "，参数：" + args);
    }


    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名：" + methodName);
    }


    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名：" + methodName + "，结 果：" + result);
    }


    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名：" + methodName + "，异常：" + ex);
    }


    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            System.out.println("环绕通知-->目标对象方法执行之前");
            //目标对象（连接点）方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->目标对象方法返回值之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->目标对象方法出现异常时");
        } finally {
            System.out.println("环绕通知-->目标对象方法执行完毕");
        }
        return result;
    }
}
```

#### 四、 声明式事务

##### 1. JdbcTemplate

> Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库操作

① 加入依赖

```xml
<!-- 基于Maven依赖传递性，导入spring-context依赖即可导入当前所需所有jar包 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.1</version>
</dependency>

<!-- Spring 持久化层支持jar包 -->
<!-- Spring 在执行持久化层操作、与持久化层技术进行整合过程中，需要使用orm、jdbc、tx三个jar包 -->
<!-- 导入 orm 包就可以通过 Maven 的依赖传递性把其他两个也导入 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.3.1</version>
</dependency>

<!-- Spring 测试相关 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.3.1</version>
</dependency>

<!-- junit测试 -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>

<!-- MySQL驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.37</version>
</dependency>

<!-- 数据源 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.0.31</version>
</dependency>
```

②创建jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true
jdbc.username=root
jdbc.password=123456
```

③配置Spring的配置文件

```xml
<!-- 导入外部属性文件 -->
<contex:property-placeholder location="classpath:jdbc.properties"/>

<!-- 配置数据源 -->
<bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="${jdbc.url}"/>
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<!-- 配置 JdbcTemplate -->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <!-- 装配数据源 -->
    <property name="dataSource" ref="druidDataSource"/>
</bean>
```

④ 测试

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate(){
        String sql = "select * from t_book";
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        query.forEach(System.out::println);
    }
}
```

##### 2. 声明式事务

###### 2.1. 编程式事务

```java
Connection conn = ...;
try {
    // 开启事务：关闭事务的自动提交
    conn.setAutoCommit(false);
    // 核心操作

    // 提交事务
    conn.commit();

}catch(Exception e){
// 回滚事务
    conn.rollBack();
}finally{
    // 释放数据库连接
    conn.close();
}
```

编程式的实现方式存在缺陷：

- 细节没有被屏蔽：具体操作过程中，所有细节都需要程序员自己来完成，比较繁琐。
- 代码复用性不高：如果没有有效抽取出来，每次实现功能都需要自己编写代码，代码就没有得到复用。

###### 2.2. 声明式事务

既然事务控制的代码有规律可循，代码的结构基本是确定的，所以框架就可以将固定模式的代码抽取出  来，进行相关的封装。
封装起来后，我们只需要在配置文件中进行简单的配置即可完成操作。
- 好处1：提高开发效率
- 好处2：消除了冗余的代码
- 好处3：框架会综合考虑相关领域中在实际开发环境下有可能遇到的各种问题，进行了健壮性、性 能等各个方面的优化

所以，我们可以总结下面两个概念：
- 编程式：自己写代码实现功能
- 声明式：通过配置让框架实现功能

##### 3. 基于注解的声明式事务

###### 3.1 准备工作
① 加入依赖

```xml
<!-- 基于Maven依赖传递性，导入spring-context依赖即可导入当前所需所有jar包 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.1</version>
</dependency>

<!-- Spring 持久化层支持jar包 -->
<!-- Spring 在执行持久化层操作、与持久化层技术进行整合过程中，需要使用orm、jdbc、tx三个
jar包 -->
<!-- 导入 orm 包就可以通过 Maven 的依赖传递性把其他两个也导入 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.3.1</version>
</dependency>

<!-- Spring 测试相关 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.3.1</version>
</dependency>

<!-- junit测试 -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>

<!-- MySQL驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.37</version>
</dependency>
<!-- 数据源 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.0.31</version>
</dependency>
```

②创建jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true
jdbc.username=root
jdbc.password=123456
```

③配置Spring的配置文件

```xml
<!-- 导入外部属性文件 -->
<contex:property-placeholder location="classpath:jdbc.properties"/>

<!-- 配置数据源 -->
<bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="${jdbc.url}"/>
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<!-- 配置 JdbcTemplate -->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <!-- 装配数据源 -->
    <property name="dataSource" ref="druidDataSource"/>
</bean>
```

④ 创建表
```sql
CREATE TABLE `t_book` (
`book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
`book_name` varchar(20) DEFAULT NULL COMMENT '图书名称',
`price` int(11) DEFAULT NULL COMMENT '价格',
`stock` int(10) unsigned DEFAULT NULL COMMENT '库存（无符号）', PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert	into `t_book`(`book_id`,`book_name`,`price`,`stock`) values (1,'斗破苍穹',80,100),(2,'斗罗大陆',50,100);

CREATE TABLE `t_user` (
`user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
`username` varchar(20) DEFAULT NULL COMMENT '用户名',
`balance` int(10) unsigned DEFAULT NULL COMMENT '余额（无符号）', PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert	into `t_user`(`user_id`,`username`,`balance`) values (1,'admin',50);
```

⑤ 创建组件

BookDao.java
```java
public interface BookDao {
    Integer getPriceByBookId(Integer bookId);
    void updateStock(Integer bookId);
    void updateBalance(Integer userId, Integer price);
}
```

BookDaoImpl.java
```java
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getPriceByBookId(Integer bookId) {
        String sql = "select price from t_book where book_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    @Override
    public void updateStock(Integer bookId) {
        String sql = "update t_book set stock = stock - 1 where book_id = ?";
        jdbcTemplate.update(sql, bookId);
    }

    @Override
    public void updateBalance(Integer userId, Integer price) {
        String sql = "update t_user set balance = balance - ? where user_id = ? ";
        jdbcTemplate.update(sql, price, userId);
    }
}
```


BookService.java
```java
public interface BookService {
    void buyBook(Integer bookId, Integer userId);
}
```


BookServiceImpl.java
```java
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void buyBook(Integer bookId, Integer userId){
        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.updateStock(bookId);
        //更新用户的余额
        bookDao.updateBalance(userId, price);
    }
}
```

BookController.java
```java
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void buyBook(Integer bookId, Integer userId){
        bookService.buyBook(bookId, userId);
    }
}
```

###### 3.2 测试无事务情况

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookTest {
    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook(){
        bookController.buyBook(1, 1);
    }
}
```

②模拟场景

用户购买图书，先查询图书的价格，再更新图书的库存和用户的余额假设用户id为1的用户，购买id为1的图书

用户余额为50，而图书价格为80

购买图书之后，用户的余额为-30，数据库中余额字段设置了无符号，因此无法将-30插入到余额字段此时执行sql语句会抛出SQLException

③观察结果

因为没有添加事务，图书的库存更新了，但是用户的余额没有更新

显然这样的结果是错误的，购买图书是一个完整的功能，更新库存和更新余额要么都成功要么都失败

###### 3.3 测试无事务情况

①添加事务配置

在Spring的配置文件中添加配置：

```xml
<!--事务管理器对象-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="druidDataSource"/>
</bean>

<!--
开启事务的注解驱动
通过注解@Transactional所标识的方法或标识的类中所有的方法，都会被事务管理器管理事务-->
<!--  transaction-manager属性的默认值是transactionManager，如果事务管理器bean的id正好就是这个默认值，则可以省略这个属性 -->
<tx:annotation-driven transaction-manager="transactionManager" />
```

②添加事务注解

因为service层表示业务逻辑层，一个方法表示一个完成的功能，因此处理事务一般在service层处理在BookServiceImpl的buybook()添加注解@Transactional

③观察结果

由于使用了Spring的声明式事务，更新库存和更新余额都没有执行

###### 3.4 @Transactional注解标识的位置

@Transactional标识在方法上，咋只会影响该方法

@Transactional标识的类上，咋会影响类中所有的方法

###### 3.5 事务属性：只读

对一个查询操作来说，如果我们把它设置成只读，就能够明确告诉数据库，这个操作不涉及写操作。这样数据库就能够针对查询操作来进行优化。

```java
@Transactional(readOnly = true)
public void buyBook(Integer bookId, Integer userId) {
//查询图书的价格
Integer price = bookDao.getPriceByBookId(bookId);
//更新图书的库存bookDao.updateStock(bookId);
//更新用户的余额bookDao.updateBalance(userId, price);
//System.out.println(1/0);
}
```

对增删改操作设置只读会抛出下面异常：

Caused by: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed

###### 3.6 事务属性：超时

```java
@Transactional(timeout = 3)
```

###### 3.7 事务属性：回滚策略

声明式事务默认只针对运行时异常回滚，编译时异常不回滚。可以通过@Transactional中相关属性设置回滚策略

- rollbackFor属性：需要设置一个Class类型的对象
- rollbackForClassName属性：需要设置一个字符串类型的全类名
- noRollbackFor属性：需要设置一个Class类型的对象
- rollbackFor属性：需要设置一个字符串类型的全类名

```java
@Transactional(noRollbackFor = ArithmeticException.class)
```

###### 3.8 事务属性：隔离级别

数据库系统必须具有隔离并发运行各个事务的能力，使它们不会相互影响，避免各种并发问题。一个事务与其他事务隔离的程度称为隔离级别。SQL标准中规定了多种事务隔离级别，不同隔离级别对应不同的干扰程度，隔离级别越高，数据一致性就越好，但并发性越弱。
隔离级别一共有四种：
- 读未提交：READ UNCOMMITTED 允许Transaction01读取Transaction02未提交的修改。
- 读已提交：READ COMMITTED 要求Transaction01只能读取Transaction02已提交的修改。
- 可重复读：REPEATABLE READ 确保Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其它事务对这个字段进行更新。
- 串行化：SERIALIZABLE 确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其它事务对这个表进行添加、更新、删除操作。可以避免任何并发问题，但性能十分低下。

```java
@Transactional(isolation = Isolation.DEFAULT)//使用数据库默认的隔离级别
@Transactional(isolation = Isolation.READ_UNCOMMITTED)//读未提交
@Transactional(isolation = Isolation.READ_COMMITTED)//读已提交@Transactional(isolation = Isolation.REPEATABLE_READ)//可重复读
@Transactional(isolation = Isolation.SERIALIZABLE)//串行化
```

###### 3.9 事务属性：事务传播行为

事务属性中的事务传播行为定义了在一个方法被调用时，如何处理已存在的事务以及如何传播新事务。事务传播行为是控制事务管理的一项重要属性，用于确保事务的一致性和隔离性。

在Spring框架中，可以使用`@Transactional`注解的`propagation`属性来指定事务传播行为。以下是一些常见的事务传播行为：

1. **REQUIRED（默认值）**：如果当前存在事务，则加入该事务，如果不存在事务，则创建一个新的事务。这是最常用的传播行为，确保方法始终在一个事务中执行。

2. **SUPPORTS**：如果当前存在事务，则加入该事务，如果不存在事务，则以非事务的方式执行。适用于读取操作，不需要强制执行在事务中。

3. **MANDATORY**：要求当前存在事务，如果没有事务，则抛出异常。通常用于需要强制在事务中执行的方法。

4. **REQUIRES_NEW**：无论当前是否存在事务，都会创建一个新的事务，并在方法执行完毕后提交。如果当前存在事务，则挂起当前事务，执行新事务，然后恢复之前的事务。

5. **NOT_SUPPORTED**：以非事务方式执行方法，如果当前存在事务，则挂起该事务。

6. **NEVER**：以非事务方式执行方法，如果当前存在事务，则抛出异常。

7. **NESTED**：如果当前存在事务，则创建一个嵌套事务，它是当前事务的一部分。嵌套事务可以独立提交或回滚，而不会影响外部事务。

以下是示例代码，演示了如何在Spring中使用`@Transactional`注解指定事务传播行为：

```java
@Transactional(propagation = Propagation.REQUIRED) // 默认的传播行为
public void methodWithRequiredPropagation() {
    // 代码逻辑
}

@Transactional(propagation = Propagation.REQUIRES_NEW) // 新建事务
public void methodWithRequiresNewPropagation() {
    // 代码逻辑
}

@Transactional(propagation = Propagation.SUPPORTS) // 支持当前事务，如果不存在则以非事务方式执行
public void methodWithSupportsPropagation() {
    // 代码逻辑
}
```

事务传播行为的选择取决于业务需求和数据一致性要求，不同的传播行为可以用来解决不同的并发问题。

##### 4. 基于XML的声明式事务

```xml
<aop:config>
<!-- 配置事务通知和切入点表达式 -->
    <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.softeem.spring.tx.xml.service.impl.*.*(..))"/>
</aop:config>
<!-- tx:advice标签：配置事务通知 -->
<!-- id属性：给事务通知标签设置唯一标识，便于引用 -->
<!-- transaction-manager属性：关联事务管理器 -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
  <tx:attributes>
    <!-- tx:method标签：配置具体的事务方法 -->
    <!-- name属性：指定方法名，可以使用星号代表多个字符 -->
    <tx:method name="get*" read-only="true"/>
    <tx:method name="query*" read-only="true"/>
    <tx:method name="find*" read-only="true"/>
    
    <!-- read-only属性：设置只读属性 -->
    <!-- rollback-for属性：设置回滚的异常 -->
    <!-- no-rollback-for属性：设置不回滚的异常 -->
    <!-- isolation属性：设置事务的隔离级别 -->
    <!-- timeout属性：设置事务的超时属性 -->
    <!-- propagation属性：设置事务的传播行为 -->
    <tx:method name="save*" read-only="false" rollback-for="java.lang.Exception" propagation="REQUIRES_NEW"/>
    <tx:method name="update*" read-only="false" rollback-for="java.lang.Exception" propagation="REQUIRES_NEW"/>
    <tx:method name="delete*" read-only="false" rollback-for="java.lang.Exception" propagation="REQUIRES_NEW"/>
  </tx:attributes>
</tx:advice>
```

> 注意：基于xml实现的声明式事务，必须引入aspectJ的依赖

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>5.3.1</version>
</dependency>
```