package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.promotion.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
	@Autowired
	private PromotionDao promotionDao;
	
	@Override
	@Transactional
	public List<Promotion> getPromotions() {
		List<Promotion> list = promotionDao.getPromotionList();
		return list;
	}
}
