package environment;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Objects;

/**
 * IEnvironment implementation.
 */
@Singleton
public class TheEnvironment implements IEnvironment {

    @Inject
    private IEnvironmentReader _envReader;

    @Override
    public Boolean IsLocal() {
        return Objects.equals(
                _envReader.get("LOCAL", "true"), "true");
    }
}
