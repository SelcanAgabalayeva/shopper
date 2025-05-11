package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
