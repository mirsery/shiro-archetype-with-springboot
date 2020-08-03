package com.hytiot.example.dtutil.config;

import com.hytiot.example.dtutil.DataTableResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-08-02 09:47
 **/
@Configuration
public class DTWebConfig implements WebMvcConfigurer {

    /**
     * Add resolvers to support custom controller method argument types.
     * <p>This does not override the built-in support for resolving handler
     * method arguments. To customize the built-in support for argument resolution, configure {@link
     * RequestMappingHandlerAdapter} directly.
     *
     * @param resolvers initially an empty list
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(dataTableResolver());
    }

    @Bean
    public DataTableResolver dataTableResolver() {
        return new DataTableResolver();
    }
}