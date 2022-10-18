package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;

import javafx.scene.input.TouchPoint;

public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter pr = null;
    private BufferedReader br = null;

    public ClientHandler(Socket s) {
        this.s = s;
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int j = 0;
        try {
            j++;
            System.out.println(br.readLine());
            pr.println("Ciao come ti chiami"); // invio messaggio
            String nome = br.readLine(); // ricevo: il nome
            System.out.println("Messagio ricevuto");

            pr.println("Benvenuto " + nome.toUpperCase() + " sei l'utente numero " +  j );

            
            System.out.println(br.readLine()); // leggo il saluto finale e lo metto in console

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}