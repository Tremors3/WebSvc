package environment;

/**
 * Interface that define methods to read environment variables.
 */
public interface IEnvironmentReader {
    String get(String variable);
    String get(String variable, String default_);
}
