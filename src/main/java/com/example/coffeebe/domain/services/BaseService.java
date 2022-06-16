package com.example.coffeebe.domain.services;

import com.example.coffeebe.domain.entities.Sequence;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.repositories.RoleRepository;
import com.example.coffeebe.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class BaseService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;


    public long generateSequence(String seqName) {
        Sequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                Sequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public User getUser(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("User not exist");
        return user;
    }

}
