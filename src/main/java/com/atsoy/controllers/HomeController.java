package com.atsoy.controllers;

import com.atsoy.data.entities.Question;
import com.atsoy.data.repositories.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Random;

@Controller
public class HomeController {

    @Autowired
    public SongsRepository songsRepository;

	@RequestMapping("/")
	public String goHome(HttpSession session, SessionStatus status){
	  //  status.setComplete();
        if (session.getAttribute("score")==null) {
	        session.setAttribute("score",0);
        }
        Question question = new Question();
        question.setSongs(songsRepository.findFourSongs());
        question.setAnswerId(question.getSongs().get(new Random().nextInt(4)).getId());
        session.setAttribute("question",question);
        return "home";
	}

    @RequestMapping("/answer")
    public String processAnswer(@RequestParam("id") Long id, HttpSession session){
	    Question question = (Question) session.getAttribute("question");
	    if (Objects.equals(question.getAnswerId(), id)) {
	        session.setAttribute("score",(Integer) session.getAttribute("score")+1);
	        session.setAttribute("result","Right!!");
	        return "redirect:/";
        } else {
            session.setAttribute("result","Wrong!");
	        return "redirect:/";
        }
    }

    @RequestMapping("/newGame")
    public String newGame(HttpSession session){
        session.removeAttribute("score");
        session.removeAttribute("result");
        session.removeAttribute("question");
        return "redirect:/";
    }
}
