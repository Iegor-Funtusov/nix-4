package ua.com.alevel.db_all_circle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ua.com.alevel.db_all_circle.dao")
public class DbAllCircleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbAllCircleApplication.class, args);
    }
}
