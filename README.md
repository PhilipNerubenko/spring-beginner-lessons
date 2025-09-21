# Spring Beginner Lessons

## Contents

1. [Lesson 1: Spring Bean Configuration](#lesson-1-spring-bean-configuration)
2. [Lesson 2: Aspect-Oriented Programming (AOP)](#lesson-2-aspect-oriented-programming-aop)
3. [Lesson 3: Hibernate ORM Basics](#lesson-3-hibernate-orm-basics)
4. [Lesson 4: Spring MVC Fundamentals](#lesson-4-spring-mvc-fundamentals)
5. [Lesson 5: Spring MVC + Hibernate + AOP Integration](#lesson-5-spring-mvc--hibernate--aop-integration)

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
@Order(10)
public class SecurityAspect {
    @Before("com.philip.spring.spring_course.aop.aspects.MyPointcuts.allAddMethods()")
    public void beforeAddLoggingAdvice(JoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    if (methodSignature.getName().equals("addBook")) {
      Object[] args = joinPoint.getArgs();
      for (Object arg : args) {
        if (arg instanceof Book) {
          Book book = (Book) arg;
          System.out.println("Book information: name - " + book.getName() + ", author - "
              + book.getAuthor() + ", year - " + book.getYearOfPublication());
        } else if (arg instanceof String) {
          System.out.println("Book add: " + arg);
        }
      }
    }

    System.out.println("Executing beforeAddLoggingAdvice");
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

## Lesson 3: Hibernate ORM Basics

This lesson introduces Hibernate ORM concepts and practical implementations:

- Entity mapping and configuration
- Relationships: One-to-One, One-to-Many, Many-to-Many
- Session management and transactions
- Basic CRUD operations

### Key Concepts

| Concept        | Purpose                          | Example Use Case         |
|----------------|----------------------------------|-------------------------|
| Entity         | Maps Java class to DB table      | `@Entity class Student` |
| Session        | Manages DB operations            | `session.save(entity)`  |
| Transaction    | Ensures atomic operations        | `session.beginTransaction()` |
| Relationship   | Links entities                   | One-to-Many, Many-to-Many |

### Implementation Examples

#### 1. Entity Definition

```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
}
```

#### 2. One-to-One Relationship

```java
@Entity
public class Employee {
    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private EmployeeDetail detail;
}
```

#### 3. One-to-Many Relationship

```java
@Entity
public class Department {
    @Id
    private int id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
```

#### 4. Many-to-Many Relationship

```java
@Entity
public class Child {
    @Id
    private int id;

    @ManyToMany
    @JoinTable(
        name = "child_section",
        joinColumns = @JoinColumn(name = "child_id"),
        inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;
}
```

### Project Structure

Hibernate lessons are organized in these packages:

- **One-to-One** ([`hibernate_one_to_one`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one)):
  - Entity: [`Employee.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/entity/Employee.java)
  - Entity: [`EmployeeDetail.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/entity/EmployeeDetail.java)
  - Demo: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/Test1.java)
  - Demo: [`Test2.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/Test2.java)

- **One-to-Many (Unidirectional)** ([`hibernate_one_to_many_uni`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni)):
  - Entity: [`Department.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni/entity/Department.java)
  - Entity: [`Employee.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni/entity/Employee.java)
  - Demo: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni/Test1.java)

- **One-to-Many (Bidirectional)** ([`hibernate_one_to_many_bi`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi)):
  - Entity: [`Department.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi/entity/Department.java)
  - Entity: [`Employee.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi/entity/Employee.java)
  - Demo: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi/Test1.java)

- **Many-to-Many** ([`hibernate_many_to_many`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many)):
  - Entity: [`Child.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many/entity/Child.java)
  - Entity: [`Section.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many/entity/Section.java)
  - Demo: [`Test.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many/Test.java)

- **General Hibernate Tests** ([`hibernate_test`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test)):
  - Demo: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test1.java)
  - Demo: [`Test2.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test2.java)
  - Demo: [`Test3.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test3.java)
  - Demo: [`Test4.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test4.java)
  - Demo: [`Test5.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test5.java)

### Configuration

Hibernate configuration is provided in:

- [`hibernate.cfg.xml`](./spring_course/src/main/resources/hibernate.cfg.xml)

Example usage:

```java
SessionFactory factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(Student.class)
    .buildSessionFactory();

Session session = factory.getCurrentSession();
session.beginTransaction();
// ... perform operations ...
session.getTransaction().commit();
```

<div align="right">
    <b><a href="#contents">↥ Back to Contents</a></b>
</div>

## Lesson 4: Spring MVC Fundamentals

This lesson covers Spring MVC framework concepts and implementations:

- MVC architecture pattern
- Controllers and request mapping
- Form handling and validation
- View resolvers and JSP pages
- Data binding

### Key Concepts

| Concept | Purpose | Example |
|---------|---------|---------|
| Controller | Handles web requests | `@Controller class MyController` |
| View | Renders the response | JSP pages, Thymeleaf templates |
| Model | Holds data between controller and view | `model.addAttribute("user", user)` |
| Validation | Ensures data integrity | `@Valid @ModelAttribute Employee employee` |

### Implementation Examples

#### 1. Basic Controller

```java
@Controller
public class MyController {
    
    @RequestMapping("/")
    public String showFirstView() {
        return "first-view";
    }
    
    @RequestMapping("/askDetails")
    public String askEmployeeDetails(Model model) {
        model.addAttribute("employee", new Employee());
        return "ask-emp-details-view";
    }
}
```

#### 2. Form Processing with Validation

```java
@Entity
public class Employee {
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;
    
    @Min(value = 500, message = "Must be greater than 499")
    @Max(value = 1000, message = "Must be less than 1001")
    private int salary;
    
    @CheckEmail(value = "abc.com", message = "Email must end with abc.com")
    private String email;
}
```

#### 3. Custom Validation Annotation

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    public String value() default "xyz.com";
    public String message() default "email must ends with xyz.com";
}
```

### Project Structure

The MVC implementation is organized in these packages:

- **Controller** ([`mvc`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/)):
  - [`MyController.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/MyController.java) - Main controller
  - [`Employee.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/Employee.java) - Model class

- **Validation** ([`validation`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/validation/)):
  - [`CheckEmail.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/validation/CheckEmail.java) - Custom annotation
  - [`CheckEmailValidator.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/validation/CheckEmailValidator.java) - Validator implementation

- **Views** ([`WEB-INF/view`](./spring_course_mvc/src/main/webapp/WEB-INF/view/)):
  - [`first-view.jsp`](./spring_course_mvc/src/main/webapp/WEB-INF/view/first-view.jsp) - Welcome page
  - [`ask-emp-details-view.jsp`](./spring_course_mvc/src/main/webapp/WEB-INF/view/ask-emp-details-view.jsp) - Employee form
  - [`show-emp-details-view.jsp`](./spring_course_mvc/src/main/webapp/WEB-INF/view/show-emp-details-view.jsp) - Result page

### Configuration Files

- [`web.xml`](./spring_course_mvc/src/main/webapp/WEB-INF/web.xml) - Servlet configuration
- [`applicationContext.xml`](./spring_course_mvc/src/main/webapp/WEB-INF/applicationContext.xml) - Spring configuration

Example web.xml configuration:

```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

### Form Processing Example

```jsp
<!-- ask-emp-details-view.jsp -->
<form:form action="showDetails" modelAttribute="employee">
    Name <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Salary <form:input path="salary"/>
    <form:errors path="salary"/>
    <br><br>
    Email <form:input path="email"/>
    <form:errors path="email"/>
    <br><br>
    <input type="submit" value="OK">
</form:form>
```

```java
@RequestMapping("/showDetails")
public String showEmployeeDetails(@Valid @ModelAttribute("employee") Employee emp,
                                BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return "ask-emp-details-view";
    }
    return "show-emp-details-view";
}
```

<div align="right">
    <b><a href="#contents">↥ Back to Contents</a></b>
</div>

## Lesson 5: Spring MVC + Hibernate + AOP Integration

This lesson demonstrates how to combine Spring MVC, Hibernate, and AOP in a single web application:

- Complete CRUD operations
- Transaction management
- Aspect logging
- Service layer implementation
- DAO pattern

### Key Concepts

| Concept | Purpose | Example |
|---------|---------|---------|
| Service Layer | Business logic implementation | `@Service class EmployeeServiceImpl` |
| DAO Layer | Database operations | `@Repository class EmployeeDAOImpl` |
| Transaction | Data consistency | `@Transactional` |
| Aspect | Cross-cutting concerns | Logging, performance monitoring |

### Project Architecture

```
Controller Layer (MVC)
      ↓
Service Layer (Business Logic)
      ↓
   DAO Layer
      ↓
Database (Hibernate)

Cross-cutting: AOP Aspects
```

### Implementation Examples

#### 1. Entity Class

```java
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "salary")
    private int salary;
    
    // Getters and setters
}
```

#### 2. DAO Layer Implementation

```java
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class).getResultList();
    }
    
    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }
}
```

#### 3. Service Layer Implementation

```java
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    
    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }
}
```

#### 4. Controller Implementation

```java
@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping("/")
    public String showAllEmployees(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);
        return "all-employees";
    }
    
    @RequestMapping("/addEmployee")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-info";
    }
}
```

#### 5. AOP Logging Aspect

```java
@Component
@Aspect
public class LoggingAspect {
    
    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    
    @Around("execution(* com.philipnerubenko.spring.service.*.*(..))")
    public Object aroundAllServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Begin method: " + methodName);
        
        Object result = proceedingJoinPoint.proceed();
        
        logger.info("End method: " + methodName);
        return result;
    }
}
```

### Configuration

#### 1. Database Configuration (applicationContext.xml)

```xml
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
      destroy-method="close">
    <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;serverTimezone=UTC"/>
    <property name="user" value="bestuser"/>
    <property name="password" value="bestuser"/>
</bean>

<bean id="sessionFactory"
      class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="packagesToScan" value="com.philipnerubenko.spring.entity"/>
    <property name="hibernateProperties">
        <props>
            <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
            <prop key="hibernate.show_sql">true</prop>
        </props>
    </property>
</bean>
```

#### 2. Transaction Management

```xml
<bean id="transactionManager"
      class="org.springframework.orm.hibernate5.HibernateTransactionManager">
    <property name="sessionFactory" ref="sessionFactory"/>
</bean>

<tx:annotation-driven transaction-manager="transactionManager"/>
```

### View Templates

#### 1. all-employees.jsp

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h2>All Employees</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Operations</th>
    </tr>
    <c:forEach var="emp" items="${allEmps}">
        <tr>
            <td>${emp.name}</td>
            <td>${emp.surname}</td>
            <td>${emp.department}</td>
            <td>${emp.salary}</td>
            <td>
                <input type="button" value="Update"
                    onclick="window.location.href='updateInfo?empId=${emp.id}'"/>
                <input type="button" value="Delete"
                    onclick="window.location.href='deleteEmployee?empId=${emp.id}'"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
```

### Project Structure

The integrated implementation is organized in these packages:

- **Controller** ([`controller`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/controller/)):
  - [`EmployeeController.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/controller/EmployeeController.java)

- **Service** ([`service`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/service/)):
  - [`EmployeeService.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/service/EmployeeService.java)
  - [`EmployeeServiceImpl.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/service/EmployeeServiceImpl.java)

- **DAO** ([`dao`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/dao/)):
  - [`EmployeeDAO.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/dao/EmployeeDAO.java)
  - [`EmployeeDAOImpl.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/dao/EmployeeDAOImpl.java)

- **Entity** ([`entity`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/entity/)):
  - [`Employee.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/entity/Employee.java)

- **Aspect** ([`aspect`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/aspect/)):
  - [`LoggingAspect.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/aspect/LoggingAspect.java)

### Key Features

1. **Layered Architecture**
   - Clear separation of concerns
   - Maintainable and testable code
   - Scalable structure

2. **Transaction Management**
   - Declarative transactions with `@Transactional`
   - Automatic rollback on exceptions
   - Transaction consistency

3. **AOP Integration**
   - Method execution logging
   - Performance monitoring
   - Exception handling

4. **Database Operations**
   - Connection pooling with C3P0
   - Hibernate ORM integration
   - CRUD operations

<div align="right">
    <b><a href="#contents">↥ Back to Contents</a></b>
</div>