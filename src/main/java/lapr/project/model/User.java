package lapr.project.model;

/**
 * The type User.
 */
public class User {
        private String email;
        private String password;
        private String role;

    /**
     * Instantiates a new User.
     *
     * @param email    - user's email
     * @param password - user's username
     */
    public User(String email, String password) {
            this.email = email;
            this.password = password;
        }

    /**
     * Instantiates a new User.
     *
     * @param email    the email
     * @param password the password
     * @param role     the role
     */
    public User(String email, String password, String role) {
            this.email = email;
            this.password = password;
            this.role = role;
        }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
            return email;
        }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
            return password;
        }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
            return role;
        }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
            this.email = email;
        }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
            this.password = password;
        }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
            this.role = role;
        }

    }

