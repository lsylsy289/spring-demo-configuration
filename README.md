# XML Configuration  

*본 프로젝트는 스프링 프레임워크를 XML 기반으로 설정하는 것을 설명합니다.*  


## 1. 용어 정리
### 1.1 DD(Deployment Descriptor)  
스프링 애플리케이션에서는 `web.xml`을 통해서 `배포 서술자`를 구현합니다. 배포 서술자는 애플리케이션 컨텍스트 및 서블릿, 필터등에 대한 설정을 하도록 되어 있습니다.

### 1.2 Spring IoC Container  
`root-context.xml`을 애플리케이션 컨텍스트라고 합니다. 애플리케이션 컨텍스트는 `빈 팩토리를 확장한 IoC 컨테이너`이기 때문에 빈을 등록 및 관리하고 스프링이 제공하는 각종 부가 서비스를 추가적으로 적용한다.

### 1.3 Bean
스프링이 IoC로 관리하는 `POJO(Plain Old Java Object)`입니다. 주의 해야할 부분은 스프링 애플리케이션에서 만드는 모든 오브젝트가 빈인 것은 아닙니다. 

### 1.4 DI(Dependency Injection)
정확한 용어는 아니지만 `DI`를 `의존성 주입기능`이라고 부르겠습니다. 스프링 애플리케이션에서는 IoC로 관리하는 빈을 해당 클래스에서 가져와서 사용할 수 있도록 의존성 주입기능을 제공합니다. 따라서, `의존성 주입기능의 전제조건`은 어떠한 방식으든 빈으로 등록되어 있어야 한다는 것입니다.

## 2. 메타정보 설정(Configuration)  

![architecture](assets/images/architecture.PNG)

### 2.1 web.xml  
`배포 서술자`는 컨텍스트 및 서블릿 그리고 필터등을 설정합니다.

- ContextLoader  
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
```
 	<filter>
	    <filter-name>encodingFilter</filter-name>
	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    <!-- 모든 인코딩을 강제로 변환하기 위해서는 forceEncoding를 true로 설정하세요 -->
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
애플리케이션 컨텍스트는 `빈을 등록하고 관리하는 빈 팩토리`입니다. 다음을 보면서 어떻게 빈으로 등록하는지를 고민해보세요!

- DataSource  
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

- MultipartResolver  
```
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="${file.maxUploadSize}"></property>
	</bean>
```

### 2.3 servlet-context.xml  
서블릿 컨텍스트는 서블릿과 관련된 메타정보들을 설정합니다. 구조 상 빈 팩토리 역할도 합니다.

- Resources  
```
	<resources mapping="/resources/**" location="/resources/" />
```

- ViewResolver  
```
	<beans:bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
		<beans:property name="order" value="3"/>
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
```
