package com.example.ToDo_App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.service.ToDoService;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping("/viewToDoList")
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message){
        model.addAttribute("list", service.getAllToDoItems());
        model.addAttribute("msg", message);
        return "viewToDoList";
    }

    @PostMapping("/updateToDoStatus")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(service.updateStatus(id)){
            redirectAttributes.addFlashAttribute("message", "Status updated successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Status Update Failed!");
        return "redirect:/viewToDoList";
        
    }
    @GetMapping ("/addToDoItem")
    public String addToDoItem(Model model){
        model.addAttribute("todo", new ToDo());
        return "AddToDoItem";
        

    }
    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo toDo, RedirectAttributes redirectAttributes){
        if (service.saveItem(toDo)){
            redirectAttributes.addFlashAttribute("message", "Saved Succesfully!");
            return "redirect:/viewToDoList";


        }
        redirectAttributes.addFlashAttribute("message", "Save Failed!");
        return "redirect:/addToDoItem";
        
    }
    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model){
        model.addAttribute("toDo", service.getToDoItemById(id));
        return "EditToDoItem";
        

    }
    @PostMapping("/updateToDoItem")
    public String updateToDoItem(ToDo toDo, RedirectAttributes redirectAttributes){
        if (service.saveItem(toDo)){
            redirectAttributes.addFlashAttribute("message", "Update Succesfully!");
            return "redirect:/viewToDoList";


        }
        redirectAttributes.addFlashAttribute("message", "Update Failed!");
        return "redirect:/editToDoItem/"+ toDo.getId();
        

    }
    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(service.deleteToDoItem(id)){
            redirectAttributes.addFlashAttribute("message", "Delete Success!");
        }
        redirectAttributes.addFlashAttribute("message", "Delete Failure!");
        return "redirect:/viewToDoList";



    }
    
}
