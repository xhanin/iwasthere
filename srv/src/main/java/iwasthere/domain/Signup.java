package iwasthere.domain;

/**
 * Date: 12/4/14
 * Time: 14:19
 */
public class Signup {
    private String email;
    private String fullName;
    private String passwordHash;

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Signup setEmail(final String email) {
        this.email = email;
        return this;
    }

    public Signup setFullName(final String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Signup setPasswordHash(final String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    @Override
    public String toString() {
        return "Signup{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
