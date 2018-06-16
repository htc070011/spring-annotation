### JUnit quick-start

- 在pom文件中导入junit相关依赖
- 使用@Test注解修饰待测试方法
- 可以使用@Before注解修饰每次测试都要执行的方法

### spring注解驱动开发
---
#### @Configuration与@Bean注解

+ 首先在pom文件中导入spring-context相关依赖
+ 创建配置类MainConfig，并用@Configuration标签进行标记
+ 在配置类中，通过给方法添加@Bean注解的方式声明注册bean，bean的class为返回值类型，bean的id默认为方法名，可以通过@Bean的value属性指定id

---
#### @ComponentScan注解自动扫描

1. @ComponentScan自动扫描

+ 使用@ComponentScan标记配置类，通过该注解的value属性指定包名

- 使用@Controller，@Service，@Repository注解标记相应类

- 注入的组件名默认为类名第一个字母小写

2. 指定扫描规则

ComponentScan的excludeFilters属性指定不进行扫描的类，includeFilters属性指定只进行扫描的类（使用时需要将@ComponentScan的default属性置为false），上述两个属性的值为@Filters注解数组

- 通过@Filters注解的type属性配置过滤方式
- 通过@Filters注解的classes属性配置实现过滤方式的类

3. @ComponentScans注解为@ComponentScan注解的数组

---
#### 自定义@Filter规则

通过@Filters的FilterType属性配置过滤方式

1. ASSIGNABLE_TYPE：直接指定相应class
2. ANNOTATION：通过注解方式
3. CUSTOM: 自定义过滤方式

+ 需要实现TypeFilter接口，实现其```boolean match(MetaDataReader metaDataReader, MetaDataReaderFacotry)```方法。其中MetaDataReader对象包含扫描类的基本信息（修饰注解，类名等），MetaDataReaderFacotry对象包含其他所有类的基本信息

---
#### 组件作用域

组件作用域包含singleton，prototype，request（表示在同一个request中只创建一个bean）和session（表示在同一个session中只创建一个bean），通过@Scope注解进行配置。

+ singleton作用域修饰的bean对象在整个容器中只有一个，默认随容器的创建而加载，可以通过@Lazy注解将其配置为在第一次调用getBean（）方法时加载
+ prototype作用域修饰的bean在容器中存在多个对象，且默认为懒加载

---
#### @Conditional注解

@Conditional注解可以对Config类中的bean进行有条件的注册，该注解需要实现Condition接口中的```matches```方法，该方法传入两个参数

+ ConditionContext对象，可以用来获取beanFactory，BeanDefinitionRegistry（bean定义的注册类），classloader，environment（当前环境，该环境可以用来获得虚拟机操作系统）
+ AnnotatedTypeMetadata对象，可以获得修饰该配置类的注解
+ @Conditional注解也可以修饰类，当满足条件时注册该配置类下的所有用@Bean修饰的bean

---
#### 注册组件的常用方式

1. 通过@Bean注解------>适用于注册第三方包实现的类

2. 通过@ComponentScan+组件注解的方式----->适用于注册自己实现的类

3. 通过@Import注解快速导入，value属性为class类型，id默认为全类名
   + @Import注解中value属性也可以是实现了ImportSelector接口的class类型，实现```String[] selectImports(AnnotationMetdata annotationMetadata)```方法，该方法以字符串的形式直接返回需要注册的组件的全限定名，其中AnnotationMetdata对象可以获得修饰该配置类的其他注解信息，**注意返回值不能为null**

   + @Import注解中value属性也可以是实现ImportBeanDefinitionRegistrar接口的类，需要实现接口

     ```void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry)```

     其中AnnotationMetadata 可以获得修饰该类的其他注解信息，可以通过BeanDefinitionRegistry 的registerBeanDefinition方法手动注册bean，该方法需要传入bean的id以及bean的定义信息，定义信息由实现了BeanDefinition接口的对象进行封装，可以指定类名，Scope等属性。

4. 通过实现FactoryBean接口进行注册

  + 实现FactoryBean的三个接口```T getObject()```,```Class<?> getObjectType()```,```boolean isSingleton()```。
  + 通过@Bean的方式注册工厂Bean，通过applicationContext.getBean("bean id")方法将得到getObject()返回的对象类型
  + 如果想在容器中注册工厂Bean，需要在getBean("bean id")时在id名前加上"&"。

---
#### Bean生命周期

