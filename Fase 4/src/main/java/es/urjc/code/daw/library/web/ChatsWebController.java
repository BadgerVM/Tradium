package es.urjc.code.daw.library.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.library.user.User;
import es.urjc.code.daw.library.user.UserComponent;
import es.urjc.code.daw.library.user.UserRepository;
import es.urjc.code.daw.library.chats.*;

@Controller
public class ChatsWebController {
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	

	@RequestMapping("/chat/{id}/new")
	public String newMessage(Model model, @PathVariable long id, Message m) {
		Message m1= new Message();
		m1.setText(m.getText());
		m1.setTransmitter(userRepository.findByid(userComponent.getLoggedUser().getId()));
		m1.setChat(chatRepository.findById(id));
		messageRepository.save(m1);
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
		
		return "redirect:../../chat/{id}";
	}
	
	@RequestMapping("/chat/{id}")
	public String showMessages(Model model, @PathVariable long id) {
		
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}

		model.addAttribute("messages", messageRepository.getMessages(chatRepository.findById(id)));
		
		return "/chats";
	}
	
	@RequestMapping("/chat")
	public String showChats(Model model) {
		if( userComponent.isLoggedUser()) {
        	User loggedUser = userComponent.getLoggedUser();
        	
			model.addAttribute("name",loggedUser);
			model.addAttribute("id", loggedUser.getId());
			model.addAttribute("logged", true);
		}else {
			
			model.addAttribute("logged", false);
			
	        
		}
		User u;
		List<Chat> chats = chatRepository.getChats(userComponent.getLoggedUser().getId());
		for(int i=0;i<chats.size();i++) {
			if(chats.get(i).getUser1().getId()!=userComponent.getLoggedUser().getId()) {
				u=chats.get(i).getUser1();
				chats.get(i).setUser1(chats.get(i).getUser2());
				chats.get(i).setUser2(u);
			}
		}
		model.addAttribute("chats",chats);
		return "/open_chats";
	}
	

}
