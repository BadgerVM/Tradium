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
		User u1 = new User("n1", "1123", "1232");
		User u2 = new User("n2", "232", "31");
		Product p1 = new Product("pr1", "barata barata", "asda", 90);
		p1.setUser(u1);
		Valoration v1 = new Valoration(u1, u2, 5);
	
		userRepository.save(u1);
		userRepository.save(u2);

		productRepository.save(p1);
		valorationRepository.save(v1);
		
	}
	
	@RequestMapping("/user")
	public String user() {
		return "seller";
	}
	
	@RequestMapping("/")
	public String tablon(Model model) {

		model.addAttribute("products", productRepository.findAll());

		return "tablon";
	}

	@RequestMapping("/product/new")
	public String nuevoAnuncio(Model model, Product product) {

		productRepository.save(product);

		return "anuncio_guardado";

	}

	@RequestMapping("/product/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Product product = productRepository.findOne(id);

		model.addAttribute("product", product);

		return "404";
	}

}