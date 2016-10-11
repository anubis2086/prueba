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
 * @author santiago
 */
@Entity
@Table(name = "emo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emo.findAll", query = "SELECT e FROM Emo e"),
    @NamedQuery(name = "Emo.findByIdEmo", query = "SELECT e FROM Emo e WHERE e.idEmo = :idEmo"),
    @NamedQuery(name = "Emo.findByTipoExamenEmo", query = "SELECT e FROM Emo e WHERE e.tipoExamenEmo = :tipoExamenEmo"),
    @NamedQuery(name = "Emo.findByRestriccionesLab", query = "SELECT e FROM Emo e WHERE e.restriccionesLab = :restriccionesLab"),
    @NamedQuery(name = "Emo.findByCualesUno", query = "SELECT e FROM Emo e WHERE e.cualesUno = :cualesUno"),
    @NamedQuery(name = "Emo.findByAntFam", query = "SELECT e FROM Emo e WHERE e.antFam = :antFam"),
    @NamedQuery(name = "Emo.findByAlergicos", query = "SELECT e FROM Emo e WHERE e.alergicos = :alergicos"),
    @NamedQuery(name = "Emo.findByAsma", query = "SELECT e FROM Emo e WHERE e.asma = :asma"),
    @NamedQuery(name = "Emo.findByCancer", query = "SELECT e FROM Emo e WHERE e.cancer = :cancer"),
    @NamedQuery(name = "Emo.findByCardiovasculares", query = "SELECT e FROM Emo e WHERE e.cardiovasculares = :cardiovasculares"),
    @NamedQuery(name = "Emo.findByDermatologicos", query = "SELECT e FROM Emo e WHERE e.dermatologicos = :dermatologicos"),
    @NamedQuery(name = "Emo.findByDiabetes", query = "SELECT e FROM Emo e WHERE e.diabetes = :diabetes"),
    @NamedQuery(name = "Emo.findByEpilepsia", query = "SELECT e FROM Emo e WHERE e.epilepsia = :epilepsia"),
    @NamedQuery(name = "Emo.findByHipertension", query = "SELECT e FROM Emo e WHERE e.hipertension = :hipertension"),
    @NamedQuery(name = "Emo.findByHipotiroidismo", query = "SELECT e FROM Emo e WHERE e.hipotiroidismo = :hipotiroidismo"),
    @NamedQuery(name = "Emo.findByNeurologicos", query = "SELECT e FROM Emo e WHERE e.neurologicos = :neurologicos"),
    @NamedQuery(name = "Emo.findByPsiquiatricos", query = "SELECT e FROM Emo e WHERE e.psiquiatricos = :psiquiatricos"),
    @NamedQuery(name = "Emo.findByQuimicos", query = "SELECT e FROM Emo e WHERE e.quimicos = :quimicos"),
    @NamedQuery(name = "Emo.findByRespiratorias", query = "SELECT e FROM Emo e WHERE e.respiratorias = :respiratorias"),
    @NamedQuery(name = "Emo.findByReumatologicos", query = "SELECT e FROM Emo e WHERE e.reumatologicos = :reumatologicos"),
    @NamedQuery(name = "Emo.findByTraumaticos", query = "SELECT e FROM Emo e WHERE e.traumaticos = :traumaticos"),
    @NamedQuery(name = "Emo.findByVaricesUno", query = "SELECT e FROM Emo e WHERE e.varicesUno = :varicesUno"),
    @NamedQuery(name = "Emo.findByVisuales", query = "SELECT e FROM Emo e WHERE e.visuales = :visuales"),
    @NamedQuery(name = "Emo.findByFumas", query = "SELECT e FROM Emo e WHERE e.fumas = :fumas"),
    @NamedQuery(name = "Emo.findByCuantos", query = "SELECT e FROM Emo e WHERE e.cuantos = :cuantos"),
    @NamedQuery(name = "Emo.findByTiempoHaceCuanto", query = "SELECT e FROM Emo e WHERE e.tiempoHaceCuanto = :tiempoHaceCuanto"),
    @NamedQuery(name = "Emo.findByExfumador", query = "SELECT e FROM Emo e WHERE e.exfumador = :exfumador"),
    @NamedQuery(name = "Emo.findByLicor", query = "SELECT e FROM Emo e WHERE e.licor = :licor"),
    @NamedQuery(name = "Emo.findByHabitual", query = "SELECT e FROM Emo e WHERE e.habitual = :habitual"),
    @NamedQuery(name = "Emo.findByDeporte", query = "SELECT e FROM Emo e WHERE e.deporte = :deporte"),
    @NamedQuery(name = "Emo.findByQueDeporte", query = "SELECT e FROM Emo e WHERE e.queDeporte = :queDeporte"),
    @NamedQuery(name = "Emo.findByCuantasVecesSemana", query = "SELECT e FROM Emo e WHERE e.cuantasVecesSemana = :cuantasVecesSemana"),
    @NamedQuery(name = "Emo.findByManualidades", query = "SELECT e FROM Emo e WHERE e.manualidades = :manualidades"),
    @NamedQuery(name = "Emo.findByCualesDos", query = "SELECT e FROM Emo e WHERE e.cualesDos = :cualesDos"),
    @NamedQuery(name = "Emo.findByMenarca", query = "SELECT e FROM Emo e WHERE e.menarca = :menarca"),
    @NamedQuery(name = "Emo.findByGravida", query = "SELECT e FROM Emo e WHERE e.gravida = :gravida"),
    @NamedQuery(name = "Emo.findByPartos", query = "SELECT e FROM Emo e WHERE e.partos = :partos"),
    @NamedQuery(name = "Emo.findByCesareas", query = "SELECT e FROM Emo e WHERE e.cesareas = :cesareas"),
    @NamedQuery(name = "Emo.findByAbortos", query = "SELECT e FROM Emo e WHERE e.abortos = :abortos"),
    @NamedQuery(name = "Emo.findByHijosVivos", query = "SELECT e FROM Emo e WHERE e.hijosVivos = :hijosVivos"),
    @NamedQuery(name = "Emo.findByFechaUltimaMenst", query = "SELECT e FROM Emo e WHERE e.fechaUltimaMenst = :fechaUltimaMenst"),
    @NamedQuery(name = "Emo.findByCitologia", query = "SELECT e FROM Emo e WHERE e.citologia = :citologia"),
    @NamedQuery(name = "Emo.findByCiclos", query = "SELECT e FROM Emo e WHERE e.ciclos = :ciclos"),
    @NamedQuery(name = "Emo.findByApariencia", query = "SELECT e FROM Emo e WHERE e.apariencia = :apariencia"),
    @NamedQuery(name = "Emo.findBySintOsteom", query = "SELECT e FROM Emo e WHERE e.sintOsteom = :sintOsteom"),
    @NamedQuery(name = "Emo.findByCadera", query = "SELECT e FROM Emo e WHERE e.cadera = :cadera"),
    @NamedQuery(name = "Emo.findByCervical", query = "SELECT e FROM Emo e WHERE e.cervical = :cervical"),
    @NamedQuery(name = "Emo.findByCodos", query = "SELECT e FROM Emo e WHERE e.codos = :codos"),
    @NamedQuery(name = "Emo.findByCuelloUno", query = "SELECT e FROM Emo e WHERE e.cuelloUno = :cuelloUno"),
    @NamedQuery(name = "Emo.findByDedos", query = "SELECT e FROM Emo e WHERE e.dedos = :dedos"),
    @NamedQuery(name = "Emo.findByDorsal", query = "SELECT e FROM Emo e WHERE e.dorsal = :dorsal"),
    @NamedQuery(name = "Emo.findByDorsolumbar", query = "SELECT e FROM Emo e WHERE e.dorsolumbar = :dorsolumbar"),
    @NamedQuery(name = "Emo.findByHombros", query = "SELECT e FROM Emo e WHERE e.hombros = :hombros"),
    @NamedQuery(name = "Emo.findByLumbosacra", query = "SELECT e FROM Emo e WHERE e.lumbosacra = :lumbosacra"),
    @NamedQuery(name = "Emo.findByManos", query = "SELECT e FROM Emo e WHERE e.manos = :manos"),
    @NamedQuery(name = "Emo.findByMuniecas", query = "SELECT e FROM Emo e WHERE e.muniecas = :muniecas"),
    @NamedQuery(name = "Emo.findByPies", query = "SELECT e FROM Emo e WHERE e.pies = :pies"),
    @NamedQuery(name = "Emo.findByRodillasUno", query = "SELECT e FROM Emo e WHERE e.rodillasUno = :rodillasUno"),
    @NamedQuery(name = "Emo.findByDescOsteom", query = "SELECT e FROM Emo e WHERE e.descOsteom = :descOsteom"),
    @NamedQuery(name = "Emo.findByOtros", query = "SELECT e FROM Emo e WHERE e.otros = :otros"),
    @NamedQuery(name = "Emo.findByRevisionSistemas", query = "SELECT e FROM Emo e WHERE e.revisionSistemas = :revisionSistemas"),
    @NamedQuery(name = "Emo.findByDescSiste", query = "SELECT e FROM Emo e WHERE e.descSiste = :descSiste"),
    @NamedQuery(name = "Emo.findByAparienciaFisica", query = "SELECT e FROM Emo e WHERE e.aparienciaFisica = :aparienciaFisica"),
    @NamedQuery(name = "Emo.findByDominancia", query = "SELECT e FROM Emo e WHERE e.dominancia = :dominancia"),
    @NamedQuery(name = "Emo.findByTalla", query = "SELECT e FROM Emo e WHERE e.talla = :talla"),
    @NamedQuery(name = "Emo.findByPeso", query = "SELECT e FROM Emo e WHERE e.peso = :peso"),
    @NamedQuery(name = "Emo.findByImc", query = "SELECT e FROM Emo e WHERE e.imc = :imc"),
    @NamedQuery(name = "Emo.findByClasificacion", query = "SELECT e FROM Emo e WHERE e.clasificacion = :clasificacion"),
    @NamedQuery(name = "Emo.findByTas", query = "SELECT e FROM Emo e WHERE e.tas = :tas"),
    @NamedQuery(name = "Emo.findByTad", query = "SELECT e FROM Emo e WHERE e.tad = :tad"),
    @NamedQuery(name = "Emo.findByFc", query = "SELECT e FROM Emo e WHERE e.fc = :fc"),
    @NamedQuery(name = "Emo.findByFr", query = "SELECT e FROM Emo e WHERE e.fr = :fr"),
    @NamedQuery(name = "Emo.findByTemp", query = "SELECT e FROM Emo e WHERE e.temp = :temp"),
    @NamedQuery(name = "Emo.findByAbdomen", query = "SELECT e FROM Emo e WHERE e.abdomen = :abdomen"),
    @NamedQuery(name = "Emo.findByArcoMovilidadArt", query = "SELECT e FROM Emo e WHERE e.arcoMovilidadArt = :arcoMovilidadArt"),
    @NamedQuery(name = "Emo.findByBoca", query = "SELECT e FROM Emo e WHERE e.boca = :boca"),
    @NamedQuery(name = "Emo.findByCicaTatu", query = "SELECT e FROM Emo e WHERE e.cicaTatu = :cicaTatu"),
    @NamedQuery(name = "Emo.findByColumCerv", query = "SELECT e FROM Emo e WHERE e.columCerv = :columCerv"),
    @NamedQuery(name = "Emo.findByColumLumbo", query = "SELECT e FROM Emo e WHERE e.columLumbo = :columLumbo"),
    @NamedQuery(name = "Emo.findByColumTora", query = "SELECT e FROM Emo e WHERE e.columTora = :columTora"),
    @NamedQuery(name = "Emo.findByColumVertebral", query = "SELECT e FROM Emo e WHERE e.columVertebral = :columVertebral"),
    @NamedQuery(name = "Emo.findByCorazon", query = "SELECT e FROM Emo e WHERE e.corazon = :corazon"),
    @NamedQuery(name = "Emo.findByCuelloDos", query = "SELECT e FROM Emo e WHERE e.cuelloDos = :cuelloDos"),
    @NamedQuery(name = "Emo.findByFilkenstein", query = "SELECT e FROM Emo e WHERE e.filkenstein = :filkenstein"),
    @NamedQuery(name = "Emo.findByGenitales", query = "SELECT e FROM Emo e WHERE e.genitales = :genitales"),
    @NamedQuery(name = "Emo.findByHernias", query = "SELECT e FROM Emo e WHERE e.hernias = :hernias"),
    @NamedQuery(name = "Emo.findByLasegue", query = "SELECT e FROM Emo e WHERE e.lasegue = :lasegue"),
    @NamedQuery(name = "Emo.findByLimitacionFuncional", query = "SELECT e FROM Emo e WHERE e.limitacionFuncional = :limitacionFuncional"),
    @NamedQuery(name = "Emo.findByMarchaPuntas", query = "SELECT e FROM Emo e WHERE e.marchaPuntas = :marchaPuntas"),
    @NamedQuery(name = "Emo.findByMarchaTalones", query = "SELECT e FROM Emo e WHERE e.marchaTalones = :marchaTalones"),
    @NamedQuery(name = "Emo.findByMiembrosInf", query = "SELECT e FROM Emo e WHERE e.miembrosInf = :miembrosInf"),
    @NamedQuery(name = "Emo.findByMiembrosSup", query = "SELECT e FROM Emo e WHERE e.miembrosSup = :miembrosSup"),
    @NamedQuery(name = "Emo.findByOidos", query = "SELECT e FROM Emo e WHERE e.oidos = :oidos"),
    @NamedQuery(name = "Emo.findByOjos", query = "SELECT e FROM Emo e WHERE e.ojos = :ojos"),
    @NamedQuery(name = "Emo.findByPanel", query = "SELECT e FROM Emo e WHERE e.panel = :panel"),
    @NamedQuery(name = "Emo.findByPiel", query = "SELECT e FROM Emo e WHERE e.piel = :piel"),
    @NamedQuery(name = "Emo.findByPulmones", query = "SELECT e FROM Emo e WHERE e.pulmones = :pulmones"),
    @NamedQuery(name = "Emo.findByRodillasDos", query = "SELECT e FROM Emo e WHERE e.rodillasDos = :rodillasDos"),
    @NamedQuery(name = "Emo.findBySilla", query = "SELECT e FROM Emo e WHERE e.silla = :silla"),
    @NamedQuery(name = "Emo.findByTinel", query = "SELECT e FROM Emo e WHERE e.tinel = :tinel"),
    @NamedQuery(name = "Emo.findByVaricesDos", query = "SELECT e FROM Emo e WHERE e.varicesDos = :varicesDos"),
    @NamedQuery(name = "Emo.findByTrabajoAlturas", query = "SELECT e FROM Emo e WHERE e.trabajoAlturas = :trabajoAlturas"),
    @NamedQuery(name = "Emo.findByPruebasVestibular", query = "SELECT e FROM Emo e WHERE e.pruebasVestibular = :pruebasVestibular"),
    @NamedQuery(name = "Emo.findByFechaEncuesta", query = "SELECT e FROM Emo e WHERE e.fechaEncuesta = :fechaEncuesta"),
    @NamedQuery(name = "Emo.findByTrabajadorMenorEdad", query = "SELECT e FROM Emo e WHERE e.trabajadorMenorEdad = :trabajadorMenorEdad"),
    @NamedQuery(name = "Emo.findByObsUno", query = "SELECT e FROM Emo e WHERE e.obsUno = :obsUno"),
    @NamedQuery(name = "Emo.findByMujerGestante", query = "SELECT e FROM Emo e WHERE e.mujerGestante = :mujerGestante"),
    @NamedQuery(name = "Emo.findByPanicoAlturas", query = "SELECT e FROM Emo e WHERE e.panicoAlturas = :panicoAlturas"),
    @NamedQuery(name = "Emo.findByRestriccionesLabdos", query = "SELECT e FROM Emo e WHERE e.restriccionesLabdos = :restriccionesLabdos"),
    @NamedQuery(name = "Emo.findByLimitacionesFisicas", query = "SELECT e FROM Emo e WHERE e.limitacionesFisicas = :limitacionesFisicas"),
    @NamedQuery(name = "Emo.findByTraumaticosdos", query = "SELECT e FROM Emo e WHERE e.traumaticosdos = :traumaticosdos"),
    @NamedQuery(name = "Emo.findByCeguera", query = "SELECT e FROM Emo e WHERE e.ceguera = :ceguera"),
    @NamedQuery(name = "Emo.findByVisionCercanaAlterada", query = "SELECT e FROM Emo e WHERE e.visionCercanaAlterada = :visionCercanaAlterada"),
    @NamedQuery(name = "Emo.findByDislipidemia", query = "SELECT e FROM Emo e WHERE e.dislipidemia = :dislipidemia"),
    @NamedQuery(name = "Emo.findByHiperglicemia", query = "SELECT e FROM Emo e WHERE e.hiperglicemia = :hiperglicemia"),
    @NamedQuery(name = "Emo.findByImcmayor", query = "SELECT e FROM Emo e WHERE e.imcmayor = :imcmayor"),
    @NamedQuery(name = "Emo.findByAntecedentesEnfermedades", query = "SELECT e FROM Emo e WHERE e.antecedentesEnfermedades = :antecedentesEnfermedades"),
    @NamedQuery(name = "Emo.findByAntecedentesAdiccion", query = "SELECT e FROM Emo e WHERE e.antecedentesAdiccion = :antecedentesAdiccion"),
    @NamedQuery(name = "Emo.findByAlteraciones", query = "SELECT e FROM Emo e WHERE e.alteraciones = :alteraciones"),
    @NamedQuery(name = "Emo.findBySignosSintomasVisuales", query = "SELECT e FROM Emo e WHERE e.signosSintomasVisuales = :signosSintomasVisuales"),
    @NamedQuery(name = "Emo.findByAlteracionesVisuales", query = "SELECT e FROM Emo e WHERE e.alteracionesVisuales = :alteracionesVisuales"),
    @NamedQuery(name = "Emo.findByAlteracionesColor", query = "SELECT e FROM Emo e WHERE e.alteracionesColor = :alteracionesColor"),
    @NamedQuery(name = "Emo.findByAlteracionProfundidad", query = "SELECT e FROM Emo e WHERE e.alteracionProfundidad = :alteracionProfundidad"),
    @NamedQuery(name = "Emo.findByVisionBinocularAlterada", query = "SELECT e FROM Emo e WHERE e.visionBinocularAlterada = :visionBinocularAlterada"),
    @NamedQuery(name = "Emo.findByPlanoHorizontalVision", query = "SELECT e FROM Emo e WHERE e.planoHorizontalVision = :planoHorizontalVision"),
    @NamedQuery(name = "Emo.findBySignosSintomasAuditivos", query = "SELECT e FROM Emo e WHERE e.signosSintomasAuditivos = :signosSintomasAuditivos"),
    @NamedQuery(name = "Emo.findByAlteracionesAudicion", query = "SELECT e FROM Emo e WHERE e.alteracionesAudicion = :alteracionesAudicion"),
    @NamedQuery(name = "Emo.findBySintomasMetabolicos", query = "SELECT e FROM Emo e WHERE e.sintomasMetabolicos = :sintomasMetabolicos"),
    @NamedQuery(name = "Emo.findByLaboratoriosAlteraciones", query = "SELECT e FROM Emo e WHERE e.laboratoriosAlteraciones = :laboratoriosAlteraciones"),
    @NamedQuery(name = "Emo.findBySintomasCardiovasculares", query = "SELECT e FROM Emo e WHERE e.sintomasCardiovasculares = :sintomasCardiovasculares"),
    @NamedQuery(name = "Emo.findByAccidenteCerebrovascular", query = "SELECT e FROM Emo e WHERE e.accidenteCerebrovascular = :accidenteCerebrovascular"),
    @NamedQuery(name = "Emo.findByImcDos", query = "SELECT e FROM Emo e WHERE e.imcDos = :imcDos"),
    @NamedQuery(name = "Emo.findBySintomasMentales", query = "SELECT e FROM Emo e WHERE e.sintomasMentales = :sintomasMentales"),
    @NamedQuery(name = "Emo.findByAntecedentesMedicamentos", query = "SELECT e FROM Emo e WHERE e.antecedentesMedicamentos = :antecedentesMedicamentos"),
    @NamedQuery(name = "Emo.findBySindromeConvulsivo", query = "SELECT e FROM Emo e WHERE e.sindromeConvulsivo = :sindromeConvulsivo"),
    @NamedQuery(name = "Emo.findByAlteracionesEquilibrio", query = "SELECT e FROM Emo e WHERE e.alteracionesEquilibrio = :alteracionesEquilibrio"),
    @NamedQuery(name = "Emo.findByAlteracionesAtencion", query = "SELECT e FROM Emo e WHERE e.alteracionesAtencion = :alteracionesAtencion"),
    @NamedQuery(name = "Emo.findByAlteracionesComportamiento", query = "SELECT e FROM Emo e WHERE e.alteracionesComportamiento = :alteracionesComportamiento"),
    @NamedQuery(name = "Emo.findByTipoConcepto", query = "SELECT e FROM Emo e WHERE e.tipoConcepto = :tipoConcepto"),
    
    @NamedQuery(name = "Emo.findByExaPeriodicoMedicinalaboral", query = "SELECT e FROM Emo e WHERE e.exaPeriodicoMedicinalaboral = :exaPeriodicoMedicinalaboral"),
    @NamedQuery(name = "Emo.findByExaEgreso", query = "SELECT e FROM Emo e WHERE e.exaEgreso = :exaEgreso"),
    @NamedQuery(name = "Emo.findByEmocol", query = "SELECT e FROM Emo e WHERE e.emocol = :emocol"),
    @NamedQuery(name = "Emo.findByFechaCreacion", query = "SELECT e FROM Emo e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Emo.findByCargo", query = "SELECT e FROM Emo e WHERE e.cargo = :cargo"),
    @NamedQuery(name = "Emo.findByAcompanante", query = "SELECT e FROM Emo e WHERE e.acompanante = :acompanante"),
    @NamedQuery(name = "Emo.findByConductasOcupacionales", query = "SELECT e FROM Emo e WHERE e.conductasOcupacionales = :conductasOcupacionales"),
    @NamedQuery(name = "Emo.findByObservaciones", query = "SELECT e FROM Emo e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "Emo.findByTipoExamen", query = "SELECT e FROM Emo e WHERE e.tipoExamen = :tipoExamen"),
    @NamedQuery(name = "Emo.findByTipoPerfil", query = "SELECT e FROM Emo e WHERE e.tipoPerfil = :tipoPerfil"),
    @NamedQuery(name = "Emo.findByResultadoConceptoFinal", query = "SELECT e FROM Emo e WHERE e.resultadoConceptoFinal = :resultadoConceptoFinal"),
    @NamedQuery(name = "Emo.findByObservacionesCiediez", query = "SELECT e FROM Emo e WHERE e.observacionesCiediez = :observacionesCiediez"),
    @NamedQuery(name = "Emo.findByExamenFisico", query = "SELECT e FROM Emo e WHERE e.examenFisico = :examenFisico"),
    @NamedQuery(name = "Emo.findByRemision", query = "SELECT e FROM Emo e WHERE e.remision = :remision"),
    @NamedQuery(name = "Emo.findByVisiometria", query = "SELECT e FROM Emo e WHERE e.visiometria = :visiometria"),
    @NamedQuery(name = "Emo.findByAudiometria", query = "SELECT e FROM Emo e WHERE e.audiometria = :audiometria"),
    @NamedQuery(name = "Emo.findByEspirometriaComputalizada", query = "SELECT e FROM Emo e WHERE e.espirometriaComputalizada = :espirometriaComputalizada"),
    @NamedQuery(name = "Emo.findByLaboratorio", query = "SELECT e FROM Emo e WHERE e.laboratorio = :laboratorio"),
    @NamedQuery(name = "Emo.findByConsentimientoInformado", query = "SELECT e FROM Emo e WHERE e.consentimientoInformado = :consentimientoInformado"),
    @NamedQuery(name = "Emo.findBySistemasVigilancia", query = "SELECT e FROM Emo e WHERE e.sistemasVigilancia = :sistemasVigilancia"),
    @NamedQuery(name = "Emo.findByObservacionesEspecificas", query = "SELECT e FROM Emo e WHERE e.observacionesEspecificas = :observacionesEspecificas"),
    @NamedQuery(name = "Emo.findByCiudadAtencion", query = "SELECT e FROM Emo e WHERE e.ciudadAtencion = :ciudadAtencion")})
