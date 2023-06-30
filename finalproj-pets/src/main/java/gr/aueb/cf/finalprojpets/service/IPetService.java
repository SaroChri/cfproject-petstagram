package gr.aueb.cf.finalprojpets.service;

import gr.aueb.cf.finalprojpets.dto.PetsDTO;
import gr.aueb.cf.finalprojpets.model.Pets;
import gr.aueb.cf.finalprojpets.service.exceptions.EntityNotFoundException;


import java.nio.file.Path;
import java.util.List;

/**
 * Interface for defining pet-related service operations.
 */
public interface IPetService {
    /**
     * Inserts a new pet.
     *
     * @param petsDTO The PetsDTO object containing the pet data.
     * @return The inserted Pets object.
     */
    Pets insertPet(PetsDTO petsDTO);
    /**
     * Updates an existing pet.
     *
     * @param petsDTO The PetsDTO object containing the updated pet data.
     * @return The updated Pets object.
     * @throws EntityNotFoundException If the pet with the specified ID is not found.
     */
    Pets updatePet(PetsDTO petsDTO) throws  EntityNotFoundException;
    /**
     * Deletes a pet by ID.
     *
     * @param id The ID of the pet to delete.
     * @throws EntityNotFoundException If the pet with the specified ID is not found.
     */
    void deletePet(Long id) throws EntityNotFoundException;
    /**
     * Retrieves a pet by ID.
     *
     * @param id The ID of the pet to retrieve.
     * @return The Pets object if found.
     * @throws EntityNotFoundException If the pet with the specified ID is not found.
     */
    Pets getPetById(Long id) throws EntityNotFoundException;
    /**
     * Retrieves pets by kind.
     *
     * @param kind The kind to search for.
     * @return A list of Pets objects matching the kind.
     * @throws EntityNotFoundException If no pets with the specified kind are found.
     */
    List<Pets> getByKind(String kind) throws EntityNotFoundException;
    /**
     * Retrieves pets by breed.
     *
     * @param breed The breed to search for.
     * @return A list of Pets objects matching the breed.
     * @throws EntityNotFoundException If no pets with the specified breed are found.
     */

    List<Pets> getByBreed(String breed) throws EntityNotFoundException;

    List<Pets> getAllPets() throws EntityNotFoundException;

    void save(PetsDTO petsDTO);
}
