package cs451.Links;

import cs451.Constants;
import cs451.Logger;
import cs451.Record;
import cs451.Host;

import java.util.List;

public class Listener implements Runnable {
    PerfectLink perfectLink;
    Logger logger;
    public boolean flag = true;
    List<Host>  hosts;

    public Listener(PerfectLink perfectLink, Logger logger, List hosts){
        this.perfectLink = perfectLink;
        this.logger = logger;
        this.hosts = hosts;
    }

    @Override
    public void run(){
        while(flag){
            Record tmp = perfectLink.receive();
            Record record = perfectLink.deliver(tmp);
            if (record != null) {
                int process = -1;
                for (Host host:hosts){
                    if (host.getIp().equals(record.ip) && host.getPort() == record.port)
                    process = host.getId();
                }
                String log = Constants.DELIVER + " " + process + " " + new String(record.m.payload) + "\n";
                logger.log(log);
            }
        }
    }


    public void stop(){
        this.flag = false;
    }
}
