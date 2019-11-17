package com.artyomefimov.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = WebAppConfig.class)
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.viewResolver(internalResourceViewResolver());
    }

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        //viewResolver.setPrefix("/WEB-INF/classes/static/");
//        viewResolver.setPrefix("/static/");
//        viewResolver.setSuffix(".html");
//        return viewResolver;
//    }

    public class ReactResourceResolver implements ResourceResolver {
        private static final String REACT_DIR = "/resources/static/";

        private static final String REACT_STATIC_DIR = "static";

        private Resource index = new ClassPathResource(REACT_DIR + "index.html");
        private List<String> rootStaticFiles = Arrays.asList("favicon.io",
                "asset-manifest.json", "manifest.json", "service-worker.js");

        @Override
        public Resource resolveResource(
                HttpServletRequest request, String requestPath,
                List<? extends Resource> locations, ResourceResolverChain chain) {
            return resolve(requestPath);
        }

        @Override
        public String resolveUrlPath(
                String resourcePath, List<? extends Resource> locations,
                ResourceResolverChain chain) {
            Resource resolvedResource = resolve(resourcePath);
            if (resolvedResource == null) {
                return null;
            }
            try {
                return resolvedResource.getURL().toString();
            } catch (IOException e) {
                return resolvedResource.getFilename();
            }
        }

        private Resource resolve(String requestPath) {
            System.out.println("searching for request path " + requestPath);
            if (requestPath == null) return null;

            if (rootStaticFiles.contains(requestPath)
                    || requestPath.startsWith(REACT_STATIC_DIR))
                return new ClassPathResource(REACT_DIR + requestPath);
            else
                return index;
        }

    }
}