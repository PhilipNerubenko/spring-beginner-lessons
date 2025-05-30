# Spring Beginner Lessons

## Contents

1. [Lesson 1: Spring Bean Configuration](#lesson-1-spring-bean-configuration)
2. [Lesson 2: Aspect-Oriented Programming (AOP)](#lesson-2-aspect-oriented-programming-aop)

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

## Lesson 2: Aspect-Oriented Programming (AOP)

This lesson explores Spring AOP concepts and implementations, including:

- Aspect creation and pointcut declarations
- Different types of advice (@Before, @After, @Around)
- Join points and method interception
- Aspect ordering and combination

### Key Concepts

| Concept | Purpose | Example |
|---------|----------|---------|
| Aspect | Modularizes cross-cutting concerns | Logging, security checks |
| Pointcut | Defines where aspects apply | Method execution patterns |
| Advice | Defines aspect behavior and timing | Before/after method execution |
| Join Point | Specific points in program execution | Method calls, exception handling |

### Implementation Examples

#### 1. Basic Aspect Definition

```java
@Component
@Aspect
public class LoggingAspect {
    @Before("execution(public void getBook())")
    public void beforeGetBookAdvice() {
        System.out.println("Attempting to get book...");
    }
}
```

#### 2. Around Advice with Exception Handling

```java
@Around("execution(* returnBook())")
public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint point) throws Throwable {
    System.out.println("Attempting to return book...");
    try {
        Object result = point.proceed();
        return result;
    } catch (Exception e) {
        System.out.println("Exception caught: " + e);
        throw e;
    }
}
```

#### 3. Ordered Aspects with Join Points

```java
@Component
@Aspect
@Order(20)
public class SecurityAspect {
    @Before("com.philip.spring.spring_course.aop.aspects.MyPointcuts.allAddMethods()")
    public void beforeAddSecurityAdvice() {
        System.out.println("Performing security check...");
    }
}
```

### Project Structure

The AOP implementation is organized in the following packages:

- **Aspects** ([`/aspects`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects)):
  - [`LoggingAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/LoggingAspect.java) - Basic logging advice
  - [`AroundLoggingAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/AroundLoggingAspect.java) - Around method execution logging
  - [`SecurityAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/SecurityAspectOrderExecutingAndJoinPoint.java) - Security checks
  - [`UniversityLoggingAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/UniversityLoggingAspect.java) - Complex logging scenarios

- **Core Classes**:
  - [`UniLibrary.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/UniLibrary.java) - Main business logic
  - [`Book.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Book.java) - Domain model
  - [`Student.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Student.java) - Domain model
  - [`University.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/University.java) - Business logic

- **Demo Classes**:
  - [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Test1.java) - Basic AOP demos
  - [`Test2.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Test2.java) - Exception handling demos
  - [`Test3.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Test3.java) - Around advice demos

### Common AOP Annotations

| Annotation | Purpose | Example Usage |
|------------|---------|---------------|
| `@Aspect` | Declares an aspect class | `@Aspect public class LoggingAspect {}` |
| `@Before` | Executes before method | `@Before("execution(* getBook())")` |
| `@After` | Executes after method | `@After("execution(* returnBook())")` |
| `@Around` | Wraps method execution | `@Around("execution(* returnBook())")` |
| `@Order` | Sets aspect precedence | `@Order(20)` |

### Configuration

Enable AOP support using Java configuration:

```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.philip.spring.spring_course.aop")
public class MyConfig {
}
```

<div align="right">
    <b><a href="#contents">↥ Back to Contents</a></b>
</div>

---
