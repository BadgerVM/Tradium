package es.urjc.code.daw.tablonanuncios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class User {
	
	public interface BasicAtt {}
	public interface PlayersAtt {}
	
	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(BasicAtt.class)
	private String name;
	@JsonView(BasicAtt.class)
	private String password;
	@JsonView(BasicAtt.class)
	private String email;
	@JsonView(BasicAtt.class)
	private String locationX;
	@JsonView(BasicAtt.class)
	private String locationY;
	@JsonView(BasicAtt.class)
	private int medValoration;
	
	@Autowired
	@OneToMany
	private List<Product> products = new ArrayList<>();
	
	public User() {}

	public User(String name, String password, String email, String locationX, String locationY) {
		super();
		this.name = name;
		this.locationX = locationX;
		this.locationY = locationY;
		this.password=password;
		this.email=email;
	}
	public User(String name, String password, String email) {
		super();
		this.name = name;
		this.password=password;
		this.email=email;
		
		this.locationX = "0000";
		this.locationY = "0000";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	
	
}
