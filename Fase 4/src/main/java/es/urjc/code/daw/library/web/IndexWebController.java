package es.urjc.code.daw.library.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.library.product.Product;
import es.urjc.code.daw.library.product.ProductRepository;
import es.urjc.code.daw.library.user.User;
import es.urjc.code.daw.library.user.UserComponent;

@Controller
public class IndexWebController {
	
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
        Product[] anArray = new Product[6];
        
        int index;
        
        for(int i=6; i > 0; i--) {
        	index = (products.size() - i);
        	anArray[index] = products.get(index);
        }
        
        
       model.addAttribute("product1", anArray[0]);
       model.addAttribute("product2", anArray[1]);
       model.addAttribute("product3", anArray[2]);
       model.addAttribute("product4", anArray[3]);
       model.addAttribute("product5", anArray[4]);
       model.addAttribute("product6", anArray[5]);
         
        return "index";
    }

}
