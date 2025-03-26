package techzen.module4_c1224.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzen.module4_c1224.service.IAuthService;
import techzen.module4_c1224.service.dto.req.AuthenticationRequest;
import techzen.module4_c1224.service.dto.req.IntrospectRequest;
import techzen.module4_c1224.service.dto.res.AuthenticationResponse;
import techzen.module4_c1224.service.dto.res.IntrospectResponse;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    IAuthService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/introspect")
    public ResponseEntity<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return ResponseEntity.ok(authenticationService.introspect(introspectRequest));
    }
}
