package es.urjc.code.daw.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.library.user.User;
import es.urjc.code.daw.library.user.UserComponent;

@Controller
public class AboutWebController {
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/about")
	public String about(Model model) {
		
		 if( userComponent.isLoggedUser()) {
	        	User loggedUser = userComponent.getLoggedUser();
	        	
				model.addAttribute("name",loggedUser);
				model.addAttribute("id", loggedUser.getId());
				model.addAttribute("logged", true);
			}else {
				
				model.addAttribute("logged", false);						        
			}

		return "/about";
	}

}
