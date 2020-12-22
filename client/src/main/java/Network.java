import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {

    private static Network network;
    private ObjectEncoderOutputStream os;
    private ObjectDecoderInputStream is;

    private Network() {
        try {
            Socket socket = new Socket("localhost", 8189);
//            is = new DataInputStream(socket.getInputStream());
//            os = new DataOutputStream(socket.getOutputStream());
            os = new ObjectEncoderOutputStream(socket.getOutputStream());
            is = new ObjectDecoderInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectDecoderInputStream getIn() {
        return is;
    }

    public ObjectEncoderOutputStream getOut() {
        return os;
    }

    public static Network get() {
        if (network == null) {
            network = new Network();
        }
        return network;
    }
}
