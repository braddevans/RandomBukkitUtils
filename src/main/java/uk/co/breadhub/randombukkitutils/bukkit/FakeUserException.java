package uk.co.breadhub.randombukkitutils.bukkit;

public class FakeUserException extends Exception {
    public FakeUserException(String message, Throwable err) {
        super(message, err);
    }

    public FakeUserException(String message) {
        super(message);
    }
}
