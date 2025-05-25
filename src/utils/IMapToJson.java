package utils;

import models.IPerson;
import org.json.JSONObject;

/**
 * Interface that define methods for obtaining a Json object fron a Person model.
 */
public interface IMapToJson {
    JSONObject mapToJson(IPerson person);
}
