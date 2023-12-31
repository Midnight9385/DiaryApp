package util.UI.Source.model.options;

import com.formdev.flatlaf.FlatDarculaLaf;
import util.UI.Source.model.UiBoosterOptions;
import util.UI.Source.model.options.themes.OceanDarkLaf;
import util.UI.Source.model.options.themes.PurpleDarkLaf;

import javax.swing.*;

public class DarkUiBoosterOptions extends UiBoosterOptions {
    public static final String defaultLoadingImage = "/loading-75_darcula.gif";

    public DarkUiBoosterOptions() {
        this(defaultIconPath);
    }

    public DarkUiBoosterOptions(String iconPath) {
        super(new OceanDarkLaf(), iconPath, defaultLoadingImage);

        // Little hack to start working on linux
        UIManager.getFont("Label.font");
    }
}
