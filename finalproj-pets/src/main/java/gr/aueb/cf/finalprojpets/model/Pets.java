package gr.aueb.cf.finalprojpets.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PETS")
public class Pets {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;


    @Column(name="GENDER", nullable = false)
    private String gender;

    @Column(name="AGE")
    private int age;

    @Column(name="YEARMONTH")
    private String YearMonth;


    @Column(name="KIND", nullable = false)
    private String kind;

    @Column(name="BREED", nullable = false)
    private String breed;


    @Column(name="NEUTERED", nullable = false)
    private String neutered;


    @Column(name="VACCINATED", nullable = false)
    private String vaccinated;

    @Column(name="ILLNESS")
    private String illness;

    public Pets(Long id, String name, String gender, int age,
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
