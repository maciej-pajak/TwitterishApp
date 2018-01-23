package pl.maciejpajak.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class EmailForm {
    
    @Email
    @NotBlank
    private String email;
    
    public EmailForm() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
