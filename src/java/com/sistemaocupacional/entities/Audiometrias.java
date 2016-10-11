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
@Table(name = "audiometrias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Audiometrias.findAll", query = "SELECT a FROM Audiometrias a"),
    @NamedQuery(name = "Audiometrias.findByIdAudiometria", query = "SELECT a FROM Audiometrias a WHERE a.idAudiometria = :idAudiometria"),
    @NamedQuery(name = "Audiometrias.findByFechaAudiometria", query = "SELECT a FROM Audiometrias a WHERE a.fechaAudiometria = :fechaAudiometria"),
    @NamedQuery(name = "Audiometrias.findByFacturado", query = "SELECT a FROM Audiometrias a WHERE a.facturado = :facturado"),
    @NamedQuery(name = "Audiometrias.findByVaoi250", query = "SELECT a FROM Audiometrias a WHERE a.vaoi250 = :vaoi250"),
    @NamedQuery(name = "Audiometrias.findByVaoi500", query = "SELECT a FROM Audiometrias a WHERE a.vaoi500 = :vaoi500"),
    @NamedQuery(name = "Audiometrias.findByVaoi1000", query = "SELECT a FROM Audiometrias a WHERE a.vaoi1000 = :vaoi1000"),
    @NamedQuery(name = "Audiometrias.findByVaoi2000", query = "SELECT a FROM Audiometrias a WHERE a.vaoi2000 = :vaoi2000"),
    @NamedQuery(name = "Audiometrias.findByVaoi3000", query = "SELECT a FROM Audiometrias a WHERE a.vaoi3000 = :vaoi3000"),
    @NamedQuery(name = "Audiometrias.findByVaoi4000", query = "SELECT a FROM Audiometrias a WHERE a.vaoi4000 = :vaoi4000"),
    @NamedQuery(name = "Audiometrias.findByVaoi6000", query = "SELECT a FROM Audiometrias a WHERE a.vaoi6000 = :vaoi6000"),
    @NamedQuery(name = "Audiometrias.findByVaoi8000", query = "SELECT a FROM Audiometrias a WHERE a.vaoi8000 = :vaoi8000"),
    @NamedQuery(name = "Audiometrias.findByVaod250", query = "SELECT a FROM Audiometrias a WHERE a.vaod250 = :vaod250"),
    @NamedQuery(name = "Audiometrias.findByVaod500", query = "SELECT a FROM Audiometrias a WHERE a.vaod500 = :vaod500"),
    @NamedQuery(name = "Audiometrias.findByVaod1000", query = "SELECT a FROM Audiometrias a WHERE a.vaod1000 = :vaod1000"),
    @NamedQuery(name = "Audiometrias.findByVaod2000", query = "SELECT a FROM Audiometrias a WHERE a.vaod2000 = :vaod2000"),
    @NamedQuery(name = "Audiometrias.findByVaod3000", query = "SELECT a FROM Audiometrias a WHERE a.vaod3000 = :vaod3000"),
    @NamedQuery(name = "Audiometrias.findByVaod4000", query = "SELECT a FROM Audiometrias a WHERE a.vaod4000 = :vaod4000"),
    @NamedQuery(name = "Audiometrias.findByVaod6000", query = "SELECT a FROM Audiometrias a WHERE a.vaod6000 = :vaod6000"),
    @NamedQuery(name = "Audiometrias.findByVaod8000", query = "SELECT a FROM Audiometrias a WHERE a.vaod8000 = :vaod8000"),
    @NamedQuery(name = "Audiometrias.findByVooi250", query = "SELECT a FROM Audiometrias a WHERE a.vooi250 = :vooi250"),
    @NamedQuery(name = "Audiometrias.findByVooi500", query = "SELECT a FROM Audiometrias a WHERE a.vooi500 = :vooi500"),
    @NamedQuery(name = "Audiometrias.findByVooi1000", query = "SELECT a FROM Audiometrias a WHERE a.vooi1000 = :vooi1000"),
    @NamedQuery(name = "Audiometrias.findByVooi2000", query = "SELECT a FROM Audiometrias a WHERE a.vooi2000 = :vooi2000"),
    @NamedQuery(name = "Audiometrias.findByVooi3000", query = "SELECT a FROM Audiometrias a WHERE a.vooi3000 = :vooi3000"),
    @NamedQuery(name = "Audiometrias.findByVooi4000", query = "SELECT a FROM Audiometrias a WHERE a.vooi4000 = :vooi4000"),
    @NamedQuery(name = "Audiometrias.findByVood250", query = "SELECT a FROM Audiometrias a WHERE a.vood250 = :vood250"),
    @NamedQuery(name = "Audiometrias.findByVood500", query = "SELECT a FROM Audiometrias a WHERE a.vood500 = :vood500"),
    @NamedQuery(name = "Audiometrias.findByVood1000", query = "SELECT a FROM Audiometrias a WHERE a.vood1000 = :vood1000"),
    @NamedQuery(name = "Audiometrias.findByVood2000", query = "SELECT a FROM Audiometrias a WHERE a.vood2000 = :vood2000"),
    @NamedQuery(name = "Audiometrias.findByVood3000", query = "SELECT a FROM Audiometrias a WHERE a.vood3000 = :vood3000"),
    @NamedQuery(name = "Audiometrias.findByVood4000", query = "SELECT a FROM Audiometrias a WHERE a.vood4000 = :vood4000"),
    @NamedQuery(name = "Audiometrias.findByLarsenod", query = "SELECT a FROM Audiometrias a WHERE a.larsenod = :larsenod"),
    @NamedQuery(name = "Audiometrias.findByLarsenoi", query = "SELECT a FROM Audiometrias a WHERE a.larsenoi = :larsenoi"),
    @NamedQuery(name = "Audiometrias.findByEliod", query = "SELECT a FROM Audiometrias a WHERE a.eliod = :eliod"),
    @NamedQuery(name = "Audiometrias.findByElioi", query = "SELECT a FROM Audiometrias a WHERE a.elioi = :elioi"),
    @NamedQuery(name = "Audiometrias.findBySalod", query = "SELECT a FROM Audiometrias a WHERE a.salod = :salod"),
    @NamedQuery(name = "Audiometrias.findBySaloi", query = "SELECT a FROM Audiometrias a WHERE a.saloi = :saloi"),
    @NamedQuery(name = "Audiometrias.findByPtaod", query = "SELECT a FROM Audiometrias a WHERE a.ptaod = :ptaod"),
    @NamedQuery(name = "Audiometrias.findByPtaoi", query = "SELECT a FROM Audiometrias a WHERE a.ptaoi = :ptaoi"),
    @NamedQuery(name = "Audiometrias.findByOtoscopiaoi", query = "SELECT a FROM Audiometrias a WHERE a.otoscopiaoi = :otoscopiaoi"),
    @NamedQuery(name = "Audiometrias.findByOtoscopiaod", query = "SELECT a FROM Audiometrias a WHERE a.otoscopiaod = :otoscopiaod"),
    @NamedQuery(name = "Audiometrias.findByImpresionDiagnosticaOidoDerecho", query = "SELECT a FROM Audiometrias a WHERE a.impresionDiagnosticaOidoDerecho = :impresionDiagnosticaOidoDerecho"),
    @NamedQuery(name = "Audiometrias.findByImpresionDiagnosticaOidoIzquierdo", query = "SELECT a FROM Audiometrias a WHERE a.impresionDiagnosticaOidoIzquierdo = :impresionDiagnosticaOidoIzquierdo"),
    @NamedQuery(name = "Audiometrias.findByConceptoOidoDerecho", query = "SELECT a FROM Audiometrias a WHERE a.conceptoOidoDerecho = :conceptoOidoDerecho"),
    @NamedQuery(name = "Audiometrias.findByConceptoOidoIzquierdo", query = "SELECT a FROM Audiometrias a WHERE a.conceptoOidoIzquierdo = :conceptoOidoIzquierdo"),
    @NamedQuery(name = "Audiometrias.findByRecomendaciones", query = "SELECT a FROM Audiometrias a WHERE a.recomendaciones = :recomendaciones")})
