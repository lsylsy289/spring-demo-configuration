# XML Configuration  

*�� ������Ʈ�� ������ �����ӿ�ũ�� XML ������� �����ϴ� ���� �����մϴ�.*  


### 1. ��� ����
##### 1.1 DD(Deployment Descriptor)  
������ ���ø����̼ǿ����� web.xml�� ���ؼ� ���� �����ڸ� �����մϴ�. ���� �����ڴ� ���ø����̼� ���ؽ�Ʈ �� ����, ���͵ ���� ������ �ϵ��� �Ǿ� �ֽ��ϴ�.

##### 1.2 Spring IoC Container  
root-context.xml�� ���ø����̼� ���ؽ�Ʈ��� �մϴ�. ���ø����̼� ���ؽ�Ʈ�� �� ���丮�� Ȯ���� IoC �����̳��̱� ������ ���� ��� �� �����ϰ� �������� �����ϴ� ���� �ΰ� ���񽺸� �߰������� �����Ѵ�.

##### 1.3 Bean
�������� IoC�� �����ϴ� POJO(Plain Old Java Object)�Դϴ�. ���� �ؾ��� �κ��� ������ ���ø����̼ǿ��� ����� ��� ������Ʈ�� ���� ���� �ƴմϴ�. 

##### 1.4 DI(Dependency Injection)
��Ȯ�� ���� �ƴ����� DI�� ������ ���Ա���̶�� �θ��ڽ��ϴ�. ������ ���ø����̼ǿ����� IoC�� �����ϴ� ���� �ش� Ŭ�������� �����ͼ� ����� �� �ֵ��� ������ ���Ա���� �����մϴ�. ����, ������ ���Ա���� ���������� ��� ������� ������ ��ϵǾ� �־�� �Ѵٴ� ���Դϴ�.

### 2. ��Ÿ���� ����(Configuration)  

![architecture](assets/images/architecture.PNG)

##### 2.1 web.xml  

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


