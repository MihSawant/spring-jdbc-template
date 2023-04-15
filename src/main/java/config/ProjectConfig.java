package config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"service", "repository"})
public class ProjectConfig {


    @Bean
    public DataSource mysqlDatasource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/office")
                .username("root")
                .password("toor")
                .driverClassName(com.mysql.cj.jdbc.Driver.class.getName());

        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
       var jdbcTemplate = new JdbcTemplate();
       jdbcTemplate.setDataSource(mysqlDatasource());
       return jdbcTemplate;
    }
}

