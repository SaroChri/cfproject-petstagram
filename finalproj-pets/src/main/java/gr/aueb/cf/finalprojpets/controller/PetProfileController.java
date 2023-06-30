package gr.aueb.cf.finalprojpets.controller;

import gr.aueb.cf.finalprojpets.model.Pets;
import gr.aueb.cf.finalprojpets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetProfileController {
    private PetRepository petRepository;

    @Autowired
    public PetProfileController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/pet/{id}")
    public String getPetProfile(@PathVariable("id") Long id, Model model) {
        Pets pet = petRepository.findPetById(id);
        model.addAttribute("pet", pet);
        return "PetProfile";
    }

    @GetMapping("/templates/main.html")
    public String redirectToMain() {
        return "redirect:/main";
    }

    // Controller method for the adoption request
    @PostMapping("/Adopted")
    public String AdoptPet( Model model) {
        return "redirect:/Adopted";
    }
}

