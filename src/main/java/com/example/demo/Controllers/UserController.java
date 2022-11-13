package com.example.demo.Controllers;


import com.example.demo.DTO.ReviewsDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.Facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userServiceImp) {
        this.userFacade = userServiceImp;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return userFacade.getUserById(id);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        return userFacade.updateUser(userDto);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userFacade.deleteUser(id);
    }

    @GetMapping("/user/troubles/{id}")
    public ResponseEntity<?> getUserTroubles(@PathVariable Long id){
        return userFacade.getTroublesByUserId(id);
    }

    @GetMapping("/user/schedule/{id}")
    public ResponseEntity<?> getUserSchedule(@PathVariable Long id){
        return userFacade.getScheduleByUserId(id);
    }

    @GetMapping("/user/record/{id}")
    public ResponseEntity<?> getUserRecord(@PathVariable Long id){
        return userFacade.getALLRecordByUserId(id);
    }

    @PostMapping("/user/reviews")
    public ResponseEntity<?> saveReviews(@RequestBody ReviewsDto reviewsDto){
        return userFacade.saveReviews(reviewsDto);
    }
}
