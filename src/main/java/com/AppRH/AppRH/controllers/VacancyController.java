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

        @RequestMapping("/deleteVacancy")
        public String deleteVacancy(long code) {
            Vacancy vacancy = vacancyRepository.findByCode(code);
            vacancyRepository.delete(vacancy);
            return  "redirect:/vacancy";
        }

        public String vacancyDetails(@PathVariable("code")long code, @Valid Candidate candidate,
                                     BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Verify the fields");
            return "redirect:/{code}";
        }
            if(candidateRepository.findByRg(candidate.getRg()) != null){
                attributes.addFlashAttribute("error message", "duplicate RG");
                return "redirect:/{code}";
            }

            Vacancy vacancy = vacancyRepository.findByCode(code);
            candidate.setVacancy(vacancy);
            attributes.addFlashAttribute("message", "Candidate added with success!");

            return "redirect:/{code}";
        }

        //deleting candidate by RG
        @RequestMapping("/deleteCandidate")
        public String deleteCandidateByRG(String rg){
                Candidate candidate = candidateRepository.findByRg(rg);
                Vacancy vacancy = candidate.getVacancy();
                String code = "" + vacancy.getCode();
                candidateRepository.delete(candidate);
                return "redirect:/" + code;
        }

        //update methods and edit form vacancy
        @RequestMapping(value = "/edit-vacancy", method = RequestMethod.GET)
        public ModelAndView editVacancy(long code){
            Vacancy vacancy = vacancyRepository.findByCode(code);
            ModelAndView modelAndView = new ModelAndView("vacancy/update-vacancy");
            modelAndView.addObject("vacancy", vacancy);
            return modelAndView;
        }

        //Update vacancy
        @RequestMapping(value = "/edit-vacancy", method = RequestMethod.POST)
        public String updateVacancy(@Valid Vacancy vacancy, BindingResult result, RedirectAttributes attributes){
            vacancyRepository.save(vacancy);
            attributes.addFlashAttribute("success", "Vacancy updated with success!");
            long codeLong = vacancy.getCode();
            String code = "" + codeLong;
            return  "redirect:/" + code;
        }

}
