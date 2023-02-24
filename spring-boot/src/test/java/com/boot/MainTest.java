package com.boot;


import com.boot.course.conditionalonproperty.EmailNotification;
import com.boot.course.conditionalonproperty.NotificationConfig;
import com.boot.course.conditionalonproperty.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MainTest {


    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Resource
    private NotificationSender notificationSender;


    @Test
    public void whenValueSetToEmail_thenCreateEmailNotification() {
        this.contextRunner.withPropertyValues("notification.service=email")
                .withUserConfiguration(NotificationConfig.class)
                .run(context -> {
                    assertThat(context).hasBean("emailNotification");
                    NotificationSender notificationSender = context.getBean(EmailNotification.class);
                    assertThat(notificationSender.send("Hello From Baeldung!")).isEqualTo("Email Notification: Hello From Baeldung!");
                    assertThat(context).doesNotHaveBean("smsNotification");
                });
    }

    @Test
    public void test(){
        System.out.println(notificationSender.send("this is a message"));
    }




}
