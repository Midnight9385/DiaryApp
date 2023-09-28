package util.UI.Source.model.options.themes;

import com.formdev.flatlaf.FlatDarkLaf;

public class OceanDarkLaf extends FlatDarkLaf {
    public static boolean setup() {
        return setup( new OceanDarkLaf() );
    }

    @Override
    public String getName() {
        return "OceanDarkLaf";
    }
}
