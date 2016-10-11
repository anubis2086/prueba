/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dandres
 */
@Entity
@Table(name = "menu_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuUsuarios.findAll", query = "SELECT m FROM MenuUsuarios m"),
    @NamedQuery(name = "MenuUsuarios.findByIdMenu", query = "SELECT m FROM MenuUsuarios m WHERE m.idMenu = :idMenu"),
    @NamedQuery(name = "MenuUsuarios.findByNombre", query = "SELECT m FROM MenuUsuarios m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MenuUsuarios.findByUrl", query = "SELECT m FROM MenuUsuarios m WHERE m.url = :url"),
    @NamedQuery(name = "MenuUsuarios.findByEstado", query = "SELECT m FROM MenuUsuarios m WHERE m.estado = :estado")})
public class MenuUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_menu")
    private Integer idMenu;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "url")
    private String url;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol idRol;

    public MenuUsuarios() {
    }

    public MenuUsuarios(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuUsuarios)) {
            return false;
        }
        MenuUsuarios other = (MenuUsuarios) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistemaocupacional.entities.MenuUsuarios[ idMenu=" + idMenu + " ]";
    }
    
}
