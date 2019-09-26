package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.exceptions.RentedCopiesException;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.repositories.UserRepository;
import com.hybrid.internship.library.services.BookRentalService;
import com.hybrid.internship.library.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRentalService bookRentalService;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean isUsernameAvailable(String username) {
        if (userRepository.findByUsername(username).orElseGet(() -> null) == null) {
            log.info("Username {} is available", username);
            return true;
        }
        log.info("Username {} isn't available", username);
        return false;
    }

    @Override
    public Boolean isUsernameAvailableForUpdate(String username, Long id) {
        if(userRepository.findByUsernameForUpdate(username, id).orElseGet(() -> null) == null) {
            //log.info("Username {} is available for the update", username);
            return true;
        }
        return false;
    }

    @Override
    public User create(User user) {
        if(isUsernameAvailable(user.getUsername())) {
            User savedUser = userRepository.save(user);
            log.info("User {} is created.", savedUser);
            return savedUser;
        }
        return null;
    }


    @Override
    public User update(User user) {
        if (isUsernameAvailableForUpdate(user.getUsername(), user.getId())) {
            User updatedUser = userRepository.save(user);
            log.info("User {} is updated.", updatedUser);
            return updatedUser;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!bookRentalService.findAllByUserIdAndIsReturned(id, false).isEmpty()) {
            log.info("User has rented copies and therefore can't be deleted.");
            throw new RentedCopiesException();
        }
        log.info("User with ID {} successfully deleted.", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }
}
