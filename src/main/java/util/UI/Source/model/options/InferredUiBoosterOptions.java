package util.UI.Source.model.options;

import com.formdev.flatlaf.FlatDarculaLaf;
import util.UI.Source.model.UiBoosterOptions;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 * Uses/Infers an existing and already set look and feel for UIBooster.
 */
public class InferredUiBoosterOptions extends UiBoosterOptions {

    public static BasicLookAndFeel InferredLookAndFeel() {
        try {
            LookAndFeel laf = UIManager.getLookAndFeel();
            if (laf == null) {
                System.err.println("UiBooster: No previously set look anf feel found! Using default.");
                return new FlatDarculaLaf();
            }
            if (!(laf instanceof BasicLookAndFeel)) {
                System.err.println("UiBooster: Look and feel is not an instance of BasicLookAndFeel! Using default.");
                return new FlatDarculaLaf();
            }
            return (BasicLookAndFeel) laf;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("An unexpected exception occurred while initialising DefaultUiBoosterOptions! Using default.");
            return new FlatDarculaLaf();
        }
    }

    public InferredUiBoosterOptions() {
        this(defaultIconPath);
    }

    public InferredUiBoosterOptions(String iconPath) {
        super(InferredLookAndFeel(), iconPath, defaultLoadingImage);
    }
}
