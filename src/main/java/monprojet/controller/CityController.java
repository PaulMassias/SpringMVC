package monprojet.controller;
import java.util.*;

import monprojet.dao.*;
import monprojet.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(path = "/cities")
@Slf4j
public class CityController {

    @Autowired 
    private CityRepository dao;

    @Autowired
    private CountryRepository dao1;

    @GetMapping(path = "show")
    public String afficheToutesLesVilles(Model model){
        model.addAttribute("cities",dao.findAll());
        model.addAttribute("countries",dao1.findAll());
        return "myTemplate";
    }


    @GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("city") City city, Model model) {
        model.addAttribute("countries",dao1.findAll());
        Country france = dao1.findById(1).orElseThrow();
		City nouvelle = new City("Nouvelle ville", france);
		nouvelle.setPopulation(50);
        model.addAttribute("city", nouvelle);
		return "formulaire";
	}



    @PostMapping(path="save") // Requête HTTP POST à l'URL http://localhost:8989/cities/save
	public String enregistreUneVille(City laVille) {
        dao.save(laVille);
		log.info("La ville {} a été enregistrée", laVille);
		// On redirige vers la page de liste des villes
		return "redirect:/cities/show";
    }




    @GetMapping(path = "edit")
	public String editerUneVille(@RequestParam("id") City city, Model model) {
		model.addAttribute("city",city);
        model.addAttribute("countries",dao1.findAll());
		return "formulaire";
	}



    @GetMapping(path = "delete")
	public String supprimerUneVille(@RequestParam("id") City city) {
		dao.delete(city); 
		return "redirect:/cities/show";
    }
    
}