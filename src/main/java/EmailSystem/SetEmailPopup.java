package EmailSystem;

import UserSystem.User;
import de.milchreis.uibooster.UiBooster;
import de.milchreis.uibooster.model.Form;


public class SetEmailPopup {
    final static UiBooster booster = new UiBooster();
    final static Form f = booster.createForm("Email Login Recovery")
                .addText("Enter the email you want attached to your account")
                .show();          
    
    User u = null;                
    public static void main(String[] args){
        // System.out.println(SetEmailPopup.createPopup());
    }

    public SetEmailPopup(User u){
        this.u = u;
        this.send();
    }

    private void send(){
        u.setEmail(f.getByLabel("Enter the email you want attached to your account").asString());
    }
}
