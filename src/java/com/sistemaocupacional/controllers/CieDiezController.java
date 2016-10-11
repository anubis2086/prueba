package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.CieDiez;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.sessions.CieDiezFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("cieDiezController")
@SessionScoped
public class CieDiezController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.CieDiezFacade ejbFacade;
    private List<CieDiez> items = null;
    private CieDiez selected;

    public CieDiezController() {
    }

    public CieDiez getSelected() {
        return selected;
    }

    public void setSelected(CieDiez selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CieDiezFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new CieDiez();
        initializeEmbeddableKey();
        return "/cieDiez/Create";
    }
    
     public String prepareEdit() {
        return "/cieDiez/Edit";
    }

    public String prepareView() {
        return "/cieDiez/View";
    }

    public String create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("CieDiezCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/cieDiez/List";
    }

    public String update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("CieDiezUpdated"));
        return "/cieDiez/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("CieDiezDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CieDiez> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public CieDiez getCieDiez(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CieDiez> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CieDiez> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CieDiez.class, value = "cieDiezConverter")
    public static class CieDiezControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CieDiezController controller = (CieDiezController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cieDiezController");
            return controller.getCieDiez(getKey(value));/*falta*/
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
            if (object instanceof CieDiez) {
                CieDiez o = (CieDiez) object;
                return getStringKey(o.getIdCie());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CieDiez.class.getName()});
                return null;
            }
        }

    }

}
