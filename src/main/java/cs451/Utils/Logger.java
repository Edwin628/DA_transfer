package cs451.Utils;

import cs451.Constants;

import java.io.*;
import java.nio.ByteBuffer;

public class Logger {
    String directory;
    File file;
    OutputStream outputStream;
    ByteBuffer buffer;
    byte[] tmp;

    public Logger(String dir){
        this.directory = dir;
        this.buffer = ByteBuffer.allocate(Constants.BUFFER_SIZE);
        this.tmp = new byte[Constants.BUFFER_SIZE];
        file = new File(directory);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
             outputStream = new FileOutputStream(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void log(String msg){
        int limit = this.buffer.limit();
        int position = this.buffer.position();
        byte[] byteStream = msg.getBytes();
        if ((limit - position) < byteStream.length){
            write();
        }
        this.buffer.put(byteStream);
    }

    //write broadcast or delivery record to the file
    public void write(){
        this.buffer.flip();
        try {
            int start = this.buffer.position();
            int end = this.buffer.limit();
            this.buffer.get(this.tmp, start, end);
            outputStream.write(this.tmp, start, end);
            this.buffer.compact();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //close the output stream
    public void close(){
        try {
            this.write();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
