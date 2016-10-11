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
@Table(name = "optometria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Optometria.findAll", query = "SELECT o FROM Optometria o"),
    @NamedQuery(name = "Optometria.findByIdOptometria", query = "SELECT o FROM Optometria o WHERE o.idOptometria = :idOptometria"),
    @NamedQuery(name = "Optometria.findByFechaOptpmetria", query = "SELECT o FROM Optometria o WHERE o.fechaOptpmetria = :fechaOptpmetria"),
    @NamedQuery(name = "Optometria.findByCargo", query = "SELECT o FROM Optometria o WHERE o.cargo = :cargo"),
    @NamedQuery(name = "Optometria.findByVcodsl", query = "SELECT o FROM Optometria o WHERE o.vcodsl = :vcodsl"),
    @NamedQuery(name = "Optometria.findByVcoisl", query = "SELECT o FROM Optometria o WHERE o.vcoisl = :vcoisl"),
    @NamedQuery(name = "Optometria.findByVlodsl", query = "SELECT o FROM Optometria o WHERE o.vlodsl = :vlodsl"),
    @NamedQuery(name = "Optometria.findByVloisl", query = "SELECT o FROM Optometria o WHERE o.vloisl = :vloisl"),
    @NamedQuery(name = "Optometria.findByVcodcl", query = "SELECT o FROM Optometria o WHERE o.vcodcl = :vcodcl"),
    @NamedQuery(name = "Optometria.findByVcoicl", query = "SELECT o FROM Optometria o WHERE o.vcoicl = :vcoicl"),
    @NamedQuery(name = "Optometria.findByVlodcl", query = "SELECT o FROM Optometria o WHERE o.vlodcl = :vlodcl"),
    @NamedQuery(name = "Optometria.findByVloicl", query = "SELECT o FROM Optometria o WHERE o.vloicl = :vloicl"),
    @NamedQuery(name = "Optometria.findByRecomendaciones", query = "SELECT o FROM Optometria o WHERE o.recomendaciones = :recomendaciones"),
    @NamedQuery(name = "Optometria.findByUsalentes", query = "SELECT o FROM Optometria o WHERE o.usalentes = :usalentes"),
    @NamedQuery(name = "Optometria.findByUltimoExamen", query = "SELECT o FROM Optometria o WHERE o.ultimoExamen = :ultimoExamen"),
    @NamedQuery(name = "Optometria.findBySintomatologia", query = "SELECT o FROM Optometria o WHERE o.sintomatologia = :sintomatologia"),
    @NamedQuery(name = "Optometria.findByNormalsn", query = "SELECT o FROM Optometria o WHERE o.normalsn = :normalsn"),
    @NamedQuery(name = "Optometria.findByAzul", query = "SELECT o FROM Optometria o WHERE o.azul = :azul"),
    @NamedQuery(name = "Optometria.findByRojo", query = "SELECT o FROM Optometria o WHERE o.rojo = :rojo"),
    @NamedQuery(name = "Optometria.findByAmarillo", query = "SELECT o FROM Optometria o WHERE o.amarillo = :amarillo"),
    @NamedQuery(name = "Optometria.findByVerde", query = "SELECT o FROM Optometria o WHERE o.verde = :verde"),
    @NamedQuery(name = "Optometria.findByNormal", query = "SELECT o FROM Optometria o WHERE o.normal = :normal"),
    @NamedQuery(name = "Optometria.findByProfundidad", query = "SELECT o FROM Optometria o WHERE o.profundidad = :profundidad")})
