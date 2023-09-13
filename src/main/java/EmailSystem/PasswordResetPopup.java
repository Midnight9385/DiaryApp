package EmailSystem;

import java.io.IOException;

import UserSystem.UserInterface;
import de.milchreis.uibooster.UiBooster;
import de.milchreis.uibooster.model.Form;


public class PasswordResetPopup {
    final static UiBooster booster = new UiBooster();
    final static Form f = booster.createForm("Email Login Recovery")
                .addText("Enter the email attached to your account")
                .show();
    static UserInterface u = null;            
    public static void main(String[] args) throws IOException {
        PasswordResetPopup l = new PasswordResetPopup(null);
        l.send();
    }

    public PasswordResetPopup(UserInterface u) throws IOException{
        // System.out.println("opened pop up");
        this.u = u;
        this.send();
    }

    public void send() throws IOException{
        // System.out.println("ran send");
        // System.out.println(f.getByLabel("Enter the email attached to your account").asString());
        u.sendEmail(f.getByLabel("Enter the email attached to your account").asString());
    }
}
