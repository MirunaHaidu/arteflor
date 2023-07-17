package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.model.user.Role;
import com.demo.arteflor.repository.user.RoleRepository;
import com.demo.arteflor.service.user.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return new ArrayList<>(roleRepository.findAll());
    }
}
