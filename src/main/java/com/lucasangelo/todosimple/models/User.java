package com.lucasangelo.todosimple.models;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import java.util.Objects;
//import org.springframework.scheduling.config.Task;

@Entity
@Table(name = "user")
public class User {
    public interface CreatUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull (groups = CreatUser.class)
    @NotEmpty (groups = CreatUser.class)
    @Size(groups = CreatUser.class, min = 2, max = 100)
    private String username;

    
    @Column(name = "password", length = 60, nullable = false)
    @NotNull (groups = {CreatUser.class, UpdateUser.class})
    @NotEmpty (groups = {CreatUser.class, UpdateUser.class})
    @Size(groups = {CreatUser.class, UpdateUser.class}, min = 5, max = 100)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();
    

    public User() {
    }


    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    

}
