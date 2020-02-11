package com.embl.person.exception;


import org.springframework.core.env.PropertyResolver;

import static org.springframework.util.StringUtils.isEmpty;

@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.LawOfDemeter"})
public class Error {

    private final String key;

    private final String message;

    public Error(final String key) {
        this(key, "");
    }

    public Error(final String key, final String message) {
        this.key = key;
        this.message = message;
    }

    public String getMessage(final PropertyResolver propertyResolver) {
        String message = getProperty(propertyResolver, key);
        message = isEmpty(message) ? getProperty(propertyResolver, ErrorConstants.GENERIC_ERROR) : message;
        return isEmpty(this.message) ? message : this.message;
    }

    private String getProperty(final PropertyResolver propertyResolver, final String key) {
        return propertyResolver.getProperty(key);
    }


    public String getKey() {
        return key;
    }
}
