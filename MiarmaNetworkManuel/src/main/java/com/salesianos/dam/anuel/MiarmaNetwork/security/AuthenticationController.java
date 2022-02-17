package com.salesianos.dam.anuel.MiarmaNetwork.security;


import com.salesianos.dam.anuel.MiarmaNetwork.security.jwt.JwtProvider;
import com.salesianos.dam.anuel.MiarmaNetwork.security.payload.JwtUserResponse;
import com.salesianos.dam.anuel.MiarmaNetwork.security.payload.LoginRequest;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtProvider jwtProvider;


    //Endopoint login
    @PostMapping("auth/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getNick(),
                                loginRequest.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(user, jwt));
    }

    private JwtUserResponse convertUserToJwtUserResponse(User user, String jwt){
        return JwtUserResponse.builder()
                .nick(user.getNick())
                .biografia(user.getBiografia())
                .email(user.getEmail())
                .phone(user.getPhone())
                .fechaNacimiento(user.getFechaNacimiento())
                .avatar(user.getAvatar())
                .token(jwt)
                .build();
    }


}
