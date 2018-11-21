package com.example.sweater.Controllers;

import com.example.sweater.Repositories.EventRepository;
import com.example.sweater.Repositories.RoleRepository;
import com.example.sweater.Repositories.UserRepository;
import com.example.sweater.Services.UserService;
import com.example.sweater.domain.Event;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminMenu()
    {
        return "userlist";
    }
    @GetMapping("/users")
    public String userList(Model model)
    {
        model.addAttribute("users",userRepo.findAll() );
        return "deleteusers";
    }
    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id, Model model)
    {
        User user = userRepo.findById(id);
        userService.deleteUser(user);
        model.addAttribute("users", userRepo.findAll() );
        return "userlist";
    }
    @GetMapping("/events")
    public String eventList(Model model)
    {
        model.addAttribute("events",eventRepository.findAll() );
        return "deleteevents";
    }
    @GetMapping("/events/{name}")
    public String deleteEvent(@PathVariable String name, Model model)
    {
        Event event = eventRepository.findByname(name);
        eventRepository.delete(event);
        model.addAttribute("events", eventRepository.findAll());
        return "deleteevents";
    }


}
