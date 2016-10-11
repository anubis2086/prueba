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
import javax.persistence.Lob;
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
 * @author dandres
 */
@Entity
@Table(name = "cuenta_cobro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaCobro.findAll", query = "SELECT c FROM CuentaCobro c"),
    @NamedQuery(name = "CuentaCobro.findByIdCuenta", query = "SELECT c FROM CuentaCobro c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "CuentaCobro.findByFechaCuenta", query = "SELECT c FROM CuentaCobro c WHERE c.fechaCuenta = :fechaCuenta"),
    @NamedQuery(name = "CuentaCobro.findByNumeroCuenta", query = "SELECT c FROM CuentaCobro c WHERE c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "CuentaCobro.findByConcepto", query = "SELECT c FROM CuentaCobro c WHERE c.concepto = :concepto"),
    @NamedQuery(name = "CuentaCobro.findByCantidad", query = "SELECT c FROM CuentaCobro c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "CuentaCobro.findByValorUnitario", query = "SELECT c FROM CuentaCobro c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "CuentaCobro.findByValorTotal", query = "SELECT c FROM CuentaCobro c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "CuentaCobro.findByBanco", query = "SELECT c FROM CuentaCobro c WHERE c.banco = :banco"),
    @NamedQuery(name = "CuentaCobro.findByCuentaAhorros", query = "SELECT c FROM CuentaCobro c WHERE c.cuentaAhorros = :cuentaAhorros"),
    @NamedQuery(name = "CuentaCobro.findByNombre", query = "SELECT c FROM CuentaCobro c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CuentaCobro.findByTitularCuenta", query = "SELECT c FROM CuentaCobro c WHERE c.titularCuenta = :titularCuenta"),
    @NamedQuery(name = "CuentaCobro.findByPagoEfectivo", query = "SELECT c FROM CuentaCobro c WHERE c.pagoEfectivo = :pagoEfectivo")})
public class CuentaCobro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Column(name = "fecha_cuenta")
    @Temporal(TemporalType.DATE)
    private Date fechaCuenta;
    @Size(max = 45)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Size(max = 3000)
    @Column(name = "concepto")
    private String concepto;

    @Column(name = "cantidad")
    private int cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Column(name = "valor_unitario")
    private int valorUnitario;

    @Column(name = "valor_total")
    private int valorTotal;
    @Size(max = 80)
    @Column(name = "banco")
    private String banco;
    @Size(max = 45)
    @Column(name = "cuenta_ahorros")
    private String cuentaAhorros;
    @Size(max = 60)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 60)
    @Column(name = "titular_cuenta")
    private String titularCuenta;
    @Size(max = 45)
    @Column(name = "pago_efectivo")
    private String pagoEfectivo;
    @Lob
    @Column(name = "firma_encargado")
    private byte[] firmaEncargado;
    @JoinColumn(name = "id_clem", referencedColumnName = "id_clem")
    @ManyToOne(optional = false)
    private Empresas idClem;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public CuentaCobro() {
    }

    public CuentaCobro(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Date getFechaCuenta() {
        return fechaCuenta;
    }

    public void setFechaCuenta(Date fechaCuenta) {
        this.fechaCuenta = fechaCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuentaAhorros() {
        return cuentaAhorros;
    }

    public void setCuentaAhorros(String cuentaAhorros) {
        this.cuentaAhorros = cuentaAhorros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    public String getPagoEfectivo() {
        return pagoEfectivo;
    }

    public void setPagoEfectivo(String pagoEfectivo) {
        this.pagoEfectivo = pagoEfectivo;
    }

    public byte[] getFirmaEncargado() {
        return firmaEncargado;
    }

    public void setFirmaEncargado(byte[] firmaEncargado) {
        this.firmaEncargado = firmaEncargado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaCobro)) {
            return false;
        }
        CuentaCobro other = (CuentaCobro) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sistemaocupacional.entities.CuentaCobro[ idCuenta=" + idCuenta + " ]";
    }

}
