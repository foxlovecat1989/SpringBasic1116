package jdbc;

import java.util.List;
import java.util.Map;
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
        // create();
        // create("DeleteMe", 19, "男");
        // updateAgeById(1, 20);
        // deleteById(3);
        // readAll();
        readAvgOfAge();
    }
    
    // CRUD - Read 查詢平均年齡
    private void readAvgOfAge() {
        String sql = "SELECT avg(cast(age as double)) FROM APP.EMP ";
        double avg = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println("avg=" + avg);
    }
    
    // CRUD - Read 查詢
    private void readAll() {
        
        // 查詢所有資料
        String sql = "SELECT eid, ename, age, sex, ct FROM Emp";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        list.stream()
//           .forEach(System.out::println);
        
        // 查詢總年齡
        int sum = list.stream()
                .mapToInt(m -> Integer.parseInt(m.get("age")+""))
                .sum();
        // System.out.println(sum);
        
        // 查詢平均年齡
        double avg = list.stream()
                .mapToInt(m -> Integer.parseInt(m.get("age")+""))
                .average()
                .getAsDouble();
        System.out.println(avg);
    }
    
     // CRUD = Delete 刪除資料
    private void deleteById(Integer eid) {
        String sql = "Delete From Emp Where eid = ?";
        jdbcTemplate.update(sql, eid);
    }
    
     // CRUD - Update 更新資料
    private void updateAgeById(Integer eid, Integer age) {
        String sql = "Update Emp Set age=? Where eid=?";
        jdbcTemplate.update(sql, age, eid);
    }
    
    // CRUD - Create 新增資料2
    private void create(String ename, Integer age, String sex) {
        String sql = "Insert Into Emp(ename, age, sex) Values(?, ?, ?)";
        jdbcTemplate.update(sql, ename, age, sex);
    }
    
    // CRUD - Create 新增資料
    private void create() {
        String sql = "Insert Into Emp(ename, age, sex) Values('John', 18, '男')";
        jdbcTemplate.update(sql);
    }

}
