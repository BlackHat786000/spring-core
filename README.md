Spring bean life cycle
-----------------------

1. Instantiation - first step is to create instance of a bean. Bean configuration metadata (XML, JavaConfig, or annotations) is used to determine which classes to instantiate.

2. Populating properties and dependencies - once bean instance is created, spring sets its properties and dependencies using dependency injection (constructor/setter/field injections based on bean configuration metadata). If constructor and setter injection both used at same time, setter injection overrides because it is executed later.

3. BeanNameAware and BeanFactoryAware (optional) - If the bean implements the BeanNameAware or BeanFactoryAware interfaces, Spring sets the bean's name and reference to the BeanFactory, respectively, by calling the corresponding methods.

4. @PostConstruct (first priority) / InitializingBean.afterPropertiesSet() method (optional) - If the bean implements the InitializingBean interface or defines a method annotated with @PostConstruct, Spring calls the appropriate method after all properties have been set. This allows the bean to perform any initialization tasks.

5. Custom initialization method (optional) - If a custom initialization method is defined in the bean's configuration (e.g., XML), Spring calls that method after the InitializingBean/@PostConstruct methods (if present).

6. BeanPostProcessor (optional) - Spring allows you to define custom BeanPostProcessor implementations. These are executed before and after initializing each bean. They provide the ability to perform additional processing on beans. The BeanPostProcessor interface has two methods that you need to implement: postProcessBeforeInitialization(Object bean, String beanName) and postProcessAfterInitialization(Object bean, String beanName)

7. Bean is ready for use - At this point, the bean is fully initialized and ready to be used within the application.

8. Destruction - When the Spring container is shut down or when the bean is removed from the container explicitly, the bean's life cycle enters the destruction phase.

9. @PreDestroy (first priority) / DisposableBean.destroy() method (optional) - If the bean implements the DisposableBean interface or defines a method annotated with @PreDestroy, Spring calls the appropriate method before the bean is destroyed. This allows the bean to perform any necessary cleanup tasks.

10. Custom destruction method (optional) - If a custom destruction method is defined in the bean's configuration (e.g., XML), Spring calls that method after the @PreDestroy/DisposableBean methods (if present).

Note - The BeanPostProcessor is a global post processing mechanism that allows you to perform custom logic before and after the initialization phase of each bean, while @PostConstruct/InitializingBean is a convenient way to define initialization behavior specifically for the bean.




Detailed order of execution of methods
---------------------------------------

1. Within IoC container, a Spring bean is created using class constructor.
2. Now the dependency injection is performed using setter method.
3. Once the dependency injection is completed, BeanNameAware.setBeanName() is called. It sets the name of bean in the bean factory that created this bean.
4. Now < code>BeanClassLoaderAware.setBeanClassLoader() is called that supplies the bean class loader to a bean instance.
5. Now < code>BeanFactoryAware.setBeanFactory() is called that provides the owning factory to a bean instance.
6. Now the IoC container calls BeanPostProcessor.postProcessBeforeInitialization on the bean. Using this method a wrapper can be applied on original bean.
7. Now the method annotated with @PostConstruct is called.
8. After @PostConstruct, the method InitializingBean.afterPropertiesSet() is called.
9. Now the method specified by init-method attribute of bean in XML configuration is called.
10. And then BeanPostProcessor.postProcessAfterInitialization() is called. It can also be used to apply wrapper on original bean.
11. Now the bean instance is ready to be used. Perform the task using the bean.
12. Now when the ApplicationContext shuts down such as by using registerShutdownHook() then the method annotated with @PreDestroy is called.
13. After that DisposableBean.destroy() method is called on the bean.
14. Now the method specified by destroy-method attribute of bean in XML configuration is called.
15. Before garbage collection, finalize() method of Object is called.