1. 定义初始化和销毁逻辑
  + 在定义bean时指定init()方法与destory()方法
      + 通过在@Bean注解中指定initMethod与destoryMethod属性。
      + 对于singleton的bean，init()方法在对象创建完成并赋值被调用，destory()方法在容器关闭时被调用
      + 对于prototype的bean，init()方法在getBean()完成对象创建和赋值后被调用，容器不对destory()进行管理
  + 通过实现spring提供接口的方式实现初始化和销毁逻辑
      + 让bean实现```InitializingBean```的接口方法
      ```void afterPropertiesSet()```
      定义bean的初始化逻辑，该方法在bean的属性赋值完成后执行
      + 通过让bean实现```DisposableBean```的接口方法
      ```void destroy()```
      定义bean的销毁逻辑，该方法在容器关闭时调用
  + 通过JSR250提供的注解修饰bean中实现逻辑的方法
      + 通过@PostConstruct注解修饰初始化逻辑方法，该方法在bean构造与赋值完成后执行
      + 通过@PreDestory注解修饰销毁逻辑方法，该方法在容器移除bean时被调用

2. 在初始化方法前后处理bean
  这里的初始化方法是指上述实现初始化逻辑的方法,例如被@PostConstruct修饰，实现afterPropertiesSet()，bean中的init()方法
  + 通过实现BeanPostProcessor的
  ```public Object postProcessBeforeInitialization(Object o, String s)```接口，
  该接口用来实现在初始化方法前需要处理的逻辑，Object o是创建并完成赋值后的对象，s为该对象在容器中的名字，返回值为经过处理的对象
  + 通过实现BeanPostProcessor的
  ```public Object postProcessAfterInitialization(Object o, String s) ```接口，
  该接口用来实现在初始化方法后需要处理的逻辑，该方法在初始化方法结束后调用

3. Bean生命周期简要总结
  主要执行流程如下：

  ```java
  a. populateBean()//对象属性赋值
  
  b. initializeBean()//执行初始化逻辑
  
  c. applyBeanPostProcessorsBeforeInitialization()
  
  d. invokeInitMethods()
  
  e. applyBeanPostProcessorsAfterInitialization()
  ```

4. BeanPostProcessor在spring中的应用

	+ ```AplicationContextAware```
	+ ```BeanValidate```
	+ ```@PostConstructor```
	+ ```@Autowired```
---
####Bean属性赋值
1. 对基本数据类型进行赋值@Value

  + 直接赋值
  + \#{}
  + ${}取环境变量里面的值

  + 首先定义.properties
  + 在配置类中通过注解@PropertySource读取配置文件， 保存在环境变量中，可以通过enviroment的getProperty获取配置文件中的
  + @PropertySources可以指定多个@PropertySource
  + 在**配置类**中也可以使用@Value注解，可以在配置类中声明属性并注入参数，也可以为每个注入方法传递参数

2. 使用`@Autowired`进行组件的自动装配
  + `@Autowired`默认根据类型在容器中寻找相应的组件进行注入
  + 如果有多个相同类型组件，再以`@Autowired`修饰的变量名作为组件id进行匹配并注入
  + 也可以通过`@Qulifier`注解修饰带装配对象的方式，指定待装配组件的id
  + 如果容器中不存在相应组件会报错，因为默认属性一定需要被赋值，可以在`@Autowired`中将required属性更改默认设置为false
  + 可以通过@Primary注解指定默认首选组件，该功能会被@Qulifier覆盖
3. 使用@Resource与@Inject注解进行自动装配
  + @Resource是java中的注解，不能与@Primary与@Qulifier配合使用，也不支持required属性
  + @Inject需要导入相关依赖，与`@Autowired`注解相同，**但不支持required属性**
4. `@Autowired`底层通过`AutowiredAnnotationBeanPostProcessor`实现
5. 使用`@Autowired`修饰构造器，参数，方法
  + ```@Autowired```修饰方法时，容器在创建该组件时会调用该方法，该方法中使用的参数从容器中获得
  + 同理可以用来修饰有参构造器与方法参数，都是从容器中进行加载
  + 如果仅存在一个有参构造器，**@Autowired注解可以省略**
  + 在使用@Bean进行注册组件时，可以在@Bean标注的方法上使用`@Autowired`进行注入
6. 向自定义组件注入spring组件
  + 通过让自定义组件实现相应aware接口的方式进行组件注入
  + BeanNameAware，可以得到该组件在容器中的id
  + ```EmbeddedValueResolverAware```，可以得到解析器```StringValueResolver```用来解析#{}, ${}等表达式
  + 上述Aware的子接口功能底层都是通过`ApplicationContextAwareProcessor`类实现的
  + **主配置类**也可以实现上述接口