public class Audiometrias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_audiometria")
    private Integer idAudiometria;
    @Column(name = "fecha_audiometria")
    @Temporal(TemporalType.DATE)
    private Date fechaAudiometria;
    @Size(max = 2)
    @Column(name = "facturado")
    private String facturado;
    @Size(max = 4)
    @Column(name = "vaoi250")
    private String vaoi250;
    @Size(max = 4)
    @Column(name = "vaoi500")
    private String vaoi500;
    @Size(max = 4)
    @Column(name = "vaoi1000")
    private String vaoi1000;
    @Size(max = 4)
    @Column(name = "vaoi2000")
    private String vaoi2000;
    @Size(max = 4)
    @Column(name = "vaoi3000")
    private String vaoi3000;
    @Size(max = 4)
    @Column(name = "vaoi4000")
    private String vaoi4000;
    @Size(max = 4)
    @Column(name = "vaoi6000")
    private String vaoi6000;
    @Size(max = 4)
    @Column(name = "vaoi8000")
    private String vaoi8000;
    @Size(max = 4)
    @Column(name = "vaod250")
    private String vaod250;
    @Size(max = 4)
    @Column(name = "vaod500")
    private String vaod500;
    @Size(max = 4)
    @Column(name = "vaod1000")
    private String vaod1000;
    @Size(max = 4)
    @Column(name = "vaod2000")
    private String vaod2000;
    @Size(max = 4)
    @Column(name = "vaod3000")
    private String vaod3000;
    @Size(max = 4)
    @Column(name = "vaod4000")
    private String vaod4000;
    @Size(max = 4)
    @Column(name = "vaod6000")
    private String vaod6000;
    @Size(max = 4)
    @Column(name = "vaod8000")
    private String vaod8000;
    @Size(max = 4)
    @Column(name = "vooi250")
    private String vooi250;
    @Size(max = 4)
    @Column(name = "vooi500")
    private String vooi500;
    @Size(max = 4)
    @Column(name = "vooi1000")
    private String vooi1000;
    @Size(max = 4)
    @Column(name = "vooi2000")
    private String vooi2000;
    @Size(max = 4)
    @Column(name = "vooi3000")
    private String vooi3000;
    @Size(max = 4)
    @Column(name = "vooi4000")
    private String vooi4000;
    @Size(max = 4)
    @Column(name = "vood250")
    private String vood250;
    @Size(max = 4)
    @Column(name = "vood500")
    private String vood500;
    @Size(max = 4)
    @Column(name = "vood1000")
    private String vood1000;
    @Size(max = 4)
    @Column(name = "vood2000")
    private String vood2000;
    @Size(max = 4)
    @Column(name = "vood3000")
    private String vood3000;
    @Size(max = 4)
    @Column(name = "vood4000")
    private String vood4000;
    @Size(max = 45)
    @Column(name = "larsenod")
    private String larsenod;
    @Size(max = 45)
    @Column(name = "larsenoi")
    private String larsenoi;
    @Size(max = 45)
    @Column(name = "eliod")
    private String eliod;
    @Size(max = 45)
    @Column(name = "elioi")
    private String elioi;
    @Size(max = 45)
    @Column(name = "salod")
    private String salod;
    @Size(max = 45)
    @Column(name = "saloi")
    private String saloi;
    @Size(max = 45)
    @Column(name = "ptaod")
    private String ptaod;
    @Size(max = 45)
    @Column(name = "ptaoi")
    private String ptaoi;
    @Size(max = 150)
    @Column(name = "otoscopiaoi")
    private String otoscopiaoi;
    @Size(max = 150)
    @Column(name = "otoscopiaod")
    private String otoscopiaod;
    @Size(max = 200)
    @Column(name = "impresion_diagnostica_oido_derecho")
    private String impresionDiagnosticaOidoDerecho;
    @Size(max = 200)
    @Column(name = "impresion_diagnostica_oido_izquierdo")
    private String impresionDiagnosticaOidoIzquierdo;
    @Size(max = 200)
    @Column(name = "concepto_oido_derecho")
    private String conceptoOidoDerecho;
    @Size(max = 200)
    @Column(name = "concepto_oido_izquierdo")
    private String conceptoOidoIzquierdo;
    @Size(max = 450)
    @Column(name = "recomendaciones")
    private String recomendaciones;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;

    public Audiometrias() {
    }

    public Audiometrias(Integer idAudiometria) {
        this.idAudiometria = idAudiometria;
    }

    public Integer getIdAudiometria() {
        return idAudiometria;
    }

    public void setIdAudiometria(Integer idAudiometria) {
        this.idAudiometria = idAudiometria;
    }

    public Date getFechaAudiometria() {
        return fechaAudiometria;
    }

    public void setFechaAudiometria(Date fechaAudiometria) {
        this.fechaAudiometria = fechaAudiometria;
    }

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }

    public String getVaoi250() {
        return vaoi250;
    }

    public void setVaoi250(String vaoi250) {
        this.vaoi250 = vaoi250;
    }

    public String getVaoi500() {
        return vaoi500;
    }

    public void setVaoi500(String vaoi500) {
        this.vaoi500 = vaoi500;
    }

    public String getVaoi1000() {
        return vaoi1000;
    }

    public void setVaoi1000(String vaoi1000) {
        this.vaoi1000 = vaoi1000;
    }

    public String getVaoi2000() {
        return vaoi2000;
    }

    public void setVaoi2000(String vaoi2000) {
        this.vaoi2000 = vaoi2000;
    }

    public String getVaoi3000() {
        return vaoi3000;
    }

    public void setVaoi3000(String vaoi3000) {
        this.vaoi3000 = vaoi3000;
    }

    public String getVaoi4000() {
        return vaoi4000;
    }

    public void setVaoi4000(String vaoi4000) {
        this.vaoi4000 = vaoi4000;
    }

    public String getVaoi6000() {
        return vaoi6000;
    }

    public void setVaoi6000(String vaoi6000) {
        this.vaoi6000 = vaoi6000;
    }

    public String getVaoi8000() {
        return vaoi8000;
    }

    public void setVaoi8000(String vaoi8000) {
        this.vaoi8000 = vaoi8000;
    }

    public String getVaod250() {
        return vaod250;
    }

    public void setVaod250(String vaod250) {
        this.vaod250 = vaod250;
    }

    public String getVaod500() {
        return vaod500;
    }

    public void setVaod500(String vaod500) {
        this.vaod500 = vaod500;
    }

    public String getVaod1000() {
        return vaod1000;
    }

    public void setVaod1000(String vaod1000) {
        this.vaod1000 = vaod1000;
    }

    public String getVaod2000() {
        return vaod2000;
    }

    public void setVaod2000(String vaod2000) {
        this.vaod2000 = vaod2000;
    }

    public String getVaod3000() {
        return vaod3000;
    }

    public void setVaod3000(String vaod3000) {
        this.vaod3000 = vaod3000;
    }

    public String getVaod4000() {
        return vaod4000;
    }

    public void setVaod4000(String vaod4000) {
        this.vaod4000 = vaod4000;
    }

    public String getVaod6000() {
        return vaod6000;
    }

    public void setVaod6000(String vaod6000) {
        this.vaod6000 = vaod6000;
    }

    public String getVaod8000() {
        return vaod8000;
    }

    public void setVaod8000(String vaod8000) {
        this.vaod8000 = vaod8000;
    }

    public String getVooi250() {
        return vooi250;
    }

    public void setVooi250(String vooi250) {
        this.vooi250 = vooi250;
    }

    public String getVooi500() {
        return vooi500;
    }

    public void setVooi500(String vooi500) {
        this.vooi500 = vooi500;
    }

    public String getVooi1000() {
        return vooi1000;
    }

    public void setVooi1000(String vooi1000) {
        this.vooi1000 = vooi1000;
    }

    public String getVooi2000() {
        return vooi2000;
    }

    public void setVooi2000(String vooi2000) {
        this.vooi2000 = vooi2000;
    }

    public String getVooi3000() {
        return vooi3000;
    }

    public void setVooi3000(String vooi3000) {
        this.vooi3000 = vooi3000;
    }

    public String getVooi4000() {
        return vooi4000;
    }

    public void setVooi4000(String vooi4000) {
        this.vooi4000 = vooi4000;
    }

    public String getVood250() {
        return vood250;
    }

    public void setVood250(String vood250) {
        this.vood250 = vood250;
    }

    public String getVood500() {
        return vood500;
    }

    public void setVood500(String vood500) {
        this.vood500 = vood500;
    }

    public String getVood1000() {
        return vood1000;
    }

    public void setVood1000(String vood1000) {
        this.vood1000 = vood1000;
    }

    public String getVood2000() {
        return vood2000;
    }

    public void setVood2000(String vood2000) {
        this.vood2000 = vood2000;
    }

    public String getVood3000() {
        return vood3000;
    }

    public void setVood3000(String vood3000) {
        this.vood3000 = vood3000;
    }

    public String getVood4000() {
        return vood4000;
    }

    public void setVood4000(String vood4000) {
        this.vood4000 = vood4000;
    }

    public String getLarsenod() {
        return larsenod;
    }

    public void setLarsenod(String larsenod) {
        this.larsenod = larsenod;
    }

    public String getLarsenoi() {
        return larsenoi;
    }

    public void setLarsenoi(String larsenoi) {
        this.larsenoi = larsenoi;
    }

    public String getEliod() {
        return eliod;
    }

    public void setEliod(String eliod) {
        this.eliod = eliod;
    }

    public String getElioi() {
        return elioi;
    }

    public void setElioi(String elioi) {
        this.elioi = elioi;
    }

    public String getSalod() {
        return salod;
    }

    public void setSalod(String salod) {
        this.salod = salod;
    }

    public String getSaloi() {
        return saloi;
    }

    public void setSaloi(String saloi) {
        this.saloi = saloi;
    }

    public String getPtaod() {
        return ptaod;
    }

    public void setPtaod(String ptaod) {
        this.ptaod = ptaod;
    }

    public String getPtaoi() {
        return ptaoi;
    }

    public void setPtaoi(String ptaoi) {
        this.ptaoi = ptaoi;
    }

    public String getOtoscopiaoi() {
        return otoscopiaoi;
    }

    public void setOtoscopiaoi(String otoscopiaoi) {
        this.otoscopiaoi = otoscopiaoi;
    }

    public String getOtoscopiaod() {
        return otoscopiaod;
    }

    public void setOtoscopiaod(String otoscopiaod) {
        this.otoscopiaod = otoscopiaod;
    }

    public String getImpresionDiagnosticaOidoDerecho() {
        return impresionDiagnosticaOidoDerecho;
    }

    public void setImpresionDiagnosticaOidoDerecho(String impresionDiagnosticaOidoDerecho) {
        this.impresionDiagnosticaOidoDerecho = impresionDiagnosticaOidoDerecho;
    }

    public String getImpresionDiagnosticaOidoIzquierdo() {
        return impresionDiagnosticaOidoIzquierdo;
    }

    public void setImpresionDiagnosticaOidoIzquierdo(String impresionDiagnosticaOidoIzquierdo) {
        this.impresionDiagnosticaOidoIzquierdo = impresionDiagnosticaOidoIzquierdo;
    }

    public String getConceptoOidoDerecho() {
        return conceptoOidoDerecho;
    }

    public void setConceptoOidoDerecho(String conceptoOidoDerecho) {
        this.conceptoOidoDerecho = conceptoOidoDerecho;
    }

    public String getConceptoOidoIzquierdo() {
        return conceptoOidoIzquierdo;
    }

    public void setConceptoOidoIzquierdo(String conceptoOidoIzquierdo) {
        this.conceptoOidoIzquierdo = conceptoOidoIzquierdo;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
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
        hash += (idAudiometria != null ? idAudiometria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Audiometrias)) {
            return false;
        }
        Audiometrias other = (Audiometrias) object;
        if ((this.idAudiometria == null && other.idAudiometria != null) || (this.idAudiometria != null && !this.idAudiometria.equals(other.idAudiometria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocupacional.entities.Audiometrias[ idAudiometria=" + idAudiometria + " ]";
    }
    
}
