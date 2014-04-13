package iwasthere;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import restx.RestxContext;
import restx.RestxFilter;
import restx.RestxHandler;
import restx.RestxHandlerMatch;
import restx.RestxRequest;
import restx.RestxRequestMatch;
import restx.RestxResponse;
import restx.StdRestxRequestMatch;
import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.Module;
import restx.factory.Provides;
import restx.http.HttpStatus;
import restx.security.BCryptCredentialsStrategy;
import restx.security.BasicPrincipalAuthenticator;
import restx.security.CredentialsStrategy;
import restx.security.SecuritySettings;
import restx.security.SignatureKey;
import restx.security.StdBasicPrincipalAuthenticator;
import restx.security.StdUserService;

import javax.inject.Named;
import java.io.IOException;

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

    @Provides @Named("restx.activation::restx.security.CORSFilter::CORSFilter")
    public String disableCorsFilter() {
        return "false";
    }

    @Provides
    public RestxFilter getCorsAuthorizerFilter() {
        return new RestxFilter() {
            @Override
            public Optional<RestxHandlerMatch> match(RestxRequest r) {
                return RestxHandlerMatch.of(Optional.of(new StdRestxRequestMatch(r.getRestxPath())),
                        new RestxHandler() {
                            @Override
                            public void handle(RestxRequestMatch match, RestxRequest req, RestxResponse resp, RestxContext ctx) throws IOException {
                                Optional<String> origin = req.getHeader("Origin");
                                if (origin.isPresent()) {
                                    resp.setHeader("Access-Control-Allow-Origin", origin.get());
                                    resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                                    resp.setHeader("Access-Control-Allow-Credentials", Boolean.TRUE.toString());
                                    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

                                    if ("OPTIONS".equals(req.getHttpMethod())) {
                                        resp.setStatus(HttpStatus.OK);
                                    } else {
                                        ctx.nextHandlerMatch().handle(req, resp, ctx);
                                    }
                                } else {
                                    ctx.nextHandlerMatch().handle(req, resp, ctx);
                                }
                            }
                        }
                );
            }
        };
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
