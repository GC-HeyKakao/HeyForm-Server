package heykakao.HeyForm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.model.User;
import heykakao.HeyForm.model.dto.SurveyQuestionDto;
import heykakao.HeyForm.model.dto.UserDto;
import heykakao.HeyForm.repository.UserRepository;
import heykakao.HeyForm.service.DtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ResponseBody
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DtoService dtoService;
    UserDto userDto;
    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    // format { account : String }
//    @GetMapping("/user/register/{userAccount}")
//    public void registerUser(@RequestParam String userAccount) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        userDto = objectMapper.readValue(userAccount, UserDto.class);
//        dtoService.saveUser(userDto);
//    }
//    @GetMapping("/user/register/{userAccount}")
//    public Long registerUser(@RequestParam String userAccount){
//        return dtoService.saveUser(userAccount);
//    }
//
    @PostMapping("/user/register")
    public User registerUser(@Validated @RequestBody User usr){
        return dtoService.saveUser(usr);
    }



}

