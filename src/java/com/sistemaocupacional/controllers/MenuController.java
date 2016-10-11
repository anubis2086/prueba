/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.MenuUsuarios;
import com.sistemaocupacional.entities.Rol;
import com.sistemaocupacional.entities.Usuarios;
import com.sistemaocupacional.sessions.MenuUsuariosFacade;
import com.sistemaocupacional.sessions.RolFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author dandres
 */
@Named
@SessionScoped

public class MenuController implements Serializable {

    @EJB
    private MenuUsuariosFacade EJBMenu;
    @EJB
    private RolFacade EJBRol;
    private List<MenuUsuarios> lista;
    private List<Rol> listaRol;
    private MenuModel model;

    @PostConstruct
    public void init() {

        this.listarMenus();
        this.listarRoles();
        model = new DefaultMenuModel();
        this.establecerMenu();

    }

    public MenuUsuariosFacade getEJBMenu() {
        return EJBMenu;
    }

    public void setEJBMenu(MenuUsuariosFacade EJBMenu) {
        this.EJBMenu = EJBMenu;
    }

    public RolFacade getEJBRol() {
        return EJBRol;
    }

    public void setEJBRol(RolFacade EJBRol) {
        this.EJBRol = EJBRol;
    }

    public void listarMenus() {
        try {
            lista = EJBMenu.findAll();
        } catch (Exception e) {
            //mensaje jsf
        }
    }

    public void listarRoles() {
        try {
            listaRol = EJBRol.findAll();
        } catch (Exception e) {
            //mensaje jsf
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void establecerMenu() {
        Usuarios us;
        us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        for (MenuUsuarios m : lista) {
            if (m.getIdRol().equals(us.getIdRol())) {
                if (m.getEstado() == true) {

                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    item.setUrl(m.getUrl());
                    model.addElement(item);

                }

            }

        }
    }

    /* public void VerificarPermisos(Event event) {
     try {
     Usuarios us;

     us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
     if (us != null && us.getIdRol().getIdRol() == 1) {
     FacesContext.getCurrentInstance().getExternalContext().redirect("./../welcome.ocupacional");
     }
     } catch (IOException e) {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
     }
     }*/
}
