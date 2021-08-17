package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.FileInfoDao;
import kr.or.connect.reservation.dto.fileInfo.FileInfo;
import kr.or.connect.reservation.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService{
	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Override
	@Transactional
	public FileInfo getFileInfo(int id) {
		return fileInfoDao.getFileInfo(id);
	}
}
