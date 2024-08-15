package com.AppRH.AppRH.controllers;

import com.AppRH.AppRH.models.Candidate;
import com.AppRH.AppRH.models.Vacancy;
import com.AppRH.AppRH.repository.CandidateRepository;
import com.AppRH.AppRH.repository.VacancyRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public class VacancyController {

    private VacancyRepository vacancyRepository;
    private CandidateRepository candidateRepository;


    @RequestMapping(value = "/registerVacancy", method = RequestMethod.GET)
    public String form() {
      return "vacancy/formVacancy";
    }


    @RequestMapping(value = "registerVacancy", method = RequestMethod.POST)
    public String form(@Valid Vacancy vacancy, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mesage", "Verify the fields");
            return "redirect:/registerVacancy";
        }
        vacancyRepository.save(vacancy);
        attributes.addFlashAttribute("mesage", "vacancy successfully registered");
        return "redirect/registerVacancy";
    }

    @RequestMapping(value = "/vacancy")
    public ModelAndView vacancyList(){
        ModelAndView mv = new ModelAndView("vacancy/vacancyList");
        Iterable<Vacancy> vacancies = vacancyRepository.findAll();
        mv.addObject("vacancies", vacancies);
        return  mv;
        }

        @RequestMapping(value = "/{code}", method = RequestMethod.GET)
        public ModelAndView vacancyDetails(@PathVariable("code") long code){
            Vacancy vacancy = vacancyRepository.findByCode(code);
            ModelAndView mv = new ModelAndView("vacancy/vacancyDetails");
            mv.addObject("vacancy");

            Iterable<Candidate> candidates = candidateRepository.findByVacancy(vacancy);
            mv.addObject("candidates", candidates);
            return mv;
        }
}
