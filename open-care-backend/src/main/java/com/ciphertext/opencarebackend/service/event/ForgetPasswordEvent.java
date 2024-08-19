package com.ciphertext.opencarebackend.service.event;

import com.ciphertext.opencarebackend.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ForgetPasswordEvent extends ApplicationEvent {
    private final User user;

    public ForgetPasswordEvent(User user) {
        super(user);
        this.user = user;
    }
}
