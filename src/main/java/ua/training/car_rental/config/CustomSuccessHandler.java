package ua.training.car_rental.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ua.training.car_rental.entity.UserRole;
import ua.training.car_rental.exeptions.NoSuchRoleExeption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

//@Slf4j
@Data
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
//        log.info("User {} has been authenticated", authentication.getName());

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        System.out.println(roles);

        //todo catch exeption
        handle(request, response, roles);
    }


    private void handle(HttpServletRequest request, HttpServletResponse response, Set<String> roles) throws IOException {
        if (roles.equals(UserRole.ADMIN)) {
            redirectStrategy.sendRedirect(request, response, "/admin");
        } else if (roles.equals(UserRole.MANAGER)) {
            redirectStrategy.sendRedirect(request, response, "/manager");
        } else if (roles.equals(UserRole.USER)) {
            redirectStrategy.sendRedirect(request, response, "/user");
        } else {
            throw new NoSuchRoleExeption("Incorrect authorisation please contact the administrator");
        }
    }
}
