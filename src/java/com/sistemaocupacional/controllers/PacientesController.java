package com.sistemaocupacional.controllers;

import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.entities.AccidentesLaborales;
import com.sistemaocupacional.entities.AntecedentesOcupacionales;
import com.sistemaocupacional.entities.Audiometrias;
import com.sistemaocupacional.entities.CieDiez;
import com.sistemaocupacional.entities.Emo;
import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.entities.Espirometrias;
import com.sistemaocupacional.entities.Laboratorio;
import com.sistemaocupacional.entities.Optometria;
import com.sistemaocupacional.entities.Pacientes;
import com.sistemaocupacional.entities.Recomendaciones;
import com.sistemaocupacional.entities.Usuarios;
import com.sistemaocupacional.sessions.AccidentesLaboralesFacade;
import com.sistemaocupacional.sessions.AntecedentesOcupacionalesFacade;
import com.sistemaocupacional.sessions.AudiometriasFacade;
import com.sistemaocupacional.sessions.CieDiezFacade;
import com.sistemaocupacional.sessions.EmoFacade;
import com.sistemaocupacional.sessions.EmpresasFacade;
import com.sistemaocupacional.sessions.EspirometriasFacade;
import com.sistemaocupacional.sessions.LaboratorioFacade;
import com.sistemaocupacional.sessions.OptometriaFacade;
import com.sistemaocupacional.sessions.PacientesFacade;
import com.sistemaocupacional.sessions.RecomendacionesFacade;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Named;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("pacientesController")
@SessionScoped
public class PacientesController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.PacientesFacade ejbFacade;
    @EJB
    private EspirometriasFacade ejbEspirometrias;
    @EJB
    private LaboratorioFacade laboratorioFacade;
    @EJB
    private OptometriaFacade optometriaFacade;
    @EJB
    private AudiometriasFacade audioFacade;
    @EJB
    private EmoFacade emoFacade;
    @EJB
    private EmpresasFacade empresasFacade;
    @EJB
    private CieDiezFacade cieFacade;
    @EJB
    private AntecedentesOcupacionalesFacade antecedentesFacade;
    @EJB
    private AccidentesLaboralesFacade accidentesFacade;
    @EJB
    private RecomendacionesFacade recomendacionFacade;
    private Recomendaciones recomendacionActual;

    private List<Pacientes> items = null;
    private List<Espirometrias> espirometriasList;
    private List<Empresas> empresasList;
    private List<CieDiez> cieList;
    private CieDiez cieActual;

    private Pacientes selected;
    private Espirometrias espiroActual;
    private Laboratorio laboratorioActual;
    private Audiometrias audioActual;
    private Optometria optometriaActual;
    private AntecedentesOcupacionales antecedenteActual;
    private List<AntecedentesOcupacionales> listaAntecedentes;
    private AccidentesLaborales accidenteActual;
    private List<AccidentesLaborales> listaAccidentes;

    private Emo emoActual;
    private String filename;
    private UploadedFile file;
    private UploadedFile fileFirma;
    private StreamedContent imagen;
    private StreamedContent imagenFirma;
    private FileImageOutputStream imageOutput;
    private byte[] data;
    private float peso;
    private float talla;
    private float imc;
    private String cad;
    private String resultadoConcepto;
    private String tipoConcepto;
    private String tipoPerfil;
    private String[] conductas;
   

    public PacientesController() {

    }

    public String getCad() {
        if (imc < 16.00) {
            cad = "Infrapeso: Delgadez Severa";
        } else if (imc <= 16.00 || imc <= 16.99) {
            cad = "Infrapeso: Delgadez moderada";
        } else if (imc <= 17.00 || imc <= 18.49) {
            cad = "Infrapeso: Delgadez aceptable";
        } else if (imc <= 18.50 || imc <= 24.99) {
            cad = "Peso Normal";
        } else if (imc <= 25.00 || imc <= 29.99) {
            cad = "Sobrepeso";
        } else if (imc <= 30.00 || imc <= 34.99) {
            cad = "Obeso: Tipo I";
        } else if (imc <= 35.00 || imc <= 39.00) {
            cad = "Obeso: Tipo II";
        } else if (imc >= 40.00) {
            cad = "Obeso: Tipo III";
        } else {
            cad = "no existe clasificación";
        }
        return cad;
    }

    public String getResultadoConcepto() {
        if (tipoConcepto.equals("PRE-INGRESO") && tipoPerfil.equals("MANIPULADORES DE ALIMENTOS")) {
            resultadoConcepto = "-Apto para manipular alimentos\n"
                    + "-No es apto para manipular alimentos\n"
                    + "-Concepto aplazado para manipular alimentos";
        } else if (tipoConcepto.equals("PRE-INGRESO") && tipoPerfil.equals("TRABAJO EN ALTURAS")) {
            resultadoConcepto = "-Apto para trabajar en alturas\n"
                    + "-No es apto para trabajar en alturas\n"
                    + "-Concepto aplazado para trabajar en alturas";
        } else if (tipoConcepto.equals("PRE-INGRESO") && tipoPerfil.equals("EXAMEN MEDICO OCUPACIONAL")) {
            resultadoConcepto = "-Apto sin restricciones\n"
                    + "-Apto con restricciones\n"
                    + "-Aplazado";
        } else if (tipoConcepto.equals("PERIODICO") && tipoPerfil.equals("MANIPULADORES DE ALIMENTOS")) {
            resultadoConcepto = "-Apto para manipular alimentos\n"
                    + "-No es apto para manipular alimentos\n"
                    + "-Concepto aplazado para manipular alimentos";
        } else if (tipoConcepto.equals("PERIODICO") && tipoPerfil.equals("TRABAJO EN ALTURAS")) {
            resultadoConcepto = "-Apto para trabajar en alturas\n"
                    + "-No es apto para trabajar en alturas\n"
                    + "-Concepto aplazado para trabajar en alturas";
        }
        return resultadoConcepto;
    }

    public void setResultadoConcepto(String resultadoConcepto) {
        this.resultadoConcepto = resultadoConcepto;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public void setCad(String cad) {
        this.cad = cad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public float getImc() {
        imc = (float) (peso / (talla * talla));
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public RecomendacionesFacade getRecomendacionFacade() {
        return recomendacionFacade;
    }

    public void setRecomendacionFacade(RecomendacionesFacade recomendacionFacade) {
        this.recomendacionFacade = recomendacionFacade;
    }

    public Recomendaciones getRecomendacionActual() {
        return recomendacionActual;
    }

    public void setRecomendacionActual(Recomendaciones recomendacionActual) {
        this.recomendacionActual = recomendacionActual;
    }

    public String[] getConductas() {

        return conductas;
    }

    public void setConductas(String[] conductas) {
        this.conductas = conductas;
    }

    public AntecedentesOcupacionales getAntecedenteActual() {
        return antecedenteActual;
    }

    public void setAntecedenteActual(AntecedentesOcupacionales selected) {
        this.antecedenteActual = selected;
    }

    public AccidentesLaborales getAccidenteActual() {
        return accidenteActual;
    }

    public void setAccidenteActual(AccidentesLaborales selected) {
        this.accidenteActual = selected;
    }

    public CieDiezFacade getCieFacade() {
        return cieFacade;
    }

    public void setCieFacade(CieDiezFacade cieFacade) {
        this.cieFacade = cieFacade;
    }

    public List<CieDiez> getCieList() {
        return cieList;
    }

    public void setCieList(List<CieDiez> cieList) {
        this.cieList = cieList;
    }

    public CieDiez getCieActual() {
        return cieActual;
    }

    public void setCieActual(CieDiez cieActual) {
        this.cieActual = cieActual;
    }

    public EmpresasFacade getEmpresasFacade() {
        return empresasFacade;
    }

    public void setEmpresasFacade(EmpresasFacade empresasFacade) {
        this.empresasFacade = empresasFacade;
    }

    public List<Empresas> getEmpresasList() {
        return empresasList;
    }

    public void setEmpresasList(List<Empresas> empresasList) {
        this.empresasList = empresasList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFileFirma() {
        return fileFirma;
    }

    public void setFileFirma(UploadedFile fileFirma) {
        this.fileFirma = fileFirma;
    }

    public EmoFacade getEmoFacade() {
        return emoFacade;
    }

    public void setEmoFacade(EmoFacade emoFacade) {
        this.emoFacade = emoFacade;
    }

    public Emo getEmoActual() {
        return emoActual;
    }

    public void setEmoActual(Emo emoActual) {
        this.emoActual = emoActual;
    }

    public OptometriaFacade getOptometriaFacade() {
        return optometriaFacade;
    }

    public void setOptometriaFacade(OptometriaFacade optometriaFacade) {
        this.optometriaFacade = optometriaFacade;
    }

    public Optometria getOptometriaActual() {
        return optometriaActual;
    }

    public void setOptometriaActual(Optometria optometriaActual) {
        this.optometriaActual = optometriaActual;
    }

    public AudiometriasFacade getAudioFacade() {
        return audioFacade;
    }

    public void setAudioFacade(AudiometriasFacade audioFacade) {
        this.audioFacade = audioFacade;
    }

    public Audiometrias getAudioActual() {
        return audioActual;
    }

    public void setAudioActual(Audiometrias audioActual) {
        this.audioActual = audioActual;
    }

    public LaboratorioFacade getLaboratorioFacade() {
        return laboratorioFacade;
    }

    public void setLaboratorioFacade(LaboratorioFacade laboratorioFacade) {
        this.laboratorioFacade = laboratorioFacade;
    }

    public Laboratorio getLaboratorioActual() {
        return laboratorioActual;
    }

    public void setLaboratorioActual(Laboratorio laboratorioActual) {
        this.laboratorioActual = laboratorioActual;
    }

    public EspirometriasFacade getEjbEspirometrias() {
        return ejbEspirometrias;
    }

    public void setEjbEspirometrias(EspirometriasFacade ejbEspirometrias) {
        this.ejbEspirometrias = ejbEspirometrias;
    }

    public List<Espirometrias> getEspirometriasList() {
        return espirometriasList;
    }

    public void setEspirometriasList(List<Espirometrias> espirometriasList) {
        this.espirometriasList = espirometriasList;
    }

    public Espirometrias getEspiroActual() {
        return espiroActual;
    }

    public void setEspiroActual(Espirometrias espiroActual) {
        this.espiroActual = espiroActual;
    }

    public Pacientes getSelected() {
        return selected;
    }

    public void setSelected(Pacientes selected) {
        this.selected = selected;
    }

    public AntecedentesOcupacionalesFacade getAntecedentesFacade() {
        return antecedentesFacade;
    }

    public void setAntecedentesFacade(AntecedentesOcupacionalesFacade antecedentesFacade) {
        this.antecedentesFacade = antecedentesFacade;
    }

    public AccidentesLaboralesFacade getAccidentesFacade() {
        return accidentesFacade;
    }

    public void setAccidentesFacade(AccidentesLaboralesFacade accidentesLaboralesFacade) {
        this.accidentesFacade = accidentesLaboralesFacade;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PacientesFacade getFacade() {
        return ejbFacade;
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        data = captureEvent.getData();

        String carpeta = "C:\\fotoPaciente\\";
        String newFileName
                = "C:\\fotoPaciente" + File.separator + filename + ".jpg";

        File fileCreate = new File(carpeta);
        try {

            if (fileCreate.exists()) {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto tomada!", "Buscarla en el disco C carpeta fotoPaciente"));
            } else {

                fileCreate.mkdir();

                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto tomada!", "Seleccionala en la carpeta fotoPaciente"));

            }

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo tomar la foto"));
            throw new FacesException("Error in writing captured image.", e);

        }
    }

    public String prepareCreateEspirometrias() {
        espiroActual = new Espirometrias();
        return "/espirometrias/Create2";
    }

    public String prepareCreateEmo() {
        emoActual = new Emo();
        antecedenteActual = new AntecedentesOcupacionales();
        accidenteActual = new AccidentesLaborales();
        initializeEmbeddableKey();
        listaAntecedentes = new ArrayList<>();
        listaAccidentes = new ArrayList<>();
        iniciar();
        return "/emo/Create2";
    }

    public boolean isGenero() {
        boolean genero = false;
        Character letra;
        letra = 'F';
        if (Objects.equals(letra, selected.getGenero())) {
            genero = true;
        }

        return genero;

    }

    public String prepareCreateOptometria() {
        optometriaActual = new Optometria();
        return "/optometria/Create2";
    }

    public String prepareCreateLaboratorio() {
        laboratorioActual = new Laboratorio();
        return "/laboratorio/Create2";
    }

    public String prepareCreateAudiometria() {
        audioActual = new Audiometrias();
        return "/audiometrias/Create2";
    }

    public String prepareCreate() {
        selected = new Pacientes();
        initializeEmbeddableKey();
        return "/pacientes/Create";
    }

    public String prepareEdit() {
        return "/pacientes/Edit";
    }

    public void llamarReporte() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/Sistema_Ocupacional/ReporteDePaciente?numero=" + selected.getNumDoc());
    }

    public String prepareView() {

        return "/pacientes/View";
    }

    public void messageCapture() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "La captura fue realizada"));
    }

    public StreamedContent getImagen() {
        InputStream input = new ByteArrayInputStream(selected.getFoto());

        imagen = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");

        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public StreamedContent getImagenFirma() {
        InputStream input = new ByteArrayInputStream(selected.getImgFirma());

        imagenFirma = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");
        return imagenFirma;
    }

    public void setImagenFirma(StreamedContent imagenFirma) {
        this.imagenFirma = imagenFirma;
    }

    public void validarDocumento(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getFacade().finByNumeroDocumento((String) value) != null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Documento repetido", "El documento ya existe en la base de datos"));
        } else {
            selected.setNumDoc((String) value);
        }
    }

    public void agregarAntecedente() {
        listaAntecedentes.add(antecedenteActual);
        antecedenteActual = new AntecedentesOcupacionales();
        initializeEmbeddableKey();
    }

    public void eliminarAntecedente() {
        listaAntecedentes.remove(antecedenteActual);
    }

    public List<AntecedentesOcupacionales> getListaAntecedentes() {
        return listaAntecedentes;
    }

    public void setListaAntecedentes(List<AntecedentesOcupacionales> listaAntecedentes) {
        this.listaAntecedentes = listaAntecedentes;
    }

    public void agregarAntecedentes() {
        listaAntecedentes.add(antecedenteActual);
        antecedenteActual = new AntecedentesOcupacionales();
        initializeEmbeddableKey();
    }

    public void agregarAccidente() {
        listaAccidentes.add(accidenteActual);
        accidenteActual = new AccidentesLaborales();
        initializeEmbeddableKey();
    }

    public void eliminarAccidente() {
        listaAccidentes.remove(accidenteActual);
    }

    public List<AccidentesLaborales> getListaAccidentes() {
        return listaAccidentes;
    }

    public void setListaAccidentes(List<AccidentesLaborales> list) {
        this.listaAccidentes = list;
    }

    public void agregarAccidentes() {
        listaAccidentes.add(accidenteActual);
        accidenteActual = new AccidentesLaborales();
        initializeEmbeddableKey();
    }

    public String addEmo() {

        try {
            Character masculino;
            masculino = 'M';
            if (Objects.equals(masculino, selected.getGenero())) {
                emoActual.setIdRecomendacion(new Recomendaciones(1));

            } else {
                emoActual.setIdRecomendacion(new Recomendaciones(2));
            }

            Usuarios us;
            us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            emoActual.setIdUsuario(us);
            emoActual.setTalla(talla);
            emoActual.setPeso(peso);
            emoActual.setImc(imc);
            emoActual.setClasificacion(cad);
            emoActual.setIdPaciente(selected);
            
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd '/' MM '/' yyyy HH:mm:ss", new Locale("es_ES"));
        
            emoActual.setFechaCreacion(formateador.format(ahora));
            emoActual.setFechaDeModificacion(formateador.format(ahora));
           /* emoActual.setFechaCreacion(new Date(System.currentTimeMillis()).toString());*/
            
            StringBuffer cadena = new StringBuffer();
            for (String conducta : conductas) {
                cadena = cadena.append(conducta).append("\n");
            }
        
            emoActual.setConductasOcupacionales(cadena.toString());

            getEmoFacade().create(emoActual);
            for (AntecedentesOcupacionales item : listaAntecedentes) {

                antecedenteActual = item;
                antecedenteActual.setIdEmo(emoActual);
                antecedentesFacade.create(antecedenteActual);
            }
            for (AccidentesLaborales item : listaAccidentes) {

                accidenteActual = item;
                accidenteActual.setIdEmo(emoActual);
                accidentesFacade.create(accidenteActual);
            }

            iniciar();
            emoActual = new Emo();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "El examen ha sido creada"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));

        }

        return "/pacientes/List";
    }

    public void iniciar() {
        talla = 0;
        peso = 0;
        imc = 0;
    }

    public String addOptometria() {
        try {
            
            optometriaActual.setIdPaciente(selected);
            getOptometriaFacade().create(optometriaActual);
            optometriaActual = new Optometria();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "La Optometría ha sido creada"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));
        }

        return "/pacientes/List";
    }

    public String addEspirometria() {
        try {

            espiroActual.setIdPaciente(selected);
            getEjbEspirometrias().create(espiroActual);
            espiroActual = new Espirometrias();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "La espirometría ha sido creada"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));
        }

        return "/pacientes/List";
    }

    public String addLaboratorio() {
        try {

            laboratorioActual.setIdPaciente(selected);
            getLaboratorioFacade().create(laboratorioActual);
            laboratorioActual = new Laboratorio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Los datos de Laboratorio han sido creada"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));
        }

        return "/laboratorio/List";
    }

    public String addAudiometria() {
        try {

            audioActual.setIdPaciente(selected);
            getAudioFacade().create(audioActual);
            audioActual = new Audiometrias();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Los datos han sido guardados"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso!", "un error ocurrio"));
        }

        return "/pacientes/List";
    }

    public String create() {
        try {
            InputStream firma = fileFirma.getInputstream();
            byte[] bufferFirma = new byte[(int) fileFirma.getSize()];
            int leer = firma.read(bufferFirma);
            selected.setImgFirma(bufferFirma);
            selected.setFoto(data);
           Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd '/' MM '/' yyyy HH:mm:ss", new Locale("es_ES"));
        
            selected.setFechaCreacion(formateador.format(ahora));

        } catch (IOException e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("PacientesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/pacientes/List";
    }

    public String update() throws IOException {

        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("PacientesUpdated"));
        return "/pacientes/List";
    }

    public void updateIMG() throws IOException {
        System.out.println(Arrays.toString(selected.getImgFirma()));

        InputStream firma = fileFirma.getInputstream();
        byte[] bufferFirma = new byte[(int) fileFirma.getSize()];
        int leer = firma.read(bufferFirma);
        selected.setImgFirma(bufferFirma);
        selected.setFoto(data);

        getFacade().edit(selected);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Imagenes Actualizadas"));

    }

    public void updateIMGFIRMA() throws IOException {
        System.out.println(Arrays.toString(selected.getImgFirma()));

        InputStream firma = fileFirma.getInputstream();
        byte[] bufferFirma = new byte[(int) fileFirma.getSize()];
        int leer = firma.read(bufferFirma);
        selected.setImgFirma(bufferFirma);

        getFacade().edit(selected);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Imagen de la firma actualizada"));

    }

    public void updateIMGFOTO() throws IOException {
        System.out.println(Arrays.toString(selected.getImgFirma()));

        InputStream foto = file.getInputstream();
        byte[] bufferFoto = new byte[(int) file.getSize()];
        int leerFoto = foto.read(bufferFoto);
        selected.setFoto(bufferFoto);
        getFacade().edit(selected);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Imagen de la foto actualizada"));

    }

    public void updateFirma() throws IOException {

        getFacade().edit(selected);

    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("PacientesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void recargarListaPacientes() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "El paciente ha sido creado"));
        items = null;
    }

    public List<Pacientes> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Empresas> getListClienteAutoComplete(String query) {
        try {
            return getEmpresasFacade().findByNombreEmpresa(query);
        } catch (Exception ex) {

            return null;
        }
    }

    public List<CieDiez> getListCieDiezAutoComplete(String query) {
        try {
            return getCieFacade().findByDescripcion(query);
        } catch (Exception ex) {

            return null;
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public Pacientes getPacientes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Pacientes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pacientes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pacientes.class)
    public static class PacientesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PacientesController controller = (PacientesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pacientesController");
            return controller.getPacientes(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pacientes) {
                Pacientes o = (Pacientes) object;
                return getStringKey(o.getIdPaciente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pacientes.class.getName()});
                return null;
            }
        }

    }

}
