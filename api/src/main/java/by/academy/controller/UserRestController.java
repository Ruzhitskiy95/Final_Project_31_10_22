package by.academy.controller;

import by.academy.controller.requests.UserCreateRequest;
import by.academy.controller.requests.UserSearchRequest;
import by.academy.domain.User;
import by.academy.exeption.service.UserService;
import by.academy.repository.hibernate.HibernateUserInterface;
//import by.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/users")
public class UserRestController {
    private final UserService userService;


    private final HibernateUserInterface hibernateUserInterface;

    @GetMapping
    @RequestMapping("/hibernate")
    public ResponseEntity<Object> findAllHibernateUsers() {

        return new ResponseEntity<>(Collections.singletonMap("result", hibernateUserInterface.findAll()), HttpStatus.OK);
    }

    @GetMapping
//    @ResponseStatus(HttpStatus.OK)

//    public List<User> findAllUsers (){
//        return userService.findAll();
//    }
//    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllUsers() {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);

        //return Collections.singletonMap("result", userService.findAll());
    }

    @GetMapping ("/search")
    public ResponseEntity<Object> findAllUsersWithParams (@ModelAttribute UserSearchRequest userSearchRequest) {
        int verifiedLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.search(verifiedLimit, verifiedOffset);
        Map<String, Object> model = new HashMap<>();
        model.put("user", "Lubochkin");
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
@GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findUserById (@PathVariable String id){
        long userId = Long.parseLong(id);
        return new ResponseEntity<>(Collections.singletonMap("user", userService.findById(userId)), HttpStatus.OK);
}

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {
        User user = new User();
        user.setUserName(createRequest.getUserName());
        user.setSurName(createRequest.getSurName());
        user.setBirthDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        userService.create(user);
        List<User> users = userService.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user.getUserName());
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public long deleteUserById (@PathVariable String id) {
        long userId = Long.parseLong(id);
        System.out.println(userId);
        Long deleteMyId = userService.delete(userId);
        return deleteMyId;
    }

}
