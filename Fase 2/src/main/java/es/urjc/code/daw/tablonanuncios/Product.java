package es.urjc.code.daw.tablonanuncios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class Product {
	public interface BasicAtt {}
	public interface PlayersAtt {}
	
	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(BasicAtt.class)
	private String name;
	
	@JsonView(BasicAtt.class)
	private String description;
	
	@JsonView(BasicAtt.class)
	private String tags;
	
	@JsonView(BasicAtt.class)
	 private String image;
	
	@JsonView(BasicAtt.class)
	private double price;
	
	@JsonView(BasicAtt.class)
	private double minPrice;
	
	@JsonView(BasicAtt.class)
	private boolean bought;
	
	@JsonView(BasicAtt.class)
	private boolean featured;
	


	@Autowired
	@OneToOne
	private User user;
	
	@Autowired
	@OneToMany
	private List <User> listBuyers = new ArrayList();
	

	public Product() {}

	public Product(String name, String desc, String tags, double price) {
		super();
		this.name = name;
		this.description = desc;
		this.tags = tags;
		this.image = "\\images\\product_images\\product_default.png";
		this.price = price;
		this.bought = false;
	}
    
	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
	
	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	
	public boolean getBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public List<User> getListBuyers() {
		return listBuyers;
	}

	public void setListBuyers(List<User> listBuyers) {
		this.listBuyers = listBuyers;
	}
	
	public void addListBuyers(User buyer) {
		this.listBuyers.add(buyer);
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Anuncio [nombre=" + name + ", asunto=" + description + ", comentario=" + price + "]";
	}

}
