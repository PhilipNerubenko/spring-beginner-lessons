package com.philip.spring.spring_course.aop.aspects;

import com.philip.spring.spring_course.aop.Student;
import java.util.List;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UniversityLoggingAspect {
  /**
   * Before advice for the getStudents method in University.
   */
  @Before("execution(* com.philip.spring.spring_course.aop.University.getStudents())")
  public void beforeGetStudentsLoggingAdvice() {
    System.out.println("beforeGetStudentsLoggingAdvice: Logging the attempt to get students list");
    System.out.println("__________________________");
  }

  /**
   * After returning advice for the getStudents method in University.
   * @param students List of students.
   */
  @AfterReturning(
      pointcut = "execution(* com.philip.spring.spring_course.aop.University.getStudents())",
      returning = "students")
  public void
  afterReturningGetStudentsLoggingAdvice(List<Student> students) {
    Student firstStudent = students.get(0);

    String nameSurname = firstStudent.getNameSurname();
    nameSurname = "Mr./Ms. " + nameSurname;
    firstStudent.setNameSurname(nameSurname);

    double averageGrade = firstStudent.getAverageGrade();
    averageGrade += 1;
    firstStudent.setAverageGrade(averageGrade);

    System.out.println("afterReturningGetStudentsLoggingAdvice: Logging the successful retrieval "
        + "of students list");
    System.out.println("__________________________");
  }

  /**
   * After throwing advice for the getStudents method in University.
   * @param exception Throwable object.
   */
  @AfterThrowing(
      pointcut = "execution(* com.philip.spring.spring_course.aop.University.getStudents())",
      throwing = "exception")
  public void
  afterThrowingGetStudentsLoggingAdvice(Throwable exception) {
    System.out.println("afterThrowingGetStudentsLoggingAdvice: Logging the exception thrown while "
        + "getting students list");
    System.out.println("Exception: " + exception.getMessage());
    System.out.println("__________________________");
  }

  /**
   * After advice for the getStudents method in University.
   */
  @After("execution(* com.philip.spring.spring_course.aop.University.getStudents())")
  public void afterFinallyGetStudentsLoggingAdvice() {
    System.out.println("afterFinallyGetStudentsLoggingAdvice: Logging the completion of "
        + "getStudents method execution");
    System.out.println("__________________________");
  }
}
