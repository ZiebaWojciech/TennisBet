package pl.coderslab.tennis_bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getByName(RoleName roleName);
}
