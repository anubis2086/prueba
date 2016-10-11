package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.AntecedentesOcupacionales;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.sessions.AntecedentesOcupacionalesFacade;

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

@Named("antecedentesOcupacionalesController")
@SessionScoped
public class AntecedentesOcupacionalesController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.AntecedentesOcupacionalesFacade ejbFacade;
    private List<AntecedentesOcupacionales> items = null;
    private AntecedentesOcupacionales selected;

    public AntecedentesOcupacionalesController() {
    }

    public AntecedentesOcupacionales getSelected() {
        return selected;
    }

    public void setSelected(AntecedentesOcupacionales selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AntecedentesOcupacionalesFacade getFacade() {
        return ejbFacade;
    }

    public AntecedentesOcupacionales prepareCreate() {
        selected = new AntecedentesOcupacionales();
        initializeEmbeddableKey();
        return selected;
    }
    
    public String prepareView(){
        return "/antecedentesOcupacionales/View";
    }
    
       public String prepareEdit(){
        return "/antecedentesOcupacionales/Edit";
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("AntecedentesOcupacionalesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public String update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("AntecedentesOcupacionalesUpdated"));
        return "/antecedentesOcupacionales/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("AntecedentesOcupacionalesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AntecedentesOcupacionales> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void recargarListaAntecedentes() {
        items = null;
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

    public AntecedentesOcupacionales getAntecedentesOcupacionales(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AntecedentesOcupacionales> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AntecedentesOcupacionales> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AntecedentesOcupacionales.class)
    public static class AntecedentesOcupacionalesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AntecedentesOcupacionalesController controller = (AntecedentesOcupacionalesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "antecedentesOcupacionalesController");
            return controller.getAntecedentesOcupacionales(getKey(value));
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
            if (object instanceof AntecedentesOcupacionales) {
                AntecedentesOcupacionales o = (AntecedentesOcupacionales) object;
                return getStringKey(o.getIdAntecedenteOcupacional());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AntecedentesOcupacionales.class.getName()});
                return null;
            }
        }

    }

}
