package boot.springbootaplication.service;

import boot.springbootaplication.model.Role;

public interface RoleService {
    void save(Role role);

    Role getByRoleName(Role.RoleName roleName);
}
