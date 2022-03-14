package net.codejava.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.User;
import net.codejava.UserServices;

@RestController
@CrossOrigin
@RequestMapping("/api/dakshina")
public class AppRestController {
	
	private UserServices service;
	
	@RequestMapping("/register")
	public String processRegister(User user, HttpServletRequest request) 
			throws UnsupportedEncodingException, MessagingException {
		service.register(user, getSiteURL(request));		
		return "register_success";
	
	}
	
	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}	
	
	@RequestMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@RequestMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (service.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}
}
	
	
	

