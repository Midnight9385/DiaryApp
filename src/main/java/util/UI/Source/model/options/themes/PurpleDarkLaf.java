package util.UI.Source.model.options.themes;

import com.formdev.flatlaf.FlatDarkLaf;

public class PurpleDarkLaf extends FlatDarkLaf {
    public static boolean setup() {
        return setup( new PurpleDarkLaf() );
    }

    @Override
    public String getName() {
        return "PurpleDarkLaf";
    }
}
