# XML Configuration  

*�� ������Ʈ�� ������ �����ӿ�ũ�� XML ������� �����ϴ� ���� �����մϴ�.*  


## 1. ��� ����  
### 1.1 DD(Deployment Descriptor)  
������ ���ø����̼ǿ����� `web.xml`�� ���ؼ� `���� ������`�� �����մϴ�. ���� �����ڴ� ���ø����̼� ���ؽ�Ʈ �� ����, ���͵ ���� ������ �ϵ��� �Ǿ� �ֽ��ϴ�.

### 1.2 Spring IoC Container  
`root-context.xml`�� ���ø����̼� ���ؽ�Ʈ��� �մϴ�. ���ø����̼� ���ؽ�Ʈ�� `�� ���丮�� Ȯ���� IoC �����̳�`�̱� ������ ���� ��� �� �����ϰ� �������� �����ϴ� ���� �ΰ� ���񽺸� �߰������� �����Ѵ�.

### 1.3 Bean
�������� IoC�� �����ϴ� `POJO(Plain Old Java Object)`�Դϴ�. ���� �ؾ��� �κ��� ������ ���ø����̼ǿ��� ����� ��� ������Ʈ�� ���� ���� �ƴմϴ�. 

### 1.4 DI(Dependency Injection)
��Ȯ�� ���� �ƴ����� `DI`�� `������ ���Ա��`�̶�� �θ��ڽ��ϴ�. ������ ���ø����̼ǿ����� IoC�� �����ϴ� ���� �ش� Ŭ�������� �����ͼ� ����� �� �ֵ��� ������ ���Ա���� �����մϴ�. ����, `������ ���Ա���� ��������`�� ��� ������� ������ ��ϵǾ� �־�� �Ѵٴ� ���Դϴ�.

## 2. ��Ÿ���� ����(Configuration)  

![architecture](assets/images/architecture.PNG)

### 2.1 web.xml  
`���� ������`�� ���ؽ�Ʈ �� ���� �׸��� ���͵��� �����մϴ�.

- ContextLoaderListener  
��Ʈ ���ø����̼� ���ؽ�Ʈ�� ����� �ʱ�ȭ�ϰ�, ���ø����̼ǰ� �Բ� ���ؽ�Ʈ�z �����Ű�� �̺�Ʈ�� ó���ϴ� `������`�ν� ���ø����̼� ���ؽ�Ʈ�� �����մϴ�. ����, `root-context.xml` ���� �ټ��� ���ø����̼� ���ؽ�Ʈ ������ �����Ͽ� ������ ���� �ֽ��ϴ�. ����, ���ø����̼��� �Ը� Ŀ���� �����ؾ��� ���� �������ٸ� �̷��� `���ø����̼� ���ؽ�Ʈ�� ��⺰�� ������ ����`�ϵ��� �սô�.   
```
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
```

- DispatcherServlet  
������ �� MVC���� �����ϴ� `����Ʈ ��Ʈ�ѷ� ����`�Դϴ�. ����, ���� �̸��� �ٸ��� �������ָ� ���ø����̼ǿ� �������� DispatcherServlet�� ����� ���� �ֽ��ϴ�. �� `DispatcherServlet`�� ������ �ʱ�ȭ�� �� ��Ʈ ���ø����̼� ���ؽ�Ʈ�� ã�Ƽ� `�ڽ��� �θ� ���ؽ�Ʈ�� ���`�մϴ�. ��Ʈ ���ø����̼� ���ؽ�Ʈ ó�� ������ ���� ������ ���� ������, `�ظ��ؼ��� �׷����� �����ϴ�.`  
```
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
		
	</servlet>
		
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
```

- Filter  
������ ����Ǵ� �پ��� `����`�� ������ �� �ֽ��ϴ�. ����, ���Ͱ� � ������ �ϴ��� �𸣰ڴٰ� �Ͻô� ���� `JSP�� ������ ����`�� ���� �κ��� �ٽ� �����Ͻñ� �ٶ��ϴ�. 
```
 	<filter>
	    <filter-name>encodingFilter</filter-name>
	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    <!-- ��� ���ڵ��� ������ ��ȯ�ϱ� ���ؼ��� forceEncoding�� true�� �����ϼ��� -->
	    <init-param>
	        <param-name>encoding</param-name>
	        <param-value>UTF-8</param-value>
	    </init-param>
	</filter>
	
	<filter-mapping>
	    <filter-name>encodingFilter</filter-name>
	    <url-pattern>/*</url-pattern>
	</filter-mapping>
```

