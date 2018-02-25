package es.urjc.code.daw.tablonanuncios;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

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

@Controller
public class Dashboard {
	
	final static int PRODUCTS_PER_PAGE = 12;
	final static int VALORATIONS_PER_PAGE = 10;
	private static final Path USER_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\user_images");
	private static final Path PRODUCT_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\product_images");

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;
	

	@PostConstruct
    public void init() {
        User u1 = new User("u1", "p1","b1@a.com", "USER");u1.setImage("\\images\\user_images\\user1.jpg");
        User u2 = new User("u2", "p2","b2@a.com", "USER");u2.setImage("\\images\\user_images\\user2.jpg");
        User u3 = new User("u3", "p3","b3@a.com", "USER");u3.setImage("\\images\\user_images\\user3.jpg");
        User u4 = new User("u4", "p4","b4@a.com", "USER");u4.setImage("\\images\\user_images\\user4.jpg");
        User u5 = new User("u5", "p5","b5@a.com", "USER");

        Product p1 = new Product("pr1", "barata barata1", "asda", 1);p1.setUser(u1);        
        Product p2 = new Product("pr2", "barata barata2", "asda", 2);p2.setUser(u1);
        Product p3 = new Product("pr3", "barata barata3", "asda", 3);p3.setUser(u1);
        Product p4 = new Product("pr4", "barata barata4", "asda", 4);p4.setUser(u1);
        Product p5 = new Product("pr5", "barata barata5", "asda", 5);p5.setUser(u1);
        Product p6 = new Product("pr6", "barata barata6", "asda", 6);p6.setUser(u1);
        Product p7 = new Product("pr7", "barata barata7", "asda", 7);p7.setUser(u1);
        Product p8 = new Product("pr8", "barata barata8", "asda", 8);p8.setUser(u1);
        Product p9 = new Product("pr9", "barata barata9", "asda", 9);p9.setUser(u1);
        Product p10 = new Product("pr10", "barata barata10", "asda", 10);p10.setUser(u1);
        Product p11 = new Product("pr11", "barata barata11", "asda", 11);p11.setUser(u1);
        Product p12 = new Product("pr12", "barata barata12", "asda", 12);p12.setUser(u1);
        Product p13 = new Product("pr13", "barata barata13", "asda", 13);p13.setUser(u1);
        Product p14 = new Product("pr14", "barata barata14", "asda", 14);p14.setUser(u1);
        Product p15 = new Product("pr15", "barata barata15", "asda", 15);p15.setUser(u1);
        Product p16 = new Product("pr16", "barata barata16", "asda", 16);p16.setUser(u1);
        Product p17 = new Product("pr17", "barata barata17", "asda", 17);p17.setUser(u2);
        Product p18 = new Product("pr18", "barata barata18", "asda", 18);p18.setUser(u2);
        Product p19 = new Product("pr19", "barata barata19", "asda", 19);p19.setUser(u2);
        Product p20 = new Product("pr20", "barata barata20", "asda", 20);p20.setUser(u3);
        Product p21 = new Product("pr21", "barata barata21", "asda", 21);p21.setUser(u3);

        Valoration v1 = new Valoration(u1, u2, 5, "all ok");
		Valoration v2 = new Valoration(u1, u2, 3, "meh","21-March-2012");
		Valoration v3 = new Valoration(u1, u4, 4, "nani");
		Valoration v4 = new Valoration(u2, u1, 4, "good","1-April-2102");
		Valoration v5 = new Valoration(u1, u3, 2, "bad");
		Valoration v6 = new Valoration(u1, u5, 5, "perfect","24-October-2017");


        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
        productRepository.save(p7);
        productRepository.save(p8);
        productRepository.save(p9);
        productRepository.save(p10);
        productRepository.save(p11);
        productRepository.save(p12);
        productRepository.save(p13);
        productRepository.save(p14);
        productRepository.save(p15);
        productRepository.save(p16);
        productRepository.save(p17);
        productRepository.save(p18);
        productRepository.save(p19);
        productRepository.save(p20);
        productRepository.save(p21);

        valorationRepository.save(v1);
        valorationRepository.save(v2);
        valorationRepository.save(v3);
        valorationRepository.save(v4);
        valorationRepository.save(v5);
        valorationRepository.save(v6);

    }
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/upload")
	public String upload(Model model) {

		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		return "/upload";
	}
	
	@RequestMapping("/index")
    public String index(Model model) {

        //model.addAttribute("products", productRepository.findAll());
        //model.addAttribute("name", userRepository.findByName("u1"));
		
        if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("ID", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}
        
        return "/index";
    }
	
	//POR SI ACASO TESTEAMOS MÁS CON PASAR PRODUCTOS USAR ESTA, ELIMINAR ANTES DE RELEASE
	@RequestMapping("/tablon")
	public String tablon(Model model) {
		
		 
		model.addAttribute("products", productRepository.findAll());

		return "/tablon";
	}

	@RequestMapping("/login")
	public String login(Model model) {
	 
		return "/login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		
		if(userComponent.isLoggedUser()){
			userComponent.setLoggedUser(null);
		}
	

		return "/logout";
	}
	
	
	@RequestMapping("/search")
	public String search(Model model) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		model.addAttribute("products", productRepository.findAll());

		return "product";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("id",userComponent.getLoggedUser().getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}


