package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.User;
import com.emi.nutritrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user, Long id) {

        Optional<User> existe = userRepository.findById(id);

        if (existe.isPresent()) {

            User userExistant = existe.get();

            userExistant.setUsername(user.getUsername());
            userExistant.setFirstName(user.getFirstName());
            userExistant.setLastName(user.getLastName());
            userExistant.setBirthDate(user.getBirthDate());
            userExistant.setWeight(user.getWeight());
            userExistant.setEmail(user.getEmail());
            userExistant.setPassword(user.getPassword());

            userRepository.save(userExistant);
        }
    }
}