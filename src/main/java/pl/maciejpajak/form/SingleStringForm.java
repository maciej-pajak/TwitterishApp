package pl.maciejpajak.form;

import org.hibernate.validator.constraints.NotBlank;

public class SingleStringForm {
    
    @NotBlank
    private String string;
    
    public SingleStringForm() {}

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
