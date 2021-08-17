package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dto.category.Category;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.product.ProductImage;
import kr.or.connect.reservation.dto.product.ProductPrice;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImageDao productImageDao;
	
	@Autowired
	ProductPriceDao productPriceDao;
	
	@Override
	@Transactional
	public int getCount(int id) {
		return productDao.getProductCount(id);
	}
	
	@Override
	@Transactional
	public List<Product> getProducts(int id) {
		List<Product> list = productDao.getProductList(id);
		return list;
	}
	
	@Override
	@Transactional
	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}
	
	@Override
	@Transactional
	public List<ProductPrice> getProductPrices(int productId){
		return productPriceDao.getProductPrices(productId);
	}
	
	@Override
	@Transactional
	public List<ProductImage> getProductImages(int productId){
		return productImageDao.getProductImages(productId);
	}
}
