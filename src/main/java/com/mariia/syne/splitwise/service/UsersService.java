package com.mariia.syne.splitwise.service;

import com.mariia.syne.splitwise.entity.Users;
import com.mariia.syne.splitwise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    public List<Users> getAllUsers() {

        List<Users> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    public Users getUser(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    public void addUser(Users user) {

        usersRepository.save(user);
    }

    public void updateUser(Users user, Integer id) {

        usersRepository.save(user);
    }

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}
