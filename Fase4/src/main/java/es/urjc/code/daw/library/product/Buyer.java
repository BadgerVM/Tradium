package es.urjc.code.daw.library.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.library.product.Product.ProductAtt;
import es.urjc.code.daw.library.user.User;

@Entity
public class Buyer {
	@JsonView(ProductAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(ProductAtt.class)
	@Autowired
	@OneToOne
	private User user;
	
	public Buyer() {
	}
	
	public Buyer(User u) {
		this.user=u;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user=u;
	}

}
