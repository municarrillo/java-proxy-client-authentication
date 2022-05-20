package cr.go.municarrillo.proxyclient.Settings;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.Proxy;

public class ProxySettings {

    private Proxy proxy;


    /**
     * Autodetect the proxy settings on the OS.
     * Add the URLs to except of the proxy server restrinctions.
     */
    public void autoDetectSettings() {
        System.getProperties().put("java.net.useSystemProxies", "true");

        try {
            this.proxy = (Proxy) ProxySelector.getDefault().select(new URI("http://firmador.libre.cr/")).iterator().next();

            // TODO: The following instructions might be unnecessary...
            if (proxy != null && proxy.address() != null) {

                InetSocketAddress addr = (InetSocketAddress) proxy.address();
                System.out.println("proxy type: " + proxy.type());
                System.getProperties().put("http.proxyHost", addr.getHostName());
                System.getProperties().put("http.proxyPort", Integer.toString(addr.getPort()));

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public boolean isProxyDetected() {
        if (this.proxy.address() != null) return true; else return false;
    }


    @Override
    public String toString() {
        if (this.proxy == null || this.proxy.address() == null) return "No proxy settings detected.";
        else {
            InetSocketAddress addr = (InetSocketAddress) proxy.address();
            return String.join("--", addr.getHostName(), Integer.toString(addr.getPort()));
        }
    }

}
