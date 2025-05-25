package utils;

import models.IPerson;
import org.json.JSONObject;

/**
 * Interface that aggregates methods to creating a Person Model.
 */
public interface IMapToPerson {
    IPerson mapToPerson(int id, int age);
    IPerson mapToPerson(JSONObject jsonObject);
}
