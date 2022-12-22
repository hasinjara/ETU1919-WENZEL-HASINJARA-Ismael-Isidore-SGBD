package algo.tableRelationnelle;
import java.io.*;
import java.lang.*;
import java.util.Vector;

import algo.filtre.*;

public class TableRelationnelle implements Serializable{
    
    String path = "donne\\";
    File emplacement;
    String[] nomFiles;
    //Vector allData = allData();

    String nom;
    String[] champ;
    Object[] donnee;

    public TableRelationnelle() {
        emplacement = new File(path);
        nomFiles = emplacement.list();
    }

    public TableRelationnelle(String nom) {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        setNom(nom);
        setChamp( (inFile(nom)[0]).toString().split("\\,"));
        setDonnee( inFile(nom));
    }

    public TableRelationnelle(String nom, Object[] donnee) {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        setNom(nom);
        setChamp( (donnee[0]).toString().split("\\,"));
        setDonnee(donnee);
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String[] getChamp() {
        return champ;
    }

    public void setChamp(String[] champ) {
        this.champ = champ;
    }

    public Object[] getDonnee() {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        return donnee;
    }

    public void setDonnee(Object[] donnee) {
        this.donnee = donnee;
    }

    public File[] getAllFile() {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        File[] list = this.emplacement.listFiles();
    return list;
    }

    public String getPath() {
        return path;
    }
    public File getEmplacement() {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        return emplacement;
    }
    public String[] getNomFiles() {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        return nomFiles;
    }

    public File getFile(String nom) {
        emplacement = new File(path);
        nomFiles = emplacement.list();
        Filtre f = new Filtre(this ,this.emplacement ,nom);
        File[] list = this.emplacement.listFiles(f);
    return list[0];
    }

    public int countLine(String nomFichier) {
            //creat fichier
        File fichier=new File(nomFichier);
        //System.out.println("huu");
        int val = 0;
        Object[] b;
        //System.out.println("tazeafa");
        //buffer --> le mpanoratra
        try(BufferedReader reader = new BufferedReader(new FileReader(fichier) )) 
        {   
            b = reader.lines().toArray();
            val = b.length;  
            //System.out.println("val : " + val);  
        }
        catch(IOException a)
        {
            a.printStackTrace();
        }
    return val;
    }

    public Object[] inFile(String nom) {
        File fichier = getFile(nom);
        int alloc = countLine(path + nom);
        Object[] val = new Object[alloc];
        try(BufferedReader reader = new BufferedReader(new FileReader(fichier) )) 
        {   
            val = reader.lines().toArray(); 
        }
        catch(IOException a)
        {
            a.printStackTrace();
        }
    return val;
    }

    public Vector allData() {
        Vector v = new Vector();
        try {
            for (int i = 0; i < nomFiles.length; i++) {
                v.add(inFile(nomFiles[i]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    return v;
    }

}
