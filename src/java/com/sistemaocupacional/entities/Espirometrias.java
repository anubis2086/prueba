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
 * @author santi
 */
@Entity
@Table(name = "espirometrias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Espirometrias.findAll", query = "SELECT e FROM Espirometrias e"),
    @NamedQuery(name = "Espirometrias.findByIdEspirometria", query = "SELECT e FROM Espirometrias e WHERE e.idEspirometria = :idEspirometria"),
    @NamedQuery(name = "Espirometrias.findByFechaEspirometria", query = "SELECT e FROM Espirometrias e WHERE e.fechaEspirometria = :fechaEspirometria"),
    @NamedQuery(name = "Espirometrias.findByTalla", query = "SELECT e FROM Espirometrias e WHERE e.talla = :talla"),
    @NamedQuery(name = "Espirometrias.findByPeso", query = "SELECT e FROM Espirometrias e WHERE e.peso = :peso"),
    @NamedQuery(name = "Espirometrias.findByEdadPulmonar", query = "SELECT e FROM Espirometrias e WHERE e.edadPulmonar = :edadPulmonar"),
    @NamedQuery(name = "Espirometrias.findByFvc", query = "SELECT e FROM Espirometrias e WHERE e.fvc = :fvc"),
    @NamedQuery(name = "Espirometrias.findByFev1Fvcporctj", query = "SELECT e FROM Espirometrias e WHERE e.fev1Fvcporctj = :fev1Fvcporctj"),
    @NamedQuery(name = "Espirometrias.findByFef2575", query = "SELECT e FROM Espirometrias e WHERE e.fef2575 = :fef2575"),
    @NamedQuery(name = "Espirometrias.findByFef7585", query = "SELECT e FROM Espirometrias e WHERE e.fef7585 = :fef7585"),
    @NamedQuery(name = "Espirometrias.findByPef", query = "SELECT e FROM Espirometrias e WHERE e.pef = :pef"),
    @NamedQuery(name = "Espirometrias.findByConcepto", query = "SELECT e FROM Espirometrias e WHERE e.concepto = :concepto"),
    @NamedQuery(name = "Espirometrias.findByDiagnostico", query = "SELECT e FROM Espirometrias e WHERE e.diagnostico = :diagnostico"),
    @NamedQuery(name = "Espirometrias.findByRecomendaciones", query = "SELECT e FROM Espirometrias e WHERE e.recomendaciones = :recomendaciones"),
    @NamedQuery(name = "Espirometrias.findByAntecedentes", query = "SELECT e FROM Espirometrias e WHERE e.antecedentes = :antecedentes"),
    @NamedQuery(name = "Espirometrias.findByObservaciones", query = "SELECT e FROM Espirometrias e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "Espirometrias.findByFechaCreacion", query = "SELECT e FROM Espirometrias e WHERE e.fechaCreacion = :fechaCreacion")})
public class Espirometrias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_espirometria")
    private Integer idEspirometria;
    @Column(name = "fecha_espirometria")
    @Temporal(TemporalType.DATE)
    private Date fechaEspirometria;
    @Column(name = "talla")
    private Integer talla;
    @Column(name = "peso")
    private Integer peso;
    @Size(max = 45)
    @Column(name = "edad_pulmonar")
    private String edadPulmonar;
    @Size(max = 45)
    @Column(name = "fvc")
    private String fvc;
    @Size(max = 45)
    @Column(name = "fev1_fvcporctj")
    private String fev1Fvcporctj;
    @Size(max = 45)
    @Column(name = "fef2575")
    private String fef2575;
    @Size(max = 45)
    @Column(name = "fef7585")
    private String fef7585;
    @Size(max = 45)
    @Column(name = "pef")
    private String pef;
    @Size(max = 45)
    @Column(name = "concepto")
    private String concepto;
    @Size(max = 45)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Size(max = 250)
    @Column(name = "recomendaciones")
    private String recomendaciones;
    @Size(max = 1500)
    @Column(name = "antecedentes")
    private String antecedentes;
    @Size(max = 3000)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;

    public Espirometrias() {
    }

    public Espirometrias(Integer idEspirometria) {
        this.idEspirometria = idEspirometria;
    }

    public Integer getIdEspirometria() {
        return idEspirometria;
    }

    public void setIdEspirometria(Integer idEspirometria) {
        this.idEspirometria = idEspirometria;
    }

    public Date getFechaEspirometria() {
        return fechaEspirometria;
    }

    public void setFechaEspirometria(Date fechaEspirometria) {
        this.fechaEspirometria = fechaEspirometria;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getEdadPulmonar() {
        return edadPulmonar;
    }

    public void setEdadPulmonar(String edadPulmonar) {
        this.edadPulmonar = edadPulmonar;
    }

    public String getFvc() {
        return fvc;
    }

    public void setFvc(String fvc) {
        this.fvc = fvc;
    }

    public String getFev1Fvcporctj() {
        return fev1Fvcporctj;
    }

    public void setFev1Fvcporctj(String fev1Fvcporctj) {
        this.fev1Fvcporctj = fev1Fvcporctj;
    }

    public String getFef2575() {
        return fef2575;
    }

    public void setFef2575(String fef2575) {
        this.fef2575 = fef2575;
    }

    public String getFef7585() {
        return fef7585;
    }

    public void setFef7585(String fef7585) {
        this.fef7585 = fef7585;
    }

    public String getPef() {
        return pef;
    }

    public void setPef(String pef) {
        this.pef = pef;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Pacientes getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Pacientes idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspirometria != null ? idEspirometria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espirometrias)) {
            return false;
        }
        Espirometrias other = (Espirometrias) object;
        if ((this.idEspirometria == null && other.idEspirometria != null) || (this.idEspirometria != null && !this.idEspirometria.equals(other.idEspirometria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocupacional.entities.Espirometrias[ idEspirometria=" + idEspirometria + " ]";
    }
    
}
