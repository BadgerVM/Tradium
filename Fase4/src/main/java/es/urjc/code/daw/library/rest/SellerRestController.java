package es.urjc.code.daw.library.rest;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.library.user.*;
import es.urjc.code.daw.library.user.User.UserIdAtt;
import es.urjc.code.daw.library.user.User.UserAtt;
import es.urjc.code.daw.library.valorations.Valoration.ValorationAtt;
import es.urjc.code.daw.library.product.*;
import es.urjc.code.daw.library.valorations.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SellerRestController {

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;	
	
	@Autowired
	private ValorationRepository valorationRepository;

	
	interface UserDetail extends User.UserAtt, User.UserIdAtt, Product.ProductAtt {}
	@JsonView(UserDetail.class)
	@RequestMapping(value="/seller/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> User (Model model, @PathVariable long id) {
		
	    User user = userRepository.findByid(id);
	    if (!user.equals(null)) {
	    	
	    	return new ResponseEntity<>(user,HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	@JsonView(UserDetail.class)
	@RequestMapping(value="/seller/products/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> products (Model model, @PathVariable long id) {
		
	    User user = userRepository.findByid(id);
	    if (!user.equals(null)) {
	    	
	    	List <Product> products = productRepository.findByUser_Id(id);
	  
	    	return new ResponseEntity<>(products,HttpStatus.OK);	
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@RequestMapping(value="/user/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User register (Model model, @RequestBody User user, HttpServletRequest request) throws FileNotFoundException, IOException {
		String s ="USER";
		List <String> role = new ArrayList();
		role.add(s);
		if (user.getImage() != ""){
			String[] parts = user.getImage().split(",");
		
			byte[] data = Base64.getDecoder().decode(parts[1]);
			String r = "src/main/resources/static/images/user_images/"+user.getName()+".jpg";
			try (OutputStream stream = new FileOutputStream(r)) {
			    stream.write(data);
			}
		}else {
			user.setImage("\\images\\user_images\\user_default.jpg");
		}
		
		if(user.getLocationX()=="") {

			user.setLocationX("0000");
		}
		if(user.getLocationY()=="") {

			user.setLocationY("0000");
		}
		
		else {
			user.setImage("\\images\\user_images\\"+user.getName()+".jpg");
		}
		
		user.setMedValoration(0);
		user.setRoles(role);
		userRepository.save(user);
		
		return user;
		
	}
	
	interface ValorationDetail extends Valoration.ValorationAtt, User.UserAtt,  User.UserIdAtt{}
	@JsonView(ValorationDetail.class)
	@RequestMapping(value="/seller/{id}/valorations", method=RequestMethod.GET)
	public ResponseEntity<List<Valoration>>  showValoration (Model model, @PathVariable Integer id) {
		
		List<Valoration> valorations = valorationRepository.findBySellerId(id);
		if (valorations != null) {
			
			
			return new ResponseEntity <>(valorations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(ValorationAtt.class)
	@RequestMapping(value="/seller/{id}/valoration", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Valoration> createValoration (Model model, @PathVariable long id, @RequestBody Valoration valoration, HttpServletRequest request) {
		
		User loggedUser = userComponent.getLoggedUser();
		if(loggedUser.getId()!=id) {
			User seller=userRepository.findByid(id);
			Valoration v =new Valoration(seller, loggedUser, valoration.getValoration(), valoration.getDescription());
			valorationRepository.save(v);
			
			return new ResponseEntity <>(v, HttpStatus.OK);
		}
		return new ResponseEntity <>(HttpStatus.UNAUTHORIZED);
		
	}

}
