package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dto.displayInfo.DisplayInfo;
import kr.or.connect.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService{
	@Autowired
	private DisplayInfoDao displayInfoDao;
	
	@Override
	@Transactional
	public int getCount(int productId) {
		return displayInfoDao.getCount(productId);
	}
	
	@Override
	@Transactional
	public List<DisplayInfo> getDisplayInfos(int productId){
		return displayInfoDao.getDisplayInfos(productId);
	}
	
	@Override
	@Transactional
	public DisplayInfo getDisplayInfo(int id){
		return displayInfoDao.getDisplayInfo(id);
	}
}
