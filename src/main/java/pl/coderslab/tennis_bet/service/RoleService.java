package pl.coderslab.tennis_bet.service;

import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getOne(int id);

    Role getByName(RoleName roleName);
}
