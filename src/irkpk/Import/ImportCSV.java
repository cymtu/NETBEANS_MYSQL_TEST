package irkpk.Import;

import irkpk.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class ImportCSV implements ImportFile {

    @Override public Vector importFile(String file) {
        String s;
        String[] tmp;
        Person person;
        BufferedReader in;
        Vector v = new Vector();
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                while ((s = in.readLine()) != null  && s.length() != 0){
                  s=s.trim();
                  if(s.charAt(0)=='#') continue;
                  tmp = s.split(";");
                  person = new Person(tmp[0],tmp[1],tmp[2]);
                  v.add(person);
                }
                return v;
            } catch (IOException ex) {
                Logger.getLogger(ImportProperty.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportProperty.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
}
