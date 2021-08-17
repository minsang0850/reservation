package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.displayInfo.DisplayInfo;
import kr.or.connect.reservation.dto.displayInfo.DisplayInfoImage;

import org.springframework.stereotype.Service;

@Service
public interface DisplayInfoService {
	public int getCount(int productId);
	public List<DisplayInfo> getDisplayInfos(int productId);
	public DisplayInfo getDisplayInfo(int id);
	public List<DisplayInfoImage> getDisplayInfoImages(int displayId);
}
