package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.product.Product;

@Service
public interface ProductService {
	public int getCount(int id);
	public List<Product> getProducts(int id);
}
