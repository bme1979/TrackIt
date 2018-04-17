package trackit;

import trackit.DAL.*;
import trackit.UI.*;

/**
 *
 * @author Bryan, Bond
 */
public class Login {

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private String errorMessage = "";
    private int loginAttempts = 0;

    /**
     * login
     */
    public Login() {
    }

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean startLogin(String userName, String password) {
        SQLConnector.getInstance().setConnectionString(userName, password);
        return startLogin();
    }

    /**
     *
     * @return
     */
    public boolean startLogin() {
        boolean returnValue = false;

        String maxAttemptsError = "Maximum number of login attempts has been exceeded.";

        if (!SQLConnector.getInstance().isValidConnection()) {
            loginAttempts++;
            this.errorMessage = SQLConnector.getInstance().getErrorMessage();
            if (this.isTooManyLoginAttempts()) {
                this.errorMessage += "\r\n\r\n" + maxAttemptsError;
            }
        } else {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * logout
     */
    public void startLogout() {
        LoginFrame dlgLogin = new LoginFrame(SQLConnector.getInstance().getUserName());
        dlgLogin.display();
    }

    /**
     *
     * @return
     */
    public boolean isTooManyLoginAttempts() {
        return loginAttempts >= MAX_LOGIN_ATTEMPTS;
    }

    /**
     * Returns the last error generated by this class.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
