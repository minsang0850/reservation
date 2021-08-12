package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.displayInfo.DisplayInfo;

import org.springframework.stereotype.Service;

@Service
public interface DisplayInfoService {
	public int getCount(int productId);
	public List<DisplayInfo> getDisplayInfos(int productId);
}
