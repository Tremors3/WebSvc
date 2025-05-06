package web.utils;

import models.IPerson;
import org.json.JSONObject;

import java.util.Dictionary;

public interface IMapToJson {
    public JSONObject mapToJson(IPerson person);
}
