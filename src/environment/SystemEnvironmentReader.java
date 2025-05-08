package environment;

import com.google.inject.Singleton;

@Singleton
public class SystemEnvironmentReader implements IEnvironmentReader {
    
    @Override
    public String get(String variable) {
        return System.getenv(variable);
    }
    
    @Override
    public String get(String variable, String default_) {
        return System.getenv().getOrDefault(variable, default_);
    }
}
