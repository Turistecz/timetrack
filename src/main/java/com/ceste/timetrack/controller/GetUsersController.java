package com.ceste.timetrack.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.service.GetUsersService;




@RestController
@RequestMapping("/admin")
public class GetUsersController {

     private final GetUsersService getUsersService;

    public GetUsersController (GetUsersService getUsersService) {
        this.getUsersService = getUsersService;
        }


     @GetMapping("/getUsers")

    public List<Employee> getUsers(){
        return this.getUsersService.getUsers();

    }
    
}
