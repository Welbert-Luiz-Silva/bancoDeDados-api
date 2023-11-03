package com.lucasangelo.todosimple.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepositories;
import com.lucasangelo.todosimple.repositories.UserRepositories;

@Service
public class UserService {

    @Autowired
    private UserRepositories userRepositories;

     @Autowired
    private TaskRepositories taskRepositories;

  
    public User findById(Long id){

        Optional<User> user = this.userRepositories.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
        ));

    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepositories.save(obj);
        this.taskRepositories.saveAll(obj.getTasks());
        return obj;
    }


    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepositories.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepositories.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");
        }
    }



    
}
