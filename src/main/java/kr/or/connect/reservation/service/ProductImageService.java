package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.product.ProductImage;

@Service
public interface ProductImageService {
	//public int getCount(int id);
	public List<ProductImage> getProductImages(int id);
	//public Product getProduct(int id);
}
