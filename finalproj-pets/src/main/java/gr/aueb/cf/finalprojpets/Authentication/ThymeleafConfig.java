package gr.aueb.cf.finalprojpets.Authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {

    // Configures resource handling for static resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


    // Defines a template resolver for Thymeleaf templates
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

        // Specifies the prefix and suffix for template resolution
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        // Sets the template mode to HTML
        resolver.setTemplateMode(TemplateMode.HTML);

        return resolver;
    }

}
