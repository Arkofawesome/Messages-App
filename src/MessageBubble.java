import java.awt.*;

public class MessageBubble {
    int x;
    int y;
    int width;
    int height;
    int arcWidth;
    int arcHeight;
    Color color;

    public MessageBubble() {
        x=0;
        y=0;
        width=0;
        height=0;
        arcWidth=0;
        arcHeight=0;
        color = Color.BLACK;
    }

    public MessageBubble(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.color = color;
    }
}
