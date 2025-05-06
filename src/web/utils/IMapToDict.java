package web.utils;

import models.IPerson;
import java.util.Dictionary;

public interface IMapToDict {
    public Dictionary<Integer, Integer> mapToDict(IPerson person);
}
