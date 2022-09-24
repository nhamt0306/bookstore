package edu.hcmute.bookstore.repository;

import edu.hcmute.bookstore.model.RoleEntity;
import edu.hcmute.bookstore.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName name);
}
