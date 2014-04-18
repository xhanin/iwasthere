package iwasthere;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.Module;
import restx.factory.Provides;
import restx.security.BCryptCredentialsStrategy;
import restx.security.BasicPrincipalAuthenticator;
import restx.security.CORSAuthorizer;
import restx.security.CredentialsStrategy;
import restx.security.SecuritySettings;
import restx.security.SignatureKey;
import restx.security.StdBasicPrincipalAuthenticator;
import restx.security.StdCORSAuthorizer;
import restx.security.StdUserService;

import javax.inject.Named;

@Module
public class AppModule {
    public static final class Roles {
        // we don't use an enum here because roles in @RolesAllowed have to be constant strings
        public static final String ADMIN = "admin";
        public static final String USER = "user";
    }

    @Provides
    public SignatureKey signatureKey() {
         return new SignatureKey("365c02b3-7531-4437-ae8c-fedabcecaeb9 iwasthere iwasthere -2854385430522339591".getBytes(Charsets.UTF_8));
    }

    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "juma";
    }

    @Provides
    public ConfigSupplier appConfigSupplier(ConfigLoader configLoader) {
        // Load settings.properties in iwasthere package as a set of config entries
        return configLoader.fromResource("iwasthere/settings");
    }

    @Provides
    public CredentialsStrategy credentialsStrategy() {
        return new BCryptCredentialsStrategy();
    }

    @Provides
    public CORSAuthorizer allowCORS() {
        return StdCORSAuthorizer.builder()
                .setOriginMatcher(Predicates.<CharSequence>alwaysTrue())
                .setPathMatcher(Predicates.<CharSequence>alwaysTrue())
                .setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE", "HEAD"))
                .setAllowedHeaders(ImmutableList.of("Origin", "X-Requested-With", "Content-Type", "Accept"))
                .setAllowCredentials(Optional.of(Boolean.TRUE))
                .build();
    }

    @Provides
    public BasicPrincipalAuthenticator basicPrincipalAuthenticator(
            AppUserRepository userRepository, SecuritySettings securitySettings,
            CredentialsStrategy credentialsStrategy,
            @Named("restx.admin.passwordHash") String adminPasswordHash) {
        return new StdBasicPrincipalAuthenticator(
                new StdUserService<>(userRepository, credentialsStrategy, adminPasswordHash), securitySettings);
    }
}
