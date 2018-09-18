package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.Role;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.RoleName;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getOne(int id);

    Role getByName(RoleName roleName);
}
