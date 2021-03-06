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
import kr.or.connect.reservation.dto.product.ProductImage;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	
	final String SELECT_BY_PRODUCT = "SELECT * FROM product_image WHERE product_id = :productId";
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductImage> getProductImages(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		return jdbc.query(SELECT_BY_PRODUCT, params, rowMapper);
	}
}
