package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.reservation.ReservationUserComment;

@Repository
public class ReservationUserCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
	
	final String SELECT_BY_PRODUCT = "SELECT * FROM reservation_user_comment WHERE product_id= :productId";
	
	public ReservationUserCommentDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ReservationUserComment> getReservationUserComments(int productId){
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		return jdbc.query(SELECT_BY_PRODUCT, params, rowMapper);
	}
}
