package UDPDemo;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP 提供者， 用于提供服务
 */

public class UDPProvider {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPPrivoder Started.");

        // 作为接收者，指定一个端口用于数据接收
        DatagramSocket ds = new DatagramSocket(20000);

        // 构建接收实体
        final byte[] buf = new byte[521];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接收
        ds.receive(receivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLen = receivePack.getLength();

        String data  = new String(receivePack.getData(),0, dataLen);

        System.out.println("UDPPrivoder received from ip "+ip
                +"\t Port "+ port+"\t data: "+data);

        // 构建一份回送数据
        String responseData = "Receive data with len:"+dataLen;

        byte[] responseDataByte = responseData.getBytes();

        // 直接根据发送者构建一份回送信息
        DatagramPacket reponsePacket = new DatagramPacket(responseDataByte,
                responseDataByte.length,
                receivePack.getAddress(),
                receivePack.getPort());

        ds.send(reponsePacket);
        // 完成
        System.out.println("UDPProvider Finished");
        ds.close();
    }
}
