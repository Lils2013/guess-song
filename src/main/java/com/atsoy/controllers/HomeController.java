package com.atsoy.controllers;

import com.atsoy.data.entities.Question;
import com.atsoy.data.entities.User;
import com.atsoy.data.repositories.SongsRepository;
import com.atsoy.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Random;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

	@RequestMapping("/")
	public String goHome(){
        return "home";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goLogin(){
		return "login";
	}

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@ModelAttribute User user){
        user.setRole("ROLE_USER");
        user.setHighScore(0L);
        userRepository.save(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(user,
                user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String goRegister(){
        return "register";
    }
}
