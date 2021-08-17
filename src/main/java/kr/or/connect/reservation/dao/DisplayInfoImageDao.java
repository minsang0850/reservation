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
import kr.or.connect.reservation.dto.displayInfo.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	//private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	final String SELECT_BY_DISPLAY = "SELECT * FROM display_info_image WHERE display_info_id= :id";
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<DisplayInfoImage> getDisplayInfoImages(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", displayInfoId);
		List<DisplayInfoImage> list= jdbc.query(SELECT_BY_DISPLAY, params, rowMapper);
		return list;
	}
}
