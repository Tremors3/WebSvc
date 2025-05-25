package unitTests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import configurations.LocalConfiguration;
import configurations.ProdConfiguration;
import configurations.modules.LoadConfiguration;
import environment.IEnvironment;
import java.io.IOException;
import repositories.IDb;
import repositories.MongoDB;
import web.MyServlet;

/**
 * Unit test for testing MyServlet module.
 */
public class MyServletTest {

    private static <T> void AssertEquals(T expected, T actual) {
        if (expected != actual) {
            System.out.println(
                "Assertion failed! Expected \'" +
                expected +
                "\', got \'" +
                actual +
                "\' instead."
            );
            System.exit(-1);
        }
    }

    private static void AssertNull(Object o) {
        if (o != null) {
            System.out.println("Assertion failed! object is not null.");
            System.exit(-1);
        }
    }

    private static void MyServlet_DbThrowsExceptionOnGet_Return400() {
        // Arrange

        // Mock up HttpServletRequest and HttpServletResponse
        MyHttpServletRequest request = new MyHttpServletRequest();
        request.setParameter("id", "1"); // This causes an exception
        request.setParameter("age", "34");
        MyHttpServletResponse response = new MyHttpServletResponse();

        // Getting the mock configuration
        Injector testConfiguration = Guice.createInjector(
            new TestConfiguration()
        );

        // SUT stands for "Service Under Test"
        MyServlet sut = testConfiguration.getInstance(MyServlet.class);

        // Act

        Exception ioException = null;

        try {
            sut.doGet(request, response); // as Get
        } catch (IOException e) {
            ioException = e;
        }

        // Assert

        AssertNull(ioException); // Check we dit not capture a wrong type of Exception
        AssertEquals(400, response.getStatus());
    }

    private static void MyServlet_DbThrowsExceptionOnPost_Return400() {
        // Arrange

        // Mock up HttpServletRequest and HttpServletResponse
        MyHttpServletRequest request = new MyHttpServletRequest();
        request.setBody("{\"id\" : \"11\", \"age\" : \"43\"}");
        MyHttpServletResponse response = new MyHttpServletResponse();

        // Getting the mock configuration
        Injector testConfiguration = Guice.createInjector(
            new TestConfiguration()
        );

        // SUT stands for "Service Under Test"
        MyServlet sut = testConfiguration.getInstance(MyServlet.class);

        // Act

        Exception ioException = null;

        try {
            sut.doPost(request, response); // as Post
        } catch (IOException e) {
            ioException = e;
        }

        // Assert

        AssertNull(ioException); // Check we dit not capture a wrong type of Exception
        AssertEquals(400, response.getStatus());
    }

    private static void Environment_IsLocal_ReturnFalse() {
        // Arrange

        Injector loadConfiguration = Guice.createInjector(
            new LoadConfiguration()
        );
        IEnvironment env = loadConfiguration.getInstance(IEnvironment.class);

        // Act

        boolean isLocal = env.IsLocal();
        Injector configuration = Guice.createInjector(
            isLocal ? new LocalConfiguration() : new ProdConfiguration()
        );
        Class<?> dbClass = configuration.getInstance(IDb.class).getClass();

        // Assert

        //AssertEquals(false, isLocal);
        AssertEquals(MongoDB.class, dbClass);
    }

    public static void main(String[] args) {
        //MyServlet_DbThrowsExceptionOnGet_Return400();
        //MyServlet_DbThrowsExceptionOnPost_Return400();
        Environment_IsLocal_ReturnFalse();
    }
}
