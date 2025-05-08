package environment;

import com.google.inject.Singleton;
import io.github.cdimascio.dotenv.Dotenv;

@Singleton
public class DotenvEnvironmentReader implements IEnvironmentReader {
    
    private Dotenv _dotenv = null;
    
    public DotenvEnvironmentReader() {
        this._dotenv = Dotenv.configure()
            .directory("./assets")
            .filename("env")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();
    }
    
    @Override
    public String get(String variable) {
        return _dotenv.get(variable);
    }
    
    @Override
    public String get(String variable, String default_) {
        return _dotenv.get(variable, default_);
    }
}
