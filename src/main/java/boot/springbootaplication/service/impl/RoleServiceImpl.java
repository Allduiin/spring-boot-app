package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.Role;
import boot.springbootaplication.repositories.RoleRepository;
import boot.springbootaplication.service.RoleService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public Role getByRoleName(Role.RoleName roleName) {
        return roleRepository.findOne(Example.of(new Role(roleName)))
                .orElseThrow(() -> new RuntimeException("Role not found by role name"));
    }
}
