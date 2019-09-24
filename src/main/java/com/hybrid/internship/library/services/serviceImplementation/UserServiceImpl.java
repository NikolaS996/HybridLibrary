package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.exceptions.RentedCopiesException;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.repositories.UserRepository;
import com.hybrid.internship.library.services.BookRentalService;
import com.hybrid.internship.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRentalService bookRentalService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean isUsernameAvailable(String username) {
        if (userRepository.findByUsername(username).orElseGet(() -> null) == null)
            return true;
        return false;
    }

    @Override
    public Boolean isUsernameAvailableForUpdate(String username, Long id) {
        if(userRepository.findByUsernameForUpdate(username, id).orElseGet(() -> null) == null)
            return true;
        return false;
    }

    @Override
    public User create(User user) {
        if(isUsernameAvailable(user.getUsername()))
            return userRepository.save(user);
        return null;
    }


    @Override
    public User update(User user) {
        if (isUsernameAvailableForUpdate(user.getUsername(), user.getId()))
            return userRepository.save(user);
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!bookRentalService.findAllByUserIdAndIsRented(id, false).isEmpty())
            throw new RentedCopiesException();
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }
}
