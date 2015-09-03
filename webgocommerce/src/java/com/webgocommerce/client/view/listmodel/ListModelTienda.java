/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.listmodel;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.webgocommerce.client.beanproxy.TiendaProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class ListModelTienda extends ListBox {

    private TiendaProxy selectedItem;
    private ArrayList<TiendaProxy> data = new ArrayList();

    public ListModelTienda() {
    }

    public ListModelTienda(ArrayList<TiendaProxy> datos) {
        data = datos;
    }

    public void setData(List<TiendaProxy> datos) {
        if(data.isEmpty()){
        data.addAll(datos);        
        }else{
            clear();
            data.addAll(datos);            
        }
        for (int i = 0; i < data.size(); i++) {
            insertItem(data.get(i).getDescripcion(), data.get(i).getId().toString(), i);
        }
        if(!data.isEmpty()){
            selectedItem=data.get(0);
            setSelectedIndex(0);
        }
    }

    @Override
    public int getItemCount() {
        try {
            return data.size();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public void clear() {
        data.clear();
        getSelectElement().clear();
    }

    private SelectElement getSelectElement() {
        return getElement().cast();
    }

    public TiendaProxy getElement(int index) throws ArrayIndexOutOfBoundsException {
        try {
            return data.get(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public TiendaProxy getElement(String codigo) {        
        Iterator<TiendaProxy> i = data.iterator();
        while (i.hasNext()) {
            TiendaProxy bean = (TiendaProxy) i.next();
            if (bean.getId().toString().equals(codigo)) {                
                return bean;
            }
        }
        return null;
    }

    @Override
    public void setSelectedIndex(int index) {        
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
    }
        
    public void setSelectedItem(String item) {  
        if(item!=null){
        int index=-1;
        for(int i=0;i<data.size();i++){
            TiendaProxy bean = data.get(i);
            if (bean.getDescripcion().toString().equals(item)) {                
                index=i;
            }
        }
        if (index >= 0) {
        getSelectElement().setSelectedIndex(index);
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        }
        }
    }
    
    @Override
    public int getSelectedIndex() {        
    return getSelectElement().getSelectedIndex();
    }
    
    public TiendaProxy getSelectedItem() {
        selectedItem=data.get(getSelectElement().getSelectedIndex());
        return selectedItem;
    }

}
