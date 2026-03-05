/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Vlasnik;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class LoginController {
    private final LoginForma lf;
    public LoginController (LoginForma lf){
        this.lf=lf;
        addActionListeners();
    }

    private void addActionListeners() {
       lf.loginAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   prijava(e);
               } catch (Exception ex) {
                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }

           private void prijava(ActionEvent e) throws Exception {
               String username=lf.getTxtUsername().getText().trim();
               String password=String.valueOf(lf.getjPasswordField1().getPassword()).trim();
               Komunikacija.getInstance().konekcija();
               Vlasnik ulogovani=Komunikacija.getInstance().login(username,password);
               if (ulogovani == null) {
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni", "GRESKA", JOptionPane.ERROR_MESSAGE);   
                    }
               else{
                   Cordinator.getInstance().setUlogovaniVlasnik(ulogovani);
                   JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                   Cordinator.getInstance().otvoriGlavnuFormu();
                   lf.dispose();
               }
           }
       });
       
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
