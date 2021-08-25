package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.reservation.ReservationInfoPrice;

@Repository
public class ReservationInfoPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfoPriceDao> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfoPriceDao.class);

	final String INSERT_RESERVATION = "INSERT INTO reservation_info_price (reservation_info_id,product_price_id,count) values "
			+ "(:reservationInfoId, :productPriceId, :count)";
	
	public ReservationInfoPriceDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ReservationInfoPrice getPrice(ReservationInfoPrice reservationInfoPrice){
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(reservationInfoPrice);
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION, paramSource, generatedKeyHolder);
		int id = generatedKeyHolder.getKey().intValue();
		reservationInfoPrice.setId(id);
		return reservationInfoPrice;
	}
}
