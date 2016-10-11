package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.Emo;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.entities.CieDiez;
import com.sistemaocupacional.entities.Recomendaciones;
import com.sistemaocupacional.sessions.CieDiezFacade;
import com.sistemaocupacional.sessions.EmoFacade;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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

@Named("emoController")
@SessionScoped
public class EmoController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.EmoFacade ejbFacade;
    @EJB
    private CieDiezFacade cieFacade;
    private List<Emo> items = null;
    private List<CieDiez> cieList;
    private CieDiez cieActual;
    private Emo selected;
    private StreamedContent imagen;

    public EmoController() {
    }

    public CieDiezFacade getCieFacade() {
        return cieFacade;
    }

    public void setCieFacade(CieDiezFacade cieFacade) {
        this.cieFacade = cieFacade;
    }

    public StreamedContent getImagen() {
        InputStream input = new ByteArrayInputStream(selected.getIdPaciente().getFoto());

        imagen = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");

        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public List<CieDiez> getCieList() {
        return cieList;
    }

    public void setCieList(List<CieDiez> cieList) {
        this.cieList = cieList;
    }

    public CieDiez getCieActual() {
        return cieActual;
    }

    public void setCieActual(CieDiez cieActual) {
        this.cieActual = cieActual;
    }

    public Emo getSelected() {
        return selected;
    }

    public void setSelected(Emo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmoFacade getFacade() {
        return ejbFacade;
    }

    public Emo prepareCreate() {
        selected = new Emo();
        initializeEmbeddableKey();
        return selected;
    }

    public String prepareView() {
        return "/emo/View";
    }

    public String prepareEdit() {
        return "/emo/Edit";
    }

    public void llamarReporte() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/Sistema_Ocupacional/HistoriaClinica?numero=" + selected.getIdEmo());

        /*  if (selected.getIdPaciente().getGenero().equals('M')
         && selected.getTipoPerfil().equals("TRABAJO EN ALTURAS")) {

         FacesContext.getCurrentInstance().getExternalContext()
         .redirect("/Sistema_Ocupacional/HistoriaClinica?numero=" + selected.getIdEmo());

         } else if(selected.getIdPaciente().getGenero().equals('H') && 
         !selected.getTipoPerfil().equals("TRABAJO EN ALTURAS")){
            
         FacesContext.getCurrentInstance().getExternalContext()
         .redirect("/Sistema_Ocupacional/HistoriaClinica2?numero=" + selected.getIdEmo());
            
         }*/
    }

    public void llamarReporteExamen() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/Sistema_Ocupacional/ReporteDePaciente?numero=" + selected.getIdEmo());
    }

    public boolean isGenero() {
        boolean genero = false;
        Character letra;
        letra = 'F';
        if (Objects.equals(letra, selected.getIdPaciente().getGenero())) {
            genero = true;
        }

        return genero;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("EmoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public String update() {

        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("EmoUpdated"));
        return "/emo/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("EmoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Emo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<CieDiez> getListCieDiezAutoComplete(String query) {
        try {
            return getCieFacade().findByDescripcion(query);
        } catch (Exception ex) {

            return null;
        }
    }

    public void recargarListaEmo() {

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

    public Emo getEmo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Emo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Emo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Emo.class)
    public static class EmoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmoController controller = (EmoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "emoController");
            return controller.getEmo(getKey(value));
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
            if (object instanceof Emo) {
                Emo o = (Emo) object;
                return getStringKey(o.getIdEmo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Emo.class.getName()});
                return null;
            }
        }

    }

}
