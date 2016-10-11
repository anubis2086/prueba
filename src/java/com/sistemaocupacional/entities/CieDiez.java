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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "cie_diez")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CieDiez.findAll", query = "SELECT c FROM CieDiez c"),
    @NamedQuery(name = "CieDiez.findByIdCie", query = "SELECT c FROM CieDiez c WHERE c.idCie = :idCie"),
    @NamedQuery(name = "CieDiez.findByCodigoCie", query = "SELECT c FROM CieDiez c WHERE c.codigoCie = :codigoCie"),
    @NamedQuery(name = "CieDiez.findByDescripcion", query = "SELECT c FROM CieDiez c WHERE c.descripcion LIKE :descripcion")})
public class CieDiez implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cie")
    private Integer idCie;
    @Size(max = 100)
    @Column(name = "codigo_cie")
    private String codigoCie;
    @Size(max = 2000)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cieDiezIdCie")
    private List<Emo> emoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cieDiezIdCie1")
    private List<Emo> emoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cieDiezIdCie2")
    private List<Emo> emoList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cieDiezIdCie3")
    private List<Emo> emoList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cieDiezIdCie4")
    private List<Emo> emoList4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cieDiezIdCie5")
    private List<Emo> emoList5;

    public CieDiez() {
    }

    public CieDiez(Integer idCie) {
        this.idCie = idCie;
    }

    public Integer getIdCie() {
        return idCie;
    }

    public void setIdCie(Integer idCie) {
        this.idCie = idCie;
    }

    public String getCodigoCie() {
        return codigoCie;
    }

    public void setCodigoCie(String codigoCie) {
        this.codigoCie = codigoCie;
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

    @XmlTransient
    public List<Emo> getEmoList1() {
        return emoList1; 
    }

    public void setEmoList1(List<Emo> emoList1) {
        this.emoList1 = emoList1;
    }

    @XmlTransient
    public List<Emo> getEmoList2() {
        return emoList2;
    }

    public void setEmoList2(List<Emo> emoList2) {
        this.emoList2 = emoList2;
    }

    @XmlTransient
    public List<Emo> getEmoList3() {
        return emoList3;
    }

    public void setEmoList3(List<Emo> emoList3) {
        this.emoList3 = emoList3;
    }

    @XmlTransient
    public List<Emo> getEmoList4() {
        return emoList4;
    }

    public void setEmoList4(List<Emo> emoList4) {
        this.emoList4 = emoList4;
    }

    @XmlTransient
    public List<Emo> getEmoList5() {
        return emoList5;
    }

    public void setEmoList5(List<Emo> emoList5) {
        this.emoList5 = emoList5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCie != null ? idCie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CieDiez)) {
            return false;
        }
        CieDiez other = (CieDiez) object;
        if ((this.idCie == null && other.idCie != null) || (this.idCie != null && !this.idCie.equals(other.idCie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
