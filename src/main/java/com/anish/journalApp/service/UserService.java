package com.anish.journalApp.service;

import com.anish.journalApp.entity.User;
import com.anish.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

        @Autowired
        private UserRepo UserRepository;

        private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        public boolean saveNewUser(User user){
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("User"));
                UserRepository.save(user);
                return true;
            } catch (Exception e) {
                log.trace("User already created or exists {}", user.getUserName(),e);
                log.debug("User already created or exists {}", user.getUserName(),e);
                log.warn("User already created or exists {}", user.getUserName(),e);
                log.info("User already created or exists {}", user.getUserName(),e);
                log.error("User already created or exists {}", user.getUserName(),e);
                return false;
            }
        }
        public void saveAdmin(User user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User","ADMIN"));
            UserRepository.save(user);
        }
        public void saveUser(User user){
            UserRepository.save(user);
        }

        public List<User> getAll(){
            return UserRepository.findAll();
        }
        public Optional<User> findById(ObjectId id){
            return UserRepository.findById(id);
        }
        public void deleteById(ObjectId id){

            UserRepository.deleteById(id);
        }
    public User findByUserName(String username){
        return UserRepository.findByUserName(username);
    }
    }


//controller --> service --> repository