		return "/about";
	}
	
	//HAY QUE ELIMINARLO EN ALGÚN MOMENTO
	
	@RequestMapping("product/upload")
	public String uploadProduct(Model model) {
		
		  if( userComponent.isLoggedUser()) {
				model.addAttribute("name",userComponent.getLoggedUser());
				model.addAttribute("logged", true);
			}else {
				
				model.addAttribute("logged", false);
		        
			}
		  
		return "Upload_product";
	}
	
	@RequestMapping(value = "product/new", method = RequestMethod.POST)
	public String handleFileUpload(Model model, Product product, @RequestParam("file") MultipartFile file) {

		String fileName = "\\product"+(productRepository.findTopByOrderByIdDesc().getId()+1)+".jpg";
		if (!file.isEmpty()) {
			try {
				
				//Absolute path!!!
				file.transferTo(new File(PRODUCT_IMAGES_FOLDER.toFile(), fileName));
				
				product.setImage("\\images\\product_images"+fileName);
				
			} catch (Exception e) {
				product.setImage("\\images\\product_images\\product_default.jpg");
			}
		} else {
			
			product.setImage("\\images\\product_images\\product_default.jpg");
		}
		
		product.setUser(userComponent.getLoggedUser());
		productRepository.save(product);
		
		return "redirect:../index";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		return "/contact";
	}
	
	
	
	@RequestMapping("user/register")
	public String registerUser(Model model) {
		
		  if( userComponent.isLoggedUser()) {
				model.addAttribute("name",userComponent.getLoggedUser());
				model.addAttribute("logged", true);
			}else {
				
				model.addAttribute("logged", false);
		        
			}
		  
		return "/register";
	}
	
	
	
	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String handleFileUpload(Model model, User user, @RequestParam("file") MultipartFile file) {

		String fileName = "\\user"+(userRepository.findTopByOrderByIdDesc().getId()+1)+".jpg";
		user.setRoles(new ArrayList<>(Arrays.asList("USER")));
		user.setLocationX("0sdad");
		user.setLocationY("0sdad0");

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
	
	
	@RequestMapping("/product/{id4}")
	public String productDetail(Model model, @PathVariable long id4) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("id",userComponent.getLoggedUser().getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}
	  
	  Product product = productRepository.findById(id4);
	  
	  if(userComponent.getLoggedUser().getId()==product.getUser().getId()) {
		  model.addAttribute("vendor", true);
	  }else {
		  model.addAttribute("vendor", false);
	  }
	  
	  			
	  		  	model.addAttribute("product", product);
	  		  	model.addAttribute("user", product.getUser());

		return "product-detail";
	}
	
	@RequestMapping("/product/{id4}/buy")
	public String productBought(Model model, @PathVariable long id4) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("id",userComponent.getLoggedUser().getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}
	  	
	  	Product product = productRepository.findById(id4);
	  	
	  	product.setBought(true);
	  	productRepository.save(product);
	  	

		return "redirect:../../index";
	}
	
	@RequestMapping("/product/{id4}/offer")
	public String productOffer(Model model, @PathVariable long id4, Offer offer) {
		
	 
	  	
	  	Product product = productRepository.findById(id4);
	  	
	  	product.addListBuyers(userComponent.getLoggedUser());
	  	//IMPLEMENT CHAT RESPONSE
	  	
	  	productRepository.save(product);
	  	

		return "redirect:../../index";
	}
	
	@RequestMapping("/product/{id3}/sold")
	public String sold(Model model, @PathVariable long id3) {
		
		if(userComponent.isLoggedUser()) {
			model.addAttribute("name", userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			model.addAttribute("logged", false);
		}
	 
		Product product = productRepository.findById(id3);
		
		if (product.getUser().getId() != userComponent.getLoggedUser().getId()) {
			return "redirect:../../error";
		}
		model.addAttribute("product", product);
		model.addAttribute("listBuyers", product.getListBuyers());
		
		return "valorationSeller";
}
	
	@RequestMapping("/product/{id}/sentRequest")
	public String ValorationRequestSent(Model model, User user) {
		
  		if( userComponent.isLoggedUser()) {
			model.addAttribute("name", userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			model.addAttribute("logged", false);
		}
  		
  		
  		
  		user = userRepository.findByid(user.getId());
  		
  		//user.setEmail("ENCONTRADO");
  		
  		//IMPLEMENTAR MÉTODO ENVIAR MENSAJE
		//userRepository.save(user);
		return "redirect:../../index";
	}
	//product/idproduct/idbuyer/buyer
	@RequestMapping("/product/{id}/{id2}/buyer")
	public String valoration(Model model, @PathVariable long id, @PathVariable long id2) {
		
		
  		if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			model.addAttribute("logged", false);	        
		}
  		
  		Product product = productRepository.findById(id);
  		
  		if (userComponent.getLoggedUser().getId() != id2) {
  			return "redirect:../../../error";
  		}  		
  		
  		
		model.addAttribute("product", product);
		
		return "valorationBuyer";

	}
	
	@RequestMapping(value="/product/{id1}/{id2}/sent", method = RequestMethod.POST)
	public String ValorationSent(@PathVariable long id1, @PathVariable long id2, Valoration valoration ) {
		Valoration valoration2 = new Valoration();
		
		valoration2.createDate();
		valoration2.setDescription(valoration.getDescription());
		valoration2.setValoration(valoration.getValoration());
		valoration2.setSeller(productRepository.findById(id1).getUser());
		valoration2.setBuyer(userRepository.findByid(id2));
		
		
		valorationRepository.save(valoration2);
		
		
		return "redirect:../../../index";
	}
	
	
	@RequestMapping("/user/{id}")
	public String showUser(Model model, @PathVariable long id, @PageableDefault(value = PRODUCTS_PER_PAGE) @Qualifier("products")Pageable productPage,  
			@PageableDefault(value = VALORATIONS_PER_PAGE) @Qualifier("valorations")Pageable valorationPage) {
		model.addAttribute("user",userRepository.findOne(id));
		
		if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
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
		return "/seller";
	}

}
