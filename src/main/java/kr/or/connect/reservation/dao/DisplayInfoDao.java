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

import kr.or.connect.reservation.dto.displayInfo.DisplayInfo;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	//private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	
	final String SELECT_BY_PRODUCT = "SELECT * FROM display_info WHERE product_id= :product_id";
	final String SELECT_BY_ID = "SELECT * FROM display_info WHERE id= :id";
	final String SELECT_COUNT = "SELECT COUNT(*) FROM display_info WHERE product_id= :product_id";
	
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<DisplayInfo> getDisplayInfos(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);
		return jdbc.query(SELECT_BY_PRODUCT, params, rowMapper);
	}
	
	public DisplayInfo getDisplayInfo(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		List<DisplayInfo> list = jdbc.query(SELECT_BY_ID, params, rowMapper);
		return list.get(0);
	}
	
	public int getCount(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);
		return jdbc.queryForObject(SELECT_COUNT, params, Integer.class);
	}
}
