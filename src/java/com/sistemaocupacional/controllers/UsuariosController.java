package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.Usuarios;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.sessions.UsuariosFacade;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private Usuarios selected;
    private String password;
    private String passwordVieja;
    private UploadedFile fileFirma;
     private StreamedContent imagenFirma;

    public UsuariosController() {
    }

    public UploadedFile getFileFirma() {
        return fileFirma;
    }

    public void setFileFirma(UploadedFile fileFirma) {
        this.fileFirma = fileFirma;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVieja() {
        return passwordVieja;
    }

    public void setPasswordVieja(String passwordVieja) {
        this.passwordVieja = passwordVieja;
    }

    public Usuarios getSelected() {
        return selected;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }
    
        public StreamedContent getImagenFirma() {
        InputStream input = new ByteArrayInputStream(selected.getFirma());

        imagenFirma = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");
        return imagenFirma;
    }

    public void setImagenFirma(StreamedContent imagenFirma) {
        this.imagenFirma = imagenFirma;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuariosFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new Usuarios();
        initializeEmbeddableKey();
        return "/usuarios/Create";
    }

    public String prepareEdit() {
        return "/usuarios/Edit";
    }

    public String create() throws IOException {
        InputStream firma = fileFirma.getInputstream();
        byte[] bufferFirma = new byte[(int) fileFirma.getSize()];
        int leer = firma.read(bufferFirma);
        selected.setFirma(bufferFirma);
        selected.setPassword(DigestUtils.sha1Hex(password));
        selected.setFechaCreacion(new Date());

        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("UsuariosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/usuarios/List";
    }

    public String update() {
        selected.setPassword(DigestUtils.sha1Hex(password));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("UsuariosUpdated"));
        return "/usuarios/List";
    }

    public void updatePassword() {

        Usuarios usu = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Usuarios us = ejbFacade.findByPassword(DigestUtils.sha1Hex(passwordVieja), usu.getIdUsuario());

        selected = usu;
        if (us != null) {

            selected.setPassword(DigestUtils.sha1Hex(password));
            getFacade().edit(selected);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Cambio de contraseña exitoso"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "La vieja contraseña no coincide"));

        }

    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("UsuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuarios> getItems() {
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

    public Usuarios getUsuarios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuarios.class)
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
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
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
                return null;
            }
        }

    }

}
