package cn.dyg.designpattern.command.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate 类是 jdbc连接demo
 *
 * @author dongyinggang
 * @date 2020-07-16 11:49
 **/
public class JdbcTemplateDemo {

    public JdbcTemplate init(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return new JdbcTemplate(dataSource);
    }
}
