package models;

import java.beans.JavaBean;

/**
 * Simple model that represents a Person.
 */
@JavaBean
public class Person implements IPerson {

    private int _id;
    private int _age;

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
