package ro.sci.ems.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController {

	
	@RequestMapping("")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("employee/login");
		
		return modelAndView;
	}
	
	
	@RequestMapping("/save")
	public ModelAndView onLogin(String userName, String pass, 
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		///use UserService to check the login
		boolean loginWithSuccess =  true;
		if (loginWithSuccess) {
			ro.sci.ems.domain.User user = new ro.sci.ems.domain.User();
			user.setUserName(userName);
			
			request.getSession().setAttribute("currentUser", user);
			modelAndView.setView(new RedirectView("/employee"));
		}
		
		return modelAndView;
	}
}
