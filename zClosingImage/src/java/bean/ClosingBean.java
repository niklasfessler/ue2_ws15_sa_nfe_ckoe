package bean;

import data.ImageEventReadable;
import interfaces.ImageEvent;
import interfaces.ImageEventHandlerImpl;
import interfaces.ImageListener;

import java.io.StreamCorruptedException;

/**
 * Created by mod on 11/23/15.
 */
public class ClosingBean extends ImageEventHandlerImpl implements ImageListener {
    private ClosingFilter closingFilter = null;
    private int radius = 0;

    public ClosingBean() {
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void onImage(ImageEvent e) {
        closingFilter = new ClosingFilter(new ImageEventReadable<ImageEvent>(e), radius);
        ImageEvent result = null;
        try {
            result = closingFilter.read();
        } catch (StreamCorruptedException e1) {
            e1.printStackTrace();
        }
        notifyAllListener(result);
    }
}
