package com.example.melone;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
    ServerSocket server=null;
    Socket client=null;
    String stringaricevuta=null;
    String stringamodificata=null;    
    BufferedReader indalclient;
    DataOutputStream outversoclient;
    
    public static void main( String[] args )
    {
        Server mserver=new Server();
        mserver.attendi();
        mserver.comunica();
    }

    public Socket attendi()
    {
        try
        {
            System.out.println("Server in esecuzione");
            server=new ServerSocket(6789);
            client=server.accept();
            server.close();
            indalclient= new BufferedReader(new InputStreamReader(client.getInputStream()));
            outversoclient=new DataOutputStream(client.getOutputStream());
            
        }
        catch(Exception e)
        {
          System.out.println(e.getMessage());
          System.out.println("Errore connessione");
          System.exit(1);  
        }
        return client;
    }

    public void comunica()
    {
        try
        {
            System.out.println("scrivi una frase e la trasformo in maiuscolo: ");
            stringaricevuta=indalclient.readLine();
            System.out.println("ricevuta stringa: "+stringaricevuta);

            stringamodificata=stringaricevuta.toUpperCase();
            System.out.println("invio stringa");
            outversoclient.writeBytes(stringamodificata+'\n');

            System.out.println("fine istruzioni");
            client.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore connessione");
            System.exit(1);
        }
    }
}
