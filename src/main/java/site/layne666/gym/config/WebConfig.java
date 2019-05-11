package site.layne666.gym.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.layne666.gym.interceptor.LoginInterceptor;

/**
 * @author layne666
 * @date 2019/05/11
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir")+"//src//main//resources//static//upload//";
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+path);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/start").excludePathPatterns("/css/**","/assets/**","/js/**","/img/**","/upload/**");
    }
}
