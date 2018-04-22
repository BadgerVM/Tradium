package es.urjc.code.daw.library.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.library.product.Product.ProductAtt;
import es.urjc.code.daw.library.user.User;

@Entity
public class Product {
	public interface ProductAtt {}
	public interface PlayersAtt {}
	
	@JsonView(ProductAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(ProductAtt.class)
	private String name;
	
	@JsonView(ProductAtt.class)
	private String description;
	
	@JsonView(ProductAtt.class)
	private String tags;
	
	@JsonView(ProductAtt.class)
	 private String image;
	
	@JsonView(ProductAtt.class)
	private double price;
	
	
	private double minPrice;
	
	@JsonView(ProductAtt.class)
	private boolean bought;
	
	@JsonView(ProductAtt.class)
	private boolean featured;
	

	@JsonView(ProductAtt.class)
	@Autowired
	@OneToOne
	private User user;
	
	
	@Autowired
	@OneToMany
	private List <Buyer> listBuyers = new ArrayList();
	

	public Product() {}
	
	public Product(String name, String desc, String tags, double price, String image) {
        super();
        this.name = name;
        this.description = desc;
        this.tags = tags;
        this.image = image;
        this.price = price;
        this.bought = false;
    }

	public Product(String name, String desc, String tags, double price) {
		super();
		this.name = name;
		this.description = desc;
		this.tags = tags;
		this.image = "\\images\\product_images\\product_default.png";
		this.price = price;
		this.bought = false;
	}
    
	public Product(String name, String desc, String tags, double price, boolean bo) {
		super();
		this.name = name;
		this.description = desc;
		this.tags = tags;
		this.image = "\\images\\product_images\\product_default.png";
		this.price = price;
		this.bought = bo;
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

	public List<Buyer> getListBuyers() {
		return listBuyers;
	}

	public void setListBuyers(List<Buyer> listBuyers) {
		this.listBuyers = listBuyers;
	}
	
	public void addListBuyers(Buyer buyer) {
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
