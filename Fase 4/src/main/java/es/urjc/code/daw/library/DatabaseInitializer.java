package es.urjc.code.daw.library;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.code.daw.library.chats.Chat;
import es.urjc.code.daw.library.chats.ChatRepository;
import es.urjc.code.daw.library.chats.Message;
import es.urjc.code.daw.library.chats.MessageRepository;
import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookRepository;
import es.urjc.code.daw.library.user.User;
import es.urjc.code.daw.library.user.UserRepository;
import es.urjc.code.daw.library.valorations.Valoration;
import es.urjc.code.daw.library.valorations.ValorationRepository;
import es.urjc.code.daw.library.product.Buyer;
import es.urjc.code.daw.library.product.Product;
import es.urjc.code.daw.library.product.ProductRepository;

@Component
public class DatabaseInitializer {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;

	@PostConstruct
	public void init() {


		// Sample users

		//userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
		
		User u1 = new User("u1", "p1","b1@a.com", "USER");u1.setImage("\\images\\user_images\\user1.jpg");
        User u2 = new User("u2", "p2","b2@a.com", "USER");u2.setImage("\\images\\user_images\\user2.jpg");
        User u3 = new User("u3", "p3","b3@a.com", "USER");u3.setImage("\\images\\user_images\\user3.jpg");
        User u4 = new User("u4", "p4","b4@a.com", "USER");u4.setImage("\\images\\user_images\\user4.jpg");
        User u5 = new User("u5", "p5","b5@a.com", "USER");
        User u6 = new User("u6", "ROOT","b6@a.com", "ADMIN");

        u1.setLocationX("40.4167754");
        u1.setLocationY("-3.7037901999999576");
        Product p1 = new Product("Grey dress", "As new", "fashion", 30,"\\images\\product_images\\Dress2-1.jpg" );p1.setUser(u1);        
        Product p2 = new Product("Crash", "A fantastic game for PS4", "videogames", 15,"\\images\\product_images\\Game2-1.jpg");p2.setUser(u1);
        Product p3 = new Product("Metro 2033", "A porst-apocalyptic game", "videogames", 15,"\\images\\product_images\\Game3-1.jpg");p3.setUser(u1);
        Product p4 = new Product("PS4 PRO", "Very little used", "electronics", 100, "\\images\\product_images\\PS2-3.jpg");p4.setUser(u1);
        Product p5 = new Product("Huntress", "Black huntress", "fashion", 20, "\\images\\product_images\\Huntress1-1.jpg");p5.setUser(u1);
        Product p6 = new Product("Dress", "Vintage dress", "fashion", 16, "\\images\\product_images\\Dress1-1.jpg");p6.setUser(u1);
        Product p7 = new Product("Shoes", "Running shoes, as new", "fashion", 7, "\\images\\product_images\\shoes1-2.jpg");p7.setUser(u1);
        Product p8 = new Product("Handwash", "Beautiful and cheap", "others", 70, "\\images\\product_images\\Furniture1-1.jpg");p8.setUser(u1);
        Product p9 = new Product("PC", "Fujitsu PC", "electrionics", 100, "\\images\\product_images\\PC1-1.jpg");p9.setUser(u1);
        Product p10 = new Product("Climate Changue", "Anice book", "books", 160,"\\images\\product_images\\ClimateChangue.jpg");p10.setUser(u1);
        Product p11 = new Product("Table", "Beautiful", "others", 80, "\\images\\product_images\\Table1-1.jpg");p11.setUser(u1);
        Product p12 = new Product("Sofa", "New sofa", "books", 120, "\\images\\product_images\\Sofa2-1.jpg");p12.setUser(u1);
        Product p13 = new Product("Resident Evil 6", "A new game for PS4", "videogames", 13, "\\images\\product_images\\Game1-1.jpg");p13.setUser(u1);
        Product p14 = new Product("Stationary bike", "As new and cheap", "sport", 50, "\\images\\product_images\\Bike1-1.jpg");p14.setUser(u1);
        Product p15 = new Product("Bike Suit", "As new", "fashion", 150,"\\images\\product_images\\BikeSuit.jpg");p15.setUser(u1);
        Product p16 = new Product("Shoes", "New shoes", "fashion", 16, "\\images\\product_images\\Shoes2.jpg");p16.setUser(u1);
        Product p17 = new Product("Gibson", "Gibson guitar", "music", 350, "\\images\\product_images\\Gibson.jpg");p17.setUser(u2);
        Product p18 = new Product("Learn Germany", "Germany for dummies", "books", 30, "\\images\\product_images\\Germany.jpg");p18.setUser(u2);
        Product p19 = new Product("Guitar", "Electric guitar", "music", 200, "\\images\\product_images\\Guitar.jpg");p19.setUser(u2);
        Product p20 = new Product("Turntable", "Not used", "music", 20, "\\images\\product_images\\Turntable.jpg");p20.setUser(u3);
        Product p21 = new Product("Poetic anthology", "Book about the G. of 27", "books", 40,"\\images\\product_images\\Poetic.jpg");p21.setUser(u1);
        Product p22 = new Product("Grey car", "volkswagen car", "others", 3000, "\\images\\product_images\\Car1-1.jpg");p22.setUser(u1);
        Product p23 = new Product("Belt", "Security belt", "fashion", 40, "\\images\\product_images\\Belt.jpg");p23.setUser(u1);
        Product p24 = new Product("Cap", "New cap", "fashion", 23, "\\images\\product_images\\Cap.jpg");p24.setUser(u1);
        Product p25 = new Product("XBOX 360", "As new XBOX, 100% dnctional", "videogames", 60, "\\images\\product_images\\XBOX1-2.jpg");p25.setUser(u1);
        Product p26 = new Product("T-Shirt", "White T-Shit from Mango", "fashion", 20, "\\images\\product_images\\tshirt.jpg");p26.setUser(u1);
        Product p27 = new Product("Black Shoes", "Not used", "fashion", 27, "\\images\\product_images\\BlackShoes.jpg");p27.setUser(u1);
        Product p28 = new Product("Skateboard", "Electric skateboards", "sport", 65, "\\images\\product_images\\Skateboard.jpg");p28.setUser(u1);
        Product p29 = new Product("Juventus T-Shirt", "barata barata29", "fashion", 29, "\\images\\product_images\\Juventus.jpg");p29.setUser(u1);
             //u1.setProducts(productRepository.findByUser_Id(u1.getId() ) );
        //u1.addProduct(p1);
        
        p1.setFeatured(true);
        p2.setFeatured(true);
        p3.setFeatured(true);
        p4.setFeatured(true);        
        p5.setFeatured(true);
        p6.setFeatured(true);
        
        Valoration v1 = new Valoration(u1, u2, 5, "all ok");
		Valoration v2 = new Valoration(u1, u2, 3, "meh","21-March-2012");
		Valoration v3 = new Valoration(u1, u4, 4, "nani");
		Valoration v4 = new Valoration(u2, u1, 4, "good","1-April-2102");
		Valoration v5 = new Valoration(u1, u3, 2, "bad");
		Valoration v6 = new Valoration(u1, u5, 5, "perfect","24-October-2017");
		
        
        Chat c1 = new Chat(u1, u2);
		Chat c2 = new Chat(u1, u3);
		Chat c3 = new Chat(u3, u2);
		
		Message m1 = new Message(u1, "hi");m1.setChat(c1);
		Message m2 = new Message(u1, "how are u?");m2.setChat(c1);
		Message m3 = new Message(u2, "fine thanks");m3.setChat(c1);
		Message m4 = new Message(u3, "SHUT UP");m4.setChat(c2);
		Message m5 = new Message(u1, "are u retarded?");m5.setChat(c2);
		Message m6 = new Message(u3, "asddsa");m6.setChat(c3);
		
		u1.setMedValoration(4);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);
        
        
        
        //p1.addListBuyers(new Buyer(u3));
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
        productRepository.save(p22);
        productRepository.save(p23);
        productRepository.save(p24);
        productRepository.save(p25);
        productRepository.save(p26);
        productRepository.save(p27);
        productRepository.save(p28);
        productRepository.save(p29);
        
        
        valorationRepository.save(v1);
        valorationRepository.save(v2);
        valorationRepository.save(v3);
        valorationRepository.save(v4);
        valorationRepository.save(v5);
        valorationRepository.save(v6);
        
        chatRepository.save(c1);
        chatRepository.save(c2);
        chatRepository.save(c3);
        
        messageRepository.save(m1);
        messageRepository.save(m2);
        messageRepository.save(m3);
        messageRepository.save(m4);
        messageRepository.save(m5);
        messageRepository.save(m6);

        c1.addMessage(m1);
        c1.addMessage(m2);
        c1.addMessage(m3);
        c2.addMessage(m4);
        c2.addMessage(m5);
        c3.addMessage(m6);

        chatRepository.save(c1);
        chatRepository.save(c2);
        chatRepository.save(c3);
	}

}
