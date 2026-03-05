/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smena;

import domen.VlSm;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class UbaciVlSmSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof VlSm)) {
            throw new Exception("Sistem ne moze da kreira vlasnik smenu.");
            
        }
        VlSm vs=(VlSm) param;
        if(vs.getIdVlasnik().getIme()==null || vs.getIdVlasnik().getIme().isEmpty()){
            throw new Exception("Sistem ne moze da kreira grumer smenu");
    }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((VlSm)param);
    }
    
}
