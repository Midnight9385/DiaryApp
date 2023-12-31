package util.UI.Source.model.options;

import com.formdev.flatlaf.FlatDarculaLaf;
import util.UI.Source.model.UiBoosterOptions;
import util.UI.Source.model.options.themes.OceanDarkLaf;
import util.UI.Source.model.options.themes.PurpleDarkLaf;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.lang.reflect.Constructor;

public class OSNativeUiBoosterOptions extends UiBoosterOptions {

    /**
     * Work around the UIManager to maintain options hierarchy and not
     * make a unique case for OS Native options directly in
     * the UiBooster constructor
     */
    public static BasicLookAndFeel OSNativeLookAndFeel() {
        try {
            String className = UIManager.getSystemLookAndFeelClassName();
            Class<?> klass = Class.forName(className);
            Constructor<?> constr = klass.getConstructor();
            return (BasicLookAndFeel) constr.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
            return new OceanDarkLaf();
        }
    }

    public OSNativeUiBoosterOptions() {
        this(defaultIconPath);
    }

    public OSNativeUiBoosterOptions(String iconPath) {
        super(OSNativeLookAndFeel(), iconPath, defaultLoadingImage);
    }

}
