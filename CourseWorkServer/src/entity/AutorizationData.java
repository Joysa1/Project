package entity;

import java.io.Serializable;

public class AutorizationData implements Serializable {
  private  String login;
  private  String Password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
