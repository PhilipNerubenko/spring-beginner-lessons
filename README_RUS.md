# Spring: Уроки для начинающих

## Содержание

1. [Урок 1: Конфигурация Spring Bean](#урок-1-конфигурация-spring-bean)
2. [Урок 2: Аспектно-Ориентированное Программирование (AOP)](#урок-2-аспектно-ориентированное-программирование-aop)
3. [Урок 3: Основы Hibernate ORM](#урок-3-основы-hibernate-orm)
4. [Урок 4: Основы Spring MVC](#урок-4-основы-spring-mvc)
5. [Урок 5: Интеграция Spring MVC + Hibernate + AOP](#урок-5-интеграция-spring-mvc--hibernate--aop)
6. [Урок 6: Разработка REST API на Spring](#урок-6-разработка-rest-api-на-spring)
7. [Урок 7: Основы Spring Security](#урок-7-основы-spring-security)
8. [Урок 8: Spring Boot и Spring Data JPA](#урок-8-spring-boot-и-spring-data-jpa)

## Урок 1: Конфигурация Spring Bean

В этом уроке рассматриваются:

- Конфигурация через XML
- Конфигурация через аннотации
- Конфигурация через Java-код
- Внедрение свойств
- Жизненный цикл и области видимости бинов

### Обзор конфигураций

| Конфигурация | Метод | Назначение | Пример использования |
|--------------|---------|------------|---------------------|
| XML | `<bean>` | Традиционная конфигурация | Устаревшие приложения |
| Аннотации | `@Component` | Сканирование компонентов | Современные приложения |
| Java-код | `@Configuration` | Типобезопасная настройка | Сложные конфигурации |

### Примеры реализации

#### 1. XML-конфигурация

```java
// applicationContext.xml
<bean id="myPerson" class="com.example.Person">
    <property name="name" value="John"/>
    <property name="age" value="25"/>
</bean>

// Использование
ClassPathXmlApplicationContext context = 
    new ClassPathXmlApplicationContext("applicationContext.xml");
Person person = context.getBean("myPerson", Person.class);
```

#### 2. Аннотации

```java
@Component
public class Person {
    @Autowired
    private Pet pet;
    
    @Value("${person.name}")
    private String name;
}
```

#### 3. Java-конфигурация

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

### Методы конфигурации

```java
// XML-конфигурация
context = new ClassPathXmlApplicationContext("applicationContext.xml");

// Аннотационная конфигурация
context = new AnnotationConfigApplicationContext("com.example.package");

// Java-конфигурация
context = new AnnotationConfigApplicationContext(AppConfig.class);
```

Код урока доступен в следующих файлах:

- Файлы конфигурации:
  - [`applicationContext.xml`](./spring_course/src/main/resources/applicationContext.xml)
  - [`applicationContext2.xml`](./spring_course/src/main/resources/applicationContext2.xml)
  - [`applicationContext3.xml`](./spring_course/src/main/resources/applicationContext3.xml)
  - [`application.properties`](./spring_course/src/main/resources/application.properties)

- Java-классы:
  - [`MyConfig.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/MyConfig.java)
  - [`Person.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/Person.java)
  - [`Dog.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/Dog.java)
  - [`Cat.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/Cat.java)

- Демонстрационные классы:
  - [`SpringPersonDemo.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/SpringPersonDemo.java)
  - [`SpringJavaConfigDemo.java`](./spring_course/src/main/java/com/philip/spring/spring_course/spring_introduction/SpringJavaConfigDemo.java)

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

---

## Урок 2: Аспектно-Ориентированное Программирование (AOP)

В этом уроке рассматриваются концепции и реализация Spring AOP:

- Создание аспектов и объявление pointcut
- Типы advice: @Before, @After, @Around
- Join point и перехват методов
- Порядок и комбинация аспектов

### Основные понятия

| Понятие | Назначение | Пример |
|---------|------------|--------|
| Аспект | Модуль для сквозных задач | Логирование, безопасность |
| Pointcut | Где применяется аспект | Шаблоны вызова методов |
| Advice | Поведение и момент срабатывания | До/после выполнения метода |
| Join Point | Конкретная точка выполнения | Вызовы методов, обработка исключений |

### Примеры реализации

#### 1. Базовый аспект

```java
@Component
@Aspect
public class LoggingAspect {
    @Before("execution(public void getBook())")
    public void beforeGetBookAdvice() {
        System.out.println("Попытка получить книгу...");
    }
}
```

#### 2. Around Advice с обработкой исключений

```java
@Around("execution(* returnBook())")
public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint point) throws Throwable {
    System.out.println("Попытка вернуть книгу...");
    try {
        Object result = point.proceed();
        return result;
    } catch (Exception e) {
        System.out.println("Поймано исключение: " + e);
        throw e;
    }
}
```

#### 3. Упорядоченные аспекты с Join Point

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
          System.out.println("Информация о книге: название - " + book.getName() + ", автор - "
              + book.getAuthor() + ", год - " + book.getYearOfPublication());
        } else if (arg instanceof String) {
          System.out.println("Добавление книги: " + arg);
        }
      }
    }

    System.out.println("Выполнение beforeAddLoggingAdvice");
  }
}
```

### Структура проекта

AOP реализован в следующих пакетах:

- **Аспекты** ([`/aspects`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects)):
  - [`LoggingAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/LoggingAspect.java) — базовое логирование
  - [`AroundLoggingAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/AroundLoggingAspect.java) — логирование вокруг метода
  - [`SecurityAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/SecurityAspectOrderExecutingAndJoinPoint.java) — проверки безопасности
  - [`UniversityLoggingAspect.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/aspects/UniversityLoggingAspect.java) — сложные сценарии логирования

- **Основные классы**:
  - [`UniLibrary.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/UniLibrary.java) — бизнес-логика
  - [`Book.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Book.java) — доменная модель
  - [`Student.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Student.java) — доменная модель
  - [`University.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/University.java) — бизнес-логика

- **Демо-классы**:
  - [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Test1.java) — базовые примеры AOP
  - [`Test2.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Test2.java) — обработка исключений
  - [`Test3.java`](./spring_course/src/main/java/com/philip/spring/spring_course/aop/Test3.java) — примеры Around advice

### Основные аннотации AOP

| Аннотация | Назначение | Пример использования |
|-----------|------------|---------------------|
| `@Aspect` | Объявление класса-аспекта | `@Aspect public class LoggingAspect {}` |
| `@Before` | До выполнения метода | `@Before("execution(* getBook())")` |
| `@After` | После выполнения метода | `@After("execution(* returnBook())")` |
| `@Around` | Обертывание выполнения метода | `@Around("execution(* returnBook())")` |
| `@Order` | Приоритет аспекта | `@Order(20)` |

### Конфигурация

Включение поддержки AOP через Java-конфигурацию:

```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.philip.spring.spring_course.aop")
public class MyConfig {
}
```

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

---

## Урок 3: Основы Hibernate ORM

В этом уроке рассматриваются основные понятия и реализация Hibernate ORM:

- Маппинг сущностей и конфигурация
- Связи: Один-к-Одному, Один-ко-Многим, Многие-ко-Многим
- Управление сессиями и транзакциями
- CRUD-операции

### Основные понятия

| Понятие      | Назначение                      | Пример использования      |
|--------------|---------------------------------|--------------------------|
| Сущность     | Маппинг класса на таблицу БД    | `@Entity class Student`  |
| Сессия       | Управление операциями с БД      | `session.save(entity)`   |
| Транзакция   | Гарантия атомарности операций   | `session.beginTransaction()` |
| Связь        | Связь между сущностями          | Один-ко-Многим, Многие-ко-Многим |

### Примеры реализации

#### 1. Описание сущности

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

#### 2. Связь Один-к-Одному

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

#### 3. Связь Один-ко-Многим

```java
@Entity
public class Department {
    @Id
    private int id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
```

#### 4. Связь Многие-ко-Многим

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

### Структура проекта

Hibernate реализован в следующих пакетах:

- **Один-к-Одному** ([`hibernate_one_to_one`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one)):
  - Сущность: [`Employee.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/entity/Employee.java)
  - Сущность: [`EmployeeDetail.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/entity/EmployeeDetail.java)
  - Демо: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/Test1.java)
  - Демо: [`Test2.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_one/Test2.java)

- **Один-ко-Многим (Unidirectional)** ([`hibernate_one_to_many_uni`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni)):
  - Сущность: [`Department.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni/entity/Department.java)
  - Сущность: [`Employee.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni/entity/Employee.java)
  - Демо: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_uni/Test1.java)

- **Один-ко-Многим (Bidirectional)** ([`hibernate_one_to_many_bi`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi)):
  - Сущность: [`Department.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi/entity/Department.java)
  - Сущность: [`Employee.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi/entity/Employee.java)
  - Демо: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_one_to_many_bi/Test1.java)

- **Многие-ко-Многим** ([`hibernate_many_to_many`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many)):
  - Сущность: [`Child.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many/entity/Child.java)
  - Сущность: [`Section.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many/entity/Section.java)
  - Демо: [`Test.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_many_to_many/Test.java)

- **Общие тесты Hibernate** ([`hibernate_test`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test)):
  - Демо: [`Test1.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test1.java)
  - Демо: [`Test2.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test2.java)
  - Демо: [`Test3.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test3.java)
  - Демо: [`Test4.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test4.java)
  - Демо: [`Test5.java`](./spring_course/src/main/java/com/philip/spring/spring_course/hibernate_test/Test5.java)

### Конфигурация

Конфигурация Hibernate:

- [`hibernate.cfg.xml`](./spring_course/src/main/resources/hibernate.cfg.xml)

Пример использования:

```java
SessionFactory factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(Student.class)
    .buildSessionFactory();

Session session = factory.getCurrentSession();
session.beginTransaction();
// ... операции ...
session.getTransaction().commit();
```

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

## Урок 4: Основы Spring MVC

В этом уроке рассматриваются концепции и реализация Spring MVC:

- Архитектура MVC
- Контроллеры и маппинг запросов
- Обработка форм и валидация
- ViewResolver и JSP-страницы
- Связывание данных

### Основные понятия

| Понятие     | Назначение           | Пример |
|-------------|----------------------|--------|
| Контроллер  | Обработка запросов   | `@Controller class MyController` |
| View        | Отображение ответа   | JSP, Thymeleaf |
| Model       | Передача данных      | `model.addAttribute("user", user)` |
| Валидация   | Проверка данных      | `@Valid @ModelAttribute Employee employee` |

### Примеры реализации

#### 1. Базовый контроллер

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

#### 2. Обработка формы с валидацией

```java
@Entity
public class Employee {
    @Size(min = 2, message = "Имя должно быть не менее 2 символов")
    private String name;
    
    @Min(value = 500, message = "Значение должно быть больше 499")
    @Max(value = 1000, message = "Значение должно быть меньше 1001")
    private int salary;
    
    @CheckEmail(value = "abc.com", message = "Email должен заканчиваться на abc.com")
    private String email;
}
```

#### 3. Кастомная аннотация валидации

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    public String value() default "xyz.com";
    public String message() default "email должен заканчиваться на xyz.com";
}
```

### Структура проекта

Spring MVC реализован в следующих пакетах:

- **Контроллер** ([`mvc`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/)):
  - [`MyController.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/MyController.java)
  - [`Employee.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/Employee.java)

- **Валидация** ([`validation`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/validation/)):
  - [`CheckEmail.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/validation/CheckEmail.java)
  - [`CheckEmailValidator.java`](./spring_course_mvc/src/main/java/com/philipnerubenko/spring/mvc/validation/CheckEmailValidator.java)

- **Views** ([`WEB-INF/view`](./spring_course_mvc/src/main/webapp/WEB-INF/view/)):
  - [`first-view.jsp`](./spring_course_mvc/src/main/webapp/WEB-INF/view/first-view.jsp)
  - [`ask-emp-details-view.jsp`](./spring_course_mvc/src/main/webapp/WEB-INF/view/ask-emp-details-view.jsp)
  - [`show-emp-details-view.jsp`](./spring_course_mvc/src/main/webapp/WEB-INF/view/show-emp-details-view.jsp)

### Конфигурационные файлы

- [`web.xml`](./spring_course_mvc/src/main/webapp/WEB-INF/web.xml)
- [`applicationContext.xml`](./spring_course_mvc/src/main/webapp/WEB-INF/applicationContext.xml)

Пример конфигурации web.xml:

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

### Пример обработки формы

```jsp
<!-- ask-emp-details-view.jsp -->
<form:form action="showDetails" modelAttribute="employee">
    Имя <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    Зарплата <form:input path="salary"/>
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
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

## Урок 5: Интеграция Spring MVC + Hibernate + AOP

В этом уроке показано, как объединить Spring MVC, Hibernate и AOP в одном веб-приложении:

- Полные CRUD-операции
- Управление транзакциями
- Логирование через аспекты
- Реализация сервисного слоя
- Паттерн DAO

### Основные понятия

| Понятие      | Назначение           | Пример |
|--------------|----------------------|--------|
| Сервисный слой | Реализация бизнес-логики | `@Service class EmployeeServiceImpl` |
| DAO-слой     | Операции с БД        | `@Repository class EmployeeDAOImpl` |
| Транзакция   | Согласованность данных | `@Transactional` |
| Аспект       | Сквозные задачи      | Логирование, мониторинг |

### Архитектура проекта

```
Контроллер (MVC)
      ↓
Сервисный слой
      ↓
   DAO-слой
      ↓
База данных (Hibernate)

Сквозные задачи: AOP-аспекты
```

### Примеры реализации

#### 1. Сущность

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
    
    // Геттеры и сеттеры
}
```

#### 2. Реализация DAO

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

#### 3. Реализация сервисного слоя

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

#### 4. Контроллер

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

#### 5. Аспект логирования

```java
@Component
@Aspect
public class LoggingAspect {
    
    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    
    @Around("execution(* com.philipnerubenko.spring.service.*.*(..))")
    public Object aroundAllServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Начало метода: " + methodName);
        
        Object result = proceedingJoinPoint.proceed();
        
        logger.info("Конец метода: " + methodName);
        return result;
    }
}
```

### Конфигурация

#### 1. Конфигурация БД (applicationContext.xml)

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

#### 2. Управление транзакциями

```xml
<bean id="transactionManager"
      class="org.springframework.orm.hibernate5.HibernateTransactionManager">
    <property name="sessionFactory" ref="sessionFactory"/>
</bean>

<tx:annotation-driven transaction-manager="transactionManager"/>
```

### Шаблоны представлений

#### 1. all-employees.jsp

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h2>Все сотрудники</h2>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отдел</th>
        <th>Зарплата</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="emp" items="${allEmps}">
        <tr>
            <td>${emp.name}</td>
            <td>${emp.surname}</td>
            <td>${emp.department}</td>
            <td>${emp.salary}</td>
            <td>
                <input type="button" value="Обновить"
                    onclick="window.location.href='updateInfo?empId=${emp.id}'"/>
                <input type="button" value="Удалить"
                    onclick="window.location.href='deleteEmployee?empId=${emp.id}'"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
```

### Структура проекта

Интеграция реализована в следующих пакетах:

- **Контроллер** ([`controller`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/controller/)):
  - [`EmployeeController.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/controller/EmployeeController.java)

- **Сервис** ([`service`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/service/)):
  - [`EmployeeService.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/service/EmployeeService.java)
  - [`EmployeeServiceImpl.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/service/EmployeeServiceImpl.java)

- **DAO** ([`dao`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/dao/)):
  - [`EmployeeDAO.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/dao/EmployeeDAO.java)
  - [`EmployeeDAOImpl.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/dao/EmployeeDAOImpl.java)

- **Сущность** ([`entity`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/entity/)):
  - [`Employee.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/entity/Employee.java)

- **Аспект** ([`aspect`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/aspect/)):
  - [`LoggingAspect.java`](./spring_course_mvc_hibernate_aop/src/main/java/com/philipnerubenko/spring/mvc_hibernate_aop/aspect/LoggingAspect.java)

### Ключевые особенности

1. **Многоуровневая архитектура**
   - Четкое разделение ответственности
   - Удобство поддержки и тестирования
   - Масштабируемость

2. **Управление транзакциями**
   - Декларативные транзакции через `@Transactional`
   - Автоматический откат при ошибках
   - Согласованность данных

3. **Интеграция AOP**
   - Логирование выполнения методов
   - Мониторинг производительности
   - Обработка исключений

4. **Работа с БД**
   - Пул соединений C3P0
   - Интеграция с Hibernate ORM
   - CRUD-операции

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

## Урок 6: Разработка REST API на Spring

В этом уроке рассматриваются принципы и реализация REST API на Spring:

- Принципы REST и архитектура
- Формат данных JSON
- Взаимодействие клиент-сервер
- Обработка исключений
- Реализация REST-клиента

### Основные понятия

| Понятие         | Назначение           | Пример |
|-----------------|---------------------|--------|
| REST-контроллер | Обработка HTTP-запросов | `@RestController` |
| RequestMapping  | Маппинг URL на методы | `@GetMapping("/api/employees")` |
| ResponseEntity  | Кастомизация ответа | `ResponseEntity<Employee>` |
| RestTemplate    | HTTP-запросы клиента | `restTemplate.getForObject()` |

### Серверная реализация

#### 1. REST-контроллер

```java
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("Сотрудник с ID не найден - " + id);
        }
        return employee;
    }
}
```

#### 2. Обработка исключений

```java
@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            NoSuchEmployeeException exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            Exception exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo("Внутренняя ошибка сервера");
        
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

### Клиентская реализация

#### 1. Конфигурация REST-клиента

```java
@Configuration
@ComponentScan("com.philipnerubenko.spring.rest")
public class MyConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

#### 2. Сервис коммуникации

```java
@Component
public class Communication {
    
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/employees";
    
    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity = 
            restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {});
        
        return responseEntity.getBody();
    }
    
    public Employee getEmployee(int id) {
        return restTemplate.getForObject(URL + "/" + id, Employee.class);
    }
    
    public void saveEmployee(Employee employee) {
        if (employee.getId() == 0) {
            ResponseEntity<String> responseEntity = 
                restTemplate.postForEntity(URL, employee, String.class);
        } else {
            restTemplate.put(URL, employee);
        }
    }
    
    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
    }
}
```

### Структура проекта

REST реализован в двух проектах:

#### Сервер ([`spring_course_mvc_rest`](./spring_course_mvc_rest/)):
- Конфигурация БД и транзакций
- REST-контроллеры и обработчики ошибок
- Сервис и DAO

#### Клиент ([`spring_course_rest_client`](./spring_course_rest_client/)):
- **Конфигурация** ([`configuration`](./spring_course_rest_client/src/main/java/com/philipnerubenko/spring/rest/configuration/)):
  - [`MyConfig.java`](./spring_course_rest_client/src/main/java/com/philipnerubenko/spring/rest/configuration/MyConfig.java)
- **Коммуникация** ([`rest`](./spring_course_rest_client/src/main/java/com/philipnerubenko/spring/rest/)):
  - [`Communication.java`](./spring_course_rest_client/src/main/java/com/philipnerubenko/spring/rest/Communication.java)
  - [`App.java`](./spring_course_rest_client/src/main/java/com/philipnerubenko/spring/rest/App.java)

### REST API endpoints

| HTTP-метод | Endpoint | Описание |
|------------|----------|----------|
| GET | `/api/employees` | Получить всех сотрудников |
| GET | `/api/employees/{id}` | Получить сотрудника по ID |
| POST | `/api/employees` | Создать нового сотрудника |
| PUT | `/api/employees` | Обновить сотрудника |
| DELETE | `/api/employees/{id}` | Удалить сотрудника |

### Примеры использования

#### Формат ответа сервера

```json
{
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "department": "IT",
    "salary": 1000
}
```

#### Формат ошибки

```json
{
    "info": "Сотрудник с ID не найден - 100"
}
```

### Ключевые особенности

1. **RESTful-архитектура**
   - Без состояния
   - Ресурсные URL
   - Семантика HTTP-методов

2. **Обработка ошибок**
   - Глобальный обработчик
   - Кастомные ответы
   - HTTP-статусы

3. **Клиентская реализация**
   - RestTemplate
   - Типобезопасные запросы
   - Обработка ответов

4. **Работа с JSON**
   - Автоматическая сериализация/десериализация
   - Контент-неготиация
   - Валидация данных

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

## Урок 7: Основы Spring Security

В этом уроке рассматриваются вопросы безопасности веб-приложений на Spring Security:

- Конфигурация и инициализация безопасности
- Ролевой доступ (HR, MANAGER, EMPLOYEE)
- Форма входа и выхода
- Защита контроллеров и JSP
- Хеширование паролей

### Основные понятия

| Понятие           | Назначение                | Пример                        |
|-------------------|--------------------------|-------------------------------|
| SecurityConfig    | Конфигурация безопасности | `@EnableWebSecurity`          |
| WebInitializer    | Инициализация фильтра     | `AbstractSecurityWebApplicationInitializer` |
| Ролевой доступ    | Ограничение по ролям      | `hasRole('HR')`               |
| Form Login        | Аутентификация через форму| `/login`                      |
| PasswordEncoder   | Хеширование паролей       | `BCryptPasswordEncoder`       |

### Пример конфигурации безопасности

```java
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
            .antMatchers("/hr_info/**").hasRole("HR")
            .antMatchers("/manager_info/**").hasRole("MANAGER")
            .and().formLogin().permitAll()
            .and().logout().permitAll();
    }
}
```

### Инициализация безопасности

```java
public class MySecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    // Регистрирует springSecurityFilterChain автоматически
}
```

### Пример контроллера

```java
@Controller
public class MyController {
    @RequestMapping("/")
    public String showAllEmployees() {
        return "view_for_all_employees";
    }

    @RequestMapping("/hr_info")
    public String showHRInfo() {
        return "view_for_hr";
    }

    @RequestMapping("/manager_info")
    public String showManagerInfo() {
        return "view_for_managers";
    }
}
```

### Примеры JSP

- [`view_for_all_employees.jsp`](./spring_course_security/src/main/webapp/WEB-INF/view/view_for_all_employees.jsp)
- [`view_for_hr.jsp`](./spring_course_security/src/main/webapp/WEB-INF/view/view_for_hr.jsp)
- [`view_for_managers.jsp`](./spring_course_security/src/main/webapp/WEB-INF/view/view_for_managers.jsp)

### Структура проекта

- **Конфигурация** ([`configuration`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/configuration/)):
  - [`MyConfig.java`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/configuration/MyConfig.java)
  - [`MySecurityConfig.java`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/configuration/MySecurityConfig.java)
  - [`MySecurityInitializer.java`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/configuration/MySecurityInitializer.java)
  - [`MyWebInitializer.java`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/configuration/MyWebInitializer.java)
- **Контроллеры** ([`controller`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/controller/)):
  - [`MyController.java`](./spring_course_security/src/main/java/com/philipnerubenko/spring/security/controller/MyController.java)
- **JSP** ([`view`](./spring_course_security/src/main/webapp/WEB-INF/view/)):
  - `view_for_all_employees.jsp`, `view_for_hr.jsp`, `view_for_managers.jsp`

### Хеширование паролей

#### BCrypt

Spring Security поддерживает хеширование паролей через BCrypt:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

- BCrypt добавляет соль и защищает от перебора.
- Для сохранения пароля:  
  `user.setPassword(passwordEncoder.encode(rawPassword));`
- Для проверки пароля:  
  `passwordEncoder.matches(rawPassword, encodedPassword);`

#### {noop} — Без хеширования

Для демонстрации можно использовать `{noop}`:

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user").password("{noop}password").roles("EMPLOYEE");
}
```

- `{noop}` — без хеширования (не рекомендуется для продакшена).
- Используйте только для тестов и демонстраций.

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>

## Урок 8: Spring Boot и Spring Data JPA

В этом уроке рассматриваются основы Spring Boot и различные подходы к созданию REST API:

- Автоконфигурация Spring Boot
- Репозитории Spring Data JPA
- Spring Data REST
- Spring Boot Actuator
- Сравнение подходов к REST API

### Основные понятия

| Понятие         | Назначение                | Пример |
|-----------------|--------------------------|--------|
| Spring Boot     | Упрощенная настройка      | `@SpringBootApplication` |
| Spring Data JPA | Абстракция репозитория    | `JpaRepository` |
| Spring Data REST| Автоматические REST endpoints | `@RepositoryRestResource` |
| Actuator        | Мониторинг приложения     | `/actuator/health` |

### Подходы к реализации

#### 1. Spring Boot REST с DAO

```java
// Контроллер
@RestController
@RequestMapping("/api")
public class MyRESTController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

// DAO
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Employee> getAllEmployees() {
        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }
}
```

#### 2. Spring Data JPA

```java
// Репозиторий
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Spring Data JPA реализует CRUD автоматически
    
    // Кастомный запрос
    List<Employee> findAllByName(String name);
}

// Сервис
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
```

#### 3. Spring Data REST

```java
@RepositoryRestResource(path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Автоматически создаются endpoints:
    // GET /employees
    // GET /employees/{id}
    // POST /employees
    // PUT /employees/{id}
    // DELETE /employees/{id}
}
```

### Структура проекта

Spring Boot реализован в трех отдельных проектах:

#### 1. Стандартный Spring Boot REST ([`springboot_rest`](./springboot_rest/)):
- **Контроллер**: [`MyRESTController.java`](./springboot_rest/src/main/java/com/philipnerubenko/spring/springboot/springboot_rest/controller/MyRESTController.java)
- **Сервис**: [`EmployeeServiceImpl.java`](./springboot_rest/src/main/java/com/philipnerubenko/spring/springboot/springboot_rest/service/EmployeeServiceImpl.java)
- **DAO**: [`EmployeeDAOImpl.java`](./springboot_rest/src/main/java/com/philipnerubenko/spring/springboot/springboot_rest/dao/EmployeeDAOImpl.java)

#### 2. Spring Data JPA ([`spring_data_jpa`](./spring_data_jpa/)):
- **Контроллер**: [`MyRESTController.java`](./spring_data_jpa/src/main/java/com/philipnerubenko/spring/springboot/spring_data_jpa/controller/MyRESTController.java)
- **Репозиторий**: [`EmployeeRepository.java`](./spring_data_jpa/src/main/java/com/philipnerubenko/spring/springboot/spring_data_jpa/dao/EmployeeRepository.java)
- **Сервис**: [`EmployeeServiceImpl.java`](./spring_data_jpa/src/main/java/com/philipnerubenko/spring/springboot/spring_data_jpa/service/EmployeeServiceImpl.java)

#### 3. Spring Data REST ([`spring_data_rest`](./spring_data_rest/)):
- **Репозиторий**: [`EmployeeRepository.java`](./spring_data_rest/src/main/java/com/philipnerubenko/spring/springboot/spring_data_rest/dao/EmployeeRepository.java)
- **Сущность**: [`Employee.java`](./spring_data_rest/src/main/java/com/philipnerubenko/spring/springboot/spring_data_rest/entity/Employee.java)

### Конфигурация

#### application.properties

```properties
# Конфигурация БД
spring.datasource.url=jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=bestuser
spring.datasource.password=bestuser

# Spring Data REST
spring.data.rest.base-path=/api
spring.data.rest.default-page-size=20

# Actuator
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```

### Spring Boot Actuator

Actuator — мониторинг и управление приложением:

#### 1. Включение Actuator

В pom.xml:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### 2. Доступные endpoints

| Endpoint | Описание | Пример ответа |
|----------|----------|--------------|
| `/actuator/health` | Статус приложения | `{"status":"UP"}` |
| `/actuator/info` | Информация | Кастомные данные |
| `/actuator/metrics` | Метрики | JVM и пользовательские |
| `/actuator/env` | Переменные окружения | Свойства конфигурации |

#### 3. Кастомный Health Indicator

```java
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up()
            .withDetail("customKey", "customValue")
            .build();
    }
}
```

### Сравнение подходов

| Подход           | Плюсы                | Минусы                |
|------------------|----------------------|----------------------|
| Стандартный REST | Полный контроль      | Много шаблонного кода|
| Spring Data JPA  | Меньше кода, стандартные операции | Ограниченная кастомизация |
| Spring Data REST | Минимум кода, HATEOAS | Меньше контроля над endpoints |

<div align="right">
    <b><a href="#содержание">↥ К содержанию</a></b>
</div>
