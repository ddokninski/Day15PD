package models;

import com.github.javafaker.Faker;

public class UserFactory {

    public User gerRandomUser() {
        Faker faker = new Faker();
        int random = faker.random().nextInt(0, 1);
        String randomGender = random == 0 ? "male" : "female";
        return new User.UserBuilder()
                .gender(randomGender)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return new User.UserBuilder()
                .gender(System.getProperty("user_gender"))
                .firstName(System.getProperty("user_first_name"))
                .lastName(System.getProperty("user_last_name"))
                .email(System.getProperty("user_email"))
                .password(System.getProperty("user_password"))
                .build();
    }



}

