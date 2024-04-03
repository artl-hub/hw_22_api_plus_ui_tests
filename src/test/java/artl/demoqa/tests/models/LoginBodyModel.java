package artl.demoqa.tests.models;

public class LoginBodyModel {
//    String body = "{\"userName\": \"testUser123\", \"password\": \"4eDsM76F*9WY3Sn\"}";
    String userName, password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String email) {
        this.userName = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
