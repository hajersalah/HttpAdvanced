package com.nhcompany.httpadvanced;

public class UserData {

    private int likes;
    private User user;

    public int getLikes() {
        return likes;
    }

    public User getUser() {
        return user;
    }

    public class User {
        private String name;
        private ProfileImage profileImage;

        public String getName() {
            return name;
        }

        public ProfileImage getProfileImage() {
            return profileImage;
        }

        public class ProfileImage {
            private  String medium;

            public String getMedium() {
                return medium;
            }
        }
    }
}
