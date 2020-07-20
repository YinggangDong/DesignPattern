package cn.dyg.designpattern.command.spring;

import cn.dyg.designpattern.command.spring.entity.JdbcTestVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.List;

/**
 * SpringSourceCodeTest 类是 命令模式在Spring源码中的体现
 *
 * @author dongyinggang
 * @date 2020-07-15 19:47
 **/
public class SpringSourceCodeTest {

    public static void main(String[] args) {
        JdbcTemplateDemo jdbcTemplateDemo = new JdbcTemplateDemo();
        JdbcTemplate jdbcTemplate = jdbcTemplateDemo.init();
        String sql = "select * from t_test order by id desc";
        //调用query方法,query方法即invoke,绑定了一个QueryStatementCallback对象
        List<JdbcTestVO> list =  jdbcTemplate.query(sql,new BeanPropertyRowMapper(JdbcTestVO.class));
        for (JdbcTestVO jdbcTestVO : list) {
            System.out.println(jdbcTestVO.getId());
        }
    }
}
