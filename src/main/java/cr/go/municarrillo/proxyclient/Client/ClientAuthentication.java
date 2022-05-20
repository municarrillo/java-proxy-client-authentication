package cr.go.municarrillo.proxyclient.Client;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;


public class ClientAuthentication {
    private final String username;
    private final String password;

    public ClientAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * Use of java.net.Authenticator to place the credentials proxy to JVM network.
     * @return true - auth successfull | false - no auth
     */
    public void authenticate() {

        Authenticator.setDefault(
            new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(username, password.toCharArray());
                }
            }
        );
    }


    public boolean isAuthenticated() {

        int responseCode = this.sendRequestTest();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Authentication successfull.");
            return true;
        }

        if (responseCode == HttpURLConnection.HTTP_PROXY_AUTH) {
            System.out.println("Error Proxy authentitacion. Bad credentials.");
            return false;
        }

        if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
            System.out.println("Internal Server Error response HTTP");
            return false;
        }

        return false;
    }

    /**
     * Send a GET Request to http://tsa.sinpe.fi.cr/tsaHttp
     * 
     * @return responseCodeHttp
     */
    private int sendRequestTest() {
        try {
            URL url = new URL("http", "tsa.sinpe.fi.cr", "/tsaHttp");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            System.out.println(con.getResponseCode());
            return con.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.join("-", this.username, this.password);
    }
    
}
