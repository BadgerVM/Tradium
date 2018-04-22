package es.urjc.code.daw.library.web;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import es.urjc.code.daw.library.chats.*;
import es.urjc.code.daw.library.product.*;
import es.urjc.code.daw.library.valorations.*;
import es.urjc.code.daw.library.user.*;


@Controller
public class ProductWebController {
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	private static final Path PRODUCT_IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "\\src\\main\\resources\\static\\images\\product_images");

	@Autowired
	private MessageRepository messageRepository;
	
	@RequestMapping("product/upload")
	public String uploadProduct(Model model) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
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
	
	@RequestMapping("/product/{id4}")
	public String productDetail(Model model, @PathVariable long id4) {
		
	  if( userComponent.isLoggedUser()) {
			model.addAttribute("name",userComponent.getLoggedUser());
			model.addAttribute("id",userComponent.getLoggedUser().getId());
			model.addAttribute("logged", true);
			model.addAttribute("admin", userRepository.findByid(userComponent.getLoggedUser().getId() ).isUserInRole("ADMIN") );
	  }else {
		  	model.addAttribute("admin", false);
			model.addAttribute("logged", false);
	        
		}
	  
	  Product product = productRepository.findById(id4);
	  if(userComponent.isLoggedUser()) {
		  
		  if(userComponent.getLoggedUser().getId() == product.getUser().getId()) {
			  model.addAttribute("vendor", true);
		  }else {
			  model.addAttribute("vendor", false);
		  }
		  
	  }
	  
	   	model.addAttribute("product", product);
	  	model.addAttribute("user", product.getUser());

		return "product_detail";
	}
	
	@RequestMapping("/product/{id4}/delete")
	public String productDeleted(Model model, @PathVariable long id4) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
	  	
	  	Product product = productRepository.findById(id4);
	  	
	  	if(userComponent.getLoggedUser()!=productRepository.findById(id4).getUser() && !userRepository.findByid(userComponent.getLoggedUser().getId() ).isUserInRole("ADMIN") ) 
	  	{
	  		return "redirect:../../index";
		}
	  	
	  	productRepository.delete(product);
	  	

		return "redirect:../../index";
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
	  	Buyer buyer = null;
	  	buyer.setUser(userComponent.getLoggedUser());
	  	product.setBought(true);
	  	product.addListBuyers(buyer);	
	  	productRepository.save(product);
	  	

		return "redirect:../../index";
	}
	
	@RequestMapping("/product/{id4}/offer")
	public String productOffer(Model model, @PathVariable long id4, Offer offer) {		
	 
	  	
	  	Product product = productRepository.findById(id4);
	  		  	
	  	//IMPLEMENT CHAT RESPONSE
	  	User seller =productRepository.findById(id4).getUser();
	  	
	  	Buyer buyer = null;
	  	
	  	buyer.setUser(userComponent.getLoggedUser());
  		
  		Chat c1 = new Chat(buyer.getUser(), seller);
  		
  		int number = Integer.parseInt(offer.getOffer());
  		
  		Message m1 = new Message();
  		
		if( number > productRepository.findById(id4).getMinPrice() ) {
			m1 = new Message(seller, "Hi! i offer you"+ offer.getOffer() +"€"); m1.setChat(c1);	
			product.addListBuyers(buyer);			
		}else {
			m1 = new Message(seller, "Hi! i offer you"+ offer.getOffer() +"€, may you get your price down a little bit?"); m1.setChat(c1);	
			product.addListBuyers(buyer);
		}

		chatRepository.save(c1);
		messageRepository.save(m1);
		productRepository.save(product);

      
		return "redirect:../../index";
	}
	
	@RequestMapping("/product/{id3}/sold")
	public String sold(Model model, @PathVariable long id3) {
		List <Buyer> users= new ArrayList();		
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
	 
		Product product = productRepository.findById(id3);
		
		if (product.getUser().getId() != userComponent.getLoggedUser().getId()) {
			return "redirect:../../error";
		}
		for(Buyer u: product.getListBuyers()) {
			users.add(u);		
			
		}
		product.setBought(true);
		model.addAttribute("product", product);
		model.addAttribute("listBuyers", users);
		
		return "valorationSeller";
}
	
	@RequestMapping("/product/{id}/sentRequest")
	public String ValorationRequestSent(Model model, @PathVariable long id, User user) {
	
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
  		
  		User seller = userRepository.findByid(id);
  		user = userRepository.findByid(user.getId());
  		
  		Chat c1 = new Chat(user, seller);
		
		Message m1 = new Message(seller, "Could you please valorate me in /product/"+id+"/"+user.getId() +"/buyer?");m1.setChat(c1);
  		
		
		chatRepository.save(c1);
		messageRepository.save(m1);
		return "redirect:../../index";
	}
	//product/idproduct/idbuyer/buyer
	@RequestMapping("/product/{id}/{id2}/buyer")
	public String valoration(Model model, @PathVariable long id, @PathVariable long id2) {
		
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
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
	public String ValorationSent(Model model, @PathVariable long id1, @PathVariable long id2, Valoration valoration ) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
		
		Valoration valoration2 = new Valoration();
		
		valoration2.createDate();
		valoration2.setDescription(valoration.getDescription());
		valoration2.setValoration(valoration.getValoration());
		valoration2.setSeller(productRepository.findById(id1).getUser());
		valoration2.setBuyer(userRepository.findByid(id2));
		
		List <Valoration> valor = new ArrayList();
		
		valor = valorationRepository.findBySellerId(id2);
		int add= 0;
		
		for (Valoration v: valor) {
			add =+ v.getValoration();			
		}
		
		add = add/ valor.size();
		User seller = productRepository.findById(id1).getUser();
		seller.setMedValoration(add);
		valorationRepository.save(valoration2);
		
		
		return "redirect:../../../index";
	}
	
}