### 2.2 root-context.xml  
���ø����̼� ���ؽ�Ʈ�� `���� ����ϰ� �����ϴ� �� ���丮`�Դϴ�. ������ ���鼭 ��� ������ ����ϴ����� ����غ�����!

- DataSource  
JDBC�� ���ؼ� DB�� ����Ϸ��� `DB Connection ������Ʈ`�� �ʿ��մϴ�. ��� `������ �׼������� ���Ǵ� �ʼ� ���ҽ�`�̱� ������ ������ ������ DataSource�� �ϳ��� ������ ������ ��ϵǾ������� �մϴ�. `org.apache.tomcat.jdbc.pool.DataSource`�� `DB Connection Pool` ����� ������ ������Ʈ�ν� `���������� �����ϴ� DataSource`�Դϴ�.
```
	<context:property-placeholder location="classpath:application.properties" /> 
	
	<bean id="dataSource" class="org.apache.tomcat.jdbc.pool.DataSource" >
		<property name="driverClassName" value="${jdbc.driver}"/>
		<property name="url" value="${jdbc.url}"/>
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
		<property name="connectionProperties" value="true" />
		<property name="testOnReturn" value="true" />
		<property name="testWhileIdle" value="true" />
		<property name="removeAbandonedTimeout" value="30" />
		<property name="removeAbandoned" value="true" />
		<property name="logAbandoned" value="false" />
		<property name="validationQuery" value="SELECT 1"/>
	</bean>
```

- TransactionManager  
```
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<tx:annotation-driven transaction-manager="transactionManager" />
```

### 2.3 servlet-context.xml  
���� ���ؽ�Ʈ�� `������ ���õ� ��Ÿ����`���� �����մϴ�. ���� �� �� ���丮 ���ҵ� �մϴ�.

- Resources  
```
	<resources mapping="/resources/**" location="/resources/" />
```

- ViewResolver  
��ġ ä�̳���? `������Ƽ`�� ������ �ǹ��ϴ���.. �ٷ� ������ ���Ա���Դϴ�. `JstlView`�� `viewClass`������ �ְ� �ֳ׿�  
As of Spring Framework 4.3, Velocity support has been deprecated due to six years without active maintenance of the Apache Velocity project  
```
	<beans:bean id="jstlViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
		<beans:property name="order" value="2"/>
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
```

- MultipartResolver  
```
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="maxUploadSize" value="${file.maxUploadSize}"></beans:property>
	</beans:bean>
```

## 3. ������ ���� ���(Dependency Injection)  
�׷� �߰������� ������̼��� ���ؼ� `������ ����ؼ� ����`�ϵ��� �ϴ� ����� `������ ���� ���`�� �ϵ��� �����ϴ� ����� �˾ƺ��ô�.

### 3.1 Bean Annotation  
������̼� ����� �� ��Ϲ���� �̿��ϱ� ���ؼ��� `<annotation-driven/>` �� ���ؼ� `������ ������̼� ������ Ȱ��ȭ`�ؾ� �մϴ�.
�� `�� ��ĳ��`�� �ش� ��Ű���� �νĵ� ��� Ŭ������ ������ ������ִ� ������ �ϰ� �˴ϴ�.  �ڼ��� ���ϸ� `��Ű���� Ž��`�ϴٰ� �Ʒ��� `������̼��� ����� Ŭ����`���� ������ `������ �ڵ������� ���`���ִ� ���� �˴ϴ�.

- @Component  
XML���� Bean�±׸� ���ؼ� ������ ����ߴٸ� �� ������̼��� Ŭ������ �����ؼ� �ش� Ŭ������ ������ ����ϴ� ���� �����մϴ�.
```

```

- @Service  
@Component�ʹ� ��������� ������ �ܼ��� �� ���� ���� �������� ���ȴٶ�� `������`�� �����ϴ�.
```

```

- @Repository  
`@Component�� @Service`�ʹ� �ٸ��� `SQLException`�� ���ؼ� `DataAccessException`���� ��ȯ�ϵ��� �����մϴ�.

```

```

- @Controller  
������ �� MVC���� �����ϴ� ������̼����� `������ �� ���� ���ؽ�Ʈ`�� ���� `�� ��û�� ó���ϴ� ��Ʈ�ѷ� ��`���� ��ϵ˴ϴ�. `@Controller`�� ������ ���캸�� �Ǹ� @Component�� ���ԵǾ� �ִ� ���� Ȯ���� �� �ֽ��ϴ�.  

