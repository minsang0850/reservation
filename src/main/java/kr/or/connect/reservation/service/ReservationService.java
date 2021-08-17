package kr.or.connect.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.reservation.ReservationUserComment;

@Service
public interface ReservationService {
	public List<ReservationUserComment> getReservationUserComments(int productId);
}
