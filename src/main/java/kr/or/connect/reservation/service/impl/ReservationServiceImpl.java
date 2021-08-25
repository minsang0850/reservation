package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dao.ReservationUserCommentDao;
import kr.or.connect.reservation.dao.ReservationUserCommentImageDao;
import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoPriceDao;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.dto.reservation.*;
import kr.or.connect.reservation.service.ReservationService;


@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationUserCommentDao reservationUserCommentDao;
	
	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;
	
	@Autowired
	private ReservationInfoDao reservationInfoDao;
	
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;
	
	@Override
	@Transactional
	public List<ReservationUserComment> getReservationUserComments(int productId) {
		List<ReservationUserComment> list = reservationUserCommentDao.getReservationUserComments(productId);
		return list;
	}
	
	@Override
	@Transactional
	public List<ReservationUserCommentImage> getReservationUserCommentImages(int commentId){
		return  reservationUserCommentImageDao.getReservationUserCommentImages(commentId);
	}
	
	@Override
	@Transactional
	public ReservationInfo postReservation(ReservationInfo reservationInfo){
		return reservationInfoDao.postReservation(reservationInfo);
	}
	
	@Override
	@Transactional
	public ReservationInfoPrice getPrice(ReservationInfoPrice reservationInfoPrice) {
		return reservationInfoPriceDao.getPrice(reservationInfoPrice);
		
	}
	
	@Override
	@Transactional
	public List<ReservationInfo> getReservationInfos(int userId){
		return reservationInfoDao.getReservationInfos(userId);
	}
	
	@Override
	@Transactional
	public boolean cancelReservation(int id) {
		ReservationInfo reservation = reservationInfoDao.getReservationInfo(id);
		if(reservation.getUserId()!=1 || reservation.getCancelFlag()==1) 
			return false;
		else {
			reservationInfoDao.cancelReservation(id);
			return true;
		}
	}
}
