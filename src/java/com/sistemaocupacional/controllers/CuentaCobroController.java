package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.CuentaCobro;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.entities.Usuarios;
import com.sistemaocupacional.sessions.CuentaCobroFacade;
import com.sistemaocupacional.sessions.EmpresasFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("cuentaCobroController")
@SessionScoped
public class CuentaCobroController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.CuentaCobroFacade ejbFacade;
    private List<CuentaCobro> items = null;
    private CuentaCobro selected;
    @EJB
    private EmpresasFacade empresasFacade;
    
    private int total = 0; 
    private int cantidad ;
    private int valorUnitario ;
    private Date fecha;
    private Usuarios usuarioActual;
    
    
    public CuentaCobroController() {
    }

    public EmpresasFacade getEmpresasFacade() {
        return empresasFacade;
    }

    public void setEmpresasFacade(EmpresasFacade empresasFacade) {
        this.empresasFacade = empresasFacade;
    }

 
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

     public int getTotal() {
        total = cantidad * valorUnitario;
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public CuentaCobroFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CuentaCobroFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Usuarios getUsuarioActual() {
        usuarioActual = new Usuarios();
        Usuarios us;
        us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usuarioActual = us;
        return usuarioActual;
    }

    public void setUsuarioActual(Usuarios usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public CuentaCobro getSelected() {
        return selected;
    } 

    public void setSelected(CuentaCobro selected) {
        this.selected = selected;
    }
    public Date getFecha() {
        
        fecha = new Date();
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CuentaCobroFacade getFacade() {
        return ejbFacade;
    }
    
   
  /*  
    public int totalCuenta(){    
      
       cantidad = 0;
       valorUnitario = 0;
       return total;
       
        
    }*/
    
   

    public String prepareCreate() {
        selected = new CuentaCobro();
        initializeEmbeddableKey();
        return "/cuentaCobro/Create";
    }
    
    public String prepareEdit(){
      /*  setCantidad(selected.getCantidad());
        setValorUnitario(selected.getValorUnitario());
        setTotal(selected.getValorTotal());*/
       
   
                     
        return "/cuentaCobro/Edit";
    }
    
    public String prepareView(){
        return "/cuentaCobro/View";
    }
    
      public void llamarReporte() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/Sistema_Ocupacional/CuentaDeCobro?numeroCuenta=" + selected.getIdCuenta());
    }

    public String create() {
        
        Usuarios us;
        us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        selected.setIdUsuario(us);
        selected.setCantidad(cantidad);
        selected.setValorUnitario(valorUnitario);
        selected.setValorTotal(total);
        selected.setFechaCuenta(new Date());
        iniciar();
      
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("CuentaCobroUpdated"));
        total = 0;
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/cuentaCobro/List";
    }
    
    public void iniciar(){
        cantidad = 0;
        valorUnitario = 0;
        total = 0;
    }
    
     public void createPrueba() {
       getFacade().create(selected);
            selected = new CuentaCobro();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso ", "Paciente ha sido creado"));
    }

    public String update() {
        selected.setCantidad(cantidad);
        selected.setValorUnitario(valorUnitario);
        selected.setValorTotal(total);
        iniciar();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("CuentaCobroUpdated"));
        return "/cuentaCobro/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("CuentaCobroDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void validarCuenta(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getFacade().finByNumeroCuenta((String) value) != null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Número repetido", "El número de cuenta ya existe en la base de datos"));
        } else {
            selected.setNumeroCuenta((String) value);
        }
    }

    public List<CuentaCobro> getItems() {
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
    
    
     public List<Empresas> getListClienteAutoComplete(String query) {
        try {
            return getEmpresasFacade().findByNombreEmpresa(query);
        } catch (Exception ex) {

            return null;
        }
    }

    public CuentaCobro getCuentaCobro(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CuentaCobro> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CuentaCobro> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CuentaCobro.class, value = "cuentaCobroConverter")
    public static class CieDiezControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CieDiezController controller = (CieDiezController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuentaCobroController");
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
            if (object instanceof CuentaCobro) {
                CuentaCobro o = (CuentaCobro) object;
                return getStringKey(o.getIdCuenta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CuentaCobro.class.getName()});
                return null;
            }
        }

    }

}
