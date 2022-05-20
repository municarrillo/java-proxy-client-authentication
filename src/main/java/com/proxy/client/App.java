package com.proxy.client;

import java.util.Scanner;

import com.proxy.client.Config.ClientAuthentication;
import com.proxy.client.Settings.ProxySettings;


public class App {

    public static void main( String[] args ) {
        System.out.println("------------- Bienvenid@ -------------");
        ProxySettings proxy = new ProxySettings();

        proxy.autoDetectSettings();
        
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

        } else System.out.println("No se ha detectado una configuración proxy.");

    }
}
