# Spring 学习

#### IOC入门案例

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

#### 依赖注入之setter注入

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

#### 依赖注入之构造器注入

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


#### 类型属性赋值

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

#### 为集合类型属性赋值

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
#### 特殊值处理

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

#### bean的生命周期

- bean对象创建（调用无参构造器） 给bean对象设置属性
- bean对象初始化之前操作（由bean的前置处理器负责） 
- bean对象初始化（需在配置bean时指定初始化方法）
- bean对象初始化之后操作（由bean的后置处理器负责） 
- bean对象就绪可以使用
- bean对象销毁（需在配置bean时指定销毁方法） IOC容器关闭



---
#### `<bean>`标签属性

##### 1. scope("singleton|prototype")

- singleton: 单例模式(默认)
- prototype: 原型模式

| 取值            | 含义                      | 创建对象时机  |
|:--------------|-------------------------|---------|
| singleton(默认) | 在IOC容器中，这个bean的对象始终为单实例 | IOC初始化时 |
| prototype     | 这个bean在IOC容器中有多个实例      | 获得bean时 |

如果是在WebApplicationContext环境中还会有另外两个域(不常用)

- request 一次请求有效
- session 一次会话有效













