package fr.diginamic.coursSpringBoot.controller;


import fr.diginamic.coursSpringBoot.dto.AuthRequestDto;
import fr.diginamic.coursSpringBoot.dto.JwtResponseDto;
import fr.diginamic.coursSpringBoot.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class IndexController {

    @Autowired
    JwtService  jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping
    public String getIndex(Model model, Authentication auth) {
   return "index";
    }

    @PostMapping
    public JwtResponseDto AuthenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO) {
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(auth.isAuthenticated()){
            return JwtResponseDto.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
