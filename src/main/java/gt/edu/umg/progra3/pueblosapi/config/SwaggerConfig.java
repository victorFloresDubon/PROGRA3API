package gt.edu.umg.progra3.pueblosapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("Pueblos API")
                        .contact(new Contact()
                                .name("Eduardo")
                                .email("vdubon@outlook.com")
                        )
                        .version("1.0")
                );
    }



}
