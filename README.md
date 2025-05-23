# Spring Beginner Lessons

## Contents

1. [Lesson 1: Spring Bean Configuration](#lesson-1-spring-bean-configuration)

## Lesson 1: Spring Bean Configuration

This lesson covers:

- XML-based configuration
- Annotation-based configuration
- Java-based configuration
- Property injection
- Bean lifecycle and scopes

### Configuration Overview

| Configuration | Method | Purpose | Example Use Case |
|--------------|---------|---------|-----------------|
| XML-based | `<bean>` tags | Traditional configuration | Legacy applications |
| Annotation | `@Component` | Component scanning | Modern applications |
| Java-based | `@Configuration` | Type-safe config | Complex configurations |

### Implementation Examples

#### 1. Using XML Configuration

```java
// applicationContext.xml
<bean id="myPerson" class="com.example.Person">
    <property name="name" value="John"/>
    <property name="age" value="25"/>
</bean>

// Usage
ClassPathXmlApplicationContext context = 
    new ClassPathXmlApplicationContext("applicationContext.xml");
Person person = context.getBean("myPerson", Person.class);
```

#### 2. Using Annotations

```java
@Component
public class Person {
    @Autowired
    private Pet pet;
    
    @Value("${person.name}")
    private String name;
}
```

#### 3. Using Java Configuration

```java
@Configuration
public class AppConfig {
    @Bean
    public Person person() {
        return new Person(pet());
    }
    
    @Bean
    public Pet pet() {
        return new Dog();
    }
}
```

### Configuration Methods

```java
// XML-based configuration
context = new ClassPathXmlApplicationContext("applicationContext.xml");

// Annotation-based configuration
context = new AnnotationConfigApplicationContext("com.example.package");

// Java-based configuration
context = new AnnotationConfigApplicationContext(AppConfig.class);
```

The lesson code is available in these files:

- Configuration Files:
  - [`applicationContext.xml`](./spring_course/src/main/resources/applicationContext.xml)
  - [`applicationContext2.xml`](./spring_course/src/main/resources/applicationContext2.xml)
  - [`applicationContext3.xml`](./spring_course/src/main/resources/applicationContext3.xml)
  - [`application.properties`](./spring_course/src/main/resources/application.properties)

- Java Classes:
  - [`MyConfig.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/MyConfig.java)
  - [`Person.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/Person.java)
  - [`Dog.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/Dog.java)
  - [`Cat.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/Cat.java)

- Demo Classes:
  - [`SpringPersonDemo.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/SpringPersonDemo.java)
  - [`SpringJavaConfigDemo.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/SpringJavaConfigDemo.java)

<div align="right">
    <b><a href="#contents">↥ Back to Contents</a></b>
</div>

---