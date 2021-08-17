package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.user.*;
import kr.or.connect.reservation.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	public User getUser(int id) {
		return userDao.getUser(id);
	}
}
