package juiceshop.api.automation.domain;

public class User {
    private String email;
    private String password;
    private String passwordRepeat;

    public String getEmail() {
        return email;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getPassword() {
        return password;
    }

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
