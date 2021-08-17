package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.displayInfo.DisplayInfoImage;
import org.springframework.stereotype.Service;

@Service
public interface DisplayInfoImageService {
	public List<DisplayInfoImage> getDisplayInfoImages(int displayId);
}
