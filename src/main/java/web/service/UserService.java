package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.repository.UserRepository;
import web.model.User;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public List<User> showUser() {
        return userRepository.findAll();
    }



    public void saveUser(User user) {
        userRepository.save(user);

    }



    public void updateUser(int id, User user) {
       userRepository.saveAndFlush(user);

    }



    public void removeUser(int id) {
        userRepository.deleteById(id);

    }



    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }
}