/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author D4MN4710N
 */
@Entity
@Table(name = "empresas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e"),
    @NamedQuery(name = "Empresas.findByIdClem", query = "SELECT e FROM Empresas e WHERE e.idClem = :idClem"),
    @NamedQuery(name = "Empresas.findByNomClem", query = "SELECT e FROM Empresas e WHERE e.nomClem LIKE :nomClem"),
    @NamedQuery(name = "Empresas.findByFecCreClem", query = "SELECT e FROM Empresas e WHERE e.fecCreClem = :fecCreClem"),
    @NamedQuery(name = "Empresas.findByNitClem", query = "SELECT e FROM Empresas e WHERE e.nitClem = :nitClem")})
public class Empresas implements Serializable {
      private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clem")
    private Integer idClem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nom_clem")
    private String nomClem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_cre_clem")
    @Temporal(TemporalType.DATE)
    private Date fecCreClem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nit_clem")
    private String nitClem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClem")
    private List<CuentaCobro> cuentaCobroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClem")
    private List<Pacientes> pacientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClem")
    private List<Contratos> contratosList;

    public Empresas() {
    }

    public Empresas(Integer idClem) {
        this.idClem = idClem;
    }

    public Empresas(Integer idClem, String nomClem, Date fecCreClem, String nitClem) {
        this.idClem = idClem;
        this.nomClem = nomClem;
        this.fecCreClem = fecCreClem;
        this.nitClem = nitClem;
    }

    public Integer getIdClem() {
        return idClem;
    }

    public void setIdClem(Integer idClem) {
        this.idClem = idClem;
    }

    public String getNomClem() {
        return nomClem;
    }

    public void setNomClem(String nomClem) {
        this.nomClem = nomClem;
    }

    public Date getFecCreClem() {
        return fecCreClem;
    }

    public void setFecCreClem(Date fecCreClem) {
        this.fecCreClem = fecCreClem;
    }

    public String getNitClem() {
        return nitClem;
    }

    public void setNitClem(String nitClem) {
        this.nitClem = nitClem;
    }

    @XmlTransient
    public List<CuentaCobro> getCuentaCobroList() {
        return cuentaCobroList;
    }

    public void setCuentaCobroList(List<CuentaCobro> cuentaCobroList) {
        this.cuentaCobroList = cuentaCobroList;
    }

    @XmlTransient
    public List<Pacientes> getPacientesList() {
        return pacientesList;
    }

    public void setPacientesList(List<Pacientes> pacientesList) {
        this.pacientesList = pacientesList;
    }

    @XmlTransient
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClem != null ? idClem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.idClem == null && other.idClem != null) || (this.idClem != null && !this.idClem.equals(other.idClem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomClem;
    }
    
}
