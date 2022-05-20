package cr.go.municarrillo.proxyclient;

import java.util.Scanner;

import cr.go.municarrillo.proxyclient.Client.ClientAuthentication;
import cr.go.municarrillo.proxyclient.Settings.ProxySettings;


public class App {

    public static void main( String[] args ) {
        System.out.println("------------- Bienvenid@ -------------");
        ProxySettings proxy = new ProxySettings();

        proxy.autoDetectSettings();
        System.out.println(proxy.toString());
        
        if (proxy.isProxyDetected()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese su nombre de usuario:");
            final String username = scanner.nextLine();

            System.out.println("Ingrese su contraseña:");
            final String password = scanner.nextLine();
            scanner.close();

            ClientAuthentication client = new ClientAuthentication(username, password);

            client.authenticate();
            client.isAuthenticated();
        }
    }
}
