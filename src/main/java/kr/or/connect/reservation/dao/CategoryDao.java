package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.category.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	//private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	final String SELECT_ALL = "SELECT id, name from category";
	final String SELECT_ONE = "SELECT id, name FROM category WHERE id= :id";
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Category> getCategoryList() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}
	
	public Category getCategory(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		List<Category> list=jdbc.query(SELECT_ONE, params, rowMapper);
		return list.get(0);
	}
}
