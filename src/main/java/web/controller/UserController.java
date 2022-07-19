package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/showAll")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String showAll(ModelMap model) {
        List<User> users = userService.showUser();
        model.addAttribute("users", users);
        return "showAll";
    }

    @GetMapping("/{id}/info")
    public String info(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("users", userService.getUser(id));
        return "info";
    }
    @GetMapping("/newUser")
    public String addNewUser(ModelMap model) {
        model.addAttribute("users", new User());
        return "newUser";
    }
    @PostMapping()
    private String createUser(@ModelAttribute("users") User user){
        userService.saveUser(user);
        return "redirect:/showAll";

    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id ) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/showAll";
    }
    @GetMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.removeUser(id);
        return "redirect:/showAll";

    }

}