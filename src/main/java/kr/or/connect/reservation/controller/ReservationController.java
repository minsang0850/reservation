package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.controller.*;
import kr.or.connect.reservation.controller.ReservationControllerObject;
import kr.or.connect.reservation.dto.reservation.*;
import kr.or.connect.reservation.dto.product.*;
import kr.or.connect.reservation.service.*;

@RestController
@RequestMapping(path="/api")		//api로 요청이 들어오면 매핑
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping (path="/reservationInfos")
	public Map<String, Object> postReservationInfo(@RequestBody ReservationPostRequestVO reservationPostRequestVO,
			HttpServletRequest request) {
		Map<String,Object> map=new HashMap<>();
		//ReservationPostVO reservationPostVO=new ReservationPostVO();
		ReservationInfo reservationInfo=new ReservationInfo();
		ReservationInfoPrice reservationInfoPrice=new ReservationInfoPrice();
		
		map.put("productId",reservationPostRequestVO.getProductId());
		map.put("displayInfoId",reservationPostRequestVO.getDisplayInfoId());
		map.put("userId",reservationPostRequestVO.getUserId());
		reservationInfo.setProductId(reservationPostRequestVO.getProductId());
		reservationInfo.setDisplayInfoId(reservationPostRequestVO.getDisplayInfoId());
		reservationInfo.setUserId(reservationPostRequestVO.getUserId());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		map.put("createDate",timestamp);
		map.put("modifyDate",timestamp);
		reservationInfo.setCreateDate(timestamp);
		reservationInfo.setModifyDate(timestamp);
		map.put("cancelFlag",0);
		reservationInfo.setCancelFlag(0);
		
		String str=reservationPostRequestVO.getReservationYearMonthDay();
		int year=Integer.parseInt(str.substring(0, 4));
		int month=Integer.parseInt(str.substring(5, 7));
		int day=Integer.parseInt(str.substring(8, 10));
		//timestamp=year+month+day;
		reservationInfo.setReservationDate(timestamp);
		reservationInfo = reservationService.postReservation(reservationInfo);
		map.put("id",reservationInfo.getId());
		List<Price> priceList = reservationPostRequestVO.getPrices();
		reservationInfoPrice.setCount(priceList.get(0).getCount());
		reservationInfoPrice.setProductPriceId(priceList.get(0).getProductPriceId());
		reservationInfoPrice.setReservationInfoId(reservationInfo.getId());
		List<ReservationInfoPrice> prices = new ArrayList<>();
		prices.add(reservationService.getPrice(reservationInfoPrice));
		map.put("prices",prices);
		return map;
	}
	
	@GetMapping	(path="/reservationInfos")						//Get method로 요청이 오면
	public Map<String, Object> getReservationInfo() {
		Map<String, Object> map = new HashMap<>();
		int userId=1;	//수정 필요
		List<ReservationInfo> list = reservationService.getReservationInfos(userId);
		int size=list.size();
		List<ReservationGetVO> itemList = new ArrayList<>();
		for(int i=0; i<size; i++) {
			ReservationInfo reservationInfo = list.get(i);
			int productId=reservationInfo.getProductId();
			Product product = productService.getProduct(productId);
			ReservationGetVO item = new ReservationGetVO();
			item.setCancelFlag(reservationInfo.getCancelFlag());
			item.setCreateDate(reservationInfo.getCreateDate());
			item.setDisplayInfoId(reservationInfo.getDisplayInfoId());
			item.setId(reservationInfo.getId());
			item.setModifyDate(reservationInfo.getModifyDate());
			item.setProductContent(product.getContent());
			item.setProductDescription(product.getDescription());
			item.setProductId(productId);
			item.setReservationDate(reservationInfo.getReservationDate());
			item.setSumPrice(1000);	//수정 필요
			item.setUserId(userId);
			itemList.add(item);
		}
		map.put("items",itemList);
		map.put("size",size);
		return map;
	}
	
	@PutMapping	(path="/reservationInfos")
	public Map<String, Object> cancelReservation(@RequestBody ReservationPutRequestVO VO) {
		Map<String, Object> map = new HashMap<>();
		boolean check = reservationService.cancelReservation(VO.getId());
		if(check)
			map.put("result","success");
		else
			map.put("result","fail");
		return map;
	}
}
