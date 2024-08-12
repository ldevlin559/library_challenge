package org.kainos.exceptions;


public class DoesNotExistException extends Throwable {
    public DoesNotExistException(final Entity entity) {
        super(entity.getEntity() + " does not exist");
    }
}
