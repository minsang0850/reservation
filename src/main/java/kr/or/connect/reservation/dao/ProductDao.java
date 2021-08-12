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
import kr.or.connect.reservation.dto.product.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	final String SELECT_COUNT = "SELECT COUNT(*) from product where id = :id";
	final String SELECT_BY_CATEGORY = "SELECt * FROM product WHERE category_id = :id";
	final String SELECT_BY_ID = "SELECt * FROM product WHERE id = :id";
	public ProductDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int getProductCount(int id) {
		//Map<String, ?> params = Collections.singletonMap("id", id);
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(SELECT_COUNT, params, Integer.class);
	}
	
	public List<Product> getProductList(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		return jdbc.query(SELECT_BY_CATEGORY, params, rowMapper);
	}
	
	public Product getProduct(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		List<Product> list = jdbc.query(SELECT_BY_ID, params, rowMapper);
		return list.get(0);
	}
}
