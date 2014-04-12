package iwasthere.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableSet;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import restx.security.RestxPrincipal;

import java.util.Collection;

/**
 * Date: 12/4/14
 * Time: 14:13
 */
public class User implements RestxPrincipal {
    @Id
    @ObjectId
    private String key;

    private String email;
    private String fullName;
    private Collection<String> roles;

    public String getKey() {
        return key;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public User setKey(final String key) {
        this.key = key;
        return this;
    }

    public User setEmail(final String email) {
        this.email = email;
        return this;
    }

    public User setFullName(final String fullName) {
        this.fullName = fullName;
        return this;
    }

    public User setRoles(final Collection<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    @JsonIgnore
    public ImmutableSet<String> getPrincipalRoles() {
        return ImmutableSet.copyOf(roles);
    }

    @Override
    @JsonIgnore
    public String getName() {
        return getEmail();
    }

    @Override
    public String toString() {
        return "User{" +
                "key='" + key + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
