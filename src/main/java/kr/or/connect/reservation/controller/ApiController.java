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
import kr.or.connect.reservation.controller.*;
import kr.or.connect.reservation.dto.promotion.Promotion;
import kr.or.connect.reservation.service.PromotionService;
import kr.or.connect.reservation.dto.product.ProductImage;
import kr.or.connect.reservation.dto.fileInfo.FileInfo;
import kr.or.connect.reservation.service.FileInfoService;
import kr.or.connect.reservation.dto.displayInfo.DisplayInfoImage;
import kr.or.connect.reservation.dto.product.ProductPrice;

@RestController
@RequestMapping(path="/api")		//api로 요청이 들어오면 매핑
public class ApiController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	DisplayInfoService displayInfoService;
	
	@Autowired
	PromotionService promotionService;
	
	
	@Autowired
	FileInfoService fileInfoService;
	
	
	
	@GetMapping	(path="/categories")						//Get method로 요청이 오면
	public Map<String, Object> categories() {
		Map<String, Object> map = new HashMap<>();
		List<Category> categoryList=categoryService.getCategories();
		List<CategoryItem> items=new ArrayList<CategoryItem>();
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
				count+=displayInfoService.getCount(productId);
			}
			CategoryItem item=new CategoryItem();
			item.setId(id);
			item.setName(name);
			item.setCount(count);
			items.add(item);
		}
		map.put("items",items);
		return map;
	}
	
	@GetMapping	(path="/displayinfos")
	public Map<String, Object> displayinfos_category( 
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start
			){
		Map<String, Object> map = new HashMap<>();
		List<Product> productList=productService.getProducts(categoryId);
		int totalCount=0;
		int productCount=0;
		List<ProductVO> products = new ArrayList<ProductVO>();
		Category category=categoryService.getCategory(categoryId);
		for(int i=0; i<productList.size(); i++) {
			int productId = productList.get(i).getId();
			totalCount+=displayInfoService.getCount(productId);
			if(i>=start && i<start+4) {
				productCount+=displayInfoService.getCount(productId);
				List<DisplayInfo> list=displayInfoService.getDisplayInfos(productId);
				for(int j=0; j<list.size(); j++) {
					ProductVO productVO = new ProductVO();
					//DisplayInfo displayInfo=list.get(i);
					productVO.setId(productId);
					productVO.setCategoryId(categoryId);
					productVO.setDisplayInfoId(list.get(j).getId());
					productVO.setName(category.getName());	//카테고리 이름
					productVO.setDescription(productList.get(i).getDescription());
					productVO.setContent(productList.get(i).getContent());
					productVO.setEvent(productList.get(i).getEvent());
					productVO.setOpeningHours(list.get(j).getOpeningHours());
					productVO.setPlaceName(list.get(j).getPlaceName());
					productVO.setPlaceLot(list.get(j).getPlaceLot());
					productVO.setPlaceStreet(list.get(j).getPlaceStreet());
					productVO.setTel(list.get(j).getTel());
					productVO.setHomepage(list.get(j).getHomepage());
					productVO.setEmail(list.get(j).getEmail());
					productVO.setCreateDate(list.get(j).getCreateDate());
					productVO.setModifyDate(list.get(j).getModifyDate());
					productVO.setFileId(100);
					products.add(productVO);
				}
			}
		}
		map.put("totalCount",totalCount);
		map.put("productCount",productCount);
		map.put("products",products);
		return map;
	}
	
	@GetMapping	(path="/promotions")						//Get method로 요청이 오면
	public Map<String, Object> promotions() {
		Map<String, Object> map = new HashMap<>();
		List<Promotion> promotionList=promotionService.getPromotions();
		List<PromotionItem> items=new ArrayList<PromotionItem>();
		Product product;
		Category category;
		int size=promotionList.size();
		map.put("size", size);
		for(int i=0; i<size; i++) {
			int id=promotionList.get(i).getId();
			int productId=promotionList.get(i).getProductId();
			product = productService.getProduct(productId);
			int categoryId=product.getCategoryId();
			category = categoryService.getCategory(categoryId);
			String categoryName=category.getName();
			String description=product.getDescription();
			PromotionItem item=new PromotionItem();
			int fileId=10;
			
			item.setId(id);
			item.setProductId(productId);
			item.setCategoryId(categoryId);
			item.setCategoryName(categoryName);
			item.setFileId(fileId);
			item.setDescription(description);
			items.add(item);
		}
		map.put("items",items);
		return map;
	}
	
	@GetMapping(path="/displayinfos/{displayId}")
	public Map<String, Object> displayinfos_displayId(@PathVariable(name="displayId") int displayId,
			HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		ProductVO productVO=new ProductVO();
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayId);
		int productId=displayInfo.getProductId();
		Product product=productService.getProduct(productId);
		int categoryId=product.getCategoryId();
		
		Category category=categoryService.getCategory(categoryId);
		productVO.setId(productId);
		productVO.setCategoryId(categoryId);
		productVO.setDisplayInfoId(displayInfo.getId());
		productVO.setName(category.getName());	//카테고리 이름
		productVO.setDescription(product.getDescription());
		productVO.setContent(product.getContent());
		productVO.setEvent(product.getEvent());
		productVO.setOpeningHours(displayInfo.getOpeningHours());
		productVO.setPlaceName(displayInfo.getPlaceName());
		productVO.setPlaceLot(displayInfo.getPlaceLot());
		productVO.setPlaceStreet(displayInfo.getPlaceStreet());
		productVO.setTel(displayInfo.getTel());
		productVO.setHomepage(displayInfo.getHomepage());
		productVO.setEmail(displayInfo.getEmail());
		productVO.setCreateDate(displayInfo.getCreateDate());
		productVO.setModifyDate(displayInfo.getModifyDate());
		productVO.setFileId(100);
		map.put("product",productVO);
		
		List<ProductImage> productImageList=productService.getProductImages(productId);
		List<ProductImageVO> productImageVOList=new ArrayList<ProductImageVO>();
		for(int i=0; i<productImageList.size(); i++) {
			ProductImageVO productImageVO=new ProductImageVO();
			productImageVO.setProductId(productId);
			productImageVO.setProductImageId(productImageList.get(i).getId());
			productImageVO.setType(productImageList.get(i).getType());
			productImageVO.setFileInfoId(productImageList.get(i).getFileId());
			FileInfo fileInfo=fileInfoService.getFileInfo(productImageList.get(i).getFileId());
			productImageVO.setFileName(fileInfo.getFileName());
			productImageVO.setSaveFileName(fileInfo.getSaveFileName());
			productImageVO.setContentType(fileInfo.getContentType());
			productImageVO.setDeleteFlag(fileInfo.getDeleteFlag());
			productImageVO.setCreateDate(fileInfo.getCreateDate());
			productImageVO.setModifyDate(fileInfo.getModifyDate());
			productImageVOList.add(productImageVO);
		}
		map.put("productImages",productImageVOList);
		
		List<DisplayInfoImage> displayInfoImageList=displayInfoService.getDisplayInfoImages(displayId);
		List<DisplayInfoImageVO> displayInfoImageVOList=new ArrayList<DisplayInfoImageVO>();
		for(int i=0; i<displayInfoImageList.size(); i++) {
			DisplayInfoImageVO displayInfoImageVO=new DisplayInfoImageVO();
			displayInfoImageVO.setId(displayInfoImageList.get(i).getId());
			displayInfoImageVO.setDisplayInfoId(displayId);
			displayInfoImageVO.setFileId(displayInfoImageList.get(i).getFileId());
			FileInfo fileInfo=fileInfoService.getFileInfo(displayInfoImageList.get(i).getFileId());
			displayInfoImageVO.setFileName(fileInfo.getFileName());
			displayInfoImageVO.setSaveFileName(fileInfo.getSaveFileName());
			displayInfoImageVO.setContentType(fileInfo.getContentType());
			displayInfoImageVO.setDeleteFlag(fileInfo.getDeleteFlag());
			displayInfoImageVO.setCreateDate(fileInfo.getCreateDate());
			displayInfoImageVO.setModifyDate(fileInfo.getModifyDate());
			displayInfoImageVOList.add(displayInfoImageVO);
		}
		map.put("displayInfoImages",displayInfoImageVOList);
		
		map.put("avgScore",3);
		
		List<ProductPrice> productPriceList=productService.getProductPrices(productId);
		List<ProductPriceVO> productPriceVOList=new ArrayList<ProductPriceVO>();
		for(int i=0; i<productPriceList.size(); i++) {
			ProductPriceVO productPriceVO=new ProductPriceVO();
			productPriceVO.setId(productPriceList.get(i).getId());
			productPriceVO.setProductId(productId);
			productPriceVO.setPriceTypeName(productPriceList.get(i).getPriceTypeName());
			productPriceVO.setPrice(productPriceList.get(i).getPrice());
			productPriceVO.setDiscountRate(productPriceList.get(i).getDiscountRate());
			productPriceVO.setCreateDate(productPriceList.get(i).getCreateDate());
			productPriceVO.setModityDate(productPriceList.get(i).getModifyDate());
			productPriceVOList.add(productPriceVO);
		}
		map.put("productPrices",productPriceVOList);
		return map;
	}
	
	/*
	@GetMapping	(path="/displayinfos")
	public Map<String, Object> displayinfos_product( 
			@RequestParam(name="productId", required=false, defaultValue="0") int productId,
			@RequestParam(name="start", required=false, defaultValue="0") int start
			){
		Map<String, Object> map = new HashMap<>();
		Product product=productService.getProduct(productId);
		int totalCount=0;
		int commentCount=0;
		List<ProductVO> products = new ArrayList<ProductVO>();
		Category category=categoryService.getCategory(categoryId);
		for(int i=0; i<productList.size(); i++) {
			int productId = productList.get(i).getId();
			totalCount+=displayInfoService.getCount(productId);
			if(i>=start && i<start+4) {
				productCount+=displayInfoService.getCount(productId);
				List<DisplayInfo> list=displayInfoService.getDisplayInfos(productId);
				for(int j=0; j<list.size(); j++) {
					ProductVO productVO = new ProductVO();
					//DisplayInfo displayInfo=list.get(i);
					productVO.setId(productId);
					productVO.setCategoryId(categoryId);
					productVO.setDisplayInfoId(list.get(j).getId());
					productVO.setName(category.getName());	//카테고리 이름
					productVO.setDescription(productList.get(i).getDescription());
					productVO.setContent(productList.get(i).getContent());
					productVO.setEvent(productList.get(i).getEvent());
					productVO.setOpeningHours(list.get(j).getOpeningHours());
					productVO.setPlaceName(list.get(j).getPlaceName());
					productVO.setPlaceLot(list.get(j).getPlaceLot());
					productVO.setPlaceStreet(list.get(j).getPlaceStreet());
					productVO.setTel(list.get(j).getTel());
					productVO.setHomepage(list.get(j).getHomepage());
					productVO.setEmail(list.get(j).getEmail());
					productVO.setCreateDate(list.get(j).getCreateDate());
					productVO.setModifyDate(list.get(j).getModifyDate());
					productVO.setFileId(100);
					products.add(productVO);
				}
			}
		}
		map.put("totalCount",totalCount);
		map.put("productCount",productCount);
		map.put("products",products);
		return map;
	}
	*/
}