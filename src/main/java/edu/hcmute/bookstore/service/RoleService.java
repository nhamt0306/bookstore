package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.RoleEntity;
import edu.hcmute.bookstore.model.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findByName(RoleName name);
}
