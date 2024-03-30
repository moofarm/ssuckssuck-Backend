package moofarm.ssuckssuck.global.config;

import moofarm.ssuckssuck.global.property.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class})
public class ConfigurationPropertiesConfig {
}
