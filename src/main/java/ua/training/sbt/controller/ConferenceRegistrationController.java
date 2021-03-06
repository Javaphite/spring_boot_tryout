package ua.training.sbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.training.sbt.model.Person;
import ua.training.sbt.model.PersonService;

@Controller
@EnableWebMvc
public class ConferenceRegistrationController {

    static final String INDEX = "/";
    static final String REGISTER_PERSON_ENDPOINT = "/registerPerson";
    static final String MAIN_PAGE = "welcome";
    static final String REGISTRATION_FORM_ENDPOINT = "registerPerson";

    @Autowired
    private PersonService service;

    @GetMapping(INDEX)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(MAIN_PAGE);
        modelAndView.addObject("participants", service.getParticipantsList());
        return modelAndView;
    }

    @GetMapping(REGISTER_PERSON_ENDPOINT)
    public String getRegisterPerson() {
        return REGISTRATION_FORM_ENDPOINT;
    }

    @PostMapping(REGISTER_PERSON_ENDPOINT)
    public String setRegisterPerson(@RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("company") String company,
                                        @RequestParam("accessLevel") String accessLevel) {
        long id = service.generateId();
        service.addPerson(new Person(id, firstName, lastName, company, accessLevel));
        return "redirect:" + INDEX;
    }
}
