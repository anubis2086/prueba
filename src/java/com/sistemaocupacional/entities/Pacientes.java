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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author D4MN4710N
 */
@Entity
@Table(name = "pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacientes.findAll", query = "SELECT p FROM Pacientes p"),
    @NamedQuery(name = "Pacientes.findByIdPaciente", query = "SELECT p FROM Pacientes p WHERE p.idPaciente = :idPaciente"),
    @NamedQuery(name = "Pacientes.findByIdClem", query = "SELECT e FROM Pacientes e WHERE e.idClem = :idClem"),
    @NamedQuery(name = "Pacientes.findByNombres", query = "SELECT p FROM Pacientes p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Pacientes.findByApellidos", query = "SELECT p FROM Pacientes p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Pacientes.findByTipoDoc", query = "SELECT p FROM Pacientes p WHERE p.tipoDoc = :tipoDoc"),
    @NamedQuery(name = "Pacientes.findByNumDoc", query = "SELECT p FROM Pacientes p WHERE p.numDoc = :numDoc"),
    @NamedQuery(name = "Pacientes.findByNumDocumento", query = "SELECT p FROM Pacientes p WHERE p.numDoc = :numeroDocumento"),
    @NamedQuery(name = "Pacientes.findByNumDocumento", query = "SELECT p FROM Pacientes p WHERE p.numDoc = :numeroDocumento"),
    @NamedQuery(name = "Pacientes.findByFecNac", query = "SELECT p FROM Pacientes p WHERE p.fecNac = :fecNac"),
    @NamedQuery(name = "Pacientes.findByEdad", query = "SELECT p FROM Pacientes p WHERE p.edad = :edad"),
    @NamedQuery(name = "Pacientes.findByTelCel", query = "SELECT p FROM Pacientes p WHERE p.telCel = :telCel"),
    @NamedQuery(name = "Pacientes.findByDireccion", query = "SELECT p FROM Pacientes p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Pacientes.findByCiudad", query = "SELECT p FROM Pacientes p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Pacientes.findByCiudadNac", query = "SELECT p FROM Pacientes p WHERE p.ciudadNac = :ciudadNac"),
    @NamedQuery(name = "Pacientes.findByEstCiv", query = "SELECT p FROM Pacientes p WHERE p.estCiv = :estCiv"),
    @NamedQuery(name = "Pacientes.findByNivAca", query = "SELECT p FROM Pacientes p WHERE p.nivAca = :nivAca"),
    @NamedQuery(name = "Pacientes.findByAfp", query = "SELECT p FROM Pacientes p WHERE p.afp = :afp"),
    @NamedQuery(name = "Pacientes.findByEps", query = "SELECT p FROM Pacientes p WHERE p.eps = :eps"),
    @NamedQuery(name = "Pacientes.findByArl", query = "SELECT p FROM Pacientes p WHERE p.arl = :arl"),
    @NamedQuery(name = "Pacientes.findByNumHijos", query = "SELECT p FROM Pacientes p WHERE p.numHijos = :numHijos"),
    @NamedQuery(name = "Pacientes.findByGenero", query = "SELECT p FROM Pacientes p WHERE p.genero = :genero"),
    @NamedQuery(name = "Pacientes.findByCargo", query = "SELECT p FROM Pacientes p WHERE p.cargo = :cargo"),
    @NamedQuery(name = "Pacientes.findByProfesion", query = "SELECT p FROM Pacientes p WHERE p.profesion = :profesion")})
public class Pacientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Column(name = "num_doc")
    private String numDoc;
    @Column(name = "fec_nac")
    @Temporal(TemporalType.DATE)
    private Date fecNac;
    @Column(name = "edad")
    private String edad;
    @Column(name = "tel_cel")
    private String telCel;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 45)
    @Column(name = "ciudad_nac")
    private String ciudadNac;
    @Size(max = 45)
    @Column(name = "est_civ")
    private String estCiv;
    @Size(max = 45)
    @Column(name = "niv_aca")
    private String nivAca;
    @Size(max = 45)
    @Column(name = "afp")
    private String afp;
    @Size(max = 45)
    @Column(name = "eps")
    private String eps;
    @Size(max = 45)
    @Column(name = "arl")
    private String arl;
    @Size(max = 45)
    @Column(name = "num_hijos")
    private String numHijos;
    @Column(name = "genero")
    private Character genero;
    @Size(max = 45)
    @Column(name = "cargo")
    private String cargo;
     @Size(max = 100)
    @Column(name = "acompanante")
    private String acompanante;
    @Size(max = 45)
    @Column(name = "profesion")
    private String profesion;
    @Size(max = 45)
    @Column(name = "fecha_creacion")    
    private String fechaCreacion;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Lob
    @Column(name = "img_firma")
    private byte[] imgFirma;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Emo> emoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Audiometrias> audiometriasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Laboratorio> laboratorioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Optometria> optometriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Espirometrias> espirometriasList;
    @JoinColumn(name = "id_clem", referencedColumnName = "id_clem")
    @ManyToOne(optional = false)
    private Empresas idClem;

    public Pacientes() {
    }

    public Pacientes(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCiudadNac() {
        return ciudadNac;
    }

    public void setCiudadNac(String ciudadNac) {
        this.ciudadNac = ciudadNac;
    }

    public String getEstCiv() {
        return estCiv;
    }

    public void setEstCiv(String estCiv) {
        this.estCiv = estCiv;
    }

    public String getNivAca() {
        return nivAca;
    }

    public void setNivAca(String nivAca) {
        this.nivAca = nivAca;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
    }

    public String getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(String numHijos) {
        this.numHijos = numHijos;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getCargo() {
        return cargo;
    }

    public String getAcompanante() {
        return acompanante;
    }

    public void setAcompanante(String acompanante) {
        this.acompanante = acompanante;
    }
    

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getImgFirma() {
        return imgFirma;
    }

    public void setImgFirma(byte[] imgFirma) {
        this.imgFirma = imgFirma;
    }

    @XmlTransient
    public List<Emo> getEmoList() {
        return emoList;
    }

    public void setEmoList(List<Emo> emoList) {
        this.emoList = emoList;
    }

    @XmlTransient
    public List<Audiometrias> getAudiometriasList() {
        return audiometriasList;
    }

    public void setAudiometriasList(List<Audiometrias> audiometriasList) {
        this.audiometriasList = audiometriasList;
    }

    @XmlTransient
    public List<Laboratorio> getLaboratorioList() {
        return laboratorioList;
    }

    public void setLaboratorioList(List<Laboratorio> laboratorioList) {
        this.laboratorioList = laboratorioList;
    }

    @XmlTransient
    public List<Optometria> getOptometriaList() {
        return optometriaList;
    }

    public void setOptometriaList(List<Optometria> optometriaList) {
        this.optometriaList = optometriaList;
    }

    @XmlTransient
    public List<Espirometrias> getEspirometriasList() {
        return espirometriasList;
    }

    public void setEspirometriasList(List<Espirometrias> espirometriasList) {
        this.espirometriasList = espirometriasList;
    }

    public Empresas getIdClem() {
        return idClem;
    }

    public void setIdClem(Empresas idClem) {
        this.idClem = idClem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacientes)) {
            return false;
        }
        Pacientes other = (Pacientes) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos;

    }

    public String toStringNom() {
        return numDoc + " " + nombres + " " + apellidos;

    }

}
