package binaris.fitxers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {

    public MyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    public void writeStreamHeader(){
        try {
            reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
