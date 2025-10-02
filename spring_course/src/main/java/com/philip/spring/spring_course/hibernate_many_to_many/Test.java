package com.philip.spring.spring_course.hibernate_many_to_many;

import com.philip.spring.spring_course.hibernate_many_to_many.entity.Child;
import com.philip.spring.spring_course.hibernate_many_to_many.entity.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
  public static void main(String[] args) {
    SessionFactory factory = new Configuration()
                                 .configure("hibernate.cfg.xml")
                                 .addAnnotatedClass(Child.class)
                                 .addAnnotatedClass(Section.class)
                                 .buildSessionFactory();

    Session session = null;

    try {
      // session = factory.getCurrentSession();

      // Section section = new Section("Section 1");
      // Child child = new Child("Alexander", 5);
      // Child child2 = new Child("Philip", 7);
      // Child child3 = new Child("Alex", 6);
      // section.addChildToSection(child);
      // section.addChildToSection(child2);
      // section.addChildToSection(child3);

      // session.beginTransaction();

      // session.persist(section);

      // session.getTransaction().commit();
      // System.out.println("Done!");
      //************************************** */

      // session = factory.getCurrentSession();

      // Section section = new Section("Section 2");
      // Section section2 = new Section("Section 3");
      // Section section3 = new Section("Section 4");
      // Child child = new Child("Igor", 10);
      // child.addSectionToChild(section);
      // child.addSectionToChild(section2);
      // child.addSectionToChild(section3);

      // session.beginTransaction();

      // session.persist(child);

      // session.getTransaction().commit();
      // System.out.println("Done!");
      //************************************** */

      // session = factory.getCurrentSession();

      // session.beginTransaction();

      // Section section = session.find(Section.class, 1);

      // Child child = session.find(Child.class, 5);
      // System.out.println(section);
      // System.out.println(section.getChildren());
      // System.out.println();
      // System.out.println(child);
      // System.out.println(child.getSections());

      // session.getTransaction().commit();
      // System.out.println("Done!");

      //************************************** */

      session = factory.getCurrentSession();

      session.beginTransaction();

      Section section = session.find(Section.class, 1);

      session.remove(section);

      session.getTransaction().commit();
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }
}
