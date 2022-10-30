package cs451;

import cs451.Host;

import java.io.*;
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



    public static byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
          return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bo);
        oos.writeObject(obj);
        return bo.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
    Object object = null;
    try {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);//
        ObjectInputStream ois = new ObjectInputStream(bis);
        object = ois.readObject();
        ois.close();
        bis.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    }
    return object;
    }

}
