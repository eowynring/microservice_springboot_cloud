package com.boot.course.conditionalonproperty;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "email")
public class EmailNotification implements NotificationSender{
    @Override
    public String send(String message) {
        return  "Email Notification: " + message;
    }
}
