# XML Configuration  

*�� ������Ʈ�� ������ �����ӿ�ũ�� XML ������� �����ϴ� ���� �����մϴ�.*  


## 1. ��� ����
### 1.1 DD(Deployment Descriptor)  
������ ���ø����̼ǿ����� web.xml�� ���ؼ� ���� �����ڸ� �����մϴ�. ���� �����ڴ� ���ø����̼� ���ؽ�Ʈ �� ����, ���͵ ���� ������ �ϵ��� �Ǿ� �ֽ��ϴ�.

### 1.2 Spring IoC Container  
root-context.xml�� ���ø����̼� ���ؽ�Ʈ��� �մϴ�. ���ø����̼� ���ؽ�Ʈ�� �� ���丮�� Ȯ���� IoC �����̳��̱� ������ ���� ��� �� �����ϰ� �������� �����ϴ� ���� �ΰ� ���񽺸� �߰������� �����Ѵ�.

### 1.3 Bean
�������� IoC�� �����ϴ� POJO(Plain Old Java Object)�Դϴ�. ���� �ؾ��� �κ��� ������ ���ø����̼ǿ��� ����� ��� ������Ʈ�� ���� ���� �ƴմϴ�. 

### 1.4 DI(Dependency Injection)
��Ȯ�� ���� �ƴ����� DI�� ������ ���Ա���̶�� �θ��ڽ��ϴ�. ������ ���ø����̼ǿ����� IoC�� �����ϴ� ���� �ش� Ŭ�������� �����ͼ� ����� �� �ֵ��� ������ ���Ա���� �����մϴ�. ����, ������ ���Ա���� ���������� ��� ������� ������ ��ϵǾ� �־�� �Ѵٴ� ���Դϴ�.

## 2. ��Ÿ���� ����(Configuration)  

![architecture](assets/images/architecture.PNG)

### 2.1 web.xml  
���� �����ڴ� ���ؽ�Ʈ �� ���� �׸��� ���͵��� �����մϴ�.

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




