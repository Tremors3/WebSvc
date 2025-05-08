package environment;

public interface IEnvironmentReader {
    String get(String variable);
    String get(String variable, String default_);
}
