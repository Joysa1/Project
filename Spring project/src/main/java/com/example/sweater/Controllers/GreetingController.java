package com.example.sweater.Controllers;

import com.example.sweater.Repositories.EventRepository;
import com.example.sweater.Repositories.GuestRepository;
import com.example.sweater.Repositories.UserRepository;
import com.example.sweater.Services.GuestService;
import com.example.sweater.Services.UserService;
import com.example.sweater.config.MailSender;
import com.example.sweater.domain.Event;
import com.example.sweater.domain.Guest;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
public class GreetingController {
    @Value("${upload.path}")
    String uploadPath;
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Event> events = eventRepository.findAll();
        if (filter != null && !filter.isEmpty() ) {
            events = eventRepository.findByplace(filter);
        } else {
            events = eventRepository.findAll();
        }
        model.addAttribute("events", events);
        model.addAttribute("filter", filter);
        return "main";
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserService userService;

    @GetMapping("/main/{name}")
            public String aboutEvent(@PathVariable String name,Model model)
    {
        GuestService guestService = new GuestService(guestRepository);
        Event event = eventRepository.findByname(name);
        model.addAttribute("event", event);
        model.addAttribute("count", guestService.countOfquests(event));
        return "event";
    }
    @PostMapping("/main/{name}")
            public String addGuest(@PathVariable String name, @Valid Guest guest, BindingResult bindingResult,  Model model)
    {
        if(bindingResult.hasErrors())
        {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("guest", guest);
            GuestService guestService = new GuestService(guestRepository);
            Event event = eventRepository.findByname(name);
            model.addAttribute("event", event);
            model.addAttribute("count", guestService.countOfquests(event));
            return "event";
        }
        else {
            Event thisevent = eventRepository.findByname(name);
            GuestService guestService = new GuestService(guestRepository);
            int count = guestService.countOfquests(thisevent);
            if (guest.getAge() >= thisevent.getRequiredage() && count < thisevent.getNumberofguests()) {

                guest.setEvent(thisevent);

                mailSender.send(guest.getEmail(), "Регистрация на " + name, "Этот email адрес был указан при регистрации на " + name + " которой пройдёт " + thisevent.getDate());
                guestRepository.save(guest);
                model.addAttribute("message", "Вы успешно зарегистрировались");

            } else if (guest.getAge() <= thisevent.getRequiredage())
                model.addAttribute("message", "Вы слишком молоды для данного мероприятия");
            else if (count >= thisevent.getNumberofguests())
                model.addAttribute("message", "Увы, но для вас нет места на этом мероприятии");
            else model.addAttribute("message", "Неизвестная ошибка");
            return "acceptguest";
        }

    }

    @PostMapping("/main")
    public String addNewUser(@Valid Event event, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) throws IOException {

        if(bindingResult.hasErrors())
        {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("event", event);
        }
        else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                event.setFilename(resultFileName);
            }
            eventRepository.save(event);
        }
        model.addAttribute("events", eventRepository.findAll());
        return "main";
    }
    @GetMapping("/profile")
    public String getProfile(Model model, Principal user) {
        model.addAttribute("username", user.getName());
        return "profile";
    }
    @PostMapping("/profile")
    public String updateProfile(Principal user, @RequestParam String password, @RequestParam String password2, Model model)
    {
        if(password.equals(password2))  userService.updateUser(user.getName(), password);
        else model.addAttribute("message", "Пароли не совпадают");
        return "redirect:/profile";
    }
}

