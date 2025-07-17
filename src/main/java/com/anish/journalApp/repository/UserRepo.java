package com.anish.journalApp.repository;

import com.anish.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

    public interface UserRepo extends MongoRepository<User, ObjectId>{

        User findByUserName(String username);
        void deleteByUserName(String username);
    }



