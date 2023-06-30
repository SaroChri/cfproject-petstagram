package gr.aueb.cf.finalprojpets.controller;



import gr.aueb.cf.finalprojpets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdoptedController {

    private PetRepository petRepository;

    @Autowired
    public AdoptedController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/Adopted")
    public String showAdopted(Model model) {
        return "Adopted";
    }
}


