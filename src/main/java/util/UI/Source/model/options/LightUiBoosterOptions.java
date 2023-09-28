package util.UI.Source.model.options;

import com.formdev.flatlaf.FlatLightLaf;
import util.UI.Source.model.UiBoosterOptions;
import util.UI.Source.model.options.themes.OceanDarkLaf;
import util.UI.Source.model.options.themes.PurpleDarkLaf;

import javax.swing.*;

public class LightUiBoosterOptions extends UiBoosterOptions {
    public static final String defaultLoadingImage = "/loading-75.gif";

    public LightUiBoosterOptions() {
        this(defaultIconPath);
    }

    public LightUiBoosterOptions(String iconPath) {
        super(new OceanDarkLaf(), iconPath, defaultLoadingImage);

        // Little hack to start working on linux
        UIManager.getFont("Label.font");
    }
}
