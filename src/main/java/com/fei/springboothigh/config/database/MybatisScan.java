package com.fei.springboothigh.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/5/27
 * @Description: Druid连接池配置类，顺带扫描mapper接口
 */
@Configuration
@MapperScan("com.fei.springboothigh.mapper")
public class MybatisScan {

    /**
     * 将DruidDataSource加入到Spring容器中，并绑定前缀进而配置一些属性
     */
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控，配置一个管理后台 注册一个servlet，后面的请求地址就是对应的映射
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置监控的filter
     */
    @Bean
    public FilterRegistrationBean webstatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
