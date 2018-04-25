package es.urjc.code.daw.library.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import es.urjc.code.daw.library.product.*;
import es.urjc.code.daw.library.user.*;


@Controller
public class SearchWebController {
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductRepository productRepository;
	
	private final static int PRODUCTS_PER_PAGE = 12;

	
	
	@RequestMapping("/search")
	public String search(Model model) {
		
		 if( userComponent.isLoggedUser()) {
	        	User loggedUser = userComponent.getLoggedUser();
	        	
				model.addAttribute("name",loggedUser);
				model.addAttribute("id", loggedUser.getId());
				model.addAttribute("logged", true);
			}else {
				
				model.addAttribute("logged", false);
				
		        
			}
		model.addAttribute("products", productRepository.findAll());

		return "product";
	}
	@RequestMapping("/consult")
	public String showProducts(Model model, String search, @PageableDefault(value = PRODUCTS_PER_PAGE) @Qualifier("products")Pageable productPage) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
		Page<Product> products = productRepository.findByTagsContaining(search, productPage);
		model.addAttribute("search", search);
		model.addAttribute("showNextProducts", !products.isLast());
		model.addAttribute("showPrevProducts", !products.isFirst());
		model.addAttribute("nextPageProducts", products.getNumber()+1);
		model.addAttribute("prevPageProducts", products.getNumber()-1);
		model.addAttribute("products", products);
		return "product";
	}

	
}
