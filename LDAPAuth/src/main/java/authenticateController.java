import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.unboundid.ldap.sdk.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateUser(username, password)) {
            response.getWriter().println("Authentication successful!");
        } else {
            response.getWriter().println("Authentication failed!");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Replace these values with your actual LDAP server details
        String ldapServer = "ldap://your-ldap-server:389";
        String ldapBaseDN = "ou=Users,dc=example,dc=com";
        String ldapBindDN = "cn=admin,dc=example,dc=com";
        String ldapBindPassword = "admin-password";

        try {
            // Set up the LDAP connection
            LDAPConnection ldapConnection = new LDAPConnection(ldapServer);
            BindRequest bindRequest = new SimpleBindRequest(ldapBindDN, ldapBindPassword);
            ldapConnection.bind(bindRequest);

            // Search for the user in the LDAP directory
            SearchRequest searchRequest = new SearchRequest(ldapBaseDN, SearchScope.SUB, "(sAMAccountName=" + username + ")");
            SearchResultEntry searchResult = ldapConnection.search(searchRequest).getSearchEntries().get(0);

            // Attempt to bind with the user's credentials
            BindRequest userBindRequest = new SimpleBindRequest(searchResult.getDN(), password);
            ldapConnection.bind(userBindRequest);

            // If the bind is successful, the authentication is successful
            return true;

        } catch (LDAPException | IOException e) {
            // Handle exceptions, log or display error messages
            e.printStackTrace();
            return false;
        }
    }
}
