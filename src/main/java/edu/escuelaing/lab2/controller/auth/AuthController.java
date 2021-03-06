package edu.escuelaing.lab2.controller.auth;

import edu.escuelaing.lab2.controller.exception.InvalidCredentialsException;
import edu.escuelaing.lab2.data.User;
import edu.escuelaing.lab2.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    @Value("${app.secret}")
    String secret;

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto) {
        // TODO: Implement findByEmail method
        User user = userService.findByEmail(loginDto.email);
        if (BCrypt.checkpw(loginDto.password, user.getPasswordHash())) {
            return generateTokenDto(user);
        } else {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken(User user, Date expirationDate) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim(Constants.CLAIMS_ROLES_KEY, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private TokenDto generateTokenDto(User user) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, Constants.TOKEN_DURATION_MINUTES);
        String token = generateToken(user, expirationDate.getTime());
        return new TokenDto(token, expirationDate.getTime());
    }


}
