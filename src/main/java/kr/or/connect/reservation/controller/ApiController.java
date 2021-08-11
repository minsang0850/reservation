package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.category.Category;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.dto.product.Product;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.dto.displayInfo.DisplayInfo;
import kr.or.connect.reservation.service.DisplayInfoService;

class Item{
	private int id;
	private String name;
	private int count;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}

@RestController
@RequestMapping(path="/api")		//api로 요청이 들어오면 매핑
public class ApiController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	DisplayInfoService displayInfoService;
	
	@GetMapping	(path="/categories")						//Get method로 요청이 오면
	public Map<String, Object> categories() {
		Map<String, Object> map = new HashMap<>();
		List<Category> categoryList=categoryService.getCategories();
		List<Item> items=new ArrayList<Item>();
		int size=categoryList.size();
		map.put("size", size);
		for(int i=0; i<size; i++) {
			int id=categoryList.get(i).getId();
			String name=categoryList.get(i).getName();
			int count=0;
			List<Product> productList=productService.getProducts(id);
			//if(i==0) map.put("productList",productList);
			for(int j=0; j<productList.size(); j++) {
				int productId = productList.get(j).getId();
				System.out.println("productId:"+productId);
				count+=displayInfoService.getCount(productId);
			}
			Item item=new Item();
			item.setId(id);
			item.setName(name);
			item.setCount(count);
			items.add(item);
		}
		map.put("items",items);
		return map;
	}
	/*
	@GetMapping	(path="/displayinfos")
	public Map<String, Object> displayinfos( 
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start
			){
		Map<String, Object> map = new HashMap<>();
		List<Product> productList=productService.getProducts(categoryId);
		int totalCount=0;
		int productCount=0;
		for(int i=0; i<productCount; i++) {
			int id=productList.get(i).getId();
		}
		return map;
	}
	*/
}