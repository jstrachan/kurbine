package io.fabric8.kurbine;

import com.netflix.turbine.discovery.Instance;
import io.fabric8.kurbine.discovery.KubernetesDiscovery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DiscoveryFeedbackServlet extends HttpServlet {

    private static final KubernetesDiscovery DISCOVERY = new KubernetesDiscovery();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("text/html");


        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println("<h1>Hystrix Endpoints:</h1>");
        try {
            for (Instance instance : DISCOVERY.getInstanceList()) {
                out.println("<h3>" + instance.getHostname() + ":" + instance.getCluster() + ":" + instance.isUp() + "</h3>");
            }
        } catch (Throwable t) {
            t.printStackTrace(out);
        }
        out.flush();
    }

}
