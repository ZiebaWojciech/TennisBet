package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.Role;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.RoleName;
import pl.coderslab.tennis_bet.betting_site.repository.RoleRepository;
import pl.coderslab.tennis_bet.betting_site.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getOne(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role getByName(RoleName roleName) {
        return roleRepository.getByName(roleName);
    }
}