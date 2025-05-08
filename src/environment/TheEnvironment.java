package environment;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Objects;

@Singleton
public class TheEnvironment implements IEnvironment {

    private final IEnvironmentReader _envReader;
    
    @Inject
    public TheEnvironment(IEnvironmentReader reader) {
        this._envReader = reader;
    }
    
    @Override
    public Boolean IsLocal() {
        return Objects.equals(_envReader.get("LOCAL", "true"), "true");
    }

}
