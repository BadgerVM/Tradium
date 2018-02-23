package es.urjc.code.daw.tablonanuncios;


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

@Controller
public class Dashboard {
	
	final static int PRODUCTS_PER_PAGE = 12;
	final static int VALORATIONS_PER_PAGE = 10;


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
        User u4 = new User("u4", "p4","b4@a.com", "USER");u4.setImage("\\images\\user_images\\user4.png");
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

		model.addAttribute("products", productRepository.findAll());
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		return "subir";
	}
	
	@RequestMapping("/index")
    public String index(Model model) {

        //model.addAttribute("products", productRepository.findAll());
        //model.addAttribute("name", userRepository.findByName("u1"));
		
        if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}
        
        return "index";
    }
	
	//POR SI ACASO TESTEAMOS MÁS CON PASAR PRODUCTOS USAR ESTA, ELIMINAR ANTES DE RELEASE
	@RequestMapping("/tablon")
	public String tablon(Model model) {
		
		 
		model.addAttribute("products", productRepository.findAll());

		return "tablon";
	}
	


	@RequestMapping("/login")
	public String login(Model model) {
	 
		return "login";
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
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}


		return "about";
	}
	
	//HAY QUE ELIMINARLO EN ALGÚN MOMENTO
	
	@RequestMapping("/product/new")
	public String nuevoAnuncio(Model model, Product product) {
		
  		if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		productRepository.save(product);

		return "anuncio_guardado";

	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		return "contact";
	}
	
	@RequestMapping("user/register")
	public String registerUser(Model model) {
		
		  if( userComponent.isLoggedUser()) {
				model.addAttribute("name",userComponent.getLoggedUser());
				model.addAttribute("logged", true);
			}else {
				
				model.addAttribute("logged", false);
		        
			}
		  
		return "register";
	}
	
	@RequestMapping("/user/new")
	public String newUser(Model model, User user) {
		userRepository.save(user);
		return "redirect:../index";
	}

	

	@RequestMapping("/user/{id}")
	public String showUser(Model model, @PathVariable long id, @PageableDefault(value = PRODUCTS_PER_PAGE) @Qualifier("products")Pageable productPage,  @PageableDefault(value = VALORATIONS_PER_PAGE) @Qualifier("valorations")Pageable valorationPage) {
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
		return "seller";
	}

}