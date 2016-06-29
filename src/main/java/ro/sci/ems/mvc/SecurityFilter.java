package ro.sci.ems.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.sci.ems.domain.User;
import ro.sci.ems.service.SecurityService;

@Component
public class SecurityFilter implements Filter {

	@Autowired
	private SecurityService securityService;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		
		User user =(User) ((HttpServletRequest)request).getSession().getAttribute("currentUser");
		
		securityService.
		setCurrentUser(user);
		
		System.out.println("Thread name: " + Thread.currentThread().getName() +
				", current user: " + (user != null ? user.getUserName() : null));
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
