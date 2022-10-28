package cs451;

import cs451.Host;
import java.util.List;

public class Constants {
    public static final int ARG_LIMIT_CONFIG = 7;

    // indexes for id
    public static final int ID_KEY = 0;
    public static final int ID_VALUE = 1;

    // indexes for hosts
    public static final int HOSTS_KEY = 2;
    public static final int HOSTS_VALUE = 3;

    // indexes for output
    public static final int OUTPUT_KEY = 4;
    public static final int OUTPUT_VALUE = 5;

    // indexes for config
    public static final int CONFIG_VALUE = 6;

    // self defined
    public static final String BROADCAST = "b";
    public static final String DELIVER = "d";
    public static final int RECEIVEINTERVAL = 5; //Interval between sending same message
    public static final int SENDINTERVAL = 500;
    public static final int NOTFOUND = -1;
    public static final int BUFFER_SIZE = 1000000;
    public static final int BIG_NUMBER = 100000;
    public static final int HASHSET_CAPACITY = (int)Math.pow(2,10);
    public static final int SEND_MESSAGE = 5000;

    public static final boolean SEND = true;
    public static final boolean ACK = false;

    public static final String getIpFromHosts(List<Host> hosts, int processId){
        return hosts.get(processId - 1).getIp();
    }

    public static final int getPortFromHosts(List<Host> hosts, int processId){
        return hosts.get(processId - 1).getPort();
    }

    public static final int getProcessIdFromIpAndPort(List<Host> hosts, String ip, int port){
        for (Host host:hosts){
            if (host.getIp().equals(ip) && host.getPort() == port)
                return host.getId();
        }
        return NOTFOUND;
    }
}
