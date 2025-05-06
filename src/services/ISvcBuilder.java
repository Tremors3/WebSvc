package services;

import repositories.IDb;
import usecases.IPersonService;

public interface ISvcBuilder {
    IDb createDb();
    IPersonService createPersonService();
}
