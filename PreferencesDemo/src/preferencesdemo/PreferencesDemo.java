/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preferencesdemo;

import java.util.prefs.Preferences;

/**
 *
 * @author Ben
 */
public class PreferencesDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Preferences pref = Preferences.userNodeForPackage(RunescapeBot.class);
    }
    
}
