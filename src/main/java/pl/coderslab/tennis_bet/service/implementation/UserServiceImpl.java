package pl.coderslab.tennis_bet.service.implementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.repository.RoleRepository;
import pl.coderslab.tennis_bet.repository.UserRepository;
import pl.coderslab.tennis_bet.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public User getByLogin(String login) {
        return userRepository.getByUsername(login);
    }

    @Override
    public User saveUserWithPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.getByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public User saveUserWithoutPassword(User user) {
        return userRepository.save(user);
    }
}
