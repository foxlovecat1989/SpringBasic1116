package jdbc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test1 {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
    JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        
    @Test
    public void t1() {
        // 若是有印出資料, 代表配置成功
        System.out.println(jdbcTemplate); 
    }
}
