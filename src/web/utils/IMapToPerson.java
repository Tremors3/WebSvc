package web.utils;

import models.IPerson;
import org.json.JSONObject;

public interface IMapToPerson {
    public IPerson mapToPerson(int id, int age);
    public IPerson mapToPerson(JSONObject jsonObject);
}
