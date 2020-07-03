package com.fileupload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TcpFileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = serverSocket.accept();
                        InputStream inputStream = socket.getInputStream();
                        String fileName = "com"+System.currentTimeMillis()+new Random().nextInt(9999)+".jpg";
                        FileOutputStream fileOutputStream = new FileOutputStream("image//"+fileName);
                        int len = 0;
                        byte []bytes = new byte[1024];
                        while ((len = inputStream.read(bytes)) != -1){
                            fileOutputStream.write(bytes,0,len);
                        }
                        socket.getOutputStream().write("上传成功".getBytes());
                        fileOutputStream.close();
                        socket.close();
                    }catch (IOException e){
                        System.out.println(e);
                    }
                }
            }).start();
        }
    }
}
