package com.sunsilong.springboot.huiji;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootHuijiApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootHuijiApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootHuijiApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        Environment env = app.run(args).getEnvironment();
        String port = env.getProperty("server.port");
        logger.info("\nAccess URLs:\n----------------------------------------------------------\n" + "Local: \t\thttp://127.0.0.1:{}/swagger-ui.html\n" +
                "----------------------------------------------------------", port);
    }

    /**
     * If no profile has been configured, set by default the "dev" profile.
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            app.setAdditionalProfiles("dev");
        }
    }
}
