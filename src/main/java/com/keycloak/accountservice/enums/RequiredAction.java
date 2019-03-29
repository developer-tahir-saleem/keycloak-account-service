package com.keycloak.accountservice.enums;

public enum RequiredAction {
    VERIFY_EMAIL{
        @Override
        public String asLowerCase() {
            return VERIFY_EMAIL.toString().toLowerCase();
        }
    },
    UPDATE_PROFILE{
        @Override
        public String asLowerCase() {
            return UPDATE_PROFILE.toString().toLowerCase();
        }
    },
    CONFIGURE_TOTP{
        @Override
        public String asLowerCase() {
            return CONFIGURE_TOTP.toString().toLowerCase();
        }
    },
    UPDATE_PASSWORD{
        @Override
        public String asLowerCase() {
            return UPDATE_PASSWORD.toString().toLowerCase();
        }
    },
    TERMS_AND_CONDITIONS{
        @Override
        public String asLowerCase() {
            return TERMS_AND_CONDITIONS.toString().toLowerCase();
        }
    };

    public abstract String asLowerCase();
}