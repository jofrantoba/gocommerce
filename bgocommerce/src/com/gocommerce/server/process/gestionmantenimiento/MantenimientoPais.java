/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionmantenimiento;

import com.gocommerce.server.model.beans.Pais;
import com.gocommerce.server.model.dao.PMF;
import com.gocommerce.server.model.logic.LogicPais;
import com.gocommerce.server.util.StringHex;
import com.gocommerce.server.util.UnknownException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.datastore.JDOConnection;

/**
 *
 * @author SISTEMAS
 */
public class MantenimientoPais {
    public static List<Pais> listar(String clavePublica) throws UnknownException{
        PersistenceManager pm = null;
        Connection cnx=null;
        try{
            String keypublic=StringHex.convertHexToString(clavePublica);
            pm = PMF.getClassPMF().getPMF(keypublic).getPersistenceManager();
            if(!pm.isClosed()){
                LogicPais logic = new LogicPais(pm);  
                JDOConnection cnxJDO = pm.getDataStoreConnection();                
                cnx =(Connection)cnxJDO.getNativeConnection();                
                List<Pais> lista=(List)logic.getListarBean(cnx);  
                cnx.close();
                return lista;
            }else{
                throw new UnknownException("Conexión expiró/nvuelva a iniciar sesión");
            }                                   
        }catch (UnknownException ex) {
            throw new UnknownException(ex.getMessage());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage());
        } finally {
            if(cnx!=null){
                try {
                    if(!cnx.isClosed()){
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MantenimientoPais.class.getName()).log(Level.SEVERE, null, ex);
                    throw new UnknownException(ex.getMessage());
                }
            }
            if (!pm.isClosed()) {                
                pm.close();
            }
        }
    }
}