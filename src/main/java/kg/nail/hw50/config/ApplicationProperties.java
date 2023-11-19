package kg.nail.hw50.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Getter
@Setter
public class ApplicationProperties {
    private String uploadDir = "src/main/resources/images";
}
