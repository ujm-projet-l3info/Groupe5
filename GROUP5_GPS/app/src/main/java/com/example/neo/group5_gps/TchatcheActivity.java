package com.example.neo.group5_gps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class TchatcheActivity extends AppCompatActivity implements Runnable{
    // The default port.
    public int portNumber = 9999;
    // The default host.
    public String host = "109.29.198.199";
    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    private View view;
    private ChatManager chatManager;
    private TextView chatLine;
    private ListView listView;
    WiFiChatFragment.ChatMessageAdapter adapter = null;
    private List<String> items = new ArrayList<String>();
        @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchatche);
        chatLine = (TextView) view.findViewById(R.id.TCHATBOX);
        listView = (ListView) view.findViewById(android.R.id.list);

    }


    @Override
    protected void onRestart() {
        super.onRestart();

    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void tchatche  (String host,int portNumber) {
        this.host = host;
        this.portNumber = portNumber;

        /*
         * Open a socket on a given host and port. Open input and output streams.
         */
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }

        /*
        * If everything has been initialized then we want to write some data to the
        * socket we have opened a connection to on the port portNumber.
        */
        if (clientSocket != null && os != null && is != null) {
            try {

                /* Create a thread to read from the server. */
                new Thread(new TchatcheActivity()).start();

                //new Thread(new MultiThreadChatClient()).start();
                while (!closed) {
                    os.println(inputLine.readLine().trim());
                }
                /*
                * Close the output stream, close the input stream, close the socket.
                */
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
        /*
        * Create a thread to read from the server. (non-Javadoc)
        *
        * @see java.lang.Runnable#run()
         */



    public void run(){
    /*
     * Keep on reading from the socket till we receive "Bye" from the
     * server. Once we received that then we want to break.
     */
        String responseLine;//=MainActivity.pseudo;
        try {
            while ((responseLine = is.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.indexOf("*** Bye") != -1)
                    break;
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }

}
