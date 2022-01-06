package utilities;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FontUtilities {

    public static Font setFont(String path) {
        Font _font = new Font("Serif", Font.PLAIN, 10);
        try {
            _font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(path)));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(_font);
        }catch(FontFormatException | IOException e)
        {
            System.out.println(e);
        }
        return _font;
    }
}
