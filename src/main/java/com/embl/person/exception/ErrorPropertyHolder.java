package com.embl.person.exception;

import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

/*
    This PropertyResolver wrapper class has been created because Spring's ConfigurableEnvironment already
    provides a default PropertyResolver, which is slow in resolving properties as it has multiple sources
    to look into.
*/
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class ErrorPropertyHolder {

    private PropertyResolver propertyResolver;

    public ErrorPropertyHolder(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    public ErrorPropertyHolder(PropertySources propertySources) {
        this(new PropertySourcesPropertyResolver(propertySources));
    }

    public PropertyResolver getPropertyResolver() {
        return propertyResolver;
    }
}
