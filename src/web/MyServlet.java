package web;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.IPerson;
import models.Person;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import repositories.IDb;
import services.IPersonBuilder;
import services.ISvcBuilder;
import services.PersonBuilder;
import services.ServiceBuilder;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IDb _myDb = null;
	private IPersonBuilder _personBuilder = null;  // Person Builders
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        this(ServiceBuilder.GetInstance(), PersonBuilder.GetInstance());
    }
    
    /**
     * Explicitly assign svcBuilder. This is used for testing.
     * We'll see how this is handled in Middleware...
     * @param svcBuilder
     */
    public MyServlet(ISvcBuilder svcBuilder, IPersonBuilder personBuilder) {
        super();
        
        this._myDb = svcBuilder.createDb();
		this._personBuilder = personBuilder;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int age = Integer.parseInt(request.getParameter("age"));

		// Creating a Person model
		IPerson person = _personBuilder.getPerson();
		person.set_id(id);
		person.set_age(age);

		try {
			this._myDb.updateBirth(person);
		}
		catch (Exception e) {
			// Return Http code 400
			response.setStatus(401); // This is wrong on purpose!
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		
		StringBuilder requestBody = new StringBuilder();
	      String line;
	      try (BufferedReader reader = request.getReader()) {
	         while ((line = reader.readLine()) != null) {
	            requestBody.append(line).append("\n");
	         }
	      }
	      
      	JSONObject jsonObject = null;
		try {
		    jsonObject = new JSONObject(requestBody.toString());
		  } catch (JSONException e) {
			response.getWriter().append(e.getMessage() + "\n" + e.getStackTrace().toString());
			return;
		  }

		String ageStr = jsonObject.getString("age");
		int age = Integer.parseInt(ageStr);

		// Creating a Person model
		Person person = new Person();
		person.set_id(id);
		person.set_age(age);

		try {
			this._myDb.updateBirth(person);
		}
		catch (Exception e) {
			// Return Http code 400
			response.setStatus(401); // This is wrong on purpose!
		}
	}

}
