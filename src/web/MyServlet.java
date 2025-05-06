package web;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import usecases.IManageBirth;
import web.utils.IMapToPerson;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IManageBirth _imb = null;
	private IMapToPerson _mtp = null;

    /**
     * Explicitly assign svcBuilder. This is used for testing.
     * We'll see how this is handled in Middleware...
     */
    public MyServlet(IManageBirth manageBirth, IMapToPerson mapToPerson) {
        super();
        
        this._imb = manageBirth;
		this._mtp = mapToPerson;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int age = Integer.parseInt(request.getParameter("age"));

		try {
			this._imb.updateBirth(_mtp.mapToPerson(id, age));
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


		try {
			this._imb.updateBirth(_mtp.mapToPerson(id, age));
		}
		catch (Exception e) {
			// Return Http code 400
			response.setStatus(401); // This is wrong on purpose!
		}
	}

}
