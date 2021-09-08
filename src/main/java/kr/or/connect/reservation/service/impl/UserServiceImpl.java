package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dao.UserRoleDao;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.user.*;
import kr.or.connect.reservation.service.UserService;
import kr.or.connect.reservation.service.security.UserEntity;
import kr.or.connect.reservation.service.security.UserRoleEntity;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	private UserRoleDao userRoleDao;
	
	public User getUser(int id) {
		return userDao.getUser(id);
	}
	
	public UserServiceImpl(UserDao userDao, UserRoleDao userRoleDao) {
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }
	
	@Override
    public UserEntity getUser(String loginUserId) {
		User user = userDao.getMemberByEmail(loginUserId);
        return new UserEntity(user.getEmail(), user.getPassword());
	}

    @Override
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
    	List<UserRole> userRoles = userRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for(UserRole userRole : userRoles) {
            list.add(new UserRoleEntity(loginUserId, userRole.getRoleName()));
        }
        return list;
    }
}
