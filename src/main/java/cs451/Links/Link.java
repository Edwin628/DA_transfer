package cs451.Links;

import cs451.Message;
import cs451.Record;

public interface Link {
    public abstract void send(Message m, String ip, int port);
    public abstract Record receive();
    public abstract Record deliver(Record m);
    public abstract void close();
}
