package kz.iitu.kettik.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSpringDataWebSupport
public class SpringFoxConfiguration extends WebMvcConfigurationSupport {
//    @Value("${pagination.default.pageSize}")
//    private int pageSize;
//
//    @Value("${pagination.default.page}")
//    private int page;

//    @Bean
//    public Docket API()
//    {
//        return new Docket(DocumentationType.SWAGGER_2)
//                                                    .select()
//                                                    .apis(RequestHandlerSelectors.any())
//                                                    .paths(PathSelectors.any())
//                                                    .build();
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
//    {
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setFallbackPageable(PageRequest.of(page, pageSize));
//        argumentResolvers.add(resolver);
//        super.addArgumentResolvers(argumentResolvers);
//    }
}
