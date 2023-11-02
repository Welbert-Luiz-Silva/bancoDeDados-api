package com.lucasangelo.todosimple.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Task;

@Repository
public interface TaskRepositories extends JpaRepository<Task, Long>{


    List<Task> findByUser_Id (Long id);

}
