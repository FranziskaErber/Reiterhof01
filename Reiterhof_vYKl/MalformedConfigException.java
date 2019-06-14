package oop.horsematching;

/**
 * Exception thrown when a config file is not properly formatted
 */
public class MalformedConfigException extends Exception {

    /**
     * default constructor
     */
    public MalformedConfigException() {
        super();
    }

    /**
     * default constructor
     * @param msg message
     */
    public MalformedConfigException(String msg) {
        super(msg);
    }
}
