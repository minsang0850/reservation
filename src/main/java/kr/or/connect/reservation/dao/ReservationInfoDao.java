package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.reservation.ReservationInfo;
import kr.or.connect.reservation.dto.reservation.ReservationUserComment;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	final String INSERT_RESERVATION = "INSERT INTO reservation_info (product_id, display_info_id, user_id, reservation_date," + 
			"cancel_flag, create_date, modify_date) values (:productId, :displayInfoId, :userId, :reservationDate," +
			 ":cancelFlag, :createDate, :modifyDate)";
	final String SELECT_BY_USERID = "SELECT * FROM reservation_info WHERE user_id = :userId";
	final String SELECT_BY_ID = "SELECT * FROM reservation_info WHERE id = :id";
	final String CANCEL = "UPDATE reservation_info SET cancel_flag=1 WHERE id= :id";
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ReservationInfo postReservation(ReservationInfo reservationInfo){
//		Map<String, Object> params = new HashMap<>();
//		params.put("productId", reservationInfo.getProductId());
//		params.put("displayInfoId", reservationInfo.getDisplayInfoId());
//		params.put("userId", reservationInfo.getUserId());
//		params.put("reservationDate", reservationInfo.getReservationDate());
//		params.put("cancelFlag",reservationInfo.getCancelFlag());
//		params.put("createDate",reservationInfo.getCreateDate());
//		params.put("modifyDate",reservationInfo.getModifyDate());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(reservationInfo);
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION, paramSource, generatedKeyHolder);
		int id = generatedKeyHolder.getKey().intValue();
		reservationInfo.setId(id);
		return reservationInfo;
	}
	
	public List<ReservationInfo> getReservationInfos(int userId){
		Map<String, Integer> params = new HashMap<>();
		params.put("userId", userId);
		List<ReservationInfo> list = jdbc.query(SELECT_BY_USERID, params, rowMapper);
		return list;
	}
	
	public ReservationInfo getReservationInfo(int id){
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		List<ReservationInfo> list = jdbc.query(SELECT_BY_ID, params, rowMapper);
		return list.get(0);
//		if(list.size()!=0)
//			return list.get(0);
//		ReservationInfo tmp = new ReservationInfo();
//		return tmp;
	}
	
	public void cancelReservation(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		jdbc.update(CANCEL, params);
		return;
	}
}
