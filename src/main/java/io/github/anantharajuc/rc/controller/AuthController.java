package io.github.anantharajuc.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.dto.AuthenticationResponse;
import io.github.anantharajuc.rc.dto.UserLoginRequestDTO;
import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;
import io.github.anantharajuc.rc.service.AuthServiceImpl;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	@Autowired
	private AuthServiceImpl authServiceImpl;

	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDTO userDto) 
	{
		authServiceImpl.signup(userDto);
		
        return ResponseEntity.status(OK).body("User Registration Successful!");
    }
	
	@GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) 
	{
        return new ResponseEntity<>(authServiceImpl.verifyAccount(token), OK);
    } 
	
	@PostMapping("/login")
    public AuthenticationResponse login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) 
	{
		return authServiceImpl.login(userLoginRequestDTO);
    }
} 