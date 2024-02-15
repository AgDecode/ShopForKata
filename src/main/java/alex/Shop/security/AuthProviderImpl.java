package alex.Bank.security;

import alex.Bank.Config.EncoderBean;
import alex.Bank.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;
    private final EncoderBean encoderBean;


    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService, EncoderBean encoderBean) {
        this.personDetailsService = personDetailsService;
        this.encoderBean = encoderBean;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();

        boolean isRightPassword = encoderBean.getPasswordEncoder().matches(password, personDetails.getPassword());

        if(!isRightPassword)
            throw new BadCredentialsException("Неверный пароль");
        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}