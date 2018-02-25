package es.urjc.code.daw.tablonanuncios;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	private final static int PRODUCTS_PER_PAGE = 12;
	private final static int VALORATIONS_PER_PAGE = 10;
	private static final Path USER_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\user_images");
	private static final Path PRODUCT_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\product_images");

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	

	@PostConstruct
    public void init() {
        User u1 = new User("u1", "p1","b1@a.com", "USER");u1.setImage("\\images\\user_images\\user1.jpg");
        User u2 = new User("u2", "p2","b2@a.com", "USER");u2.setImage("\\images\\user_images\\user2.jpg");
        User u3 = new User("u3", "p3","b3@a.com", "USER");u3.setImage("\\images\\user_images\\user3.jpg");
        User u4 = new User("u4", "p4","b4@a.com", "USER");u4.setImage("\\images\\user_images\\user4.jpg");
        User u5 = new User("u5", "p5","b5@a.com", "USER");

        Product p1 = new Product("pr1", "barata barata1", "fashion", 1);p1.setUser(u1);        
        Product p2 = new Product("pr2", "barata barata2", "videogames", 2);p2.setUser(u1);
        Product p3 = new Product("pr3", "barata barata3", "books", 3);p3.setUser(u1);
        Product p4 = new Product("pr4", "barata barata4", "books", 4);p4.setUser(u1);
        Product p5 = new Product("pr5", "barata barata5", "books", 5);p5.setUser(u1);
        Product p6 = new Product("pr6", "barata barata6", "books", 6);p6.setUser(u1);
        Product p7 = new Product("pr7", "barata barata7", "books", 7);p7.setUser(u1);
        Product p8 = new Product("pr8", "barata barata8", "books", 8);p8.setUser(u1);
        Product p9 = new Product("pr9", "barata barata9", "electrionics", 9);p9.setUser(u1);
        Product p10 = new Product("pr10", "barata barata10", "fashion", 10);p10.setUser(u1);
        Product p11 = new Product("pr11", "barata barata11", "books", 11);p11.setUser(u1);
        Product p12 = new Product("pr12", "barata barata12", "books", 12);p12.setUser(u1);
        Product p13 = new Product("pr13", "barata barata13", "books", 13);p13.setUser(u1);
        Product p14 = new Product("pr14", "barata barata14", "books", 14);p14.setUser(u1);
        Product p15 = new Product("pr15", "barata barata15", "fashion", 15);p15.setUser(u1);
        Product p16 = new Product("pr16", "barata barata16", "videogames", 16);p16.setUser(u1);
        Product p17 = new Product("pr17", "barata barata17", "sport", 17);p17.setUser(u2);
        Product p18 = new Product("pr18", "barata barata18", "electrionics", 18);p18.setUser(u2);
        Product p19 = new Product("pr19", "barata barata19", "films", 19);p19.setUser(u2);
        Product p20 = new Product("pr20", "barata barata20", "fashion", 20);p20.setUser(u3);
        Product p21 = new Product("pr21", "barata barata21", "books", 21);p21.setUser(u1);
        Product p22 = new Product("pr22", "barata barata22", "books", 22);p22.setUser(u1);
        Product p23 = new Product("pr23", "barata barata23", "others", 23);p23.setUser(u1);
        Product p24 = new Product("pr24", "barata barata24", "others", 24);p24.setUser(u1);
        Product p25 = new Product("pr25", "barata barata25", "books", 25);p25.setUser(u1);
        Product p26 = new Product("pr26", "barata barata26", "books", 26);p26.setUser(u1);
        Product p27 = new Product("pr27", "barata barata27", "books", 27);p27.setUser(u1);
        Product p28 = new Product("pr28", "barata barata28", "books", 28);p28.setUser(u1);
        Product p29 = new Product("pr29", "barata barata29", "books", 29);p29.setUser(u1);
        
        p1.setFeatured(true);
        p2.setFeatured(true);
        p3.setFeatured(true);
        p4.setFeatured(true);        
        p5.setFeatured(true);
        p6.setFeatured(true);

        Valoration v1 = new Valoration(u1, u2, 5, "all ok");
        u1.setMedValoration(4);
		Valoration v2 = new Valoration(u1, u2, 3, "meh","21-March-2012");
		Valoration v3 = new Valoration(u1, u4, 4, "nani");
		Valoration v4 = new Valoration(u2, u1, 4, "good","1-April-2102");
		Valoration v5 = new Valoration(u1, u3, 2, "bad");
		Valoration v6 = new Valoration(u1, u5, 5, "perfect","24-October-2017");
		
		Chat c1 = new Chat(u1, u2);
		Chat c2 = new Chat(u1, u3);
		Chat c3 = new Chat(u3, u2);
		Chat c4 = new Chat(u5, u1);
		
		Message m1 = new Message(u1, "hi");m1.setChat(c1);
		Message m2 = new Message(u1, "how are u?");m2.setChat(c1);
		Message m3 = new Message(u2, "fine thanks");m3.setChat(c1);
		Message m4 = new Message(u3, "SHUT UP");m4.setChat(c2);
		Message m5 = new Message(u1, "are u retarded?");m5.setChat(c2);



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
        productRepository.save(p22);
        productRepository.save(p23);
        productRepository.save(p24);
        productRepository.save(p25);
        productRepository.save(p26);
        productRepository.save(p27);
        productRepository.save(p28);
        productRepository.save(p29);
        

        valorationRepository.save(v1);
        valorationRepository.save(v2);
        valorationRepository.save(v3);
        valorationRepository.save(v4);
        valorationRepository.save(v5);
        valorationRepository.save(v6);
        
        chatRepository.save(c1);
        chatRepository.save(c2);
        chatRepository.save(c3);
        chatRepository.save(c4);
        
        messageRepository.save(m1);
        messageRepository.save(m2);
        messageRepository.save(m3);
        messageRepository.save(m4);
        messageRepository.save(m5);


    }
	
	@Autowired
	private UserComponent userComponent;
	
	
	@RequestMapping("/")
    public String home(Model model) {
		return "redirect:/index";
	}
	
	
	@RequestMapping("/index")
    public String index(Model model) {

		
        if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("ID", loggedUser.getId());
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
       
        return "/index";
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
	
	@RequestMapping("/chat/{id}/new")
	public String newMessage(Model model, @PathVariable long id, Message m) {
		Message m1= new Message();
		m1.setText(m.getText());
		m1.setTransmitter(userRepository.findByid(userComponent.getLoggedUser().getId()));
		m1.setChat(chatRepository.findById(id));
		messageRepository.save(m1);
		return "redirect:../../chat/{id}";
	}
	
	@RequestMapping("/chat/{id}")
	public String showMessages(Model model, @PathVariable long id) {
		
		if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}

		model.addAttribute("messages", messageRepository.getMessages(chatRepository.findById(id)));
		
		return "/chats";
	}
	
	@RequestMapping("/chat")
	public String showChats(Model model) {
		if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
	        
		}
		User u;
		List<Chat> chats = chatRepository.getChats(userRepository.findByid(userComponent.getLoggedUser().getId()));
		for(int i=0;i<chats.size();i++) {
			if(chats.get(i).getUser1().getId()!=userComponent.getLoggedUser().getId()) {
				u=chats.get(i).getUser1();
				chats.get(i).setUser1(chats.get(i).getUser2());
				chats.get(i).setUser2(u);
			}
		}
		model.addAttribute("chats",chats);
		return "/open_chats";
	}
	
	@RequestMapping("/product")
	public String showProducts(Model model, String search, @PageableDefault(value = PRODUCTS_PER_PAGE) @Qualifier("products")Pageable productPage) {
		
		Page<Product> products = productRepository.findByTagsContaining(search, productPage);
		model.addAttribute("search", search);
		model.addAttribute("showNextProducts", !products.isLast());
		model.addAttribute("showPrevProducts", !products.isFirst());
		model.addAttribute("nextPageProducts", products.getNumber()+1);
		model.addAttribute("prevPageProducts", products.getNumber()-1);
		model.addAttribute("products", products);
		return "/product";
	}

}
