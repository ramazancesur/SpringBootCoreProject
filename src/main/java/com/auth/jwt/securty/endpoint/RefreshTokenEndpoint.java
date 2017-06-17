package com.auth.jwt.securty.endpoint;

import com.auth.jwt.securty.auth.jwt.extractor.TokenExtractor;
import com.auth.jwt.securty.auth.jwt.verifier.TokenVerifier;
import com.auth.jwt.securty.config.WebSecurtyConfig;
import com.auth.jwt.securty.model.token.JwtToken;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.service.interfaces.IUserService;
import com.auth.jwt.securty.config.JwtSettings;
import com.auth.jwt.securty.exceptions.InvalidJwtToken;
import com.auth.jwt.securty.model.UserContext;
import com.auth.jwt.securty.model.token.JwtTokenFactory;
import com.auth.jwt.securty.model.token.RawAccessJwtToken;
import com.auth.jwt.securty.model.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ramazancesur on 17/06/2017.
 */
@RestController
public class RefreshTokenEndpoint {
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private IUserService userService;
    @Autowired
    private TokenVerifier tokenVerifier;
    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @RequestMapping(value = "/api/auth/token", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurtyConfig.JWT_TOKEN_HEADER_PARAM));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user=new User();
        try{
            user = userService.getByUsername(subject);
        }catch (Exception ex){
            ex.printStackTrace();
            new UsernameNotFoundException("User not found: " + subject);
        }

        if (user.getRole() == null)
            throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = Arrays.asList(user.getRole()).stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getYetkiAdi()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUserName(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
