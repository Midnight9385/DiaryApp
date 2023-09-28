package util.UI.Source.model.options;

import util.UI.Source.model.UiBoosterOptions;
import util.UI.Source.model.options.themes.OceanDarkLaf;
import util.UI.Source.model.options.themes.PurpleDarkLaf;

import javax.swing.plaf.metal.MetalLookAndFeel;

public class SwingUiBoosterOptions extends UiBoosterOptions {

    public SwingUiBoosterOptions() {
        this(defaultIconPath);
    }

    public SwingUiBoosterOptions(String iconPath) {
        super(new OceanDarkLaf(), iconPath == null ? defaultIconPath : iconPath, defaultLoadingImage);
    }
}
