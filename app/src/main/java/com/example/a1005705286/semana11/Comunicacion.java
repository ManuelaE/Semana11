package com.example.a1005705286.semana11;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Comunicacion extends Thread {

    DatagramSocket ds;
    OnMessage observer;

    public Comunicacion () {

    }

    @Override
    public void run () {

        try {

            ds = new DatagramSocket(5000);

        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public void recibir () throws IOException {

        byte[] buf = new byte[1000];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);
        Log.e("Llegó", "Mensaje recibido");

        String mensaje = new String( p.getData() );
        observer.onReceived(mensaje);
    }

    public void enviar (String dato) throws IOException {

        byte[] buf = dato.getBytes();
        InetAddress ip;
        ip = InetAddress.getByName("10.0.2.2");
        DatagramPacket p = new DatagramPacket(buf, buf.length, ip, 5000);
        ds.send(p);
        Log.e("envié", "Mensaje enviado");
    }

    public interface OnMessage{
        public void onReceived(String input);
    }

    public void setObserver(OnMessage observer) {
        this.observer = observer;
    }

}
