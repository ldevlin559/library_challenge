package org.kainos.exceptions;

public class FailedToCreateException extends Throwable {
    public FailedToCreateException(final Entity entity) {
        super(entity.getEntity() + " failed to create entity");
    }
}
