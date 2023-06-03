package com.softwaredesign.englishcenter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.softwaredesign.englishcenter.controller.HomeController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class })
public class ThymeleafConfig implements WebMvcConfigurer, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public ViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setApplicationContext(this.applicationContext);
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/static/templates/");
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/templates/assets/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/templates/images/");
		registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/templates/vendor/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/templates/fonts/");
	}

}
