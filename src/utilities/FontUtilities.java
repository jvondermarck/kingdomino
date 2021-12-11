package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontUtilities {

    public static Font setFont(String path) {
        Font _font = new Font("Serif", Font.PLAIN, 10);
        try {
            _font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(_font);
        } catch(FontFormatException | IOException e)
        {
            System.out.println(e);
        }
        return _font;
    }
}
