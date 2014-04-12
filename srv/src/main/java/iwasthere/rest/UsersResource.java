package iwasthere.rest;

import com.google.common.base.Optional;
import iwasthere.AppModule.Roles;
import iwasthere.AppUserRepository;
import iwasthere.domain.Signup;
import iwasthere.domain.User;
import restx.WebException;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.http.HttpStatus;
import restx.security.PermitAll;
import restx.security.RestxPrincipal;
import restx.security.RestxSession;
import restx.security.RolesAllowed;

import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * Date: 12/4/14
 * Time: 13:20
 */
@Component
@RestxResource
public class UsersResource {
    private final AppUserRepository userRepository;

    public UsersResource(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RolesAllowed(Roles.ADMIN)
    @GET("/users")
    public Iterable<User> findUsers() {
        return userRepository.findAllUsers();
    }

    @PermitAll
    @GET("/users/:email")
    public Optional<User> getCurrentUser(String email) {
        Optional<User> principal = (Optional<User>) RestxSession.current().getPrincipal();
        if ("current".equals(email)) {
            return principal;
        } else {
            if (principal.isPresent()
                    || (
                    !principal.get().getEmail().equals(email)
                            && !principal.get().getRoles().contains(Roles.ADMIN))) {
                throw new WebException(HttpStatus.UNAUTHORIZED);
            }

            return userRepository.findUserByName(email);
        }
    }

    @PermitAll
    @POST("/users")
    public User signup(Signup signup) {
        User user = userRepository.createUser(
                new User().setEmail(signup.getEmail()).setFullName(signup.getFullName())
                .setRoles(new ArrayList<>(asList(Roles.USER)))
        );

        userRepository.setCredentials(user.getName(), signup.getPasswordHash());

        if (!RestxSession.current().getPrincipal().isPresent()) {
            RestxSession.current().authenticateAs(user);
        }

        return user;
    }
}
