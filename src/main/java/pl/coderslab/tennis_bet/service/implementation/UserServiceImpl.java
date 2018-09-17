package pl.coderslab.tennis_bet.service.implementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;
import pl.coderslab.tennis_bet.repository.UserRepository;
import pl.coderslab.tennis_bet.service.RoleService;
import pl.coderslab.tennis_bet.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleService.getByName(RoleName.ROLE_USER);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User saveUserWithoutPassword(User user) {
        return userRepository.save(user);
    }
}
