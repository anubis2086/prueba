/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import com.sistemaocupacional.entities.CieDiez;
import com.sistemaocupacional.entities.Emo;
import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.entities.Pacientes;
import com.sistemaocupacional.entities.Recomendaciones;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author santiago
 */
@WebServlet(name = "HistoriaClinica", urlPatterns = {"/HistoriaClinica"})
public class HistoriaClinica extends HttpServlet {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private ResultSet rsu;
    private Pacientes paciente;
    private Empresas empresa;
    private Emo emo;
    private Recomendaciones recomendaciones;
    private CieDiez cieDiez;
    private CieDiez cieDiezDos;
    private CieDiez cieDiezTres;
    private CieDiez cieDiezCuatro;
    private CieDiez cieDiezCinco;
    private CieDiez cieDiezSeis;
    private ResultSet rs2;
    private Statement st2;

    private ResultSet rs3;
    private Statement st3;
    private PdfPTable tabla2;
    private PdfPCell celdaT1;
    private PdfPCell celdaT2;
    private PdfPCell celdaT3;
    private PdfPCell celdaT4;

    private PdfPTable tabla;
    private PdfPCell celda1;
    private PdfPCell celda6;
    private PdfPCell celda2;
    private PdfPCell celda3;
    private PdfPCell celda4;
    private PdfPCell celda5;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();
        String numeroDocumento = request.getParameter("numero");

