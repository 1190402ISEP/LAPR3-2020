package lapr.project.data;

import lapr.project.model.User;

/**
 * The type User session.
 */
public class UserSession {
    private static UserSession instance;

    private User user;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return this.user;
    }
}
