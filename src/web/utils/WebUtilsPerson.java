package web.utils;

import models.IPerson;
import services.IPersonBuilder;
import services.PersonBuilder;

import java.util.Dictionary;
import java.util.Hashtable;

public class WebUtilsPerson extends WebUtils implements IWebUtilsPerson {

    private IPersonBuilder _personBuilder = null;

    public WebUtilsPerson() {
        this._personBuilder = PersonBuilder.GetInstance();
    }

    public WebUtilsPerson(IPersonBuilder personBuilder) {
        this._personBuilder = personBuilder;
    }
    
    @Override
    public IPerson mapToPerson(int id, int age) {
        IPerson person = this._personBuilder.getPerson();
        person.set_id(id);
        person.set_age(age);
        return person;
    }

    @Override
    public Dictionary<Integer, Integer> mapToDict(IPerson person) {
        Dictionary<Integer, Integer> dict = new Hashtable<>();
        dict.put(person.get_id(), person.get_age());
        return dict;
    }
}