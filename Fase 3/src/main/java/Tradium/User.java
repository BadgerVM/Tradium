package Tradium;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class User {
	
	public interface UserAtt {}
	public interface UserIdAtt {}
	public interface PlayersAtt {}
	
	@JsonView(UserIdAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@JsonView(UserAtt.class)
	private String name;
	
	
	private String passwordHash;
	
	@JsonView(UserAtt.class)
	private String email;
		
	@JsonView(UserAtt.class)
	private String locationX;
	
	@JsonView(UserAtt.class)
	private String locationY;
	
	@JsonView(UserAtt.class)
	private int medValoration;
	
	@JsonView(UserAtt.class)
	private String image;
	
	@JsonView(UserAtt.class)
	@ElementCollection(fetch = FetchType.EAGER)
	 private List<String> roles;
	
	@JsonView(UserAtt.class)
	@Autowired
	@OneToMany
	private List<Product> products = new ArrayList<>();
	
	public User() {}
	
	public User(String name, String password, String email, String... roles) {
		super();
		this.name = name;
		this.email=email;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.locationX = "0000";
		this.locationY = "0000";
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.image="\\images\\user_images\\user_default.jpg";
	}
	
	

	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public int getMedValoration() {
		return medValoration;
	}

	public void setMedValoration(int medValoration) {
		this.medValoration = medValoration;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product e) {
		this.products.add(e);
	}
	
	public void deleteProduct(Product e) {
        this.products.remove(e);
    }



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getPasswordHash() {
		return passwordHash;
	}
	
	public boolean isUserInRole(String str) {
		return this.roles.contains(str);
	}



	public void setPasswordHash(String passwordHash) {		
		this.passwordHash = new BCryptPasswordEncoder().encode(passwordHash);
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



}
	
	
	
