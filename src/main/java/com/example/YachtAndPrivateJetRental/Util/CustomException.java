package com.example.YachtAndPrivateJetRental.Util;

public class CustomException extends RuntimeException {

    private final Type type;

    public CustomException(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String getMessage() {
        return this.type.getMessage();
    }

    public enum Type {
        CATEGORY_ALREADY_EXIST("category already exist",500),
        DATE_INVALID("Invalid Date Format", 501),
        TIME_INVALID("Invalid Time Format", 501),

        PHONE_NUMBER_ALREADY_EXISTS("Phone Number already exists",500),
        EMAIL_ALREADY_EXITS("Email Already Exists", 500),
        USERNAME_ALREADY_EXIST("Username already Exists", 500),
        INVALID_FILE_EXTENSION("Image Extension Should be of .png, .jpeg or .jpg",400 ),
        INVALID_MIME_TYPE("Invalid MIMEType",400),

        INVALID_IMAGE_REQUEST("The images for jet should not be empty",400),

        INVALID_JET_PRICE_REQUEST("The Jet should have at least one price",400),

        INVALID_FILE_SIZE("Upload file fize shouldn't be more than 3MB",400 );
        private String message;
        private int code;

        Type(String message, int code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public Type updateMessage(String message, int code) {
            this.message = message;
            this.code = code;
            return this;
        }
    }
}
