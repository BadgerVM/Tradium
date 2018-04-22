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

		// Sample books

		bookRepository.save(new Book("SUEÑOS DE ACERO Y NEON",
				"Los personajes que protagonizan este relato sobreviven en una sociedad en decadencia a la que, no obstante, lograrán devolver la posibilidad de un futuro. Año 2484. En un mundo dominado por las grandes corporaciones, solo un hombre, Jordi Thompson, detective privado deslenguado y vividor, pero de gran talento y sentido d..."));
		bookRepository.save(new Book("LA VIDA SECRETA DE LA MENTE",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos."));
		bookRepository.save(new Book("CASI SIN QUERER",
				"El amor algunas veces es tan complicado como impredecible. Pero al final lo que más valoramos son los detalles más simples, los más bonitos, los que llegan sin avisar. Y a la hora de escribir sobre sentimientos, no hay nada más limpio que hacerlo desde el corazón. Y eso hace Defreds en este libro."));
		bookRepository.save(new Book("TERMINAMOS Y OTROS POEMAS SIN TERMINAR",
				"Recopilación de nuevos poemas, textos en prosa y pensamientos del autor. Un sabio dijo una vez: «Pocas cosas hipnotizan tanto en este mundo como una llama y como la luna, será porque no podemos cogerlas o porque nos iluminan en la penumbra». Realmente no sé si alguien dijo esta cita o me la acabo de inventar pero deberían de haberla escrito porque el poder hipnótico que ejercen esa mujer de rojo y esa dama blanca sobre el ser humano es digna de estudio."));
		bookRepository.save(new Book("LA LEGIÓN PERDIDA",
				"En el año 53 a. C. el cónsul Craso cruzó el Éufrates para conquistar Oriente, pero su ejército fue destrozado en Carrhae. Una legión entera cayó prisionera de los partos. Nadie sabe a ciencia cierta qué pasó con aquella legión perdida.150 años después, Trajano está a punto de volver a cruzar el Éufrates. ..."));

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
        Product p1 = new Product("pr1", "barata barata1", "fashion", 15);p1.setUser(u1);        
        Product p2 = new Product("pr2", "barata barata2", "videogames", 2);p2.setUser(u1);
        Product p3 = new Product("pr3", "barata barata3", "books", 3);p3.setUser(u1);
        Product p4 = new Product("pr4", "barata barata4", "books", 4);p4.setUser(u1);
        Product p5 = new Product("pr5", "barata barata5", "books", 5);p5.setUser(u1);
        Product p6 = new Product("pr6", "barata barata6", "books", 6);p6.setUser(u1);
        Product p7 = new Product("pr7", "barata barata7", "books", 7);p7.setUser(u1);
        Product p8 = new Product("pr8", "barata barata8", "books", 8);p8.setUser(u1);
        Product p9 = new Product("pr9", "barata barata9", "electronics", 9);p9.setUser(u1);
        Product p10 = new Product("pr10", "barata barata10", "fashion", 10);p10.setUser(u1);
        Product p11 = new Product("pr11", "barata barata11", "books", 11);p11.setUser(u1);
        Product p12 = new Product("pr12", "barata barata12", "books", 12);p12.setUser(u1);
        Product p13 = new Product("pr13", "barata barata13", "books", 13);p13.setUser(u1);
        Product p14 = new Product("pr14", "barata barata14", "books", 14);p14.setUser(u1);
        Product p15 = new Product("pr15", "barata barata15", "fashion", 15);p15.setUser(u1);
        Product p16 = new Product("pr16", "barata barata16", "videogames", 16);p16.setUser(u1);
        Product p17 = new Product("pr17", "barata barata17", "sport", 17);p17.setUser(u2);
        Product p18 = new Product("pr18", "barata barata18", "electronics", 18);p18.setUser(u2);
        Product p19 = new Product("pr19", "barata barata19", "films", 19);p19.setUser(u2);
        Product p20 = new Product("pr20", "barata barata20", "fashion", 20);p20.setUser(u3);
        Product p21 = new Product("pr21", "barata barata21", "books", 21);p21.setUser(u1);
        Product p22 = new Product("pr22", "barata barata22", "books", 22);p22.setUser(u1);
        Product p23 = new Product("pr23", "barata barata23", "others", 23);p23.setUser(u1);
        Product p24 = new Product("pr24", "barata barata24", "others", 24);p24.setUser(u1);
        Product p25 = new Product("pr25", "barata barata25", "books", 25);p25.setUser(u1);
        Product p26 = new Product("pr26", "barata barata26", "books", 26);p26.setUser(u1);
        Product p27 = new Product("pr27", "barata barata27", "books", 27);p27.setUser(u1);
        Product p28 = new Product("pr28", "barata barata28", "books", 28);p28.setUser(u1);
        Product p29 = new Product("pr29", "barata barata29", "books", 29);p29.setUser(u1);
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