7. @Profile选择性加载组件
  + 使用@Profile标注组件，@Profile(value="环境")
    + 可以通过配置虚拟机参数-Dspring.profile.active进行切换
    + 使用代码的方式，手动加载`applicationContext`，过程如下所示
    ```java
    //调用applicationContext无参构造器
    	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    //配置环境变量
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles("dev");
    //注册配置文件
        applicationContext.register(MainConfiOfProfile.class);
    //刷新容器
        applicationContext.refresh();
    ```
  + @Profile也可以标注配置类，表示当环境满足要求时才加载相应配置类
  + @Conditional也可以实现选择性注册，**springboot中大量使用**
---
#### AOP使用
1. AOP使用流程
  + 导入aop相关依赖AspectJ
  + 将切面类与待切类注入到容器
  + @Aspect标注切面类
  + @Before，@After，@AfterReturning，@AfterThrowing，@Around标注对应方法
    + 每个方法都可以传入JoinPoint对象用来获取切点信息
    + @AfterReturning可以通过属性值returning获得返回值
    + @AfterThrowing可以通过属性值throwing获得异常对象
  + 可以先使用@Pointcut(value="execution()")声明修饰一个pointcut()方法，然后用@Pointcut()修饰切面方法，其中value值为"方法名()"
  + 也可以通过注解的形式
    + 首先定义注解
    + 然后用该注解修饰待切入方法
    + 在@Pointcut("@annotation()")中写注解全类名
  + 在主配置类上声明@EnableAspectJAutoProxy实现自动代理
---
2. AOP底层实现流程

    AOP底层实现需要掌握以下四个过程

    + 首先是开启AOP功能的过程。使用`@EnableAspectJAutoProxy`注解，通过实现`ImportBeanDefinitionRegistrar`接口的方式，向容器中注册`AnnotationAwareAspectJAutoProxyCreator`组件，该组件的主要功能在抽象类`AbstractAutoProxyCreator`中实现。该抽象类实现了`SmartInstantiationAwareBeanPostProcessor`接口，用于在bean初始化时自动创建待增强对象的代理对象
    + 接下来是`AbstractAutoProxyCreator`的创建过程。该过程在容器的`refresh()`方法中的`registerBeanPostProcessors(beanFactory)`进行，容器将该过程交由创建代理`PostProcessorRegistrationDelegate`，大体流程是按照优先级遍历所有已注册的`PostProcessor`，然后分别将其创建，该创建过程同`bean`的创建过程
    + 再然后是`AbstractAutoProxyCreator`创建代理对象的过程。通过`applyBeanPostProcessorsAfterInitialization`方法，在每个待增强bean创建完成后，现根据不同的增强类型将增强方法适配成相应的interceptor方法，然后通过代理工厂的方式创建代理对象，该过程可以通过目标对象是否实现接口自动选择jdk动态代理或cglib动态代理。
    + 最后是调用目标方法过程。该过程会通过代理对象`CglibAopProxy`的静态内部类`DynamicAdvisedInterceptor`得到`ReflectiveMethodInvocation`对象，由其调用增强方法对象`interceptorOrInterceptionAdvice`的proceed方法，每个增强方法对象在调用时都传入`ReflectiveMethodInvocation`引用，以实现链式的递归调用，完成对目标方法的增强。
---
#### 声明式事务
1. 声明式事务使用流程

  + 首先在pom文件中添加数据源，驱动，jdbc的相关依赖
  + 在容器中注入`DataSource`, `JdbcTemplate`以及`DataSourceTransactionManager`
  + 在配置类上使用注解`@EnableTransactionManagement`开启事务管理功能
  + 在需要事务控制的方法上使用注解`@Transactional`进行修饰
2. 声明式事务底层实现流程
  从`@EnableTransactionManagement`注解出发，该注解向容器中注册了两个组件`AutoProxyRegistrar`与`ProxyTransactionManagementConfiguration`
  + `AutoProxyRegistrar`类比于AOP中的`AspectJAutoProxyRegistrar`，用于向容器中注册`InfrastructureAdvisorAutoProxyCreator`组件，该组件功能类比于AOP中的`AnnotationAwareAspectJAutoProxyCreator`

    需要说明的是，在上述两个Creator的抽象父类中，`AbstractAdvisorAutoProxyCreator`用于操作代理对象中所需的**增强方法**，而`AbstractAutoProxyCreator`中包含**创建代理对象整个流程**的方法

  + `ProxyTransactionManagementConfiguration`是一个配置类，他向容器中注册了以下三个组件

    + `BeanFactoryTransactionAttributeSourceAdvisor`
      用于创建增强方法的工厂类，该工厂类需要`TransactionAttributeSource`
    + `TransactionAttributeSource`
      该组件中包含多种解析器，用于解析事务方法的信息，包括事务方法注解中的信息等
    + `TransactionInterceptor`
      用于将`TransactionManagement`的回滚，提交等方法封装在`Interceptor`中

  + 类比于AOP可以发现，`ProxyTransactionManagementConfiguration`主要作用是**构建`Advisor`对象**。

    事务的处理实际上也是通过切面编程实现的，只不过这里的增强方法都是回滚、提交等操作，所以在事务中不需要自定义增强方法，而是将`TransactionManagement`提供的方法直接封装进行使用。
