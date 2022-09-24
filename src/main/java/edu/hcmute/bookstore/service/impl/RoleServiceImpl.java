package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.model.RoleEntity;
import edu.hcmute.bookstore.model.RoleName;
import edu.hcmute.bookstore.repository.RoleRepository;
import edu.hcmute.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
