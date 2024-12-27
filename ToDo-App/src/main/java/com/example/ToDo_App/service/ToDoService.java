package com.example.ToDo_App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.repository.ToDoRepo;

@Service
public class ToDoService {
    @Autowired
    ToDoRepo toDoRepo;

    public List <ToDo> getAllToDoItems(){
        ArrayList<ToDo> toDoList = new ArrayList<>();
        toDoRepo.findAll().forEach(toDo -> toDoList.add(toDo));
        return toDoList;
    }
    public ToDo getToDoItemById(Long id){
        return toDoRepo.findById(id).get();

    }
    public boolean updateStatus(Long id){
        ToDo toDo = getToDoItemById(id);
        toDo.setStatus("Completed");
        return saveItem(toDo);

    }

    public boolean saveItem(ToDo toDo){
        ToDo newToDo = toDoRepo.save(toDo);
        if (getToDoItemById(newToDo.getId()) != null){
            return true;
        }
        return false;

    }
    public boolean deleteToDoItem(Long id){
        toDoRepo.deleteById(id);
        if (getToDoItemById(id) == null){
            return true;
        }
        return false;
    }    
}
