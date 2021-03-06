package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.AccidentesLaborales;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.sessions.AccidentesLaboralesFacade;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("accidentesLaboralesController")
@SessionScoped
public class AccidentesLaboralesController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.AccidentesLaboralesFacade ejbFacade;
    private List<AccidentesLaborales> items = null;
    private AccidentesLaborales selected;
    private StreamedContent imagen;

    public AccidentesLaboralesController() {
    }

    public StreamedContent getImagen() {
        InputStream input = new ByteArrayInputStream(selected.getIdEmo().getIdPaciente().getFoto());

        imagen = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");

        return imagen;
    }

    public AccidentesLaborales getSelected() {
        return selected;
    }

    public void setSelected(AccidentesLaborales selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AccidentesLaboralesFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new AccidentesLaborales();
        initializeEmbeddableKey();
        return "/accidentesLaborales/Create";
    }

    public String prepareEdit() {
        return "/accidentesLaborales/Edit";
    }

    public String prepareView() {
        return "/accidentesLaborales/View";
    }

    public String create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("AccidentesLaboralesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/accidentesLaborales/List";
    }

    public String update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("AccidentesLaboralesUpdated"));
        return "/accidentesLaborales/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("AccidentesLaboralesDeleted"));

        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AccidentesLaborales> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void recargarListaAccidentes() {
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

    public AccidentesLaborales getAccidentesLaborales(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AccidentesLaborales> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AccidentesLaborales> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AccidentesLaborales.class)
    public static class AccidentesLaboralesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AccidentesLaboralesController controller = (AccidentesLaboralesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "accidentesLaboralesController");
            return controller.getAccidentesLaborales(getKey(value));
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
            if (object instanceof AccidentesLaborales) {
                AccidentesLaborales o = (AccidentesLaborales) object;
                return getStringKey(o.getIdAccidenteLaboral());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AccidentesLaborales.class.getName()});
                return null;
            }
        }

    }

}
