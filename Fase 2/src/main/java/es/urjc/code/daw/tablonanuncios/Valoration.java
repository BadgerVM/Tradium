package es.urjc.code.daw.tablonanuncios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Valoration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Autowired
	@ManyToOne
	private User seller;
	
	@Autowired
	@ManyToOne
	private User buyer;
	
	
	private int valoration;
	
	
	public Valoration () {}
	
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public int getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}

	
	public Valoration(User seller, User buyer, int valoration) {
		super();
		this.id = id;
		this.seller = seller;
		this.buyer = buyer;
		this.valoration = valoration;
	}
	
	
	

}
