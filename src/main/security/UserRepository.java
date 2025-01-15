package org.gestionare_taskuri.security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :usr")
    User findUserByUsername(@Param("usr") String username);
}
