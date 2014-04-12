package iwasthere;

import iwasthere.domain.User;
import org.bson.types.ObjectId;
import restx.admin.AdminModule;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.jongo.JongoUserRepository;
import restx.security.CredentialsStrategy;

import javax.inject.Named;
import java.util.Arrays;

import static iwasthere.AppModule.Roles.ADMIN;

@Component
public class AppUserRepository extends JongoUserRepository<User> {
    public static final User defaultAdminUser = new User()
            .setKey(new ObjectId().toString())
            .setEmail("admin@iwasthere.io")
            .setRoles(Arrays.asList(ADMIN, AdminModule.RESTX_ADMIN_ROLE));

    public static final RefUserByNameStrategy<User> USER_REF_STRATEGY = new RefUserByNameStrategy<User>() {
        @Override
        public String getNameProperty() {
            return "email";
        }
    };

    public AppUserRepository(@Named("users") JongoCollection users,
                             @Named("usersCredentials") JongoCollection usersCredentials,
                             CredentialsStrategy credentialsStrategy) {
        super(
                users, usersCredentials,
                USER_REF_STRATEGY, credentialsStrategy,
                User.class, defaultAdminUser
        );
    }
}
