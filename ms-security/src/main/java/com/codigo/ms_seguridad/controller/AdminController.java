package com.codigo.ms_seguridad.controller;

import com.codigo.ms_seguridad.aggregates.request.SignUpRequest;
import com.codigo.ms_seguridad.entity.Usuario;
import com.codigo.ms_seguridad.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/v1/")
@RequiredArgsConstructor

public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/createuser")
    public ResponseEntity<Usuario> signUpUser(
            @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService
                .signUpUser(signUpRequest));
    }

    @PostMapping("/users")
    public ResponseEntity<Usuario> signUpAdmin(
            @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService
                .signUpAdmin(signUpRequest));
    }

    @GetMapping("/saludo")
    public ResponseEntity<String> getSaludo(){
        return ResponseEntity.ok("Hola Userrr!!");
    }


}
