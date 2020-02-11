package com.embl.person.exception;

import java.util.Arrays;
import java.util.List;

import static com.embl.person.exception.ErrorConstants.INTERNAL_SERVER_ERROR;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.join;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 8314336005080069771L;

    private final Errors errors = new Errors();

    public ApplicationException(final String... errorKeys) {
        this(null, errorKeys);
    }

    public ApplicationException(final Errors errors) {
        this.errors.addAll(errors);
    }

    public ApplicationException(final Throwable throwable, final String... errorKeys) {
        super(errorMessage(errorKeys), throwable);
        errors.addAll(toErrorMessageList(errorKeysDefaulted(errorKeys)));
    }

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    private static Errors toErrorMessageList(final String... errorKeys) {
        final Errors errors = new Errors();
        List<String> errorKeyList = Arrays.asList(errorKeys);

        for (String error: errorKeyList) {
            errors.add(new Error(error));
        }
        return errors;
    }

    private static String[] errorKeysDefaulted(final String... errorKeys) {
        return isEmpty(errorKeys) ? new String[]{INTERNAL_SERVER_ERROR} : errorKeys;
    }

    private static String errorMessage(final String... errorKeys) {
        return join(errorKeysDefaulted(errorKeys), ", ");
    }

    public Errors getErrors() {
        return errors;
    }
}
