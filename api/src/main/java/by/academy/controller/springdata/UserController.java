package by.academy.controller.springdata;

import by.academy.controller.requests.UserCreateRequest;
import by.academy.controller.requests.UserUpdateRequest;
import by.academy.domain.Gender;
import by.academy.domain.entity.Credentials;
import by.academy.domain.entity.HibernateUser;
import by.academy.repository.springdata.UserSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {
    private final UserSpringDataRepository userSpringDataRepository;

    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "Select all users", description = "Select all users")
    @GetMapping("/findAll")
    public ResponseEntity<Object> userFindAllEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                userSpringDataRepository.findAll()), HttpStatus.OK);
    }


    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "Select user by id", description = "Select user by id")
    @GetMapping("/findById")
    public ResponseEntity<Object> userFindByIdEndpoint(
            @RequestParam("id") @Min(1) @Parameter(
                    description = "User id") Long userId) {
        if (userSpringDataRepository.existsById(userId)) {
            return new ResponseEntity<>(Collections.singletonMap("result",
                    userSpringDataRepository.findById(userId)), HttpStatus.OK);
        } else return new ResponseEntity<>("User not found", HttpStatus.OK);
    }

    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "Select user by gender", description = "Select user by gender")
    @GetMapping("/findByGender")
    public ResponseEntity<Object> userFindAllUserByGender(@RequestParam("gender") @Parameter(
            description = "User gender") String gender) {
        return new ResponseEntity<>(Collections.singletonMap("result",
                userSpringDataRepository.findUserByGender(gender)), HttpStatus.OK);
    }

    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "Create new user", description = "Create new user")
    @PostMapping("/createUser")
    @Transactional
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {

        HibernateUser user = new HibernateUser();
        user.setUserName(createRequest.getUserName());
        user.setSurName(createRequest.getSurName());
        user.setBirthDate(createRequest.getBirthDate());
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        Credentials credentials = new Credentials(createRequest.getLogin(), createRequest.getPassword());

        user.setCredentials(credentials);
        user.setGender(createRequest.getGender());

        HibernateUser createdUser = userSpringDataRepository.save(user);

        userSpringDataRepository.createRoleRow(createdUser.getId(), userSpringDataRepository.findById(1L).get().getId());

        Map<String, Object> model = new HashMap<>();
        model.put("user", createdUser);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }


    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "HARD Delete user by id", description = "HARD Delete user by id")
    @GetMapping("/HardDelete")
    public String userDeleteByIdEndpoint(
            @RequestParam("id") @Min(1) @Parameter(
                    description = "User id") Long userId) {
        if (userSpringDataRepository.existsById(userId)) {
            userSpringDataRepository.deleteById(userId);
            return "User has been deleted";
        } else return "User not found";

    }

    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "Update user with Param", description = "Update user with Param")
    @PutMapping("/updateUserParam")
    public ResponseEntity<Object> userUpdateByIdEndpoint(

            @RequestParam("user_name") @Size(min = 1, max = 50) @Parameter(
                    description = "User name") String userName,
            @RequestParam("sur_name") @Size(min = 1, max = 50) @Parameter(
                    description = "User sur_name") String surName,
            @RequestParam("birth_date") @Parameter(
                    description = "User birth_date") Timestamp birthDate,
            @RequestParam("user_login") @Size(min = 1, max = 50) @Parameter(
                    description = "User login") String userLogin,
            @RequestParam("user_password") @Size(min = 8, max = 50) @Parameter(
                    description = "User password") String userPassword,
            @RequestParam("gender") @Parameter(
                    description = "User gender") Gender gender,
            @RequestParam("id") @Min(1) @Parameter(
                    description = "User id") Long userId) {

        Timestamp modificationDate = new Timestamp(new Date().getTime());

        String genderToString = gender.toString();

        if (userSpringDataRepository.existsById(userId)) {
            userSpringDataRepository.updateUserSuccess(userName, surName,
                    birthDate,
                    userLogin,
                    userPassword,
                    modificationDate,
                    genderToString,
                    userId);

            Map<String, Object> model = new HashMap<>();
            model.put("userName", userName);
            model.put("surName", surName);
            model.put("birthDate", birthDate);
            model.put("userLogin", userLogin);
            model.put("userPassword", userPassword);
            model.put("modificationDate", modificationDate);
            model.put("gender", gender);
            model.put("userId", userId);

            return new ResponseEntity<>(model, HttpStatus.OK);
        } else return new ResponseEntity<>("User not found", HttpStatus.OK);

    }

    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "Update user with ModelAttribute", description = "Update user with ModelAttribute")
    @PutMapping("/updateUserModelAttribute")
    public ResponseEntity<Object> userUpdateByIdEndpoint(
            @RequestBody UserUpdateRequest userUpdateRequest) {

        HibernateUser user = new HibernateUser();
        user.setId(userUpdateRequest.getId());
        user.setUserName(userUpdateRequest.getUserName());
        user.setSurName(userUpdateRequest.getSurName());
        user.setBirthDate(userUpdateRequest.getBirthDate());
        user.setModificationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(userUpdateRequest.getLogin(),
                userUpdateRequest.getPassword());

        user.setCredentials(credentials);
        user.setGender(userUpdateRequest.getGender());

        String genderToString = user.getGender().toString();

        if (userSpringDataRepository.existsById(user.getId())) {
            userSpringDataRepository.updateUserSuccess(
                    user.getUserName(),
                    user.getSurName(),
                    user.getBirthDate(),
                    user.getCredentials().getLogin(),
                    user.getCredentials().getPassword(),
                    user.getModificationDate(),
                    genderToString,
                    user.getId());

            Map<String, Object> model = new HashMap<>();
            model.put("userName", user.getUserName());
            model.put("surName", user.getSurName());
            model.put("birthDate", user.getBirthDate());
            model.put("userLogin", user.getCredentials().getLogin());
            model.put("userPassword", user.getCredentials().getPassword());
            model.put("modificationDate", user.getModificationDate());
            model.put("gender", user.getGender());
            model.put("userId", user.getId());

            return new ResponseEntity<>(model, HttpStatus.OK);
        } else return new ResponseEntity<>("User not found", HttpStatus.OK);

    }

    //  SOFT DELETE USER
    @Tag(name = "Endpoint for user", description = "CRUD operation for user")
    @Operation(summary = "SOFT Delete user by id", description = "SOFT Delete user by id")
    @GetMapping("/softDelete")
    public String userSoftDeleteByIdEndpoint(
            @RequestParam("id") @Min(1) @Parameter(
                    description = "User id") Long userId) {
        if (userSpringDataRepository.existsById(userId)) {
            userSpringDataRepository.softDeleteUser(userId);
            return "User has been deleted";
        } else return "User not found";

    }

}
