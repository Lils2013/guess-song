package com.atsoy.controllers;

import com.atsoy.data.entities.Question;
import com.atsoy.data.entities.User;
import com.atsoy.data.repositories.SongsRepository;
import com.atsoy.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    public SongsRepository songsRepository;

    @Autowired
    private UserRepository userRepository;

	@RequestMapping("/")
	public String goHome(HttpSession session, SessionStatus status){
        if (session.getAttribute("score")==null) {
	        session.setAttribute("score",0);
        }
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Question question = new Question();
        question.setSongs(songsRepository.findFourSongs());
        question.setAnswerId(question.getSongs().get(new Random().nextInt(4)).getId());
        session.setAttribute("question",question);
        session.setAttribute("high_score",user.getHighScore());
        return "game";
	}

    @RequestMapping("/answer")
    public String processAnswer(@RequestParam("id") Long id, HttpSession session){
	    Question question = (Question) session.getAttribute("question");
	    if (Objects.equals(question.getAnswerId(), id)) {
	        Integer score = (Integer) session.getAttribute("score");
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (score + 1 > user.getHighScore()) {
                user.setHighScore((long)(int) score + 1);
            }
            userRepository.save(user);
	        session.setAttribute("score",score+1);
	        session.setAttribute("result","Right!!");
	        return "redirect:/game/";
        } else {
            session.setAttribute("result","Wrong!");
	        return "redirect:/game/";
        }
    }

    @RequestMapping("/newGame")
    public String newGame(HttpSession session){
        session.removeAttribute("score");
        session.removeAttribute("result");
        session.removeAttribute("question");
        return "redirect:/game/";
    }
}
