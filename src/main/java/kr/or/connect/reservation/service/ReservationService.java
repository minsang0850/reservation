package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.reservation.*;

@Service
public interface ReservationService {
	public List<ReservationUserComment> getReservationUserComments(int productId);
	public List<ReservationUserCommentImage> getReservationUserCommentImages(int commentId);
}
