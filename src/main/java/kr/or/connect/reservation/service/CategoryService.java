package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.category.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
	public List<Category> getCategories();
	public Category getCategory(int id);
}
