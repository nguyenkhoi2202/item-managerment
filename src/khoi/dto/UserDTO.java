/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dto;

/**
 *
 * @author Nguyen Khoi
 */
public class UserDTO {
    private String username;
    private String fullname;
    private String password;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(String username, String fullname, String password, boolean status) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
