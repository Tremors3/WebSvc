package unitTests;

import repositories.IDb;
import services.ISvcBuilder;
import usecases.IPersonService;
import usecases.PersonService;

public class ServicesBuilderForMocks implements ISvcBuilder {

	@Override
	public IDb createDb() {
		return new MockThatThrowsException();
	}

	@Override
	public IPersonService createPersonService() {
		return new PersonService(createDb());
	}

}
