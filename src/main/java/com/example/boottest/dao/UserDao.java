//package com.example.boottest.dao;
//
//import com.example.boottest.dao.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Repository
//public class UserDao {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public int getTotal(){
//        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
//        return rowCount;
//    }
//
//    public User getUserById(int id){
//        User user = jdbcTemplate.queryForObject("select * from user where id=?", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("name"));
//                user.setDepartmentId(rs.getInt("department_id"));
//                return user;
//            }
//        }, id);
//        return user;
//    }
//
//    public Integer insert(User user) {
//        String sql = "insert into user (name,department_id) values (?,?)";
//        //KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        System.out.println(sql);
//        jdbcTemplate.execute(sql);
//        return 0;
//    }
//
//    public void update(User user){
//        String sql = "update user set name=? and department_id=? where id=?";
//        System.out.println(sql);
//        System.out.println("name:" + user.getName() + ", id=" + user.getId());
//        jdbcTemplate.update(sql, user.getName(), user.getDepartmentId(), user.getId());
//    }
//}
