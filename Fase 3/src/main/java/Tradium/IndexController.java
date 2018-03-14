package Tradium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController implements ErrorController {
	 private static final String PATH = "../../../error";
	 
	 @Autowired
	 private UserComponent userComponent;
	 
	 @RequestMapping(value = PATH)
	    public String error(Model model) {
		 
			if( userComponent.isLoggedUser()) {
				model.addAttribute("name",userComponent.getLoggedUser());
				model.addAttribute("logged", true);
				
			}else {	
				model.addAttribute("logged", false);
			}
	        return "Error handling";
	    }
	 
	@Override
	public String getErrorPath() {
		
		// TODO Auto-generated method stub
		return PATH;
	}

}