public class Emo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emo")
    private Integer idEmo;
    @Size(max = 45)
    @Column(name = "tipo_examen_emo")
    private String tipoExamenEmo;
    @Size(max = 4)
    @Column(name = "restricciones_lab")
    private String restriccionesLab;
    @Size(max = 200)
    @Column(name = "cuales_uno")
    private String cualesUno;
    @Size(max = 500)
    @Column(name = "ant_fam")
    private String antFam;
    @Size(max = 4)
    @Column(name = "alergicos")
    private String alergicos;
    @Size(max = 4)
    @Column(name = "asma")
    private String asma;
    @Size(max = 4)
    @Column(name = "cancer")
    private String cancer;
    @Size(max = 4)
    @Column(name = "cardiovasculares")
    private String cardiovasculares;
    @Size(max = 4)
    @Column(name = "dermatologicos")
    private String dermatologicos;
    @Size(max = 4)
    @Column(name = "diabetes")
    private String diabetes;
    @Size(max = 4)
    @Column(name = "epilepsia")
    private String epilepsia;
    @Size(max = 4)
    @Column(name = "hipertension")
    private String hipertension;
    @Size(max = 4)
    @Column(name = "hipotiroidismo")
    private String hipotiroidismo;
    @Size(max = 4)
    @Column(name = "neurologicos")
    private String neurologicos;
    @Size(max = 4)
    @Column(name = "psiquiatricos")
    private String psiquiatricos;
    @Size(max = 4)
    @Column(name = "quimicos")
    private String quimicos;
    @Size(max = 4)
    @Column(name = "respiratorias")
    private String respiratorias;
    @Size(max = 4)
    @Column(name = "reumatologicos")
    private String reumatologicos;
    @Size(max = 4)
    @Column(name = "traumaticos")
    private String traumaticos;
    @Size(max = 2)
    @Column(name = "varices_uno")
    private String varicesUno;
    @Size(max = 2)
    @Column(name = "visuales")
    private String visuales;
    @Size(max = 2)
    @Column(name = "fumas")
    private String fumas;
    @Size(max = 11)
    @Column(name = "cuantos")
    private String cuantos;
    @Size(max = 45)
    @Column(name = "tiempo_hace_cuanto")
    private String tiempoHaceCuanto;
    @Size(max = 2)
    @Column(name = "exfumador")
    private String exfumador;
    @Size(max = 2)
    @Column(name = "licor")
    private String licor;
    @Size(max = 2)
    @Column(name = "habitual")
    private String habitual;
    @Size(max = 2)
    @Column(name = "deporte")
    private String deporte;
    @Size(max = 100)
    @Column(name = "que_deporte")
    private String queDeporte;
    @Size(max = 25)
    @Column(name = "cuantas_veces_semana")
    private String cuantasVecesSemana;
    @Size(max = 2)
    @Column(name = "manualidades")
    private String manualidades;
    @Size(max = 100)
    @Column(name = "cuales_dos")
    private String cualesDos;
    @Size(max = 45)
    @Column(name = "menarca")
    private String menarca;
    @Size(max = 45)
    @Column(name = "gravida")
    private String gravida;
    @Size(max = 45)
    @Column(name = "partos")
    private String partos;
    @Size(max = 45)
    @Column(name = "cesareas")
    private String cesareas;
    @Size(max = 45)
    @Column(name = "abortos")
    private String abortos;
    @Size(max = 45)
    @Column(name = "hijos_vivos")
    private String hijosVivos;
    @Column(name = "fecha_ultima_menst")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaMenst;
    @Column(name = "citologia")
    @Temporal(TemporalType.DATE)
    private Date citologia;
    @Size(max = 200)
    @Column(name = "ciclos")
    private String ciclos;
    @Size(max = 200)
    @Column(name = "apariencia")
    private String apariencia;
    @Size(max = 2)
    @Column(name = "sint_osteom")
    private String sintOsteom;
    @Size(max = 2)
    @Column(name = "cadera")
    private String cadera;
    @Size(max = 2)
    @Column(name = "cervical")
    private String cervical;
    @Size(max = 2)
    @Column(name = "codos")
    private String codos;
    @Size(max = 2)
    @Column(name = "cuello_uno")
    private String cuelloUno;
    @Size(max = 2)
    @Column(name = "dedos")
    private String dedos;
    @Size(max = 2)
    @Column(name = "dorsal")
    private String dorsal;
    @Size(max = 2)
    @Column(name = "dorsolumbar")
    private String dorsolumbar;
    @Size(max = 2)
    @Column(name = "hombros")
    private String hombros;
    @Size(max = 2)
    @Column(name = "lumbosacra")
    private String lumbosacra;
    @Size(max = 2)
    @Column(name = "manos")
    private String manos;
    @Size(max = 2)
    @Column(name = "muniecas")
    private String muniecas;
    @Size(max = 2)
    @Column(name = "pies")
    private String pies;
    @Size(max = 2)
    @Column(name = "rodillas_uno")
    private String rodillasUno;
    @Size(max = 1000)
    @Column(name = "desc_osteom")
    private String descOsteom;
    @Size(max = 450)
    @Column(name = "otros")
    private String otros;
    @Size(max = 2)
    @Column(name = "revision_sistemas")
    private String revisionSistemas;
    @Size(max = 200)
    @Column(name = "desc_siste")
    private String descSiste;
    @Size(max = 20)
    @Column(name = "apariencia_fisica")
    private String aparienciaFisica;
    @Size(max = 45)
    @Column(name = "dominancia")
    private String dominancia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "talla")
    private Float talla;
    @Column(name = "peso")
    private Float peso;
    @Column(name = "imc")
    private Float imc;
    @Size(max = 60)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Size(max = 11)
    @Column(name = "tas")
    private String tas;
    @Size(max = 11)
    @Column(name = "tad")
    private String tad;
    @Size(max = 11)
    @Column(name = "fc")
    private String fc;
    @Size(max = 11)
    @Column(name = "fr")
    private String fr;
    @Size(max = 11)
    @Column(name = "temp")
    private String temp;
    @Size(max = 45)
    @Column(name = "abdomen")
    private String abdomen;
    @Size(max = 45)
    @Column(name = "arco_movilidad_art")
    private String arcoMovilidadArt;
    @Size(max = 45)
    @Column(name = "boca")
    private String boca;
    @Size(max = 2)
    @Column(name = "cica_tatu")
    private String cicaTatu;
    @Size(max = 45)
    @Column(name = "colum_cerv")
    private String columCerv;
    @Size(max = 45)
    @Column(name = "colum_lumbo")
    private String columLumbo;
    @Size(max = 45)
    @Column(name = "colum_tora")
    private String columTora;
    @Size(max = 45)
    @Column(name = "colum_vertebral")
    private String columVertebral;
    @Size(max = 45)
    @Column(name = "corazon")
    private String corazon;
    @Size(max = 45)
    @Column(name = "cuello_dos")
    private String cuelloDos;
    @Size(max = 45)
    @Column(name = "filkenstein")
    private String filkenstein;
    @Size(max = 45)
    @Column(name = "genitales")
    private String genitales;
    @Size(max = 2)
    @Column(name = "hernias")
    private String hernias;
    @Size(max = 45)
    @Column(name = "lasegue")
    private String lasegue;
    @Size(max = 45)
    @Column(name = "limitacion_funcional")
    private String limitacionFuncional;
    @Size(max = 45)
    @Column(name = "marcha_puntas")
    private String marchaPuntas;
    @Size(max = 45)
    @Column(name = "marcha_talones")
    private String marchaTalones;
    @Size(max = 45)
    @Column(name = "miembros_inf")
    private String miembrosInf;
    @Size(max = 45)
    @Column(name = "miembros_sup")
    private String miembrosSup;
    @Size(max = 45)
    @Column(name = "oidos")
    private String oidos;
    @Size(max = 45)
    @Column(name = "ojos")
    private String ojos;
    @Size(max = 45)
    @Column(name = "panel")
    private String panel;
    @Size(max = 45)
    @Column(name = "piel")
    private String piel;
    @Size(max = 45)
    @Column(name = "pulmones")
    private String pulmones;
    @Size(max = 45)
    @Column(name = "rodillas_dos")
    private String rodillasDos;
    @Size(max = 45)
    @Column(name = "silla")
    private String silla;
    @Size(max = 45)
    @Column(name = "tinel")
    private String tinel;
    @Size(max = 45)
    @Column(name = "varices_dos")
    private String varicesDos;
    @Size(max = 45)
    @Column(name = "trabajo_alturas")
    private String trabajoAlturas;
    @Size(max = 12)
    @Column(name = "pruebas_vestibular")
    private String pruebasVestibular;
    @Column(name = "fecha_encuesta")
    @Temporal(TemporalType.DATE)
    private Date fechaEncuesta;
    @Size(max = 2)
    @Column(name = "trabajador_menor_edad")
    private String trabajadorMenorEdad;
    @Size(max = 300)
    @Column(name = "obs_uno")
    private String obsUno;
    @Size(max = 2)
    @Column(name = "mujer_gestante")
    private String mujerGestante;
    @Size(max = 2)
    @Column(name = "panico_alturas")
    private String panicoAlturas;
    @Size(max = 2)
    @Column(name = "restricciones_labdos")
    private String restriccionesLabdos;
    @Size(max = 2)
    @Column(name = "limitaciones_fisicas")
    private String limitacionesFisicas;
    @Size(max = 2)
    @Column(name = "traumaticosdos")
    private String traumaticosdos;
    @Size(max = 2)
    @Column(name = "ceguera")
    private String ceguera;
    @Size(max = 2)
    @Column(name = "vision_cercana_alterada")
    private String visionCercanaAlterada;
    @Size(max = 2)
    @Column(name = "dislipidemia")
    private String dislipidemia;
    @Size(max = 2)
    @Column(name = "hiperglicemia")
    private String hiperglicemia;
    @Size(max = 2)
    @Column(name = "imcmayor")
    private String imcmayor;
    @Size(max = 2)
    @Column(name = "antecedentes_enfermedades")
    private String antecedentesEnfermedades;
    @Size(max = 2)
    @Column(name = "antecedentes_adiccion")
    private String antecedentesAdiccion;
    @Size(max = 2)
    @Column(name = "alteraciones")
    private String alteraciones;
    @Size(max = 2)
    @Column(name = "signos_sintomas_visuales")
    private String signosSintomasVisuales;
    @Size(max = 2)
    @Column(name = "alteraciones_visuales")
    private String alteracionesVisuales;
    @Size(max = 2)
    @Column(name = "alteraciones_color")
    private String alteracionesColor;
    @Size(max = 2)
    @Column(name = "alteracion_profundidad")
    private String alteracionProfundidad;
    @Size(max = 2)
    @Column(name = "vision_binocular_alterada")
    private String visionBinocularAlterada;
    @Size(max = 2)
    @Column(name = "plano_horizontal_vision")
    private String planoHorizontalVision;
    @Size(max = 2)
    @Column(name = "signos_sintomas_auditivos")
    private String signosSintomasAuditivos;
    @Size(max = 2)
    @Column(name = "alteraciones_audicion")
    private String alteracionesAudicion;
    @Size(max = 2)
    @Column(name = "sintomas_metabolicos")
    private String sintomasMetabolicos;
    @Size(max = 2)
    @Column(name = "laboratorios_alteraciones")
    private String laboratoriosAlteraciones;
    @Size(max = 2)
    @Column(name = "sintomas_cardiovasculares")
    private String sintomasCardiovasculares;
    @Size(max = 2)
    @Column(name = "accidente_cerebrovascular")
    private String accidenteCerebrovascular;
    @Size(max = 2)
    @Column(name = "imc_dos")
    private String imcDos;
    @Size(max = 2)
    @Column(name = "sintomas_mentales")
    private String sintomasMentales;
    @Size(max = 2)
    @Column(name = "antecedentes_medicamentos")
    private String antecedentesMedicamentos;
    @Size(max = 2)
    @Column(name = "sindrome_convulsivo")
    private String sindromeConvulsivo;
    @Size(max = 2)
    @Column(name = "alteraciones_equilibrio")
    private String alteracionesEquilibrio;
    @Size(max = 2)
    @Column(name = "alteraciones_atencion")
    private String alteracionesAtencion;
    @Size(max = 2)
    @Column(name = "alteraciones_comportamiento")
    private String alteracionesComportamiento;
    @Size(max = 100)
    @Column(name = "tipo_concepto")
    private String tipoConcepto;
    @Size(max = 4000)
    @Column(name = "consentimientoinformado")
    private String consentimientoinformado;
    @Size(max = 100)
    @Column(name = "exa_periodico_medicinalaboral")
    private String exaPeriodicoMedicinalaboral;
    @Size(max = 100)
    @Column(name = "exa_egreso")
    private String exaEgreso;
    @Size(max = 45)
    @Column(name = "emocol")
    private String emocol;
    @Size(max = 45)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Size(max = 60)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 100)
    @Column(name = "acompanante")
    private String acompanante;
    @Size(max = 5000)
    @Column(name = "conductas_ocupacionales")
    private String conductasOcupacionales;
    @Size(max = 1500)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 45)
    @Column(name = "tipo_examen")
    private String tipoExamen;
    @Size(max = 45)
    @Column(name = "tipo_perfil")
    private String tipoPerfil;
    @Size(max = 150)
    @Column(name = "resultado_concepto_final")
    private String resultadoConceptoFinal;
    @Size(max = 3000)
    @Column(name = "observaciones_ciediez")
    private String observacionesCiediez;
    @Size(max = 1500)
    @Column(name = "examen_fisico")
    private String examenFisico;
    @Size(max = 150)
    @Column(name = "remision")
    private String remision;
    @Size(max = 45)
    @Column(name = "visiometria")
    private String visiometria;
    @Size(max = 45)
    @Column(name = "audiometria")
    private String audiometria;
    @Size(max = 45)
    @Column(name = "espirometria_computalizada")
    private String espirometriaComputalizada;
    @Size(max = 45)
    @Column(name = "laboratorio")
    private String laboratorio;
    @Size(max = 4000)
    @Column(name = "consentimiento_informado")
    private String consentimientoInformado;
    @Size(max = 2000)
    @Column(name = "sistemas_vigilancia")
    private String sistemasVigilancia;
    @Size(max = 4500)
    @Column(name = "observaciones_especificas")
    private String observacionesEspecificas;   
    @Size(max = 45)
    @Column(name = "ciudad_atencion")
    private String ciudadAtencion;
    @JoinColumn(name = "cie_diez_id_cie", referencedColumnName = "id_cie")
    @ManyToOne(optional = false)
    private CieDiez cieDiezIdCie;
    @JoinColumn(name = "cie_diez_id_cie1", referencedColumnName = "id_cie")
    @ManyToOne(optional = false)
    private CieDiez cieDiezIdCie1;
    @JoinColumn(name = "cie_diez_id_cie2", referencedColumnName = "id_cie")
    @ManyToOne(optional = false)
    private CieDiez cieDiezIdCie2;
    @JoinColumn(name = "cie_diez_id_cie3", referencedColumnName = "id_cie")
    @ManyToOne(optional = false)
    private CieDiez cieDiezIdCie3;
    @JoinColumn(name = "cie_diez_id_cie4", referencedColumnName = "id_cie")
    @ManyToOne(optional = false)
    private CieDiez cieDiezIdCie4;
    @JoinColumn(name = "cie_diez_id_cie5", referencedColumnName = "id_cie")
    @ManyToOne(optional = false)
    private CieDiez cieDiezIdCie5;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;
    @JoinColumn(name = "id_recomendacion", referencedColumnName = "id_recomendacion")
    @ManyToOne(optional = false)
    private Recomendaciones idRecomendacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmo")
    private List<AccidentesLaborales> accidentesLaboralesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmo")
    private List<AntecedentesOcupacionales> antecedentesOcupacionalesList;

    public Emo() {
    }

    public Emo(Integer idEmo) {
        this.idEmo = idEmo;
    }

    public Emo(Integer idEmo, String ciudadAtencion) {
        this.idEmo = idEmo;
        this.ciudadAtencion = ciudadAtencion;
    }

    public Integer getIdEmo() {
        return idEmo;
    }

    public void setIdEmo(Integer idEmo) {
        this.idEmo = idEmo;
    }

    public String getTipoExamenEmo() {
        return tipoExamenEmo;
    }

    public void setTipoExamenEmo(String tipoExamenEmo) {
        this.tipoExamenEmo = tipoExamenEmo;
    }

    public String getRestriccionesLab() {
        return restriccionesLab;
    }

    public void setRestriccionesLab(String restriccionesLab) {
        this.restriccionesLab = restriccionesLab;
    }

    public String getCualesUno() {
        return cualesUno;
    }

    public void setCualesUno(String cualesUno) {
        this.cualesUno = cualesUno;
    }

    public String getAntFam() {
        return antFam;
    }

    public void setAntFam(String antFam) {
        this.antFam = antFam;
    }

    public String getAlergicos() {
        return alergicos;
    }

    public void setAlergicos(String alergicos) {
        this.alergicos = alergicos;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getCancer() {
        return cancer;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public String getCardiovasculares() {
        return cardiovasculares;
    }

    public void setCardiovasculares(String cardiovasculares) {
        this.cardiovasculares = cardiovasculares;
    }

    public String getDermatologicos() {
        return dermatologicos;
    }

    public void setDermatologicos(String dermatologicos) {
        this.dermatologicos = dermatologicos;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getEpilepsia() {
        return epilepsia;
    }

    public void setEpilepsia(String epilepsia) {
        this.epilepsia = epilepsia;
    }

    public String getHipertension() {
        return hipertension;
    }

    public void setHipertension(String hipertension) {
        this.hipertension = hipertension;
    }

    public String getHipotiroidismo() {
        return hipotiroidismo;
    }

    public void setHipotiroidismo(String hipotiroidismo) {
        this.hipotiroidismo = hipotiroidismo;
    }

    public String getNeurologicos() {
        return neurologicos;
    }

    public void setNeurologicos(String neurologicos) {
        this.neurologicos = neurologicos;
    }

    public String getPsiquiatricos() {
        return psiquiatricos;
    }

    public void setPsiquiatricos(String psiquiatricos) {
        this.psiquiatricos = psiquiatricos;
    }

    public String getQuimicos() {
        return quimicos;
    }

    public void setQuimicos(String quimicos) {
        this.quimicos = quimicos;
    }

    public String getRespiratorias() {
        return respiratorias;
    }

    public void setRespiratorias(String respiratorias) {
        this.respiratorias = respiratorias;
    }

    public String getReumatologicos() {
        return reumatologicos;
    }

    public void setReumatologicos(String reumatologicos) {
        this.reumatologicos = reumatologicos;
    }

    public String getTraumaticos() {
        return traumaticos;
    }

    public void setTraumaticos(String traumaticos) {
        this.traumaticos = traumaticos;
    }

    public String getVaricesUno() {
        return varicesUno;
    }

    public void setVaricesUno(String varicesUno) {
        this.varicesUno = varicesUno;
    }

    public String getVisuales() {
        return visuales;
    }

    public void setVisuales(String visuales) {
        this.visuales = visuales;
    }

    public String getFumas() {
        return fumas;
    }

    public void setFumas(String fumas) {
        this.fumas = fumas;
    }

    public String getCuantos() {
        return cuantos;
    }

    public void setCuantos(String cuantos) {
        this.cuantos = cuantos;
    }

    public String getTiempoHaceCuanto() {
        return tiempoHaceCuanto;
    }

    public void setTiempoHaceCuanto(String tiempoHaceCuanto) {
        this.tiempoHaceCuanto = tiempoHaceCuanto;
    }

    public String getExfumador() {
        return exfumador;
    }

    public void setExfumador(String exfumador) {
        this.exfumador = exfumador;
    }

    public String getLicor() {
        return licor;
    }

    public void setLicor(String licor) {
        this.licor = licor;
    }

    public String getHabitual() {
        return habitual;
    }

    public void setHabitual(String habitual) {
        this.habitual = habitual;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getQueDeporte() {
        return queDeporte;
    }

    public void setQueDeporte(String queDeporte) {
        this.queDeporte = queDeporte;
    }

    public String getCuantasVecesSemana() {
        return cuantasVecesSemana;
    }

    public void setCuantasVecesSemana(String cuantasVecesSemana) {
        this.cuantasVecesSemana = cuantasVecesSemana;
    }

    public String getManualidades() {
        return manualidades;
    }

    public void setManualidades(String manualidades) {
        this.manualidades = manualidades;
    }

    public String getCualesDos() {
        return cualesDos;
    }

    public void setCualesDos(String cualesDos) {
        this.cualesDos = cualesDos;
    }

    public String getMenarca() {
        return menarca;
    }

    public void setMenarca(String menarca) {
        this.menarca = menarca;
    }

    public String getGravida() {
        return gravida;
    }

    public void setGravida(String gravida) {
        this.gravida = gravida;
    }

    public String getPartos() {
        return partos;
    }

    public void setPartos(String partos) {
        this.partos = partos;
    }

    public String getCesareas() {
        return cesareas;
    }

    public void setCesareas(String cesareas) {
        this.cesareas = cesareas;
    }

    public String getAbortos() {
        return abortos;
    }

    public void setAbortos(String abortos) {
        this.abortos = abortos;
    }

    public String getHijosVivos() {
        return hijosVivos;
    }

    public void setHijosVivos(String hijosVivos) {
        this.hijosVivos = hijosVivos;
    }

    public Date getFechaUltimaMenst() {
        return fechaUltimaMenst;
    }

    public void setFechaUltimaMenst(Date fechaUltimaMenst) {
        this.fechaUltimaMenst = fechaUltimaMenst;
    }

    public Date getCitologia() {
        return citologia;
    }

    public void setCitologia(Date citologia) {
        this.citologia = citologia;
    }

    public String getCiclos() {
        return ciclos;
    }

    public void setCiclos(String ciclos) {
        this.ciclos = ciclos;
    }

    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    public String getSintOsteom() {
        return sintOsteom;
    }

    public void setSintOsteom(String sintOsteom) {
        this.sintOsteom = sintOsteom;
    }

    public String getCadera() {
        return cadera;
    }

    public void setCadera(String cadera) {
        this.cadera = cadera;
    }

    public String getCervical() {
        return cervical;
    }

    public void setCervical(String cervical) {
        this.cervical = cervical;
    }

    public String getCodos() {
        return codos;
    }

    public void setCodos(String codos) {
        this.codos = codos;
    }

    public String getCuelloUno() {
        return cuelloUno;
    }

    public void setCuelloUno(String cuelloUno) {
        this.cuelloUno = cuelloUno;
    }

    public String getDedos() {
        return dedos;
    }

    public void setDedos(String dedos) {
        this.dedos = dedos;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getDorsolumbar() {
        return dorsolumbar;
    }

    public void setDorsolumbar(String dorsolumbar) {
        this.dorsolumbar = dorsolumbar;
    }

    public String getHombros() {
        return hombros;
    }

    public void setHombros(String hombros) {
        this.hombros = hombros;
    }

    public String getLumbosacra() {
        return lumbosacra;
    }

    public void setLumbosacra(String lumbosacra) {
        this.lumbosacra = lumbosacra;
    }

    public String getManos() {
        return manos;
    }

    public void setManos(String manos) {
        this.manos = manos;
    }

    public String getMuniecas() {
        return muniecas;
    }

    public void setMuniecas(String muniecas) {
        this.muniecas = muniecas;
    }

    public String getPies() {
        return pies;
    }

    public void setPies(String pies) {
        this.pies = pies;
    }

    public String getRodillasUno() {
        return rodillasUno;
    }

    public void setRodillasUno(String rodillasUno) {
        this.rodillasUno = rodillasUno;
    }

    public String getDescOsteom() {
        return descOsteom;
    }

    public void setDescOsteom(String descOsteom) {
        this.descOsteom = descOsteom;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getRevisionSistemas() {
        return revisionSistemas;
    }

    public void setRevisionSistemas(String revisionSistemas) {
        this.revisionSistemas = revisionSistemas;
    }

    public String getDescSiste() {
        return descSiste;
    }

    public void setDescSiste(String descSiste) {
        this.descSiste = descSiste;
    }

    public String getAparienciaFisica() {
        return aparienciaFisica;
    }

    public void setAparienciaFisica(String aparienciaFisica) {
        this.aparienciaFisica = aparienciaFisica;
    }

    public String getDominancia() {
        return dominancia;
    }

    public void setDominancia(String dominancia) {
        this.dominancia = dominancia;
    }

    public Float getTalla() {
        return talla;
    }

    public void setTalla(Float talla) {
        this.talla = talla;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getImc() {
        return imc;
    }

    public void setImc(Float imc) {
        this.imc = imc;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTas() {
        return tas;
    }

    public void setTas(String tas) {
        this.tas = tas;
    }

    public String getTad() {
        return tad;
    }

    public void setTad(String tad) {
        this.tad = tad;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getArcoMovilidadArt() {
        return arcoMovilidadArt;
    }

    public void setArcoMovilidadArt(String arcoMovilidadArt) {
        this.arcoMovilidadArt = arcoMovilidadArt;
    }

    public String getBoca() {
        return boca;
    }

    public void setBoca(String boca) {
        this.boca = boca;
    }

    public String getCicaTatu() {
        return cicaTatu;
    }

    public void setCicaTatu(String cicaTatu) {
        this.cicaTatu = cicaTatu;
    }

    public String getColumCerv() {
        return columCerv;
    }

    public void setColumCerv(String columCerv) {
        this.columCerv = columCerv;
    }

    public String getColumLumbo() {
        return columLumbo;
    }

    public void setColumLumbo(String columLumbo) {
        this.columLumbo = columLumbo;
    }

    public String getColumTora() {
        return columTora;
    }

    public void setColumTora(String columTora) {
        this.columTora = columTora;
    }

    public String getColumVertebral() {
        return columVertebral;
    }

    public void setColumVertebral(String columVertebral) {
        this.columVertebral = columVertebral;
    }

    public String getCorazon() {
        return corazon;
    }

    public void setCorazon(String corazon) {
        this.corazon = corazon;
    }

    public String getCuelloDos() {
        return cuelloDos;
    }

    public void setCuelloDos(String cuelloDos) {
        this.cuelloDos = cuelloDos;
    }

    public String getFilkenstein() {
        return filkenstein;
    }

    public void setFilkenstein(String filkenstein) {
        this.filkenstein = filkenstein;
    }

    public String getGenitales() {
        return genitales;
    }

    public void setGenitales(String genitales) {
        this.genitales = genitales;
    }

    public String getHernias() {
        return hernias;
    }

    public void setHernias(String hernias) {
        this.hernias = hernias;
    }

    public String getLasegue() {
        return lasegue;
    }

    public void setLasegue(String lasegue) {
        this.lasegue = lasegue;
    }

    public String getLimitacionFuncional() {
        return limitacionFuncional;
    }

    public void setLimitacionFuncional(String limitacionFuncional) {
        this.limitacionFuncional = limitacionFuncional;
    }

    public String getMarchaPuntas() {
        return marchaPuntas;
    }

    public void setMarchaPuntas(String marchaPuntas) {
        this.marchaPuntas = marchaPuntas;
    }

    public String getMarchaTalones() {
        return marchaTalones;
    }

    public void setMarchaTalones(String marchaTalones) {
        this.marchaTalones = marchaTalones;
    }

    public String getMiembrosInf() {
        return miembrosInf;
    }

    public void setMiembrosInf(String miembrosInf) {
        this.miembrosInf = miembrosInf;
    }

    public String getMiembrosSup() {
        return miembrosSup;
    }

    public void setMiembrosSup(String miembrosSup) {
        this.miembrosSup = miembrosSup;
    }

    public String getOidos() {
        return oidos;
    }

    public void setOidos(String oidos) {
        this.oidos = oidos;
    }

    public String getOjos() {
        return ojos;
    }

    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public String getPiel() {
        return piel;
    }

    public void setPiel(String piel) {
        this.piel = piel;
    }

    public String getPulmones() {
        return pulmones;
    }

    public void setPulmones(String pulmones) {
        this.pulmones = pulmones;
    }

    public String getRodillasDos() {
        return rodillasDos;
    }

    public void setRodillasDos(String rodillasDos) {
        this.rodillasDos = rodillasDos;
    }

    public String getSilla() {
        return silla;
    }

    public void setSilla(String silla) {
        this.silla = silla;
    }

    public String getTinel() {
        return tinel;
    }

    public void setTinel(String tinel) {
        this.tinel = tinel;
    }

    public String getVaricesDos() {
        return varicesDos;
    }

    public void setVaricesDos(String varicesDos) {
        this.varicesDos = varicesDos;
    }

    public String getTrabajoAlturas() {
        return trabajoAlturas;
    }

    public void setTrabajoAlturas(String trabajoAlturas) {
        this.trabajoAlturas = trabajoAlturas;
    }

    public String getPruebasVestibular() {
        return pruebasVestibular;
    }

    public void setPruebasVestibular(String pruebasVestibular) {
        this.pruebasVestibular = pruebasVestibular;
    }

    public Date getFechaEncuesta() {
        return fechaEncuesta;
    }

    public void setFechaEncuesta(Date fechaEncuesta) {
        this.fechaEncuesta = fechaEncuesta;
    }

    public String getTrabajadorMenorEdad() {
        return trabajadorMenorEdad;
    }

    public void setTrabajadorMenorEdad(String trabajadorMenorEdad) {
        this.trabajadorMenorEdad = trabajadorMenorEdad;
    }

    public String getObsUno() {
        return obsUno;
    }

    public void setObsUno(String obsUno) {
        this.obsUno = obsUno;
    }

    public String getMujerGestante() {
        return mujerGestante;
    }

    public void setMujerGestante(String mujerGestante) {
        this.mujerGestante = mujerGestante;
    }

    public String getPanicoAlturas() {
        return panicoAlturas;
    }

    public void setPanicoAlturas(String panicoAlturas) {
        this.panicoAlturas = panicoAlturas;
    }

    public String getRestriccionesLabdos() {
        return restriccionesLabdos;
    }

    public void setRestriccionesLabdos(String restriccionesLabdos) {
        this.restriccionesLabdos = restriccionesLabdos;
    }

    public String getLimitacionesFisicas() {
        return limitacionesFisicas;
    }

    public void setLimitacionesFisicas(String limitacionesFisicas) {
        this.limitacionesFisicas = limitacionesFisicas;
    }

    public String getTraumaticosdos() {
        return traumaticosdos;
    }

    public void setTraumaticosdos(String traumaticosdos) {
        this.traumaticosdos = traumaticosdos;
    }

    public String getCeguera() {
        return ceguera;
    }

    public void setCeguera(String ceguera) {
        this.ceguera = ceguera;
    }

    public String getVisionCercanaAlterada() {
        return visionCercanaAlterada;
    }

    public void setVisionCercanaAlterada(String visionCercanaAlterada) {
        this.visionCercanaAlterada = visionCercanaAlterada;
    }

    public String getDislipidemia() {
        return dislipidemia;
    }

    public void setDislipidemia(String dislipidemia) {
        this.dislipidemia = dislipidemia;
    }

    public String getHiperglicemia() {
        return hiperglicemia;
    }

    public void setHiperglicemia(String hiperglicemia) {
        this.hiperglicemia = hiperglicemia;
    }

    public String getImcmayor() {
        return imcmayor;
    }

    public void setImcmayor(String imcmayor) {
        this.imcmayor = imcmayor;
    }

    public String getAntecedentesEnfermedades() {
        return antecedentesEnfermedades;
    }

    public void setAntecedentesEnfermedades(String antecedentesEnfermedades) {
        this.antecedentesEnfermedades = antecedentesEnfermedades;
    }

    public String getAntecedentesAdiccion() {
        return antecedentesAdiccion;
    }

    public void setAntecedentesAdiccion(String antecedentesAdiccion) {
        this.antecedentesAdiccion = antecedentesAdiccion;
    }

    public String getAlteraciones() {
        return alteraciones;
    }

    public void setAlteraciones(String alteraciones) {
        this.alteraciones = alteraciones;
    }

    public String getSignosSintomasVisuales() {
        return signosSintomasVisuales;
    }

    public void setSignosSintomasVisuales(String signosSintomasVisuales) {
        this.signosSintomasVisuales = signosSintomasVisuales;
    }

    public String getAlteracionesVisuales() {
        return alteracionesVisuales;
    }

    public void setAlteracionesVisuales(String alteracionesVisuales) {
        this.alteracionesVisuales = alteracionesVisuales;
    }

    public String getAlteracionesColor() {
        return alteracionesColor;
    }

    public void setAlteracionesColor(String alteracionesColor) {
        this.alteracionesColor = alteracionesColor;
    }

    public String getAlteracionProfundidad() {
        return alteracionProfundidad;
    }

    public void setAlteracionProfundidad(String alteracionProfundidad) {
        this.alteracionProfundidad = alteracionProfundidad;
    }

    public String getVisionBinocularAlterada() {
        return visionBinocularAlterada;
    }

    public void setVisionBinocularAlterada(String visionBinocularAlterada) {
        this.visionBinocularAlterada = visionBinocularAlterada;
    }

    public String getPlanoHorizontalVision() {
        return planoHorizontalVision;
    }

    public void setPlanoHorizontalVision(String planoHorizontalVision) {
        this.planoHorizontalVision = planoHorizontalVision;
    }

    public String getSignosSintomasAuditivos() {
        return signosSintomasAuditivos;
    }

    public void setSignosSintomasAuditivos(String signosSintomasAuditivos) {
        this.signosSintomasAuditivos = signosSintomasAuditivos;
    }

    public String getAlteracionesAudicion() {
        return alteracionesAudicion;
    }

    public void setAlteracionesAudicion(String alteracionesAudicion) {
        this.alteracionesAudicion = alteracionesAudicion;
    }

    public String getSintomasMetabolicos() {
        return sintomasMetabolicos;
    }

    public void setSintomasMetabolicos(String sintomasMetabolicos) {
        this.sintomasMetabolicos = sintomasMetabolicos;
    }

    public String getLaboratoriosAlteraciones() {
        return laboratoriosAlteraciones;
    }

    public void setLaboratoriosAlteraciones(String laboratoriosAlteraciones) {
        this.laboratoriosAlteraciones = laboratoriosAlteraciones;
    }

    public String getSintomasCardiovasculares() {
        return sintomasCardiovasculares;
    }

    public void setSintomasCardiovasculares(String sintomasCardiovasculares) {
        this.sintomasCardiovasculares = sintomasCardiovasculares;
    }

    public String getAccidenteCerebrovascular() {
        return accidenteCerebrovascular;
    }

    public void setAccidenteCerebrovascular(String accidenteCerebrovascular) {
        this.accidenteCerebrovascular = accidenteCerebrovascular;
    }

    public String getImcDos() {
        return imcDos;
    }

    public void setImcDos(String imcDos) {
        this.imcDos = imcDos;
    }

    public String getSintomasMentales() {
        return sintomasMentales;
    }

    public void setSintomasMentales(String sintomasMentales) {
        this.sintomasMentales = sintomasMentales;
    }

    public String getAntecedentesMedicamentos() {
        return antecedentesMedicamentos;
    }

    public void setAntecedentesMedicamentos(String antecedentesMedicamentos) {
        this.antecedentesMedicamentos = antecedentesMedicamentos;
    }

    public String getSindromeConvulsivo() {
        return sindromeConvulsivo;
    }

    public void setSindromeConvulsivo(String sindromeConvulsivo) {
        this.sindromeConvulsivo = sindromeConvulsivo;
    }

    public String getAlteracionesEquilibrio() {
        return alteracionesEquilibrio;
    }

    public void setAlteracionesEquilibrio(String alteracionesEquilibrio) {
        this.alteracionesEquilibrio = alteracionesEquilibrio;
    }

    public String getAlteracionesAtencion() {
        return alteracionesAtencion;
    }

    public void setAlteracionesAtencion(String alteracionesAtencion) {
        this.alteracionesAtencion = alteracionesAtencion;
    }

    public String getAlteracionesComportamiento() {
        return alteracionesComportamiento;
    }

    public void setAlteracionesComportamiento(String alteracionesComportamiento) {
        this.alteracionesComportamiento = alteracionesComportamiento;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public String getConsentimientoinformado() {
        return consentimientoinformado;
    }

    public void setConsentimientoinformado(String consentimientoinformado) {
        this.consentimientoinformado = consentimientoinformado;
    }

    

    public String getExaPeriodicoMedicinalaboral() {
        return exaPeriodicoMedicinalaboral;
    }

    public void setExaPeriodicoMedicinalaboral(String exaPeriodicoMedicinalaboral) {
        this.exaPeriodicoMedicinalaboral = exaPeriodicoMedicinalaboral;
    }

    public String getExaEgreso() {
        return exaEgreso;
    }

    public void setExaEgreso(String exaEgreso) {
        this.exaEgreso = exaEgreso;
    }

    public String getEmocol() {
        return emocol;
    }

    public void setEmocol(String emocol) {
        this.emocol = emocol;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAcompanante() {
        return acompanante;
    }

    public void setAcompanante(String acompanante) {
        this.acompanante = acompanante;
    }

    public String getConductasOcupacionales() {
        return conductasOcupacionales;
    }

    public void setConductasOcupacionales(String conductasOcupacionales) {
        this.conductasOcupacionales = conductasOcupacionales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getResultadoConceptoFinal() {
        return resultadoConceptoFinal;
    }

    public void setResultadoConceptoFinal(String resultadoConceptoFinal) {
        this.resultadoConceptoFinal = resultadoConceptoFinal;
    }

    public String getObservacionesCiediez() {
        return observacionesCiediez;
    }

    public void setObservacionesCiediez(String observacionesCiediez) {
        this.observacionesCiediez = observacionesCiediez;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public String getVisiometria() {
        return visiometria;
    }

    public void setVisiometria(String visiometria) {
        this.visiometria = visiometria;
    }

    public String getAudiometria() {
        return audiometria;
    }

    public void setAudiometria(String audiometria) {
        this.audiometria = audiometria;
    }

    public String getEspirometriaComputalizada() {
        return espirometriaComputalizada;
    }

    public void setEspirometriaComputalizada(String espirometriaComputalizada) {
        this.espirometriaComputalizada = espirometriaComputalizada;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getConsentimientoInformado() {
        return consentimientoInformado;
    }

    public void setConsentimientoInformado(String consentimientoInformado) {
        this.consentimientoInformado = consentimientoInformado;
    }

    public String getSistemasVigilancia() {
        return sistemasVigilancia;
    }

    public void setSistemasVigilancia(String sistemasVigilancia) {
        this.sistemasVigilancia = sistemasVigilancia;
    }

    public String getObservacionesEspecificas() {
        return observacionesEspecificas;
    }

    public void setObservacionesEspecificas(String observacionesEspecificas) {
        this.observacionesEspecificas = observacionesEspecificas;
    }

    public String getCiudadAtencion() {
        return ciudadAtencion;
    }

    public void setCiudadAtencion(String ciudadAtencion) {
        this.ciudadAtencion = ciudadAtencion;
    }

    public CieDiez getCieDiezIdCie() {
        return cieDiezIdCie;
    }

    public void setCieDiezIdCie(CieDiez cieDiezIdCie) {
        this.cieDiezIdCie = cieDiezIdCie;
    }

    public CieDiez getCieDiezIdCie1() {
        return cieDiezIdCie1;
    }

    public void setCieDiezIdCie1(CieDiez cieDiezIdCie1) {
        this.cieDiezIdCie1 = cieDiezIdCie1;
    }

    public CieDiez getCieDiezIdCie2() {
        return cieDiezIdCie2;
    }

    public void setCieDiezIdCie2(CieDiez cieDiezIdCie2) {
        this.cieDiezIdCie2 = cieDiezIdCie2;
    }

    public CieDiez getCieDiezIdCie3() {
        return cieDiezIdCie3;
    }

    public void setCieDiezIdCie3(CieDiez cieDiezIdCie3) {
        this.cieDiezIdCie3 = cieDiezIdCie3;
    }

    public CieDiez getCieDiezIdCie4() {
        return cieDiezIdCie4;
    }

    public void setCieDiezIdCie4(CieDiez cieDiezIdCie4) {
        this.cieDiezIdCie4 = cieDiezIdCie4;
    }

    public CieDiez getCieDiezIdCie5() {
        return cieDiezIdCie5;
    }

    public void setCieDiezIdCie5(CieDiez cieDiezIdCie5) {
        this.cieDiezIdCie5 = cieDiezIdCie5;
    }

    public Pacientes getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Pacientes idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Recomendaciones getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Recomendaciones idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<AccidentesLaborales> getAccidentesLaboralesList() {
        return accidentesLaboralesList;
    }

    public void setAccidentesLaboralesList(List<AccidentesLaborales> accidentesLaboralesList) {
        this.accidentesLaboralesList = accidentesLaboralesList;
    }

    @XmlTransient
    public List<AntecedentesOcupacionales> getAntecedentesOcupacionalesList() {
        return antecedentesOcupacionalesList;
    }

    public void setAntecedentesOcupacionalesList(List<AntecedentesOcupacionales> antecedentesOcupacionalesList) {
        this.antecedentesOcupacionalesList = antecedentesOcupacionalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmo != null ? idEmo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emo)) {
            return false;
        }
        Emo other = (Emo) object;
        if ((this.idEmo == null && other.idEmo != null) || (this.idEmo != null && !this.idEmo.equals(other.idEmo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Emo[ idEmo=" + idEmo + " ]";
    }
    
}
