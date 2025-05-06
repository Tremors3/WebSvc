package repositories;

import java.util.Dictionary;
import java.util.Hashtable;

import models.IPerson;

public class InMemoryDb implements IDb {

    private Dictionary<Integer, Integer> _db = new Hashtable<>();
    
    public InMemoryDb() {
        // Seeding for test...
        _db.put(11, 25);
    }
    
    @Override
    public void updateBirth(IPerson person) throws Exception {
        if(_db.get(person.get_id()) == null)
            throw new Exception("User with " + person.get_id() + " does not exist!");
        
        _db.put(person.get_id(), person.get_age());
    }

}
