package es.urjc.code.daw.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.library.user.UserComponent;

@Controller
public class LoginWebController {

    @Autowired
    private UserComponent userComponent;

    @RequestMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {

        if(userComponent.isLoggedUser()){
            userComponent.setLoggedUser(null);
        }


        return "logout";
    }

}