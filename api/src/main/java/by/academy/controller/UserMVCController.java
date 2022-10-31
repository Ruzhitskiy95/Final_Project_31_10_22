package by.academy.controller;

import by.academy.controller.requests.UserSearchRequest;
import by.academy.domain.User;
import by.academy.exeption.service.UserService;
//import by.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserMVCController {

    private final UserService userService;

    @GetMapping
    public ModelAndView findAllUsers() {
        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
//        model.addObject("user", "Karpenko");
        model.addObject("users", users);

        model.setViewName("hello");

        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView findUserById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long userId = Long.parseLong(id);
        User user = userService.findById(userId);

        ModelAndView model = new ModelAndView();
        model.addObject("userName", user.getUserName());
        model.addObject("user", user);

        model.setViewName("userId");

        return model;
    }

    @GetMapping("/search")
    public ModelAndView findAllUsersWithParams(@ModelAttribute UserSearchRequest userSearchRequest) {
        int verifyLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifyOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.findAll(verifyLimit, verifyOffset);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("hello");

        return modelAndView;
    }
}
