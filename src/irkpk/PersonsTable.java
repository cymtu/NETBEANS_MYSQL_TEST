package irkpk;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class PersonsTable extends JTable implements Observer{
    
    Hibernate hibernate;
    
    public PersonsTable(){
        super();
        hibernate = Hibernate.getInstance();
        setModel(new PersonsModel(hibernate.getList()));
    }
    
    @Override public void update(Observable o, Object arg) {
        setModel(new PersonsModel(hibernate.getList()));
    }
    
}
