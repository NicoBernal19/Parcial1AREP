package edu.eci.arep;

import java.net.*;
import java.io.*;
public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }
        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean isFline = true;
            String Fline = " ";
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (isFline = true){
                    Fline = inputLine;
                    isFline = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            outputLine =
                    "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>\n"
                            + "<html>\n"
                            + "<head>\n"
                            + "<meta charset=\"UTF-8\">\n"
                            + "<title>Title of the document</title>\n"
                            + "</head>\n"
                            + "<body>\n"
                            + "<h1>Form with GET</h1>\n"
                            + "</body>\n"
                            + "</html>\n";
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
    public static String getRqUrl(URL url){
        String res = url.getPath().split(" ")[1];
        return res;
    }
}
