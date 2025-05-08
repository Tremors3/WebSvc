package utils;

import models.IPerson;
import org.json.JSONObject;

public interface IMapToJson {
    JSONObject mapToJson(IPerson person);
}
