package gr.aueb.cf.finalprojpets.dto;

import gr.aueb.cf.finalprojpets.model.Pets;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PetsDTO {
    private Long id;

    private String name;

    private String gender;

    private int age;

    private String YearMonth;

    private String kind;

    private String breed;

    private String neutered;

    private String vaccinated;

    private String illness;

    public PetsDTO(Long id, String name, String gender, int age,
                   String yearMonth, String kind, String breed,
                   String neutered, String vaccinated, String illness)
    {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.YearMonth = yearMonth;
        this.kind = kind;
        this.breed = breed;
        this.neutered = neutered;
        this.vaccinated = vaccinated;
        this.illness = illness;

    }
}
