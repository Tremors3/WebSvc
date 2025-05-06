package unitTests;

import java.io.IOException;

import services.ISvcBuilder;
import services.PersonBuilder;
import web.utils.MapperPerson;
import web.MyServlet;

public class MyServletTest {
	
	private static <T>void AssertEquals(T expected, T actual) {
		if(expected != actual) {
			System.out.println("Assertion failed! Expected \'" + expected + "\', got \'"+ actual + "\' instead");
			System.exit(-1);
		}
	}

	private static void AssertNull(Object o) {
		if(o != null) {
			System.out.println("Assertion failed! object is not null");
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

		// Mock svcbuilder
		ISvcBuilder svcBuilder = new ServicesBuilderForMocks();

	    // SUT stands for "Service Under Test"
		MyServlet sut = new MyServlet(
				svcBuilder.createPersonService(),
				new MapperPerson(PersonBuilder.GetInstance())
		);


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

		// Mock svcbuilder
		ISvcBuilder svcBuilder = new ServicesBuilderForMocks();

		// SUT stands for "Service Under Test"
		MyServlet sut = new MyServlet(
				svcBuilder.createPersonService(),
				new MapperPerson(PersonBuilder.GetInstance())
		);


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
	
	public static void main(String[] args) {
		MyServlet_DbThrowsExceptionOnGet_Return400();
		MyServlet_DbThrowsExceptionOnPost_Return400();
	}

}
