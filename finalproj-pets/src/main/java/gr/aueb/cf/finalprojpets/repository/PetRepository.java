package gr.aueb.cf.finalprojpets.repository;

import gr.aueb.cf.finalprojpets.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on Pet entities.
 */
@Repository
public interface PetRepository extends JpaRepository<Pets, Long> {

/**
 * Finds pets by kind.
 *
 * @param kind The kind to search for.
 * @return A list of Pets objects matching the kind.
 */
    List<Pets> findByKind(String kind);
    /**
     * Finds pets by breed.
     *
     * @param breed The breed to search for.
     * @return A list of Pets objects matching the breed.
     */
    List<Pets> findByBreed(String breed);
    /**
     * Finds a pet by ID.
     *
     * @param id The ID of the pet to find.
     * @return The Pets object if found, null otherwise.
     */
    Pets findPetById(Long id);
}
