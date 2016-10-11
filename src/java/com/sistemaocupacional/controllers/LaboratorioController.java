package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.Laboratorio;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.sessions.LaboratorioFacade;
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

@Named("laboratorioController")
@SessionScoped
public class LaboratorioController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.LaboratorioFacade ejbFacade;
    private List<Laboratorio> items = null;
    private Laboratorio selected;
    private StreamedContent imagen;

    public LaboratorioController() {
    }

    public Laboratorio getSelected() {
        return selected;
    }

    public void setSelected(Laboratorio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LaboratorioFacade getFacade() {
        return ejbFacade;
    }
    
    
    public StreamedContent getImagen() {
        InputStream input = new ByteArrayInputStream(selected.getIdPaciente().getFoto());

        imagen = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");

        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public Laboratorio prepareCreate() {
        selected = new Laboratorio();
        initializeEmbeddableKey();
        return selected;
    }

    public String prepareEdit() {
        return "/laboratorio/Edit";
    }

    public String prepareView() {
        return "/laboratorio/View";
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("LaboratorioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public String update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("LaboratorioUpdated"));
        return "/laboratorio/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("LaboratorioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void recargarListaLaboratorio() {

        items = null;
    }

    public List<Laboratorio> getItems() {
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

    public Laboratorio getLaboratorio(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Laboratorio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Laboratorio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Laboratorio.class)
    public static class LaboratorioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LaboratorioController controller = (LaboratorioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "laboratorioController");
            return controller.getLaboratorio(getKey(value));
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
            if (object instanceof Laboratorio) {
                Laboratorio o = (Laboratorio) object;
                return getStringKey(o.getIdLaboratorio());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Laboratorio.class.getName()});
                return null;
            }
        }

    }

}
