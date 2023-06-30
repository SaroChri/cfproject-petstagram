package gr.aueb.cf.finalprojpets.controller;

import gr.aueb.cf.finalprojpets.dto.PetsDTO;
import gr.aueb.cf.finalprojpets.service.IPetService;
import gr.aueb.cf.finalprojpets.service.ImageService;
import gr.aueb.cf.finalprojpets.validator.PetsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class AdoptAPetFormController {

    private final IPetService petService;
    private final PetsValidator petsValidator;
    private final ImageService imageService;

    @Autowired
    public AdoptAPetFormController(IPetService petService, PetsValidator petsValidator, ImageService imageService) {
        this.petService = petService;
        this.petsValidator = petsValidator;
        this.imageService = imageService;
    }

    @GetMapping("/AdoptAPetForm")
    String AdoptAPetForm(Model model) {
        model.addAttribute("petsDTO", new PetsDTO());
        return "AdoptAPetForm";
    }

    @PostMapping("/AdoptAPetForm")
    public String AdoptAPet(@ModelAttribute("petsDTO")PetsDTO petsDTO, BindingResult bindingResult, Model model,
                            @RequestParam("image") MultipartFile imageFile) throws IOException {
        petsValidator.validate(petsDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "AdoptAPetForm";
        }
            try {
                // Save the petsDTO using the petService
                petService.save(petsDTO);

                if (!imageFile.isEmpty()) {
                    // Process the uploaded image

                    try (InputStream inputStream = imageFile.getInputStream()) {
                        byte[] imageBytes = imageFile.getBytes();
                        imageService.saveImage(imageFile, petsDTO.getName());
                    }
                }

                // Add attributes to the model for success and redirect to "/AdoptAPet"
                model.addAttribute("success", true);
                return "redirect:/AdoptAPet";
            } catch (IOException e) {
                // Handle the exception and add attribute to the model for error
                model.addAttribute("error", true);
                return "redirect:/AdoptAPet";
            }
        }
    }


