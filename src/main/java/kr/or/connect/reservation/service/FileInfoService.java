package kr.or.connect.reservation.service;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.fileInfo.FileInfo;

@Service
public interface FileInfoService {
	public FileInfo getFileInfo(int id);
}
