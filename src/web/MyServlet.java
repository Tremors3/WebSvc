package web;

import com.google.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import services.IManageBirth;
import utils.IMapToPerson;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private IManageBirth _imb;

    @Inject
    private IMapToPerson _mtp;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));

        try {
            this._imb.updateBirth(_mtp.mapToPerson(id, age));
        } catch (Exception e) {
            // Return Http code 400
            response.setStatus(401); // This is wrong on purpose!
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        JSONObject jsonObject = null;
        try {
            jsonObject = WebUtils.getHttpRequestBodyAsJson(request);
        } catch (JSONException e) {
            response
                .getWriter()
                .append(e.getMessage() + "\n" + e.getStackTrace().toString());
            return;
        }

        try {
            this._imb.updateBirth(_mtp.mapToPerson(jsonObject));
        } catch (Exception e) {
            // Return Http code 400
            response.setStatus(401); // This is wrong on purpose!
        }
    }
}
