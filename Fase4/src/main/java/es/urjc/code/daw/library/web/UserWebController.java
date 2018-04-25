package es.urjc.code.daw.library.web;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import es.urjc.code.daw.library.user.*;
import es.urjc.code.daw.library.product.*;
import es.urjc.code.daw.library.user.*;
import es.urjc.code.daw.library.valorations.*;


@Controller
public class UserWebController {
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;
	
	@Autowired
	private ProductRepository productRepository;
	

	private final static int PRODUCTS_PER_PAGE = 12;
	private final static int VALORATIONS_PER_PAGE = 10;
	private static final Path USER_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\user_images");
	private static final Path PRODUCT_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\product_images");
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}

		return "contact";
	}
	
	
	
	@RequestMapping("user/register")
	public String registerUser(Model model) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
		  
		return "register";
	}
	
	
	
	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String handleFileUpload(Model model, User user, @RequestParam("file") MultipartFile file) {

        String fileName = "\\user"+(userRepository.findTopByOrderByIdDesc().getId()+1)+".jpg";
        user.setRoles(new ArrayList<>(Arrays.asList("USER")));

        if (!file.isEmpty()) {
            try {

                //Absolute path!!!
                file.transferTo(new File(USER_IMAGES_FOLDER.toFile(), fileName));
                user.setImage("\\images\\user_images"+fileName);

            } catch (Exception e) {
                user.setImage("\\images\\user_images\\user_default.jpg");
            }
        } else {

            user.setImage("\\images\\user_images\\user_default.jpg");
        }
        userRepository.save(user);
        return "redirect:../index";
    }
	@RequestMapping("/user/{id}")
	public String showUser(Model model, @PathVariable long id, @PageableDefault(value = PRODUCTS_PER_PAGE) @Qualifier("products")Pageable productPage,  
			@PageableDefault(value = VALORATIONS_PER_PAGE) @Qualifier("valorations")Pageable valorationPage) {
		model.addAttribute("user",userRepository.findOne(id));
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);		
	        
		}
		
		Page<Product> products = productRepository.findByUser_Id(id, productPage);
		model.addAttribute("products", products);
		
		model.addAttribute("showNextProducts", !products.isLast());
		model.addAttribute("showPrevProducts", !products.isFirst());
		model.addAttribute("nextPageProducts", products.getNumber()+1);
		model.addAttribute("prevPageProducts", products.getNumber()-1);
		
		
		Page<Valoration> valorations = valorationRepository.findBySeller_Id(id, valorationPage);
		model.addAttribute("valorations", valorations);
		
		model.addAttribute("showNextValorations", !valorations.isLast());
		model.addAttribute("showPrevValorations", !valorations.isFirst());
		model.addAttribute("nextPageValorations", valorations.getNumber()+1);
		model.addAttribute("prevPageValorations", valorations.getNumber()-1);
		
		
		
		//model.addAttribute("products", productRepository.findByUser_Id(id));
		return "seller";
	}
	
	
}
