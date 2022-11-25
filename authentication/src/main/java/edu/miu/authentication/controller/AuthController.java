package edu.miu.authentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.authentication.model.*;
import edu.miu.authentication.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authentication")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws JsonProcessingException {
        AuthenticationStatus status = authService.authenticate(authRequest);
        if (!status.getIsAuthenticated()){
            List<String> details = new ArrayList<>();
            details.add(status.getMessage());
            ErrorResponseDto error = new ErrorResponseDto(new Date(),
                    HttpStatus.UNAUTHORIZED.value(),
                    "UNAUTHORIZED", details, "uri");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(status.getData());

        final String token = authService.generateToken(json);
        final String refreshToken = authService.generateRefreshToken(json);

        return ResponseEntity.ok(new AuthClientResponse(token,refreshToken));
    }

    @PostMapping("/validateUser")
    public ResponseEntity<?> isValid(@RequestBody TokenDto token) throws Exception {
        log.info(token.getAccessToken());
        AuthResponse data = authService.validateToken(token.getAccessToken());
        return ResponseEntity.ok(data);
    }

    @PostMapping("/authorizedUser")
    public ResponseEntity<?> isAuthorized(@RequestBody TokenDto tokenDto) throws  Exception {
        log.info(tokenDto.getAccessToken());
        var response = authService.isAuthorizedToken(tokenDto.getAccessToken());
        return ResponseEntity.ok(response);
    }

}
