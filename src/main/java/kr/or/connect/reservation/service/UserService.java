package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.user.*;
import kr.or.connect.reservation.service.security.UserDbService;

@Service
public interface UserService extends UserDbService{
	public User getUser(int id);
	//	public List<ReservationUserComment> getReservationUserComments(int productId);
//	public List<ReservationUserCommentImage> getReservationUserCommentImages(int commentId);
}
