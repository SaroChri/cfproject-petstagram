package gr.aueb.cf.finalprojpets.controller;

import gr.aueb.cf.finalprojpets.model.Pets;
import gr.aueb.cf.finalprojpets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdoptAPetController {
    private PetRepository petRepository;
    @Autowired
    public AdoptAPetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/AdoptAPet")
    public String getAdoptPage(Model model) {
        List<Pets> pets = petRepository.findAll(); // Fetch all pets from the database
        model.addAttribute("pets", pets); // Pass the list of pets to the template
        return "AdoptAPet";
    }
}
