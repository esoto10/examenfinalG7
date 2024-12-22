package com.codigo.ms_seguridad.controller;

import com.codigo.ms_seguridad.aggregates.request.SignInRequest;
import com.codigo.ms_seguridad.aggregates.response.SignInResponse;
import com.codigo.ms_seguridad.entity.Usuario;
import com.codigo.ms_seguridad.service.AuthenticationService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/authentication/v1/")
@RequiredArgsConstructor
@RefreshScope
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/users/{username}")
    public ResponseEntity<Usuario> getuserbyname(@PathVariable String username){
        return ResponseEntity.ok(authenticationService.findbyname(username));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(
            @RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService
                .signIn(signInRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getALl(){
        return ResponseEntity.ok(authenticationService.todos());
    }

    @GetMapping("/clave")
    public ResponseEntity<String> getClaveFirma(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String dato = Base64.getEncoder().encodeToString(key.getEncoded());
        return ResponseEntity.ok(dato);
    }
}
