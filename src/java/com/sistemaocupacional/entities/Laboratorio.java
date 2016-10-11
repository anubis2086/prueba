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
@Table(name = "laboratorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laboratorio.findAll", query = "SELECT l FROM Laboratorio l"),
    @NamedQuery(name = "Laboratorio.findByIdLaboratorio", query = "SELECT l FROM Laboratorio l WHERE l.idLaboratorio = :idLaboratorio"),
    @NamedQuery(name = "Laboratorio.findByTipoExamenLab", query = "SELECT l FROM Laboratorio l WHERE l.tipoExamenLab = :tipoExamenLab"),
    @NamedQuery(name = "Laboratorio.findByResultado", query = "SELECT l FROM Laboratorio l WHERE l.resultado = :resultado"),
    @NamedQuery(name = "Laboratorio.findByConcepto", query = "SELECT l FROM Laboratorio l WHERE l.concepto = :concepto"),
    @NamedQuery(name = "Laboratorio.findByUsuarioTomaMuestra", query = "SELECT l FROM Laboratorio l WHERE l.usuarioTomaMuestra = :usuarioTomaMuestra"),
    @NamedQuery(name = "Laboratorio.findByFehcaToma", query = "SELECT l FROM Laboratorio l WHERE l.fehcaToma = :fehcaToma")})
public class Laboratorio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_laboratorio")
    private Integer idLaboratorio;
    @Size(max = 80)
    @Column(name = "tipo_examen_lab")
    private String tipoExamenLab;
    
    @Size(max = 2)
      @Column(name = "facturado")
    private String facturado;
    
    @Size(max = 200)
    @Column(name = "resultado")
    private String resultado;
    @Size(max = 45)
    @Column(name = "concepto")
    private String concepto;
     @Size(max = 200)
     @Column(name = "notas")
     private String notas;
     
    @Size(max = 45)
    @Column(name = "usuario_toma_muestra")
    private String usuarioTomaMuestra;
    @Column(name = "fehca_toma")
    @Temporal(TemporalType.DATE)
    private Date fehcaToma;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;

    public Laboratorio() {
    }

    public Laboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public Integer getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getTipoExamenLab() {
        return tipoExamenLab;
    }

    public void setTipoExamenLab(String tipoExamenLab) {
        this.tipoExamenLab = tipoExamenLab;
    }

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }
    
    

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
    

    public String getUsuarioTomaMuestra() {
        return usuarioTomaMuestra;
    }

    public void setUsuarioTomaMuestra(String usuarioTomaMuestra) {
        this.usuarioTomaMuestra = usuarioTomaMuestra;
    }

    public Date getFehcaToma() {
        return fehcaToma;
    }

    public void setFehcaToma(Date fehcaToma) {
        this.fehcaToma = fehcaToma;
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
        hash += (idLaboratorio != null ? idLaboratorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorio)) {
            return false;
        }
        Laboratorio other = (Laboratorio) object;
        if ((this.idLaboratorio == null && other.idLaboratorio != null) || (this.idLaboratorio != null && !this.idLaboratorio.equals(other.idLaboratorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistemaocupacional.entities.Laboratorio[ idLaboratorio=" + idLaboratorio + " ]";
    }
    
}
