import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class uipendu {
    private JButton button1;
    private JPanel jpmain;
    private JTextField textField1;
    private JLabel motslab;
    private JLabel letttest;
    private JLabel erreur;
    private JLabel penduDessin;
    private String[] mots = {"Apocalypse","Attraction","Aventurier","Bouillotte",
            "Citrouille","Controverse","Coquelicot","Tomahawk",
            "Toujours","Gangster","Gothique","Qualification",
            "Protozoaire","Hochet","Hormis","Humour","Billard",
            "Bretzel","Iceberg","Javelot"};
    private String mot = "",motCache = "", lettres = "", temps ="", wonge ="", anciennes="", image = "1";
    boolean temp3;
    int nbErrors = 1 ;




    public uipendu() {
        ImageIcon dessin = new ImageIcon("./src/dessin/"+nbErrors+".png");
        choisirMots();
        penduDessin.setIcon(dessin);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                temps = textField1.getText();
                if(testeLettreDansMots(temps,mot) == true ){
                    lettres = lettres + textField1.getText();
                    remplacerLettres(mot,lettres);

                }else if(testeLettreDansMots(temps,mot) == false){
                    if (recupérerNouvelleLettre(anciennes) == false){
                    wonge = wonge + textField1.getText();

                        nbErrors = nbErrors +1;
                        ImageIcon dessin = new ImageIcon("./src/dessin/"+nbErrors+".png");
                        penduDessin.setIcon(dessin);
                    }if(nbErrors == 11){
                        System.out.println("tu a perdu");
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        System.exit(1);
                    }


                    erreur.setText(String.valueOf(nbErrors));


                }
                 System.out.println(wonge);
                 textField1.setText("");
                 letttest.setText(wonge);
                 motslab.setText(motCache);



            }
        });


    }

    public String choisirMots(){
        int x = (int)(Math.random()*20);
        mot = mots[x];
        for (int i = 0; i < mot.length(); i++){
            motCache += "*";
        }
        motslab.setText(motCache);
        System.out.println(mot);
        return mot;
    }




    public boolean testeLettreDansMots(String lettre, String chaine){
        System.out.println(chaine.indexOf(lettre));
    if(chaine.indexOf(lettre.toUpperCase())!=-1) {
        return true;
    }
       else if(chaine.indexOf(lettre.toLowerCase())!=-1) {
           return true;
    }
    else return false;
    }


    public void remplacerLettres(String mot, String lettres ){
        for (int j = 0; j < mot.length(); j++){
            for (int i = 0; i < lettres.length(); i++){
                if(mot.charAt(j) == lettres.toLowerCase().charAt(i)){
                    motCache = motCache.substring(0, j) + lettres.toLowerCase().charAt(i) + motCache.substring(j+1);

                }else if(mot.charAt(j) == lettres.toUpperCase().charAt(i)){
                    motCache = motCache.substring(0, j) + lettres.toUpperCase().charAt(i) + motCache.substring(j+1);

                }
            }
        }
    }

    public boolean recupérerNouvelleLettre(String anciennes){
        String temp1 =  textField1.getText();
        if(anciennes.indexOf(temp1)!=-1) {

            System.out.println("cette lettre a déjà été proposée:"+temp1);
            return true; //si la lettre est deja mise
        }
        else{
            this.anciennes = anciennes + temp1;
            return false;
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("uipendu");
        frame.setContentPane(new uipendu().jpmain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);



    }


}
