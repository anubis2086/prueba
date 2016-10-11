/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.controllers;

import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.entities.MenuUsuarios;
import com.sistemaocupacional.sessions.MenuUsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dandres
 */
@Named(value = "menuUsuariosController")
@SessionScoped
public class MenuUsuariosController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.MenuUsuariosFacade ejbFacade;
    private List<MenuUsuarios> items = null;
    private MenuUsuarios selected;

    public MenuUsuariosController() {
    }

    public MenuUsuarios getSelected() {
        return selected;
    }

    public void setSelected(MenuUsuarios selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MenuUsuariosFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new MenuUsuarios();
        initializeEmbeddableKey();
        return "/menu/Create";
    }
    
     public String prepareEdit() {
        return "/menu/Edit";
    }

    public String prepareView() {
        return "/menu/View";
    }

    public String create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("MenuCreate"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/menu/List";
    }

    public String update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("MenuUpdated"));
        return "/menu/List";
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("MenuDestroy"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MenuUsuarios> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MenuUsuarios getMenuUsuarios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MenuUsuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MenuUsuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MenuUsuarios.class)
    public static class MenuUsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MenuUsuariosController controller = (MenuUsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "menuUsuariosController");
            return controller.getMenuUsuarios(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MenuUsuarios) {
                MenuUsuarios o = (MenuUsuarios) object;
                return getStringKey(o.getIdMenu());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MenuUsuarios.class.getName()});
                return null;
            }
        }

    }

}
