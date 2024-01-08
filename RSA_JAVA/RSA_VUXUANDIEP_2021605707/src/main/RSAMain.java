package main;

import view.RSAView;

public class RSAMain {

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RSAView().setVisible(true);
            }
        });
    }
}
