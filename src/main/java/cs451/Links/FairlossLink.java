package cs451.Links;

import cs451.Host;
import cs451.Constants;
import cs451.Message;
import cs451.Record;

import java.io.*;
import java.net.*;
import java.util.List;
public class FairlossLink implements Link{

    DatagramSocket socket;
    List hosts;

    public FairlossLink(int port, List hosts){
        try {
            this.socket = new DatagramSocket(port);
            int size = socket.getSendBufferSize();
            socket.setReceiveBufferSize(hosts.size() * size);
            this.hosts = hosts;

        } catch (SocketException e){
            e.printStackTrace();
        }
    }

    @Override
    public void send(Message m, String ip, int port) {
        try {
            //trasfer object to byte array
            byte[] bytes = new byte[128];
            try {
                bytes = Constants.getBytesFromObject(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress(ip, port));
            socket.send(packet);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Record receive() {
        byte[] container = new byte[128];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        try {
            socket.receive(packet);
            Object obj = Constants.deserialize(packet.getData());
            Record record = new Record((Message) obj, packet.getAddress().getHostAddress(), packet.getPort());
	        return record;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Record deliver(Record m) { return m; }

    @Override
    public void close(){ socket.close(); }

}
