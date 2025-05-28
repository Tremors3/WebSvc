package unitTests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import configurations.LocalConfiguration;
import configurations.ProdConfiguration;
import configurations.modules.LoadConfiguration;
import environment.IEnvironment;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import repositories.IDb;
import repositories.MongoDB;
import web.MyServlet;

/**
 * Unit test for testing MyServlet module.
 */
public class MyServletTests {

    static Injector loadConfiguration;

    @BeforeAll
    public static void Setup() {
        MyServletTests.loadConfiguration = Guice.createInjector(
                new LoadConfiguration()
        );
    }

    @Test
    @DisplayName("MyServlet DbThrowsExceptionOnGet Return400")
    public void MyServlet_DbThrowsExceptionOnGet_Return400() {

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

        assertNull(ioException); // Check we dit not capture a wrong type of Exception
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("MyServlet DbThrowsExceptionOnPost Return400")
    public void MyServlet_DbThrowsExceptionOnPost_Return400() {

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

        assertNull(ioException); // Check we dit not capture a wrong type of Exception
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Environment IsLocal ReturnFalse")
    public void Environment_IsLocal_ReturnFalse() {

        // Arrange

        IEnvironment env = MyServletTests.loadConfiguration.getInstance(IEnvironment.class);

        // Act

        boolean isLocal = env.IsLocal();
        Injector configuration = Guice.createInjector(
            isLocal ? new LocalConfiguration() : new ProdConfiguration()
        );
        Class<? extends IDb> dbClass = configuration.getInstance(IDb.class).getClass();

        // Assert

        assertFalse(isLocal);
        assertEquals(MongoDB.class, dbClass);
    }

}
