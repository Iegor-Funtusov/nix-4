package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import ua.com.alevel.service.init.InitDbService;

import java.io.IOException;

@SpringBootApplication
public class Application {

    private final InitDbService initDbService;

    public Application(InitDbService initDbService) {
        this.initDbService = initDbService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initMigration() throws IOException {
        initDbService.init();
    }
}
