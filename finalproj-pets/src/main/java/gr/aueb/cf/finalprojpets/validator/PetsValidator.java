package gr.aueb.cf.finalprojpets.validator;

import gr.aueb.cf.finalprojpets.dto.PetsDTO;
import gr.aueb.cf.finalprojpets.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PetsValidator implements Validator {

    private final IPetService petService;

    @Autowired
    public PetsValidator(IPetService petService) {
        this.petService = petService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return PetsDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {

        PetsDTO petsDTO = (PetsDTO) target;

        // Validation for breed

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "breed", "empty");
        if (petsDTO.getBreed().length() < 3 || petsDTO.getBreed().length() > 30) {
            errors.rejectValue("breed", "size");

        }

        if (!petsDTO.getBreed().matches("^[A-Za-z]+$")) {
            errors.rejectValue("breed", "containsIntegersOrSpecialChars");
        }

        // Validation for age

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "empty");

        if (petsDTO.getAge() > 2) {
            errors.rejectValue("age", "invalidAge");

        }
    }
}
