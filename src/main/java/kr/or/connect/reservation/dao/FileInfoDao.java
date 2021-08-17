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
import kr.or.connect.reservation.dto.fileInfo.FileInfo;
import kr.or.connect.reservation.dto.product.Product;

@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	
	final String SELECT_BY_ID = "SELECT * FROM file_info WHERE id = :id";
	
	public FileInfoDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	public FileInfo getFileInfo(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		List<FileInfo> list = jdbc.query(SELECT_BY_ID, params, rowMapper);
		return list.get(0);
	}
	
}
