package com.lucasangelo.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

//import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepositories;

@Service
public class TaskService {

    @Autowired
    private TaskRepositories taskRepositories;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepositories.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()
        ));
    }

    @Transactional
    public Task create (Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepositories.save(obj);
        return obj;        
    }

    @Transactional
    public Task update (Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepositories.save(newObj);
    }

    public void delete (Long id) {
        findById(id);
        try {
            this.taskRepositories.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");
        }
    }
    


    
}