```

```

### 3.2 Dependency Injection Annotation
������̼� ����� �� ��Ϲ���� �ִٸ� `������ ���� ����� �����ϴ� ������̼�`�� �����մϴ�. �� ������̼��� ���ؼ� ������ ���� ����� ����ϱ� ���ؼ��� `<context:annotation-config />`��� `�� ��ó����`�� ������־�� �մϴ�. �̿� ���� �� ��ó����� ���� ����ϴ� ������ �ƴϱ� ������ �̹� `��ϵ� ���� �����ؾ��մϴ�.`

- @Autowired  
�� ������̼��� `Ÿ�� ����� ����`�� ���� �õ��ϰ� �̸� ������� ������ �õ��մϴ�. �׸��� @Resource�ʹ� �ٸ��� `�����ڿ��� ����`�� �� �ֽ��ϴ�. �ٸ� ������ ���� �ϳ��� �����ڿ��� ������ �� �ִٴ� ���� �˰� �־�� �մϴ�.
```

```

- @Resource  
�� ������̼��� `�̸� ����� ����`�� ���� �õ��ϰ� Ÿ�� ������� ������ �õ��մϴ�.
```

```

- @Inject  
@Autowired�� �������� �����ϴ� ���� �������̼��̶��, @Inject�� JavaEE 6�� ǥ�� ���忡 ���ǵǱ� ������ �ٸ� �����ӿ�ũ���� ����� ���ɼ��� ���� ��� ����ϸ� �˴ϴ�.
```

```

## 4. ���� ���� ���α׷���(Aspect Oriented Programming)  
`AOP`�� `IoC/DI`�� ���Ҿ� `�������� ��ǥ ��ݱ��`���� �ϳ��Դϴ�. �������� ��� �߿��� ���� �����ϱ� ���� ������ ������ ���� ����̱� ������ ���� ����� ����帮�� ����ϴ�. AOP�� �����ؼ� �̿��ϴ� ��ǥ���� ����� `������ Ʈ�����`�Դϴ�. �ٷ� `@Transactional`����. �׷��� `Ʈ����� �Ŵ���`�� ���� �������� ���Թ��� �ʰ��� Ʈ����� Ŀ�԰� �ѹ��� �����ϵ��� �� �� �ְ� �˴ϴ�.

- ����� ������ Vol.1 6�� AOP���� �����ؼ� ���� �����սô�.

## 5. ������ ���� �� �� ��� ���  
������ ������ 3.0 �̻��� ������ ����ϱ� ������ XML����̳� �ڹ� �ڵ� ����̳ĸ� �������� �����ŵ� �˴ϴ�.
- Spring 2.5 : <bean>, Bean Scan 
- Spring 3.0 : <bean>, Bean Scan, Java Code  
- Spring 4.0 : <bean>, Bean Scan, Java Code  

## 6. Database ORM Framework  
������ JDBC�� �۽ý��Ͻ� �������� Ȱ��Ǵ� �پ��� ������ �׼��� ����� `ORM Framework`�� �����մϴ�.  �ܼ��ϰ� ������ JDBC�� �̿��ϴ� �� ���ٴ� `ORM Framework�� ���� Ȱ���ϴ� ���� �����ϴ�.`  
- ibatis, Mybatis (DataSourceTransactionManager)
- JPA, Hibernate (JpaTransactionManager)

*�������ʹ� �����е��� �����Դϴ�. ������ Ŭ����, ���� ����, ����Ͻ� ����, ��Ʈ�ѷ����� �����ؼ� ���� API�� �����ϰ� ����Ʈ ���带 ���������ν� �� ���ø����̼��� ���� �� �ֽ��ϴ�. ������ ������ �����ӿ�ũ���� �����ϴ� �پ��� ������Ʈ�� ���������ν� �������� ���ø����̼��� ���� �� ���׷��̵� �� �� �ֽ��ϴ�.*

---

## Java Configuration
������ 3.1 �������� Ȯ���ϰ� XML ����� ������ ���ø����̼� ������ �ڵ� ������� �����ϴ� ���� �����մϴ�. �� �κп� ���ؼ��� [����](https://github.com/kdevkr/spring-demo-java)���� �� �� �ֽ��ϴ�.