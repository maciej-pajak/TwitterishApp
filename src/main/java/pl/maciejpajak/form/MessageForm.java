package pl.maciejpajak.form;

import org.hibernate.validator.constraints.NotBlank;

public class MessageForm {
    
    @NotBlank
    private String recipientUsername;
    @NotBlank
    private String message;
    
    public MessageForm() {}

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
