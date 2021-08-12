package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.promotion.Promotion;

@Service
public interface PromotionService {
	public List<Promotion> getPromotions();
}