public class Optometria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_optometria")
    private Integer idOptometria;
    @Column(name = "fecha_optpmetria")
    @Temporal(TemporalType.DATE)
    private Date fechaOptpmetria;
    @Size(max = 45)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 45)
    @Column(name = "vcodsl")
    private String vcodsl;
    @Size(max = 45)
    @Column(name = "vcoisl")
    private String vcoisl;
    @Size(max = 45)
    @Column(name = "vlodsl")
    private String vlodsl;
    @Size(max = 45)
    @Column(name = "vloisl")
    private String vloisl;
    @Size(max = 45)
    @Column(name = "vcodcl")
    private String vcodcl;
    @Size(max = 45)
    @Column(name = "vcoicl")
    private String vcoicl;
    @Size(max = 45)
    @Column(name = "vlodcl")
    private String vlodcl;
    @Size(max = 45)
    @Column(name = "vloicl")
    private String vloicl;
    @Size(max = 250)
    @Column(name = "recomendaciones")
    private String recomendaciones;
    @Size(max = 2)
    @Column(name = "usalentes")
    private String usalentes;
    @Column(name = "ultimo_examen")
    @Temporal(TemporalType.DATE)
    private Date ultimoExamen;
    @Size(max = 300)
    @Column(name = "sintomatologia")
    private String sintomatologia;
    @Column(name = "normalsn")
    private Character normalsn;
    @Size(max = 45)
    @Column(name = "azul")
    private String azul;
    @Size(max = 45)
    @Column(name = "rojo")
    private String rojo;
    @Size(max = 45)
    @Column(name = "amarillo")
    private String amarillo;
    @Size(max = 45)
    @Column(name = "verde")
    private String verde;
    @Size(max = 2)
    @Column(name = "normal")
    private String normal;
    @Size(max = 45)
    @Column(name = "profundidad")
    private String profundidad;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;

    public Optometria() {
    }

    public Optometria(Integer idOptometria) {
        this.idOptometria = idOptometria;
    }

    public Integer getIdOptometria() {
        return idOptometria;
    }

    public void setIdOptometria(Integer idOptometria) {
        this.idOptometria = idOptometria;
    }

    public Date getFechaOptpmetria() {
        return fechaOptpmetria;
    }

    public void setFechaOptpmetria(Date fechaOptpmetria) {
        this.fechaOptpmetria = fechaOptpmetria;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getVcodsl() {
        return vcodsl;
    }

    public void setVcodsl(String vcodsl) {
        this.vcodsl = vcodsl;
    }

    public String getVcoisl() {
        return vcoisl;
    }

    public void setVcoisl(String vcoisl) {
        this.vcoisl = vcoisl;
    }

    public String getVlodsl() {
        return vlodsl;
    }

    public void setVlodsl(String vlodsl) {
        this.vlodsl = vlodsl;
    }

    public String getVloisl() {
        return vloisl;
    }

    public void setVloisl(String vloisl) {
        this.vloisl = vloisl;
    }

    public String getVcodcl() {
        return vcodcl;
    }

    public void setVcodcl(String vcodcl) {
        this.vcodcl = vcodcl;
    }

    public String getVcoicl() {
        return vcoicl;
    }

    public void setVcoicl(String vcoicl) {
        this.vcoicl = vcoicl;
    }

    public String getVlodcl() {
        return vlodcl;
    }

    public void setVlodcl(String vlodcl) {
        this.vlodcl = vlodcl;
    }

    public String getVloicl() {
        return vloicl;
    }

    public void setVloicl(String vloicl) {
        this.vloicl = vloicl;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getUsalentes() {
        return usalentes;
    }

    public void setUsalentes(String usalentes) {
        this.usalentes = usalentes;
    }

    public Date getUltimoExamen() {
        return ultimoExamen;
    }

    public void setUltimoExamen(Date ultimoExamen) {
        this.ultimoExamen = ultimoExamen;
    }

    public String getSintomatologia() {
        return sintomatologia;
    }

    public void setSintomatologia(String sintomatologia) {
        this.sintomatologia = sintomatologia;
    }

    public Character getNormalsn() {
        return normalsn;
    }

    public void setNormalsn(Character normalsn) {
        this.normalsn = normalsn;
    }

    public String getAzul() {
        return azul;
    }

    public void setAzul(String azul) {
        this.azul = azul;
    }

    public String getRojo() {
        return rojo;
    }

    public void setRojo(String rojo) {
        this.rojo = rojo;
    }

    public String getAmarillo() {
        return amarillo;
    }

    public void setAmarillo(String amarillo) {
        this.amarillo = amarillo;
    }

    public String getVerde() {
        return verde;
    }

    public void setVerde(String verde) {
        this.verde = verde;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
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
        hash += (idOptometria != null ? idOptometria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Optometria)) {
            return false;
        }
        Optometria other = (Optometria) object;
        if ((this.idOptometria == null && other.idOptometria != null) || (this.idOptometria != null && !this.idOptometria.equals(other.idOptometria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocupacional.entities.Optometria[ idOptometria=" + idOptometria + " ]";
    }
    
}
