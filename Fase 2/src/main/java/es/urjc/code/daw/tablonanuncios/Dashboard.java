package es.urjc.code.daw.tablonanuncios;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Dashboard {
	
	//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
	//base de datos de la aplicación

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;
	

	@PostConstruct
	public void init() {
		User u1 = new User("u1", "p1","b1@a.com","1123", "1232");
		User u2 = new User("u2", "p2","b2@a.com","232", "31");
		User u3 = new User("u3", "p3","b3@a.com","232", "31");
		User u4 = new User("u4", "p4","b4@a.com","232", "31");
		
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
		
		Valoration v1 = new Valoration(u1, u2, 5);
		Valoration v2 = new Valoration(u1, u2, 3);
		Valoration v3 = new Valoration(u1, u4, 4);
	
		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);
		userRepository.save(u4);

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
		
	}
	
	
	
	@RequestMapping("/")
	public String tablon(Model model) {

		model.addAttribute("products", productRepository.findAll());

		return "tablon";
	}

	@RequestMapping("/product/new")
	public String newProduct(Model model, Product product) {

		productRepository.save(product);

		return "anuncio_guardado";

	}

	@RequestMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable long id) {
		
		Product product = productRepository.findOne(id);

		model.addAttribute("product", product);

		return "404";
	}
	
	@RequestMapping("user/register")
	public String registerUser(Model model) {
		return "register";
	}
	
	
	@RequestMapping("/user/new")
	public String newUser(Model model, User user) {
		userRepository.save(user);
		return "seller";
	}
	
	@RequestMapping("/user/{id}")
	public String showUser(Model model, @PathVariable long id) {
		User user = userRepository.findOne(id);
		model.addAttribute("user",user);
		model.addAttribute("products", productRepository.findByUser_Id(id));
		return "seller";
	}

}