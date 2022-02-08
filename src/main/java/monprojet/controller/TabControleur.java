package monprojet;
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


@Controller
@RequestMapping(path="/liste")
public class TabControleur {

    @Autowired 
    private CityRepository dao;

    @Autowired
    private CountryRepository dao1;

    @GetMapping(path="show")
    public String afficheToutesLesVilles(Model model){
        model.addAttribute("cities",dao.findAll());
        model.addAttribute("countries",dao.findAll());
        return "myTemplate";
    }


    @GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("categorie") Categorie categorie) {
		return "formulaireCategorie";
	}


}