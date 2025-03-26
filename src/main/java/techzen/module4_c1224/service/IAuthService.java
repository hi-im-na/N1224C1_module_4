package techzen.module4_c1224.service;

import com.nimbusds.jose.JOSEException;
import techzen.module4_c1224.service.dto.req.AuthenticationRequest;
import techzen.module4_c1224.service.dto.req.IntrospectRequest;
import techzen.module4_c1224.service.dto.res.AuthenticationResponse;
import techzen.module4_c1224.service.dto.res.IntrospectResponse;

import java.text.ParseException;

public interface IAuthService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    IntrospectResponse introspect(IntrospectRequest token) throws ParseException, JOSEException;
}
