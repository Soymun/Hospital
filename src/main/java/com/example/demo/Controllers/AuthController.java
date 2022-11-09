package com.example.demo.Controllers;


import com.example.demo.DTO.LoginDto;
import com.example.demo.DTO.RegistrationDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Security.JwtTokenProvider;
import com.example.demo.Service.Ipm.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hospital")
@Slf4j
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserServiceImp userServiceImp;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtTokenProvider jwtTokenProvider, UserServiceImp userServiceImp, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userServiceImp = userServiceImp;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDto registrationDto){
       if(userServiceImp.getUserByEmail(registrationDto.getEmail()) != null){
           throw new RuntimeException("User already exsist");
       }
       User user = new User();
       user.setEmail(registrationDto.getEmail());
       user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
       user.setAge(registrationDto.getAge());
       user.setName(registrationDto.getName());
       user.setSurname(registrationDto.getSurname());
       user.setOMS(registrationDto.getOMS());
       user.setPlotId(registrationDto.getPlotId());
       user.setSnils(registrationDto.getSnils());
       user.setRole(Role.USER);
       userServiceImp.saveUser(user);
       return ResponseEntity.ok("Ok");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try{
            User user = userServiceImp.getUserByEmail(loginDto.getEmail());
            if(user == null){
                log.debug("User not found");
                throw  new NotFoundException("User not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("role", user.getRole());
            map.put("token", token);
            return ResponseEntity.ok(map);
        }
        catch (AuthenticationException e){
            throw new RuntimeException("");
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(servletRequest, servletResponse, null);
        return ResponseEntity.ok("Suggest");
    }
}
