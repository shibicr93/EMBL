package com.embl.person.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Errors implements Iterable<Error> {

    private final List<Error> errorList = new ArrayList<>(2);

    @Override
    public Iterator<Error> iterator() {
        return errorList.iterator();
    }

    public void add(final Error error) {
        errorList.add(error);
    }

    public void addAll(final Errors errors) {
        this.errorList.addAll(errors.errorList);
    }

}
