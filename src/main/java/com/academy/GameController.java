package com.academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {

    private User user;

    @Autowired
    private GameRepository gameRepository;


    @RequestMapping(method = RequestMethod.GET, path = "/lastpage")
    public ModelAndView lastpage() {
        ModelAndView modelAndView = new ModelAndView("lastpage");
        return modelAndView;
    } // Länkar till lastpage från index.html

    /*@RequestMapping(method = RequestMethod.GET, path = "/problem1")
    public ModelAndView problem1() {
        ModelAndView modelAndView = new ModelAndView("problem1");
        return modelAndView;
    } // Länkar till problem1.html */

    @RequestMapping(method = RequestMethod.POST, path="/post")
    public ModelAndView nameVar(@RequestParam String nameVar, HttpSession session){
        session.setAttribute("nameVar", nameVar);
        //metod som sparar i databasen
        long timeNow = System.currentTimeMillis();
        user = new User(nameVar);
        user.setStartTime(timeNow);
        ModelAndView modelAndView = new ModelAndView("problem1");
        return modelAndView;
    } // Lägger name i en variabel.


    @RequestMapping(method = RequestMethod.POST, path = "/problem2")
    public ModelAndView problem2(@RequestParam String solution) {
        if(solution.trim().equals("Hello World")){
            ModelAndView modelAndView = new ModelAndView("problem2");
            return modelAndView;
        }
        ModelAndView modelAndViewError = new ModelAndView("problem1");
        modelAndViewError.addObject("Message", "Wrong answer!");
        return modelAndViewError;
    } // Länkar till problem2 om svaret är rätt. Annars tillbaka till problem1.


	@RequestMapping(method = RequestMethod.POST, path = "/problem3")
	public ModelAndView problem3(@RequestParam String solution1) {
		if (solution1.trim().equals("buzz")) {
			ModelAndView modelAndView = new ModelAndView("problem3");
			return modelAndView;
		}
		ModelAndView modelAndViewError2 = new ModelAndView("problem2");
		modelAndViewError2.addObject("Message2", "Wrong answer!");
		return modelAndViewError2;
	} // Länkar till problem 3 om svaret är rätt. Annars tillbaka till problem2.


	@RequestMapping(method = RequestMethod.POST, path = "/problem4")
	public ModelAndView problem4(@RequestParam String solution2) {
		if (solution2.trim().equals("1995")) {
			ModelAndView modelAndView = new ModelAndView("problem4");
			return modelAndView;
		}
		ModelAndView modelAndViewError3 = new ModelAndView("problem3");
		modelAndViewError3.addObject("Message3", "Wrong answer!");
		return modelAndViewError3;
	} // Länkar till problem 4 om svaret är rätt. Annars tillbaka till problem3.

    @RequestMapping(method = RequestMethod.POST, path="/lastpage")
    public ModelAndView listNames () {
        gameRepository.saveUser(user);
        return new ModelAndView("/lastpage")
                .addObject("names",gameRepository.ListUsers());
    } // Länkar till lastpage och visar denna sidan med en lista av namnen från databasen.
}
