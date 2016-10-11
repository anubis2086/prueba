package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.entities.Contratos;
import com.sistemaocupacional.entities.CuentaCobro;
import com.sistemaocupacional.entities.Pacientes;
import com.sistemaocupacional.sessions.ContratosFacade;
import com.sistemaocupacional.sessions.CuentaCobroFacade;
import com.sistemaocupacional.sessions.EmpresasFacade;
import com.sistemaocupacional.sessions.PacientesFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javax.faces.validator.ValidatorException;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.UploadedFile;

@Named("empresasController")
@SessionScoped
public class EmpresasController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.EmpresasFacade ejbFacade;
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB

    private CuentaCobroFacade cuentaFacade;
    @EJB
    private ContratosFacade contratosFacade;
    private List<Empresas> items = null;
    private Empresas selected;
    private List<Pacientes> listPacientes = null;
    private Pacientes pacienteActual;
    private Contratos contratoActual;
    private CuentaCobro cuentaActual;
    private String filename;
    private UploadedFile file;
    private byte[] data;

    public EmpresasController() {
    }

    public CuentaCobroFacade getCuentaFacade() {
        return cuentaFacade;
    }

    public void setCuentaFacade(CuentaCobroFacade cuentaFacade) {
        this.cuentaFacade = cuentaFacade;
    }

    public CuentaCobro getCuentaActual() {
        return cuentaActual;
    }

    public void setCuentaActual(CuentaCobro cuentaActual) {
        this.cuentaActual = cuentaActual;
    }
    
    

    public ContratosFacade getContratosFacade() {
        return contratosFacade;
    }

    public void setContratosFacade(ContratosFacade contratosFacade) {
        this.contratosFacade = contratosFacade;
    }

    public Contratos getContratoActual() {
        return contratoActual;
    }

    public void setContratoActual(Contratos contratoActual) {
        this.contratoActual = contratoActual;
    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Empresas getSelected() {
        return selected;
    }

    public void setSelected(Empresas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmpresasFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new Empresas();
        initializeEmbeddableKey();
        return "/empresas/Create";
    }

    public String prepareEdit() {
        return "/empresas/Edit";
    }

    public String prepareView() {
        return "/empresas/View";
    }

    public String prepareCreatePaciente() {
        pacienteActual = new Pacientes();
        recargarListaPacientes();
        return "/pacientes/Create2.ocupacional";
    }

    public String prepareCreateContratros() {
        contratoActual = new Contratos();

        return "/contratos/Create2.ocupacional";
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        data = captureEvent.getData();

    }

    public void validarDocumento(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getPacientesFacade().finByNumeroDocumento((String) value) != null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Documento repetido", "El documento ya existe en la base de datos"));
        } else {
            pacienteActual.setNumDoc((String) value);
        }
    }

    public void validarNumeroContrato(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getContratosFacade().finByNumeroContrato((String) value) != null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Número repetido", "El contrato ya existe en la base de datos"));
        } else {
            contratoActual.setNumCont((String) value);
        }
    }

    public String addPaciente() {
        try {
            InputStream fi = file.getInputstream();
            byte[] buffer = new byte[(int) file.getSize()];
            int readers = fi.read(buffer);
            pacienteActual.setImgFirma(buffer);
            pacienteActual.setFoto(data);
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd '/' MM '/' yyyy HH:mm:ss", new Locale("es_ES"));
        
            pacienteActual.setFechaCreacion(formateador.format(ahora));
            pacienteActual.setIdClem(selected);

            getPacientesFacade().create(pacienteActual);
            pacienteActual = new Pacientes();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso ", "Paciente ha sido creado"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));
        }

        return "/empresas/List";
    }

    public String addContrato() {
        try {

            contratoActual.setIdClem(selected);

            getContratosFacade().create(contratoActual);
            contratoActual = new Contratos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso ", "Contrato ha sido creado"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));
        }

        return "/empresas/List";
    }

    public void recargarListaPacientes() {
        listPacientes = null;
    }

    public List<Pacientes> getListPacientes() {
        if (listPacientes == null) {
            listPacientes = getPacientesFacade().findByEmpresa(selected);
        }
        return listPacientes;
    }

    public List<Pacientes> getListPacientes(Empresas empresa) {
        return getPacientesFacade().findByEmpresa(empresa);
    }

    public PacientesFacade getPacientesFacade() {
        return pacientesFacade;
    }

    public Pacientes getPacienteActual() {
        return pacienteActual;
    }

    public String create() {
        selected.setFecCreClem(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("EmpresasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/empresas/List";
    }

    public String update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("EmpresasUpdated"));
        return "/empresas/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("EmpresasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Empresas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
     public void validarNit(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getFacade().finByNumeroNit((String) value) != null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Número repetido", "El número ya existe en la base de datos"));
        } else {
            selected.setNitClem((String) value);
        }
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

    public Empresas getEmpresas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Empresas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Empresas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Empresas.class, value = "empresasConverter")
    public static class EmpresasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresasController controller = (EmpresasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresasController");
            return controller.getEmpresas(getKey(value));
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
            if (object instanceof Empresas) {
                Empresas o = (Empresas) object;
                return getStringKey(o.getIdClem());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Empresas.class.getName()});
                return null;
            }
        }

    }

}
