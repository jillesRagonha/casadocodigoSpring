package br.com.agilles.casadocodigoSpring.loja.conf;

import br.com.agilles.casadocodigoSpring.loja.controller.HomeController;
import br.com.agilles.casadocodigoSpring.loja.dao.ProdutoDao;
import br.com.agilles.casadocodigoSpring.loja.infra.FileSaver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDao.class, FileSaver.class})
public class AppWebConfiguration {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setCacheSeconds(1);
        source.setBasename("/WEB-INF/message");
        source.setDefaultEncoding("UTF-8");
        return source;

    }

    @Bean
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
        DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
        dateFormatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
        dateFormatterRegistrar.registerFormatters(defaultFormattingConversionService);
        return defaultFormattingConversionService;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


}
