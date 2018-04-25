package es.urjc.code.daw.library.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.library.product.Product;
import es.urjc.code.daw.library.product.ProductRepository;
import es.urjc.code.daw.library.security.LoginController;
import es.urjc.code.daw.library.user.User;
import es.urjc.code.daw.library.user.UserComponent;

@Controller
public class IndexWebController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/index")
    public String index(Model model) {		
        if( userComponent.getLoggedUser()!= null) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
        
       List <Product>  products = productRepository.findByFeatured(true);
        
       model.addAttribute("product1", products.get(0));
       model.addAttribute("product2", products.get(1));
       model.addAttribute("product3", products.get(2));
       model.addAttribute("product4", products.get(3));
       model.addAttribute("product5", products.get(4));
       model.addAttribute("product6", products.get(5));
         
        return "index";
    }

}
