package com.demiurg.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.demiurg.spring.rest")
//   <context:component-scan base-package="com.demiurg.spring.mvc_hibernate_aop"/>
@EnableWebMvc // <mvc:annotation-driven/>
@EnableTransactionManagement   //  <tx:annotation-driven transaction-manager="transactionManager"/>

public class MyConfig {

    @Bean
    public DataSource dateSource() {     //   <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?serverTimezone=Europe/Moscow&useSSL=false");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {     //    <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dateSource());
        sessionFactory.setPackagesToScan("com.demiurg.spring.rest.entity");    //  <property name="packagesToScan" value="com.demiurg.spring.mvc_hibernate_aop.entity"/>

        Properties hibernateProperties = new Properties();    //  <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean//    <bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}

