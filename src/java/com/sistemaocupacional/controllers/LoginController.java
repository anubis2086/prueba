/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.controllers;

import com.sistemaocupacional.entities.Emo;
import com.sistemaocupacional.entities.Pacientes;
import com.sistemaocupacional.entities.Usuarios;
import com.sistemaocupacional.sessions.EmoFacade;
import com.sistemaocupacional.sessions.PacientesFacade;
import com.sistemaocupacional.sessions.UsuariosFacade;
import java.awt.Event;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Maicol
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private UsuariosFacade EJBusuarios;
    @EJB
    private EmoFacade emoFacade;
    private List<Emo> listaEmo;
    private Emo emoActual;
    @EJB
    private PacientesFacade pacienteFacade;
    private List<Pacientes> pacienteList;
    private Pacientes pacienteActual;

    private Usuarios usuarios;
    private String numeroDoc;
    private String password;
    private List<Usuarios> listaUsuarios;
    private String logueado;
    private String message;
    private String consulta;
    private String annio;
    FacesContext context = FacesContext.getCurrentInstance();

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {

    }

    public String getAnnio() {
       
        Calendar c2 = new GregorianCalendar();
        annio = Integer.toString(c2.get(Calendar.YEAR));
        
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public EmoFacade getEmoFacade() {
        return emoFacade;
    }

    public void setEmoFacade(EmoFacade emoFacade) {
        this.emoFacade = emoFacade;
    }

    public List<Emo> getListaEmo() {

        return listaEmo;

    }

    public void setListaEmo(List<Emo> listaEmo) {
        this.listaEmo = listaEmo;
    }

    public void consultar() {
        pacienteActual = new Pacientes();
        pacienteActual = getPacienteFacade().finByNumeroDocumento(consulta);
        emoActual = new Emo();

        listaEmo = getEmoFacade().findByPaciente(pacienteActual);

    }

    public Emo getEmoActual() {
        return emoActual;
    }

    public void setEmoActual(Emo emoActual) {
        this.emoActual = emoActual;
    }

    public String prepareReporte() {
        return "certificados";

    }

    public PacientesFacade getPacienteFacade() {
        return pacienteFacade;
    }

    public void setPacienteFacade(PacientesFacade pacienteFacade) {
        this.pacienteFacade = pacienteFacade;
    }

    public List<Pacientes> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Pacientes> pacienteList) {
        this.pacienteList = pacienteList;
    }

    public Pacientes getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(Pacientes pacienteActual) {
        this.pacienteActual = pacienteActual;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public UsuariosFacade getEJBUsuarios() {
        return EJBusuarios;
    }

    public void setEJBusuarios(UsuariosFacade EJBusuarios) {
        this.EJBusuarios = EJBusuarios;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha1Hex(password);
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public String getLogueado() {
        return logueado;
    }

    public void setLogueado(String logueado) {
        this.logueado = logueado;
    }

    public FacesContext getContext() {
        return context;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void dialogoContraseña() {

        RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('CambioContraseñaDlg').show();");
    }

    public String iniciarSesion() {
        String redireccion = null;
        try {
            boolean estado;
            estado = true;

            Usuarios us = EJBusuarios.findBylogin(numeroDoc, password, estado);

            if (us != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Bienvenid@ ", us.toString()));

                context.getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "/pacientes/List";


                /*  }else if(us.getEstado() == false){
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                 "Aviso", "usuario inactivo"));*/
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso", "El número de documento o la contraseña son incorretas"));
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }

        return redireccion;

    }

    public void VerificarSesion(Event event) {
        try {

            Usuarios us = (Usuarios) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                context.getExternalContext().redirect("./../index.ocupacional");

            }
        } catch (IOException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public boolean isAdmin() {
        Usuarios us;
        boolean rol = false;
        us = (Usuarios) context.getExternalContext().getSessionMap().get("usuario");
        if (us != null && us.getIdRol().getIdRol() == 1) {
            rol = true;
        }
        return rol;

    }

    public boolean isUser() {
        Usuarios us;
        boolean rol = false;
        us = (Usuarios) context.getExternalContext().getSessionMap().get("usuario");
        if (us != null && us.getIdRol().getIdRol() == 2 || us != null && us.getIdRol().getIdRol() == 1) {
            rol = true;
        }
        return rol;

    }

    public boolean isInvited() {
        Usuarios us;
        boolean rol = false;
        us = (Usuarios) context.getExternalContext().getSessionMap().get("usuario");
        if (us != null && us.getIdRol().getIdRol() == 3) {
            rol = true;
        }
        return rol;

    }

    public void cerrarSesion() {
        context.getExternalContext().invalidateSession();
    }
}
