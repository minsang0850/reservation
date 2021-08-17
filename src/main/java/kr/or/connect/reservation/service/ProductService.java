package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.product.ProductImage;
import kr.or.connect.reservation.dto.product.ProductPrice;

@Service
public interface ProductService {
	public int getCount(int id);
	public List<Product> getProducts(int id);
	public Product getProduct(int id);
	public List<ProductPrice> getProductPrices(int productId);
	public List<ProductImage> getProductImages(int id);
}