---
#### BeanFactoryPostProcessor
1. 自定义组件通过实现`BeanFactoryPostProcessor`的接口方法`postProcessBeanFactory()`实现对BeanFactory的后置处理
2. 底层实现过程
    BeanFactoryPostProcessor方法的调用时机是在**所有bean的定义都已加入到beanFactory中**，但**尚未对bean进行实例化**

    + BeanFactoryPostProcessor的底层实现流程与BeanPostProcessor类似

      + 容器调用`invokeBeanFactoryPostProcessors()`通过代理对象PostProcessorRegistrationDelegate完成BeanFactoryPostProcessor组件的创建
      + PostProcessorRegistrationDelegate按照优先级遍历beanFactory中的所有定义的BeanFactoryPostProcessor接口完成对象实例化，并将其加入到容器中
      + 随后调用BeanFactoryPostProcessor的接口方法
      

---
#### BeanDefinitionRegistryPostProcessor

1. 使用流程
	+ 将自定义组件监听组件注册到容器
	
	+ BeanDefinitionRegistryPostProcessor继承自BeanFactoryPostProcessor接口，自定义组件通过实现 BeanDefinitionRegistryPostProcessor接口的`postProcessBeanDefinitionRegistry()`与 `postProcessBeanFactory()`方法实现对BeanDefinition的后置处理。

2. 底层实现过程
	+ BeanDefinitionRegistryPostProcessor的调用是在bean的定义尚未加载进beanFactory时

	+ 具体时机是在PostProcessorRegistrationDelegate的`invokeBeanFactoryPostProcessors()`方法中，**在处理BeanFactoryPostProcessor之前**进行方法调用，整个流程与BeanFactoryPostProcessor相类似
	
	+ BeanDefinitionRegistryPostProcessor方法的 `postProcessBeanFactory()`**先于**`BeanFactoryPostProcessor`接口的`postProcessBeanFactory()`被调用

---

#### ApplicationListener

1. 使用流程
	+ 将自定义组件监听组件注册到容器
	
	+ 通过实现ApplicationListener接口的方式监听到容器中发布的事件

	+ 通过applicationContext的`publishEvent()`发布事件

2. 底层原理

	+ 事件发布
		+ applicationContext通过`publishEvent()`方法，将发布事件交由SimpleApplicationEventMulticaster多播器进行处理

		+ 在SimpleApplicationEventMulticaster中，存有注册在容器中的所有监听器，发布事件后，多播器会遍历所有监听器，调用其注册的监听方法，即实现的接口方法。

	+ 多播器注册
		+ 在容器初始化过程中，容器会调用`initApplicationEventMulticaster()`方法注册多播器

			+ 如果beanFactory中存在多播器,则直接返回
			+ 如果beanFactory中不存在多播器，则会创建一个多播器并添加到beanFactory中

	+ 监听器注册
		+ 在容器初始化过程中，容器**会在多播器注册完成后**，调用`registerListeners()`方法**注册beanFactory中注册的监听器**，并将其加入到所有多播器中 

---

#### @EventListener SmartInitializingSingleton

还可以通过@EventListener注解将任意方法注册为监听方法

1. 使用流程
	+ 使用@EventListener标注目标方法，并在注解中指定监听事件的类型

	+ 将事件参数传入目标方法以备使用

2. 底层原理

	+ @EventListener注解通过EventListenerMethodProcessor实现监听注册功能，该组件实现了SmartInitializingSingleton接口

	+ 实现了SmartInitializingSingleton接口组件将在该组件创建完成后调用`afterSingletonsInstantiated()`

	+ 对于EventListenerMethodProcessor组件，在其完成创建后，会在`afterSingletonsInstantiated()`中遍历容器中所有组件，看其是否有方法被@EventListener标注，若有，则将其封装为applicationListener对象注册到容器

---







































