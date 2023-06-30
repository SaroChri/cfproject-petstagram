package gr.aueb.cf.finalprojpets.service;

import gr.aueb.cf.finalprojpets.dto.PetsDTO;
import gr.aueb.cf.finalprojpets.model.Pets;
import gr.aueb.cf.finalprojpets.repository.PetRepository;
import gr.aueb.cf.finalprojpets.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.finalprojpets.service.exceptions.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the IPetService interface.
 */
@Service
public class PetServiceImpl implements IPetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    @Override
    public Pets insertPet(PetsDTO petsDTO) {
            return petRepository.save(convertToPets(petsDTO));
        }

    @Transactional
    @Override
    public Pets updatePet(PetsDTO petsDTO) throws EntityNotFoundException {
        Pets pet = petRepository.findPetById(petsDTO.getId());
        if (pet == null) throw new EntityNotFoundException(Pets.class, petsDTO.getId());
        return petRepository.save(convertToPets(petsDTO));
    }

    @Transactional
    @Override
    public void deletePet(Long id) { petRepository.deleteById(id);}

    @Override
    public List<Pets> getByKind(String kind) throws EntityNotFoundException {
        List<Pets> pets;
        pets = petRepository.findByKind(kind);
        if (pets.size() == 0) throw new EntityNotFoundException(Pets.class, 0L);
        return pets;
    }

    @Override
    public List<Pets> getByBreed(String breed) throws EntityNotFoundException {
        List<Pets> pets;
        pets = petRepository.findByBreed(breed);
        if (pets.size() == 0) throw  new EntityNotFoundException(Pets.class, 0L);
        return pets;
    }


    @Override
    public List<Pets> getAllPets() throws EntityNotFoundException {
        List<Pets> pets = petRepository.findAll();

        if (pets.isEmpty()) {
            throw new EntityNotFoundException(Pets.class,null);
        }
        return pets;
    }

    @Override
    public void save(PetsDTO petsDTO) {
        Pets pet = convertToPets(petsDTO);

        petRepository.save(pet);

    }

    public  Pets getPetById(Long id) throws EntityNotFoundException {
        Optional<Pets> pet;
        pet = petRepository.findById(id);
        if (pet.isEmpty()) throw new EntityNotFoundException(Pets.class, 0L);
        return  pet.get();
    }

    /**
     * Converts a PetsDTO object to a Pets object.
     *
     * @param dto the PetsDTO object to convert
     * @return the converted Pets object
     */
    private static Pets convertToPets(PetsDTO dto) {
        return new Pets(dto.getId(), dto.getName(), dto.getGender(), dto.getAge(), dto.getYearMonth(),
                dto.getKind(), dto.getBreed(), dto.getNeutered(), dto.getVaccinated(), dto.getIllness());
    }

}
