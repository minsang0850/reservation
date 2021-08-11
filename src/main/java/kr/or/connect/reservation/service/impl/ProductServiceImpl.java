package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.category.Category;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional
	public int getCount(int id) {
		return productDao.getProductCount(id);
	}
	
	public List<Product> getProducts(int id) {
		List<Product> list = productDao.getProductList(id);
		return list;
	}
}
