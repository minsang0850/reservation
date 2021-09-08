package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.reservation.ReservationUserCommentImage;
import kr.or.connect.reservation.dto.user.*;

@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
	
	final String SELECT_BY_ID = "SELECT * FROM user WHERE id = :id";
	
	public UserDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User getUser(int id){
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		List<User> list = jdbc.query(SELECT_BY_ID, params, rowMapper);
		return list.get(0);
	}
	
	public User getMemberByEmail(String email) {
		Map<String,Object> map = new HashMap<>();
		map.put("email",email);
		return jdbc.queryForObject(UserDaoSqls.SELECT_ALL_BY_EMAIL,map,rowMapper);
	}
}