        try {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaocupacional?user=root&password=Persefone2016");
                st = (Statement) con.createStatement();
                rs = st.executeQuery("SELECT * FROM pacientes AS p\n"
                        + "INNER JOIN empresas AS e ON p.id_clem = e.id_clem\n"
                        + "INNER JOIN emo AS em ON p.id_paciente = em.id_paciente\n"
                        + "INNER JOIN recomendaciones AS re ON em.id_recomendacion = re.id_recomendacion\n"
                        + "INNER JOIN cie_diez AS cd ON em.cie_diez_id_cie = cd.id_cie\n"
                        + "INNER JOIN cie_diez AS cd_dos ON em.cie_diez_id_cie1 = cd_dos.id_cie\n"
                        + "INNER JOIN cie_diez AS cd_tres ON em.cie_diez_id_cie2 = cd_tres.id_cie\n"
                        + "INNER JOIN cie_diez AS cd_cuatro ON em.cie_diez_id_cie3 = cd_cuatro.id_cie\n"
                        + "INNER JOIN cie_diez AS cd_cinco ON em.cie_diez_id_cie4 = cd_cinco.id_cie\n"
                        + "INNER JOIN cie_diez AS cd_seis ON em.cie_diez_id_cie5 = cd_seis.id_cie\n"
                        + "WHERE em.id_emo =" + numeroDocumento + ";");

                while (rs.next()) {
                    paciente = new Pacientes();
                    empresa = new Empresas();
                    emo = new Emo();
                    recomendaciones = new Recomendaciones();
                    cieDiez = new CieDiez();
                    cieDiezDos = new CieDiez();
                    cieDiezTres = new CieDiez();
                    cieDiezCuatro = new CieDiez();
                    cieDiezCinco = new CieDiez();
                    cieDiezSeis = new CieDiez();

                    paciente.setNombres(rs.getString("p.nombres"));
                    paciente.setApellidos(rs.getString("p.apellidos"));
                    paciente.setTipoDoc(rs.getString("p.tipo_doc"));
                    paciente.setNumDoc(rs.getString("p.num_doc"));
                    paciente.setFecNac(rs.getDate("p.fec_nac"));
                    paciente.setEdad(rs.getString("p.edad"));
                    paciente.setTelCel(rs.getString("p.tel_cel"));
                    paciente.setDireccion(rs.getString("p.direccion"));
                    paciente.setCiudad(rs.getString("p.ciudad"));
                    paciente.setCiudadNac(rs.getString("p.ciudad_nac"));
                    paciente.setAfp(rs.getString("p.afp"));
                    paciente.setEps(rs.getString("p.eps"));
                    paciente.setArl(rs.getString("p.arl"));
                    paciente.setGenero(rs.getString("p.genero").charAt(0));
                    paciente.setCargo(rs.getString("p.cargo"));
                    paciente.setFoto(rs.getBytes("p.foto"));
                    paciente.setImgFirma(rs.getBytes("p.img_firma"));
                    paciente.setProfesion(rs.getString("p.profesion"));
                    paciente.setFechaCreacion(rs.getString("p.fecha_creacion"));
                    empresa.setNomClem(rs.getString("e.nom_clem"));
                    empresa.setNitClem(rs.getString("e.nit_clem"));
                    emo.setTipoConcepto(rs.getString("em.tipo_concepto"));
                    emo.setTipoPerfil(rs.getString("em.tipo_perfil"));
                    emo.setQuimicos(rs.getString("em.quimicos"));
                    emo.setTraumaticos(rs.getString("em.traumaticos"));
                    emo.setAlergicos(rs.getString("em.alergicos"));
                    emo.setAsma(rs.getString("em.asma"));
                    emo.setCancer(rs.getString("em.cancer"));
                    emo.setCardiovasculares(rs.getString("em.cardiovasculares"));
                    emo.setDermatologicos(rs.getString("em.dermatologicos"));
                    emo.setDiabetes(rs.getString("em.diabetes"));
                    emo.setEpilepsia(rs.getString("em.epilepsia"));
                    emo.setHipertension(rs.getString("em.hipertension"));
                    emo.setHipotiroidismo(rs.getString("em.hipotiroidismo"));
                    emo.setNeurologicos(rs.getString("em.neurologicos"));
                    emo.setPsiquiatricos(rs.getString("em.psiquiatricos"));
                    emo.setQuimicos(rs.getString("em.quimicos"));
                    emo.setRespiratorias(rs.getString("em.respiratorias"));
                    emo.setReumatologicos(rs.getString("em.reumatologicos"));
                    emo.setTraumaticos(rs.getString("em.traumaticos"));
                    emo.setVaricesUno(rs.getString("em.varices_uno"));
                    emo.setVisuales(rs.getString("em.visuales"));
                    emo.setFumas(rs.getString("em.fumas"));
                    emo.setCuantos(rs.getString("em.cuantos"));
                    emo.setTiempoHaceCuanto(rs.getString("em.tiempo_hace_cuanto"));
                    emo.setExfumador(rs.getString("em.exfumador"));
                    emo.setLicor(rs.getString("em.licor"));
                    emo.setHabitual(rs.getString("em.habitual"));
                    emo.setDeporte(rs.getString("em.deporte"));
                    emo.setQueDeporte(rs.getString("em.que_deporte"));
                    emo.setCuantasVecesSemana(rs.getString("em.cuantas_veces_semana"));
                    emo.setManualidades(rs.getString("em.manualidades"));
                    emo.setCualesDos(rs.getString("em.cuales_dos"));
                    emo.setMenarca(rs.getString("em.menarca"));
                    emo.setGravida(rs.getString("em.gravida"));
                    emo.setPartos(rs.getString("em.partos"));
                    emo.setCesareas(rs.getString("em.cesareas"));
                    emo.setCeguera(rs.getString("em.ceguera"));
                    emo.setAbortos(rs.getString("em.abortos"));
                    emo.setHijosVivos(rs.getString("em.hijos_vivos"));
                    emo.setFechaUltimaMenst(rs.getDate("em.fecha_ultima_menst"));
                    emo.setCitologia(rs.getDate("em.citologia"));
                    emo.setCiclos(rs.getString("em.ciclos"));
                    emo.setApariencia(rs.getString("em.apariencia"));
                    emo.setSintOsteom(rs.getString("em.sint_osteom"));
                    emo.setCervical(rs.getString("em.cervical"));
                    emo.setCuelloUno(rs.getString("em.cuello_uno"));
                    emo.setCodos(rs.getString("em.codos"));
                    emo.setDedos(rs.getString("em.dedos"));
                    emo.setDorsal(rs.getString("em.dorsal"));
                    emo.setCadera(rs.getString("em.cadera"));
                    emo.setDorsolumbar(rs.getString("em.dorsolumbar"));
                    emo.setHombros(rs.getString("em.hombros"));
                    emo.setLumbosacra(rs.getString("em.lumbosacra"));
                    emo.setManos(rs.getString("em.manos"));
                    emo.setMuniecas(rs.getString("em.muniecas"));
                    emo.setPies(rs.getString("em.pies"));
                    emo.setRodillasUno(rs.getString("em.rodillas_uno"));
                    emo.setDescOsteom(rs.getString("em.desc_osteom"));
                    emo.setOtros(rs.getString("em.otros"));
                    emo.setRevisionSistemas(rs.getString("em.revision_sistemas"));
                    emo.setDescSiste(rs.getString("em.desc_siste"));
                    emo.setAparienciaFisica(rs.getString("em.apariencia_fisica"));
                    emo.setDominancia(rs.getString("em.dominancia"));
                    emo.setTalla(rs.getFloat("em.talla"));
                    emo.setPeso(rs.getFloat("em.peso"));
                    emo.setImc(rs.getFloat("em.imc"));
                    emo.setClasificacion(rs.getString("em.clasificacion"));
                    emo.setTas(rs.getString("em.tas"));
                    emo.setTad(rs.getString("em.tad"));
                    emo.setFc(rs.getString("em.fc"));
                    emo.setFr(rs.getString("em.fr"));
                    emo.setTemp(rs.getString("em.temp"));
                    emo.setAbdomen(rs.getString("em.abdomen"));
                    emo.setArcoMovilidadArt(rs.getString("em.arco_movilidad_art"));
                    emo.setBoca(rs.getString("em.boca"));
                    emo.setCicaTatu(rs.getString("em.cica_tatu"));
                    emo.setColumCerv(rs.getString("em.colum_cerv"));
                    emo.setColumLumbo(rs.getString("em.colum_lumbo"));
                    emo.setColumTora(rs.getString("em.colum_tora"));
                    emo.setColumVertebral(rs.getString("em.colum_vertebral"));
                    emo.setCorazon(rs.getString("em.corazon"));
                    emo.setCuelloDos(rs.getString("em.cuello_dos"));
                    emo.setFilkenstein(rs.getString("em.filkenstein"));
                    emo.setGenitales(rs.getString("em.genitales"));
                    emo.setHernias(rs.getString("em.hernias"));
                    emo.setLasegue(rs.getString("em.lasegue"));
                    emo.setLimitacionFuncional(rs.getString("em.limitacion_funcional"));
                    emo.setMarchaPuntas(rs.getString("em.marcha_puntas"));
                    emo.setMarchaTalones(rs.getString("em.marcha_talones"));
                    emo.setMiembrosInf(rs.getString("em.miembros_inf"));
                    emo.setMiembrosSup(rs.getString("em.miembros_sup"));
                    emo.setOidos(rs.getString("em.oidos"));
                    emo.setOjos(rs.getString("em.ojos"));
                    emo.setPanel(rs.getString("em.panel"));
                    emo.setPiel(rs.getString("em.piel"));
                    emo.setPulmones(rs.getString("em.pulmones"));
                    emo.setRodillasDos(rs.getString("em.rodillas_dos"));
                    emo.setSilla(rs.getString("em.silla"));
                    emo.setTinel(rs.getString("em.tinel"));
                    emo.setVaricesDos(rs.getString("em.varices_dos"));
                    emo.setConsentimientoInformado(rs.getString("em.consentimiento_informado"));
                    emo.setTrabajoAlturas(rs.getString("em.trabajo_alturas"));
                    emo.setPruebasVestibular(rs.getString("em.pruebas_vestibular"));
                    emo.setFechaEncuesta(rs.getDate("em.fecha_encuesta"));
                    emo.setObsUno(rs.getString("em.obs_uno"));
                    emo.setMujerGestante(rs.getString("em.mujer_gestante"));
                    emo.setPanicoAlturas(rs.getString("em.panico_alturas"));
                    emo.setRestriccionesLabdos(rs.getString("em.restricciones_labdos"));
                    emo.setLimitacionesFisicas(rs.getString("limitaciones_fisicas"));
                    emo.setTraumaticosdos(rs.getString("traumaticosdos"));
                    emo.setVisionCercanaAlterada(rs.getString("vision_cercana_alterada"));
                    emo.setAntecedentesEnfermedades(rs.getString("em.antecedentes_enfermedades"));
                    emo.setAntecedentesAdiccion(rs.getString("em.antecedentes_adiccion"));
                    emo.setAlteraciones(rs.getString("em.alteraciones"));
                    emo.setSignosSintomasVisuales(rs.getString("em.signos_sintomas_visuales"));
                    emo.setAlteracionesVisuales(rs.getString("em.alteraciones_visuales"));
                    emo.setAlteracionesColor(rs.getString("em.alteraciones_color"));
                    emo.setAlteracionProfundidad(rs.getString("em.alteracion_profundidad"));
                    emo.setVisionBinocularAlterada(rs.getString("em.vision_binocular_alterada"));
                    emo.setPlanoHorizontalVision(rs.getString("em.plano_horizontal_vision"));
                    emo.setSignosSintomasAuditivos(rs.getString("em.signos_sintomas_auditivos"));
                    emo.setAlteracionesAudicion(rs.getString("em.alteraciones_audicion"));
                    emo.setSintomasMetabolicos(rs.getString("em.sintomas_metabolicos"));
                    emo.setLaboratoriosAlteraciones(rs.getString("em.laboratorios_alteraciones"));
                    emo.setSintomasCardiovasculares(rs.getString("em.sintomas_cardiovasculares"));
                    emo.setAccidenteCerebrovascular(rs.getString("em.accidente_cerebrovascular"));
                    emo.setImcDos(rs.getString("em.imc_dos"));
                    emo.setSintomasMentales(rs.getString("em.sintomas_mentales"));
                    emo.setAntecedentesMedicamentos(rs.getString("em.antecedentes_medicamentos"));
                    emo.setSindromeConvulsivo(rs.getString("em.sindrome_convulsivo"));
                    emo.setAlteracionesEquilibrio(rs.getString("em.alteraciones_equilibrio"));
                    emo.setAlteracionesAtencion(rs.getString("em.alteraciones_atencion"));
                    emo.setAlteracionesComportamiento(rs.getString("em.alteraciones_comportamiento"));
                    emo.setTipoConcepto(rs.getString("em.tipo_concepto"));
                    emo.setObservacionesCiediez(rs.getString("em.observaciones_ciediez"));
                    emo.setManualidades(rs.getString("em.manualidades"));
                    emo.setConductasOcupacionales(rs.getString("em.conductas_ocupacionales"));
                    emo.setObservaciones(rs.getString("em.observaciones"));
                    emo.setExamenFisico(rs.getString("em.examen_fisico"));
                    emo.setRemision(rs.getString("em.remision"));
                    emo.setSistemasVigilancia(rs.getString("em.sistemas_vigilancia"));
                    emo.setObservacionesEspecificas(rs.getString("em.observaciones_especificas"));
                    emo.setCiudadAtencion(rs.getString("em.ciudad_atencion"));
                    emo.setFechaCreacion(rs.getString("em.fecha_creacion"));
                    emo.setAntFam(rs.getString("em.ant_fam"));
                    emo.setCualesUno(rs.getString("em.cuales_uno"));
                    emo.setTrabajadorMenorEdad(rs.getString("em.trabajador_menor_edad"));
                    emo.setImcmayor(rs.getString("em.imcmayor"));
                    emo.setDislipidemia(rs.getString("em.dislipidemia"));
                    emo.setHiperglicemia(rs.getString("em.hiperglicemia"));
                    emo.setSistemasVigilancia(rs.getString("em.sistemas_vigilancia"));
                    emo.setRemision(rs.getString("em.remision"));

                    cieDiez.setDescripcion(rs.getString("cd.descripcion"));
                    cieDiezDos.setDescripcion(rs.getString("cd_dos.descripcion"));
                    cieDiezTres.setDescripcion(rs.getString("cd_tres.descripcion"));
                    cieDiezCuatro.setDescripcion(rs.getString("cd_cuatro.descripcion"));
                    cieDiezCinco.setDescripcion(rs.getString("cd_cinco.descripcion"));
                    cieDiezSeis.setDescripcion(rs.getString("cd_seis.descripcion"));
                    emo.setResultadoConceptoFinal(rs.getString("em.resultado_concepto_final"));
                    emo.setVisiometria(rs.getString("em.visiometria"));
                    emo.setAudiometria(rs.getString("em.audiometria"));
                    emo.setEspirometriaComputalizada(rs.getString("em.espirometria_computalizada"));
                    emo.setLaboratorio(rs.getString("em.laboratorio"));
                    emo.setCualesUno(rs.getString("em.cuales_uno"));
                    emo.setConsentimientoinformado(rs.getString("em.consentimientoinformado"));
                    emo.setObservacionesEspecificas(rs.getString("em.observaciones_especificas"));

                    recomendaciones.setDescripcion(rs.getString("re.descripcion"));
//
//                    if(true){
//                       Boolean.getBoolean("SI");
//                    }else 
//                        Boolean.getBoolean("NO");
//

                }

                st2 = (Statement) con.createStatement();
                rs2 = st2.executeQuery("SELECT * \n"
                        + "FROM antecedentes_ocupacionales AS ae\n"
                        + "WHERE id_emo =" + numeroDocumento + ";");

                tabla = new PdfPTable(4);
                celda1 = new PdfPCell(new Paragraph("Empresa", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                // celda1.setBorderColor(BaseColor.WHITE);
                celda1.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda2 = new PdfPCell(new Paragraph("Actividad económica", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                //  celda2.setBorderColor(BaseColor.WHITE);
                celda2.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda3 = new PdfPCell(new Paragraph("Oficio", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                //  celda3.setBorderColor(BaseColor.WHITE);
                celda3.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda4 = new PdfPCell(new Paragraph("Antiguedad", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                // celda4.setBorderColor(BaseColor.WHITE);
                celda4.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda5 = new PdfPCell(new Paragraph("Riesgos", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                // celda5.setBorderColor(BaseColor.WHITE);
                celda5.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda6 = new PdfPCell(new Paragraph("Epp", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                // celda6.setBorderColor(BaseColor.WHITE);
                celda6.setHorizontalAlignment(Element.ALIGN_LEFT);

                tabla.addCell(celda1);

                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);

                while (rs2.next()) {

                    tabla.addCell(rs2.getString(3));

                    tabla.addCell(rs2.getString(5));
                    tabla.addCell(rs2.getString(6));
                    tabla.addCell(rs2.getString(7));

                }

                st3 = (Statement) con.createStatement();
                rs3 = st3.executeQuery("SELECT * \n"
                        + "FROM accidentes_laborales AS ae\n"
                        + "WHERE id_emo =" + numeroDocumento + ";");

                tabla2 = new PdfPTable(4);
                celdaT1 = new PdfPCell(new Paragraph("Año", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                // celda1.setBorderColor(BaseColor.WHITE);
                celdaT1.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaT2 = new PdfPCell(new Paragraph("Accidente de tranajo", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                //  celda2.setBorderColor(BaseColor.WHITE);
                celdaT2.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaT3 = new PdfPCell(new Paragraph("Enfermedad profesional", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                //  celda3.setBorderColor(BaseColor.WHITE);
                celdaT3.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaT4 = new PdfPCell(new Paragraph("ARL", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLUE)));
                // celda4.setBorderColor(BaseColor.WHITE);
                celdaT4.setHorizontalAlignment(Element.ALIGN_LEFT);

                tabla2.addCell(celdaT1);

                tabla2.addCell(celdaT2);
                tabla2.addCell(celdaT3);
                tabla2.addCell(celdaT4);

                while (rs3.next()) {

                    tabla2.addCell(rs3.getString(2));

                    tabla2.addCell(rs3.getString(4));
                    tabla2.addCell(rs3.getString(5));
                    tabla2.addCell(rs3.getString(8));

                }

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.getMessage();

            }
            try {

                if (rs != null) {

                    Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
                    documento.addAuthor("Nidia");
                    documento.addTitle("Certificado médico de aptitud laboral");

                    PdfWriter writer = PdfWriter.getInstance(documento, output);
                    documento.open();

                    PdfContentByte cb = writer.getDirectContent();
                    //Se crea un templete para asignar la marca de agua
                    PdfTemplate template = cb.createTemplate(600, 300);
                    template.beginText();
                    //Inicializamos los valores para el templete
                    //Se define el tipo de letra, color y tamaño
                    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                    template.setColorFill(new BaseColor(0xD7, 0xD7, 0xD7));
                    template.setFontAndSize(bf, 6);

                    template.setTextMatrix(0, 0);
                    //Se define el texto que se agregara como marca de agua
                    template.showText("COPIA DE USO PERSONAL");

                    template.endText();
                    //Se asigna el templete
                    //Se asignan los valores para el texto de marca de agua
                    // Se asigna el grado de inclinacion y la posicion donde aparecerá el texto
                    //                                                     x    y
                    cb.addTemplate(template, 3, 3, -1, 5, 200, 280);

                    LineSeparator ls = new LineSeparator();

                    /*  String date = new Date().toString();
                     Paragraph fecha;
                     fecha = new Paragraph();
                     Font fontfecha = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                     fecha.add(new Phrase(date, fontfecha));
                     fecha.setAlignment(Element.ALIGN_LEFT);
                     fecha.add(new Phrase(Chunk.NEWLINE));
                     fecha.add(new Phrase(Chunk.NEWLINE));
                     documento.add(new Chunk(ls));
                     documento.add(fecha);*/
                    Paragraph par1 = new Paragraph();
                    Font fonttitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                    par1.add(new Phrase("HISTORIA CLÍNICA  " + emo.getTipoConcepto(), fonttitulo));
                    par1.setAlignment(Element.ALIGN_CENTER);
                    par1.add(new Phrase(Chunk.NEWLINE));
                    par1.add(new Phrase(Chunk.NEWLINE));

                    documento.add(par1);

                    Paragraph parPerfil = new Paragraph();
                    Font fonttituloP = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                    parPerfil.add(new Phrase(emo.getTipoPerfil(), fonttituloP));
                    parPerfil.setAlignment(Element.ALIGN_CENTER);
                    parPerfil.add(new Phrase(Chunk.NEWLINE));
                    parPerfil.add(new Phrase(Chunk.NEWLINE));

                    documento.add(parPerfil);

                    String dateAten = "Fecha y hora de Atención: " + emo.getFechaCreacion() + " Ciudad de atención: "
                            + emo.getCiudadAtencion();
                    Paragraph fechaAten;
                    fechaAten = new Paragraph();
                    Font fontfechaAten = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
                    fechaAten.add(new Phrase(dateAten, fontfechaAten));
                    fechaAten.setAlignment(Element.ALIGN_LEFT);
                    fechaAten.add(new Phrase(Chunk.NEWLINE));

                    documento.add(fechaAten);
                    documento.add(new Chunk(ls));

                    Image image = Image.getInstance(paciente.getFoto());
                    image.scaleAbsolute(70, 90);
                    image.setAlignment(Element.ALIGN_RIGHT);
                    image.setAbsolutePosition(470, 600);

                    documento.add(image);

                    Paragraph parDatos;
                    String datos = "Paciente: " + paciente.getNombres() + " " + paciente.getApellidos()
                            + "  Identificación: " + paciente.getTipoDoc() + " " + paciente.getNumDoc()
                            + "\n" + "Genero: " + paciente.getGenero() + "  Fecha de nacimiento: " + paciente.getFecNac()
                            + "  Edad: " + paciente.getEdad() + " Años"
                            + "\n" + "Dirección: " + paciente.getDireccion() + " " + paciente.getCiudad() + "  "
                            + "Teléfono: " + paciente.getTelCel() + "\n"
                            + "Profesión: " + paciente.getProfesion() + " Cargo: " + paciente.getCargo() + "\n"
                            + "Fondo de pensiones: " + paciente.getAfp() + " EPS: " + paciente.getEps() + " ARL: " + paciente.getArl() + "\n"
                            + "Empresa: " + empresa.getNomClem() + "  Nit: " + empresa.getNitClem() + "\n"
                            + "Fecha y hora de creación del paciente: " + paciente.getFechaCreacion();
                    parDatos = new Paragraph();
                    Font fontdatos = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parDatos.add(new Phrase(datos, fontdatos));
                    parDatos.setAlignment(Element.ALIGN_LEFT);
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parDatos);

                    documento.add(new Chunk(ls));

                    Paragraph antecedentesOcu;
                    Font anFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    antecedentesOcu = new Paragraph();
                    antecedentesOcu.add(new Phrase("ANTECEDENTES OCUPACIONALES:  ", anFont));
                    antecedentesOcu.setAlignment(Element.ALIGN_LEFT);
                    antecedentesOcu.add(new Phrase(Chunk.NEWLINE));
                    antecedentesOcu.add(new Phrase(Chunk.NEWLINE));
                    antecedentesOcu.add(new Phrase(Chunk.NEWLINE));
                    documento.add(antecedentesOcu);
                    documento.add(tabla);

                    documento.add(new Chunk(ls));

                    Paragraph accidentes;
                    Font accidentesFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    accidentes = new Paragraph();
                    accidentes.add(new Phrase("ACCIDENTES LABORALES:  ", accidentesFont));
                    accidentes.setAlignment(Element.ALIGN_LEFT);
                    accidentes.add(new Phrase(Chunk.NEWLINE));

                    Paragraph restriAccidentes;

                    restriAccidentes = new Paragraph();
                    restriAccidentes.add(new Phrase("Restricciones laborales por accidente:  " + emo.getCualesUno(), accidentesFont));
                    restriAccidentes.setAlignment(Element.ALIGN_JUSTIFIED);
                    restriAccidentes.add(new Phrase(Chunk.NEWLINE));
                    restriAccidentes.add(new Phrase(Chunk.NEWLINE));
                    restriAccidentes.add(new Phrase(Chunk.NEWLINE));
                    documento.add(accidentes);
                    documento.add(restriAccidentes);
                    documento.add(tabla2);

                    documento.add(new Chunk(ls));

                    Paragraph parAntecedentesPersonales;
                    Font fontdatosAntPer = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parAntecedentesPersonales = new Paragraph();
                    parAntecedentesPersonales.add(new Phrase("ANTECEDENTES PERSONALES:  ", fontdatosAntPer));
                    String datosAntPer
                            = "\nAlergicos:         " + emo.getAlergicos() + "         Asma:            " + emo.getAsma() + "            Cancer:          " + emo.getCancer()
                            + "\nCardiovasculares:  " + emo.getCardiovasculares() + "  Dermatológicos:  " + emo.getDermatologicos() + "  Diabetes:        " + emo.getDiabetes()
                            + "\nEpilepsia:         " + emo.getEpilepsia() + "         Hipertensión:    " + emo.getHipertension() + "    Hipotiroidismo:  " + emo.getHipotiroidismo()
                            + "\nNeurológicos:      " + emo.getNeurologicos() + "      Psiquiátricos:   " + emo.getPsiquiatricos() + "   Quirúrjicos:     " + emo.getQuimicos()
                            + "\nRespiratorios:     " + emo.getRespiratorias() + "     Reumatológicos:  " + emo.getReumatologicos() + "  Traumáticos:     " + emo.getTraumaticos()
                            + "\nVisuales:          " + emo.getVisuales() + "\nAntecedentes Familiares:  " + emo.getAntFam();
                    parAntecedentesPersonales.add(new Phrase(datosAntPer, fontdatosAntPer));
                    parAntecedentesPersonales.setAlignment(Element.ALIGN_LEFT);
                    parAntecedentesPersonales.add(new Phrase(Chunk.NEWLINE));
                    parAntecedentesPersonales.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parAntecedentesPersonales);

                    documento.add(new Chunk(ls));

                    Paragraph parHabitos;
                    Font fontdatosHabitos = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parHabitos = new Paragraph();
                    parHabitos.add(new Phrase("HÁBITOS:  ", fontdatosAntPer));
                    String datosHabitos
                            = "\nFuma:  " + emo.getFumas() + "  Cuantos al día:  " + emo.getCuantos() + "  Tiempo de Consumo:  " + emo.getTiempoHaceCuanto()
                            + "\nExfumador:  " + emo.getExfumador() + "  Licor:  " + emo.getLicor() + "  Habitual:  " + emo.getHabitual()
                            + "\nDeporte:  " + emo.getDeporte() + "  Qué Deporte:  " + emo.getQueDeporte() + "  Cuantas veces por semana:  " + emo.getCuantasVecesSemana()
                            + "\nManualidades:  " + emo.getManualidades() + "  Cuales:  " + emo.getCualesDos();
                    parHabitos.add(new Phrase(datosHabitos, fontdatosHabitos));
                    parHabitos.setAlignment(Element.ALIGN_LEFT);
                    parHabitos.add(new Phrase(Chunk.NEWLINE));
                    parHabitos.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parHabitos);

                    documento.add(new Chunk(ls));

                    Paragraph parGineco;
                    Font fontdatosGineco = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parGineco = new Paragraph();
                    parGineco.add(new Phrase("GINECO-OBSTÉTRICOS:  ", fontdatosGineco));
                    String datosGineco
                            = "\nMenarca:  " + emo.getMenarca() + " (Años)" + "  Fecha de Última Menstruación:  " + emo.getFechaUltimaMenst() + "  Gravidez:  " + emo.getGravida()
                            + "\nPartos:  " + emo.getPartos() + "  Cesareas:  " + emo.getCesareas() + "  Abortos:  " + emo.getAbortos()
                            + "\nHijos Vivos:  " + emo.getHijosVivos() + "  Citología:  " + emo.getCitologia() + "  Ciclos y metodo de planificación:  " + emo.getCiclos();
                    parGineco.add(new Phrase(datosGineco, fontdatosGineco));
                    parGineco.setAlignment(Element.ALIGN_LEFT);
                    parGineco.add(new Phrase(Chunk.NEWLINE));
                    parGineco.add(new Phrase(Chunk.NEWLINE));

                    if (paciente.getGenero().equals('F')) {
                        documento.add(parGineco);
                    }

                    documento.add(new Chunk(ls));

                    Paragraph parOsteoM;
                    Font fontdatosOsteoM = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parOsteoM = new Paragraph();
                    parOsteoM.add(new Phrase("SINTOMAS OSTEOMUSCULARES:", fontdatosOsteoM));
                    String datosOsteoM
                            = "\nCuello:   " + emo.getCuelloUno() + "  Cervical:     " + emo.getCervical() + "     Hombros:     " + emo.getHombros()
                            + "\nManos:    " + emo.getManos() + "     Muñecas:      " + emo.getMuniecas() + "     Dedos:       " + emo.getDedos()
                            + "\nDorsal:   " + emo.getDorsal() + "    Dorsolumbar:  " + emo.getDorsolumbar() + "  Lumbosacra:  " + emo.getLumbosacra()
                            + "\nCaderas:  " + emo.getCadera() + "     Rodillas:     " + emo.getRodillasUno() + "  Pies:        " + emo.getPies();
                    parOsteoM.add(new Phrase(datosOsteoM, fontdatosOsteoM));
                    parOsteoM.setAlignment(Element.ALIGN_LEFT);
                    parOsteoM.add(new Phrase(Chunk.NEWLINE));
                    parOsteoM.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parOsteoM);

                    documento.add(new Chunk(ls));

                    Paragraph parRevision;
                    Font fontdatosRevision = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parRevision = new Paragraph();
                    parRevision.add(new Phrase("REVISIÓN POR SISTEMAS:", fontdatosRevision));
                    String datosRevision
                            = "\nDescripción:   " + emo.getDescSiste();
                    parRevision.add(new Phrase(datosRevision, fontdatosRevision));
                    parRevision.setAlignment(Element.ALIGN_JUSTIFIED);
                    parRevision.add(new Phrase(Chunk.NEWLINE));
                    parRevision.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parRevision);

                    documento.add(new Chunk(ls));

                    Paragraph parExamenFis;
                    Font fontdatosExamenFis = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parExamenFis = new Paragraph();
                    parExamenFis.add(new Phrase("EXAMEN FÍSICO:", fontdatosAntPer));
                    String datosExamenFis
                            = "\nDominancia:  " + emo.getDominancia() + "  Peso (KG):  " + emo.getPeso() + "    Tas:   " + emo.getTas() + " (mmHg)"
                            + "\nTad:  " + emo.getTad() + " (mmHg) " + "  Fc:  " + emo.getFc() + " (Por minuto) " + "    Fr:   " + emo.getFr() + "  (Por minuto) " + "    Temp:   " + emo.getTemp() + " °C"
                            + "\nTalla(MT):  " + emo.getTalla() + "  IMC:  " + emo.getImc() + "  Clasificación:  " + emo.getClasificacion()
                            + "\nPiel:   " + emo.getPiel() + "     Cicatrices|Tatuajes:     " + emo.getCicaTatu() + "    Boca:  " + emo.getBoca()
                            + "\nOidos:  " + emo.getOidos() + "     Ojos:     " + emo.getOjos() + "  Cuello:        " + emo.getCuelloDos()
                            + "\nCorazón:  " + emo.getCorazon() + "     Pulmones:     " + emo.getPulmones() + "  Abdomen:        " + emo.getAbdomen()
                            + "\nHernias:  " + emo.getHernias() + "     Genitales:     " + emo.getGenitales() + "  Varices:        " + emo.getVaricesDos()
                            + "\nColumna Vertebral:  " + emo.getColumVertebral() + "     Columna Cervical:     " + emo.getColumCerv() + "  Columna Torácica:        " + emo.getColumTora()
                            + "\nColumna Lumbosacra:  " + emo.getColumLumbo() + "     Arco de Movilidad Articular:     " + emo.getArcoMovilidadArt() + "  Lasegue:        " + emo.getLasegue()
                            + "\nLimitación Funcional:  " + emo.getLimitacionFuncional() + "     Finkelstein:     " + emo.getFilkenstein() + "  Phalen:        " + emo.getPanel() + "     Tinel:     " + emo.getTinel() + "  Silla:        " + emo.getSilla()
                            + "\nRodillas:  " + emo.getRodillasDos() + "     Marcha en Puntas:     " + emo.getMarchaPuntas() + "  Marcha en Talones:        " + emo.getMarchaTalones()
                            + "\nMiembros Superiores  " + emo.getMiembrosSup() + "     Miembros Inferiores:     " + emo.getMiembrosInf() + "  Limitación Funcional:        " + emo.getLimitacionFuncional()
                            + "\nApariencia y Hallazgos Positivos:  " + emo.getConsentimientoInformado();
                    parExamenFis.add(new Phrase(datosExamenFis, fontdatosExamenFis));
                    parExamenFis.setAlignment(Element.ALIGN_LEFT);
                    parExamenFis.add(new Phrase(Chunk.NEWLINE));
                    parExamenFis.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parExamenFis);

                    documento.add(new Chunk(ls));

                    Paragraph parTrabajoAlturas;
                    Font fontdatosTrabajoAlturas = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parTrabajoAlturas = new Paragraph();
                    parTrabajoAlturas.add(new Phrase("TRABAJO EN ALTURAS:", fontdatosTrabajoAlturas));
                    String datosTrabajoAlturas
                            = "\nMenor de Edad:  " + emo.getTrabajadorMenorEdad() + "  Mujer Gestante:  " + emo.getMujerGestante() + "  Pánico a las Alturas:  " + emo.getPanicoAlturas()
                            + "\nRestricciones Laborales:  " + emo.getRestriccionesLabdos() + "  Limitaciones Físicas:  " + emo.getLimitacionesFisicas() + "  Traumáticos:  " + emo.getTraumaticosdos()
                            + "\nPérdida de Capacidad Laboral:   " + emo.getAlteraciones() + "    Adicción:  " + emo.getAntecedentesMedicamentos() + "  Ceguera:  " + emo.getCeguera()
                            + "\nVisión Cercana Alterada:     " + emo.getVisionCercanaAlterada() + "  Visión Lejana Alterada:        " + emo.getPlanoHorizontalVision()
                            + "\nAlteración Colores:  " + emo.getAlteracionesColor() + "     Alteración Profundidad:     " + emo.getAlteracionProfundidad() + "  Visión Binocular Alterada:        " + emo.getVisionBinocularAlterada()
                            + "\nAlteración Auditiva:  " + emo.getAlteracionesAudicion() + "     Mareo / Vértigo:     " + emo.getAlteracionesEquilibrio() + "  Epilepsia:        " + emo.getSindromeConvulsivo()
                            + "\nDislipidemia:  " + emo.getDislipidemia() + "     Hiperglicemia:     " + emo.getHiperglicemia() + "  Accidente Cerebro-Vascular:        " + emo.getAccidenteCerebrovascular()
                            + "\nInfarto:  " + emo.getSintomasCardiovasculares() + "     Alteraciones Respiratorias:     " + emo.getAlteracionesComportamiento() + "  IMC Mayor a 35:        " + emo.getImcmayor()
                            + "\nTrastorno de Comportamiento:  " + emo.getSintomasMentales() + "  Pruebas Vestibulares:        " + emo.getPruebasVestibular()
                            + "\nObservaciones:     " + emo.getObsUno();
                    parTrabajoAlturas.add(new Phrase(datosTrabajoAlturas, fontdatosTrabajoAlturas));
                    parTrabajoAlturas.setAlignment(Element.ALIGN_LEFT);
                    parTrabajoAlturas.add(new Phrase(Chunk.NEWLINE));
                    parTrabajoAlturas.add(new Phrase(Chunk.NEWLINE));

                    if (emo.getTipoPerfil().equals("TRABAJO EN ALTURAS")) {
                        documento.add(parTrabajoAlturas);
                    }

                    documento.add(new Chunk(ls));

                    Paragraph parConductas;
                    Font fontdatosConductas = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parConductas = new Paragraph();
                    parConductas.add(new Phrase("CONDUCTAS OCUPACIONALES:", fontdatosConductas));
                    String datosConductas
                            = "\n" + emo.getConductasOcupacionales();
                    parConductas.add(new Phrase(datosConductas, fontdatosConductas));
                    parConductas.setAlignment(Element.ALIGN_JUSTIFIED);
                    parConductas.add(new Phrase(Chunk.NEWLINE));
                    parConductas.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parConductas);

                    documento.add(new Chunk(ls));

                    Paragraph parRestricciones;
                    Font fontdatosRestricciones = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parRestricciones = new Paragraph();
                    parRestricciones.add(new Phrase("RESTRICCIONES LABORALES:  ", fontdatosRestricciones));
                    String datosRestricciones
                            = "\nRestricciones:  " + emo.getObservaciones()
                            + "\n" + emo.getOtros();
                    parRestricciones.add(new Phrase(datosRestricciones, fontdatosRestricciones));
                    parRestricciones.setAlignment(Element.ALIGN_LEFT);
                    parRestricciones.add(new Phrase(Chunk.NEWLINE));
                    parRestricciones.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parRestricciones);

                    documento.add(new Chunk(ls));

                    Paragraph parCieDiez;

                    parCieDiez = new Paragraph();
                    Font fontdatosCieDiez = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    String datosCieDiez
                            = "DIAGNOSTICOS CIE 10 \n"
                            + "Diagnostico 1:   \n" + cieDiez.getDescripcion() + "\n"
                            + "Diagnostico 2:  \n" + cieDiezDos.getDescripcion() + "\n"
                            + "Diagnostico 3:  \n" + cieDiezTres.getDescripcion() + "\n"
                            + "Diagnostico 4:  \n" + cieDiezCuatro.getDescripcion() + "\n"
                            + "Diagnostico 5:  \n" + cieDiezCinco.getDescripcion() + "\n"
                            + "Diagnostico 6:  \n" + cieDiezSeis.getDescripcion();
                    parCieDiez.add(new Phrase(datosCieDiez, fontdatosCieDiez));
                    parCieDiez.setAlignment(Element.ALIGN_LEFT);
                    parCieDiez.add(new Phrase(Chunk.NEWLINE));
                    parCieDiez.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parCieDiez);

                    documento.add(new Chunk(ls));

                    Paragraph parResul = new Paragraph();
                    Font fontResul = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
                    parResul.add(new Phrase("RESULTADO DE CONCEPTO FINAL:  " + emo.getResultadoConceptoFinal(), fontResul));
                    parResul.setAlignment(Element.ALIGN_LEFT);
                    parResul.add(new Phrase(Chunk.NEWLINE));
                    parResul.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parResul);

                    Paragraph sistemaVi = new Paragraph();
                    Font sistemaViFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    sistemaVi.add(new Phrase("SISTEMA DE VIGILANCIA EPIDENIOLÓGICA:  " + emo.getSistemasVigilancia(), sistemaViFont));
                    sistemaVi.setAlignment(Element.ALIGN_JUSTIFIED);
                    sistemaVi.add(new Phrase(Chunk.NEWLINE));
                    sistemaVi.add(new Phrase(Chunk.NEWLINE));
                    documento.add(sistemaVi);

                    Paragraph consentimiento = new Paragraph();

                    consentimiento.add(new Phrase("CONSENTIMIENTO INFORMADO:  " + emo.getConsentimientoinformado(), sistemaViFont));
                    consentimiento.setAlignment(Element.ALIGN_JUSTIFIED);
                    consentimiento.add(new Phrase(Chunk.NEWLINE));
                    consentimiento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(consentimiento);

                    Paragraph remision = new Paragraph();

                    remision.add(new Phrase("REMISIÓN:  " + emo.getRemision(), sistemaViFont));
                    remision.setAlignment(Element.ALIGN_JUSTIFIED);
                    remision.add(new Phrase(Chunk.NEWLINE));
                    remision.add(new Phrase(Chunk.NEWLINE));
                    documento.add(remision);
                    
                    Paragraph obser = new Paragraph();

                    obser.add(new Phrase("OBSERVACIONES ESPECIFICAS:  " + emo.getObservacionesEspecificas(), sistemaViFont));
                    obser.setAlignment(Element.ALIGN_JUSTIFIED);
                    obser.add(new Phrase(Chunk.NEWLINE));
                    obser.add(new Phrase(Chunk.NEWLINE));
                    documento.add(obser);

                    documento.add(new Chunk(ls));

                    Paragraph parComple = new Paragraph();
                    Font fontComple = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
                    parComple.add(new Phrase("PRUEBA COMPLEMENTARIAS:  ", fontComple));
                    parComple.setAlignment(Element.ALIGN_LEFT);
                    parComple.add(new Phrase(Chunk.NEWLINE));
                    parComple.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parComple);

                    Paragraph parVisio = new Paragraph();
                    Font fontvisio = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parVisio.add(new Phrase("VISIOMETRÍA:  " + emo.getVisiometria(), fontvisio));
                    parVisio.setAlignment(Element.ALIGN_LEFT);
                    parVisio.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parVisio);

                    Paragraph parAudio = new Paragraph();
                    Font fontAudio = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parAudio.add(new Phrase("AUDIOMETRÍA:  " + emo.getAudiometria(), fontAudio));
                    parAudio.setAlignment(Element.ALIGN_LEFT);
                    parAudio.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parAudio);

                    Paragraph parEspiro = new Paragraph();
                    Font fontEspiro = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parEspiro.add(new Phrase("ESPIROMETRÍA:  " + emo.getEspirometriaComputalizada(), fontEspiro));
                    parEspiro.setAlignment(Element.ALIGN_LEFT);
                    parEspiro.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parEspiro);

                    Paragraph parLabo = new Paragraph();
                    Font fontLabo = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parLabo.add(new Phrase("LABORATORIO:  " + emo.getLaboratorio(), fontLabo));
                    parLabo.setAlignment(Element.ALIGN_LEFT);
                    parLabo.add(new Phrase(Chunk.NEWLINE));
                    parLabo.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parLabo);

                    documento.add(new Chunk(ls));

                    Paragraph parRege = new Paragraph();
                    Font fontRege = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parRege.add(new Phrase("RECOMENDACIONES GENERALES: \n" + recomendaciones.getDescripcion(), fontRege));
                    parRege.setAlignment(Element.ALIGN_JUSTIFIED);
                    parRege.add(new Phrase(Chunk.NEWLINE));
                    parRege.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parRege);

                    Image imageFirma = Image.getInstance(paciente.getImgFirma());
                    imageFirma.setBorderWidth((float) 15.0);
                    imageFirma.setBorderColorBottom(BaseColor.ORANGE);
                    imageFirma.scaleAbsolute(120, 70);
                    imageFirma.setAlignment(Element.ALIGN_LEFT);
                    documento.add(imageFirma);

                    String cedula = "CC " + paciente.getNumDoc();
                    Paragraph firma = new Paragraph();
                    Font fontfirma = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
                    Font fontcedula = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                    firma.add(new Phrase("Firma del paciente \n", fontfirma));
                    firma.add(new Phrase(cedula, fontcedula));

                    firma.setAlignment(Element.ALIGN_LEFT);

                    documento.add(firma);
                    documento.add(new Chunk(ls));

                    SimpleDateFormat formateador2
                            = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss", new Locale("es_ES"));
                    Date fechaImpresion = new Date();
                    String fechaImp = formateador2.format(fechaImpresion);
                    String date = "Impreso por: " + "                                           "
                            + "                                       nidiabenavidesmso@gmail.com\n"
                            + "Fecha y hora de impresión: " + fechaImp + "\n"
                            + "http://www.nbd-ocupacional.tk\n";
                    Paragraph fecha;
                    fecha = new Paragraph();
                    Font fontfecha = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.BLACK);
                    fecha.add(new Phrase(date, fontfecha));
                    fecha.setAlignment(Element.ALIGN_LEFT);
                    fecha.add(new Phrase(Chunk.NEWLINE));
                    fecha.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Chunk(ls));
                    documento.add(fecha);

                    documento.close();
                }
            } catch (DocumentException ex) {
                ex.getMessage();

            }

        } finally {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
