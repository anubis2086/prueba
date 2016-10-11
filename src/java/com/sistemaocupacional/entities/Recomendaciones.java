/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "recomendaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recomendaciones.findAll", query = "SELECT r FROM Recomendaciones r"),
    @NamedQuery(name = "Recomendaciones.findByIdRecomendacion", query = "SELECT r FROM Recomendaciones r WHERE r.idRecomendacion = :idRecomendacion"),
    @NamedQuery(name = "Recomendaciones.findByGenero", query = "SELECT r FROM Recomendaciones r WHERE r.genero = :genero"),
    @NamedQuery(name = "Recomendaciones.findByDescripcion", query = "SELECT r FROM Recomendaciones r WHERE r.descripcion = :descripcion")})
public class Recomendaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recomendacion")
    private Integer idRecomendacion;
    @Column(name = "genero")
    private Character genero;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecomendacion")
    private List<Emo> emoList;

    public Recomendaciones() {
    }

    public Recomendaciones(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public Integer getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Emo> getEmoList() {
        return emoList;
    }

    public void setEmoList(List<Emo> emoList) {
        this.emoList = emoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecomendacion != null ? idRecomendacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recomendaciones)) {
            return false;
        }
        Recomendaciones other = (Recomendaciones) object;
        if ((this.idRecomendacion == null && other.idRecomendacion != null) || (this.idRecomendacion != null && !this.idRecomendacion.equals(other.idRecomendacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
