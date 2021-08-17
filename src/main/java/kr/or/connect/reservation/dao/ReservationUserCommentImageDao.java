package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.promotion.Promotion;
import kr.or.connect.reservation.dto.reservation.ReservationUserCommentImage;

@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentImage> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);
	
	final String SELECT_BY_ID = "SELECT * FROM reservation_user_comment WHERE product_id= :productId";
	
	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ReservationUserCommentImage> getReservationUserCommentImages(int id){
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", id);
		return jdbc.query(SELECT_BY_ID, params, rowMapper);
	}
}