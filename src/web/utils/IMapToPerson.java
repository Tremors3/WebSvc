package web.utils;

import models.IPerson;
import org.json.JSONObject;

public interface IMapToPerson {
    IPerson mapToPerson(int id, int age);
    IPerson mapToPerson(JSONObject jsonObject);
}
