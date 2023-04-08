package models;

public class User {
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public static final class UserBuilder {
        private String gender;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            if (gender.isEmpty()) {
                throw new IllegalStateException("gender cannot be empty");
            }
            if (firstName.isEmpty()) {
                throw new IllegalStateException("firstName cannot be empty");
            }
            if (lastName.isEmpty()) {
                throw new IllegalStateException("lastName cannot be empty");
            }
            if (email.isEmpty()) {
                throw new IllegalStateException("email cannot be empty");
            }
            if (password.isEmpty()) {
                throw new IllegalStateException("password cannot be empty");
            }

            User user = new User();
            user.gender = this.gender;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.email = this.email;
            user.password = this.password;

            return user;
        }
    }
}
