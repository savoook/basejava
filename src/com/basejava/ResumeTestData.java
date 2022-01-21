package com.basejava;

import com.basejava.model.Resume;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);

        /*r.addContact(ContactType.PHONE, "+7(921) 855-0482");
        r.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        r.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        r.addContact(ContactType.HOME_PAGE, "http://gkislin.ru");
        r.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        r.addContact(ContactType.SKYPE, "grigory.kislin");
        r.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");

        r.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика," +
                " креативность, инициативность. Пурист кода и архитектуры."));

        r.addSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов \"Разработка Web" +
                " приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб" +
                " сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение" +
                " проектов. Более 1000 выпускников.\"," +
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с" +
                " Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\"," +
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita" +
                " BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery." +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java" +
                " сервера.\"," +
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC," +
                " GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\"," +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base" +
                " архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через" +
                " систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы" +
                " по JMX (Jython/ Django).\"" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, " +
                "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\""));

        r.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, " +
                "Tomcat, Jetty, WebLogic, WSO2" +
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce" +
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle," +
                "MySQL, SQLite, MS SQL, HSQLDB" +
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy," +
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts," +
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, " +
                "Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, " +
                "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)." +
                "Python: Django." +
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js" +
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka" +
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB," +
                " JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT." +
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix," +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, " +
                "OpenCmis, Bonita, pgBouncer." +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов," +
                " UML, функционального программирования" +
                "Родной русский, английский \"upper intermediate\""));

        r.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Java Online Projects",
                "https://javaops.ru",
                new Organization.Position((LocalDate.of(2013, 10, 1)),
                        LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн " +
                        "проектов и стажировок."), new Organization.Position(LocalDate.of(2012, 10, 1),
                LocalDate.of(2013, 10, 1), "Автор проекта.", "Создание," +
                " организация и проведение Java онлайн проектов и стажировок."))));

        r.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Wrike", null,
                new Organization.Position(LocalDate.of(2014, 10, 1),
                        LocalDate.of(2016, 1, 1), "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike" +
                                " (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));

        r.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("Coursera",
                "https://www.coursera.org/learn/scala-functional-programming",
                new Organization.Position(LocalDate.of(2013, 3, 1),
                        LocalDate.of(2013, 5, 1), "\"Functional Programming Principles in " +
                        "Scala\" by Martin Odersky", null))));

        r.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("Luxoft",
                "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                new Organization.Position(LocalDate.of(2011, 3, 1),
                        LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. " +
                        "Концептуальное моделирование на UML.\"", ""))));
       */ return r;
    }
}
