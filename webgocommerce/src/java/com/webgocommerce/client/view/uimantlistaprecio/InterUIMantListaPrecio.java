/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimantlistaprecio;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIMantListaPrecio {
    void loadFields();
    void cleanForm();
    void goToUIListaPrecio();
    void loadListBox();
    void processDesactivar();
    boolean isValidData();
}