package utils;

import com.google.inject.Inject;
import modelBuilders.IPersonBuilder;
import models.IPerson;
import org.json.JSONObject;

public class MapperPerson implements IMapperPerson {

    @Inject
    private IPersonBuilder _personBuilder;

    @Override
    public IPerson mapToPerson(JSONObject jsonObject) {
        IPerson person = this._personBuilder.getPerson();
        person.set_id(Integer.parseInt(jsonObject.getString("key")));
        person.set_age(Integer.parseInt(jsonObject.getString("age")));
        return person;
    }

    @Override
    public IPerson mapToPerson(int id, int age) {
        IPerson person = this._personBuilder.getPerson();
        person.set_id(id);
        person.set_age(age);
        return person;
    }

    @Override
    public JSONObject mapToJson(IPerson person) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("id", person.get_id());
        jsonObject.append("age", person.get_age());
        return jsonObject;
    }
}
