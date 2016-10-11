/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author D4MN4710N
 */
@Entity
@Table(name = "accidentes_laborales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccidentesLaborales.findAll", query = "SELECT a FROM AccidentesLaborales a"),
    @NamedQuery(name = "AccidentesLaborales.findByIdAccidenteLaboral", query = "SELECT a FROM AccidentesLaborales a WHERE a.idAccidenteLaboral = :idAccidenteLaboral"),
    @NamedQuery(name = "AccidentesLaborales.findByAnio", query = "SELECT a FROM AccidentesLaborales a WHERE a.anio = :anio"),
    @NamedQuery(name = "AccidentesLaborales.findByDescIni", query = "SELECT a FROM AccidentesLaborales a WHERE a.descIni = :descIni"),
    @NamedQuery(name = "AccidentesLaborales.findByAccTrab", query = "SELECT a FROM AccidentesLaborales a WHERE a.accTrab = :accTrab"),
    @NamedQuery(name = "AccidentesLaborales.findByEnferProfe", query = "SELECT a FROM AccidentesLaborales a WHERE a.enferProfe = :enferProfe"),
    @NamedQuery(name = "AccidentesLaborales.findByEmpAcc", query = "SELECT a FROM AccidentesLaborales a WHERE a.empAcc = :empAcc"),
    @NamedQuery(name = "AccidentesLaborales.findBySecuelas", query = "SELECT a FROM AccidentesLaborales a WHERE a.secuelas = :secuelas"),
    @NamedQuery(name = "AccidentesLaborales.findByArl", query = "SELECT a FROM AccidentesLaborales a WHERE a.arl = :arl")})
public class AccidentesLaborales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_accidente_laboral")
    private Integer idAccidenteLaboral;
    @Size(max = 5)
    @Column(name = "anio")
    private String anio;
    @Size(max = 200)
    @Column(name = "desc_ini")
    private String descIni;
    @Size(max = 200)
    @Column(name = "acc_trab")
    private String accTrab;
    @Size(max = 200)
    @Column(name = "enfer_profe")
    private String enferProfe;
    @Size(max = 100)
    @Column(name = "emp_acc")
    private String empAcc;
    @Size(max = 250)
    @Column(name = "secuelas")
    private String secuelas;
    @Size(max = 30)
    @Column(name = "arl")
    private String arl;
    @JoinColumn(name = "id_emo", referencedColumnName = "id_emo")
    @ManyToOne(optional = false)
    private Emo idEmo;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public AccidentesLaborales() {
    }

    public AccidentesLaborales(Integer idAccidenteLaboral) {
        this.idAccidenteLaboral = idAccidenteLaboral;
    }

    public Integer getIdAccidenteLaboral() {
        return idAccidenteLaboral;
    }

    public void setIdAccidenteLaboral(Integer idAccidenteLaboral) {
        this.idAccidenteLaboral = idAccidenteLaboral;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDescIni() {
        return descIni;
    }

    public void setDescIni(String descIni) {
        this.descIni = descIni;
    }

    public String getAccTrab() {
        return accTrab;
    }

    public void setAccTrab(String accTrab) {
        this.accTrab = accTrab;
    }

    public String getEnferProfe() {
        return enferProfe;
    }

    public void setEnferProfe(String enferProfe) {
        this.enferProfe = enferProfe;
    }

    public String getEmpAcc() {
        return empAcc;
    }

    public void setEmpAcc(String empAcc) {
        this.empAcc = empAcc;
    }

    public String getSecuelas() {
        return secuelas;
    }

    public void setSecuelas(String secuelas) {
        this.secuelas = secuelas;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
    }

    public Emo getIdEmo() {
        return idEmo;
    }

    public void setIdEmo(Emo idEmo) {
        this.idEmo = idEmo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccidenteLaboral != null ? idAccidenteLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccidentesLaborales)) {
            return false;
        }
        AccidentesLaborales other = (AccidentesLaborales) object;
        if ((this.idAccidenteLaboral == null && other.idAccidenteLaboral != null) || (this.idAccidenteLaboral != null && !this.idAccidenteLaboral.equals(other.idAccidenteLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistemaocupacional.entities.AccidentesLaborales[ idAccidenteLaboral=" + idAccidenteLaboral + " ]";
    }

}
