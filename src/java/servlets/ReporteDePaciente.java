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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sistemaocupacional.entities.Emo;
import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.entities.Pacientes;
import com.sistemaocupacional.entities.Recomendaciones;
import com.sistemaocupacional.entities.Usuarios;
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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dandres
 */
@ManagedBean
@ViewScoped
@WebServlet(name = "ReporteDePaciente", urlPatterns = {"/ReporteDePaciente"})
public class ReporteDePaciente extends HttpServlet {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    private Pacientes paciente;
    private Empresas empresa;
    private Emo emo;
    private Recomendaciones recomendaciones;
    private Usuarios usuarios;

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();
        String numeroDocumento = request.getParameter("numero");

        try {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaocupacional?user=root&password=Persefone2016");
                st = (Statement) con.createStatement();
                rs = st.executeQuery("SELECT * \n"
                        + "FROM pacientes AS p\n"
                        + "INNER JOIN empresas AS e ON p.id_clem = e.id_clem\n"
                        + "INNER JOIN emo AS em ON p.id_paciente = em.id_paciente\n"
                        + "INNER JOIN recomendaciones AS re ON em.id_recomendacion = re.id_recomendacion\n"
                        + "INNER JOIN usuarios AS u ON u.id_usuario = em.id_usuario\n"
                        + "WHERE em.id_emo =" + numeroDocumento + ";");

                while (rs.next()) {
                    paciente = new Pacientes();
                    empresa = new Empresas();
                    emo = new Emo();
                    recomendaciones = new Recomendaciones();
                    usuarios = new Usuarios();

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
                    recomendaciones.setDescripcion(rs.getString("re.descripcion"));
                    emo.setFechaCreacion(rs.getString("em.fecha_creacion"));
                    emo.setTipoConcepto(rs.getString("em.tipo_concepto"));
                    emo.setTipoPerfil(rs.getString("em.tipo_perfil"));
                    emo.setResultadoConceptoFinal(rs.getString("em.resultado_concepto_final"));
                    emo.setRemision(rs.getString("em.remision"));
                    emo.setVisiometria(rs.getString("em.visiometria"));
                    emo.setAudiometria(rs.getString("em.audiometria"));
                    emo.setEspirometriaComputalizada(rs.getString("em.espirometria_computalizada"));
                    emo.setLaboratorio(rs.getString("em.laboratorio"));
                    emo.setObservacionesCiediez(rs.getString("em.observaciones_ciediez"));
                    emo.setConductasOcupacionales(rs.getString("em.conductas_ocupacionales"));
                    emo.setSistemasVigilancia(rs.getString("em.sistemas_vigilancia"));
                    emo.setObservacionesEspecificas(rs.getString("em.observaciones_especificas"));
                    emo.setCiudadAtencion(rs.getString("em.ciudad_atencion"));
                    usuarios.setNom(rs.getString("u.nom"));
                    usuarios.setApe(rs.getString("u.ape"));
                    usuarios.setCorreo(rs.getString("u.correo"));
                    usuarios.setTel(rs.getString("u.tel"));
                    usuarios.setCel(rs.getString("u.cel"));
                    usuarios.setDireccion(rs.getString("u.direccion"));
                    usuarios.setNumeroDoc(rs.getString("u.numero_doc"));
                    usuarios.setFirma(rs.getBytes("u.firma"));

                }

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.getMessage();

            }
            try {

                if (rs != null) {

                    Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
                    documento.addAuthor("Nidia");
                    documento.addTitle("Certificado médico de aptitud ");

                    PdfWriter.getInstance(documento, output);
                    documento.open();

                    LineSeparator ls = new LineSeparator();

                    Paragraph par1 = new Paragraph();
                    Font fonttitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                    par1.add(new Phrase("CERTIFICADO MÉDICO DE APTITUP " + emo.getTipoConcepto(), fonttitulo));
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
                    image.setAbsolutePosition(470, 580);

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
                            + "Empresa: " + empresa.getNomClem() + "  Nit: " + empresa.getNitClem() +"\n"
                            + "Fecha y hora de creación del paciente: " + paciente.getFechaCreacion();
                    
                    parDatos = new Paragraph();
                    Font fontdatos = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parDatos.add(new Phrase(datos, fontdatos));
                    parDatos.setAlignment(Element.ALIGN_LEFT);
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parDatos);

                    documento.add(new Chunk(ls));

                    Paragraph parResul = new Paragraph();
                    Font fontResul = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
                    parResul.add(new Phrase("CONCEPTO MÉDICO DE APTITUD OCUPACIONAL:  " + emo.getResultadoConceptoFinal(), fontResul));
                    parResul.setAlignment(Element.ALIGN_LEFT);
                    parResul.add(new Phrase(Chunk.NEWLINE));
                    parResul.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parResul);

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

                    Paragraph parReob = new Paragraph();
                    Font fontReob = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parReob.add(new Phrase("RECOMENDACIONES PARA EL PACIENTE: \n" + emo.getObservacionesCiediez(), fontReob));
                    parReob.setAlignment(Element.ALIGN_JUSTIFIED);
                    parReob.add(new Phrase(Chunk.NEWLINE));
                    parReob.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parReob);

                    Paragraph parEspe = new Paragraph();
                    Font fontEspe = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parEspe.add(new Phrase("OBSERVACIONES ESPECÍFICAS: \n" + emo.getObservacionesEspecificas(), fontEspe));
                    parEspe.setAlignment(Element.ALIGN_JUSTIFIED);
                    parEspe.add(new Phrase(Chunk.NEWLINE));
                    parEspe.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parEspe);

                    Paragraph parConduc = new Paragraph();
                    Font fontConduct = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parConduc.add(new Phrase("CONDUCTAS OCUPACIONALES: \n" + emo.getConductasOcupacionales(), fontConduct));
                    parConduc.setAlignment(Element.ALIGN_JUSTIFIED);
                    parConduc.add(new Phrase(Chunk.NEWLINE));
                    parConduc.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parConduc);

                    documento.add(new Chunk(ls));

                    Paragraph parRemision = new Paragraph();
                    Font fontRemision = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parRemision.add(new Phrase("REMISION: \n" + emo.getRemision(), fontRemision));
                    parRemision.setAlignment(Element.ALIGN_JUSTIFIED);
                    parRemision.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parRemision);

                    documento.add(new Chunk(ls));

                    Paragraph parSiste = new Paragraph();
                    Font fontSiste = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parSiste.add(new Phrase("SISTEMA DE VIGILANCIA EPIDEMIOLOGICA: \n" + emo.getSistemasVigilancia(), fontSiste));
                    parSiste.setAlignment(Element.ALIGN_JUSTIFIED);
                    parSiste.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parSiste);

                    documento.add(new Chunk(ls));

                    SimpleDateFormat formateador = new SimpleDateFormat("dd '/' MM '/' yyyy", new Locale("es_ES"));
                    Date fechaDate = new Date();
                    String fecha2 = formateador.format(fechaDate);
                    Paragraph parfinal = new Paragraph();
                    Font fontparfinal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
                    parfinal.add(new Phrase("CONSIDERACIONES LEGALES RELATIVAS A LOS EXAMENES MEDICOS OCUPACIONALES: "
                            + "Las Resoluciones 2346 del 11 de julio de 2007 y 1918 de Junio 5 de 2009 del Ministerio"
                            + " de la Protección Social (actualmente Ministerios de Trabajo y de Salud y Protección "
                            + "Social) reglamentan la práctica de las evaluaciones médicas con el objeto de determinar "
                            + "si existen restricciones para el trabajo a desempeñar según el profesiograma de la empresa, "
                            + "monitorear la exposición a los factores de riesgo ocupacional e identificar posibles alteraciones"
                            + " temporales, permanentes o agravamiento del estado de salud del trabajador, ocasionadas por la labor"
                            + " o por el medio ambiente de trabajo, y para detectar precozmente enfermedades de origen común, con el "
                            + "fin de establecer un manejo preventivo. También establece que la Empresa solo puede conocer el"
                            + " CONCEPTO DE APTITUD del aspirante. Los documentos completos de la Historia Clínica Ocupacional "
                            + "están sometidos a reserva profesional y quedan bajo nuestra guarda y custodia, acorde "
                            + "con lo establecido en la Resolución 1918 de Junio 5 de 2009 y el trabajador puede obtener"
                            + " una copia de ellos cuando lo requiera, entendiendo que hacen parte integral de su"
                            + " historial médico. \n \n \n" + "AUTORIZACIÓN DE USUARIO PARA CONOCIMIENTO DE LA HISTORIA CLÍNICA"
                            + " OCUPACIONAL POR PARTE DEL MÉDICO ESPECIALISTA EN SALUD OCUPACIONAL DE LA EMPRESA: Yo, " + paciente.getNombres() + " " + paciente.getApellidos() + ", con documento de identificación No. " + paciente.getNumDoc() + ", actuando libremente"
                            + " y en nombre propio, autorizo expresamente a los médicos especialistas en Medicina del Trabajo "
                            + "o Salud Ocupacional que formen parte de los servicios médicos de la empresa, a tener acceso a"
                            + " todos los documentos de mi Historia Clínica Ocupacional y a todos los datos consignados en"
                            + " ellos o que lleguen a ser registrados, con el objeto del cumplimiento de las obligaciones "
                            + "relativas a la vigilancia de mi salud. Para ello, tendrán la guarda y custodia de la historia "
                            + "clínica ocupacional de los documentos a los cuales tengan acceso y son responsables de garantizar"
                            + " su confidencialidad y secreto profesional, conforme lo establece el artículo 16 de la Resoluciones"
                            + " 2346 de 2007 y 1918 de 2009, la Ley 23 de 1981 y las demás normas que lo modifiquen, adicionen o sustituyan. "
                            + " Autorizo, en caso de ser necesario, a hacer entrega de estos documentos a las Entidades de la Seguridad Social,"
                            + " para que puedan definir el origen de los eventos adversos a mi salud o solucionar cualquier controversia relacionada "
                            + "con el origen, las secuelas o la definición de la perdida de mi capacidad laboral, acorde con lo definido en el Artículo"
                            + " 16 de la Resolución 2346 de 2007 (modificado por la Resolución 1918 de 2009). Certifico que las respuestas dadas por mí"
                            + " en este examen están completas y son verídicas. . Para constancia firmo este consentimiento el " + fecha2 + ". "
                            + " El presente documento se diligenció para dar cumplimiento a la exigencia legal, planteada por la Ley 23 de 1981 "
                            + "en su Artículo 34 y la Resoluciones 2346 de 2007 y 1918 de 2009 del Ministerio de Salud y de la Protección Social).", fontparfinal));
                    parfinal.setAlignment(Element.ALIGN_JUSTIFIED);
                    documento.add(parfinal);

                    documento.add(new Chunk(ls));

                    String cedula = usuarios.getNom() + " " + usuarios.getApe() + "\n"
                            + "Médica y Cirujana\n"
                            + "Universidad del valle\n"
                            + "Resolución No. 763363-06\n"
                            + "Especialista en Salud Ocupacional\n"
                            + "Universidad de Antioquia\n"
                            + "Licencia No. 112526 DSSA\n";
                    Paragraph firma = new Paragraph();
                    Font fontfirma = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
                    Font fontcedula = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                    firma.add(new Phrase(cedula, fontcedula));

                    firma.add(new Phrase("Firma del profesional", fontfirma));

                    firma.setAlignment(Element.ALIGN_LEFT);
                    
                   String cedulaPa = paciente.getNombres()+ " " + paciente.getApellidos()+ "\n"                        
                            + "Documento:  "+paciente.getNumDoc()+"\n";
                    Paragraph firma2 = new Paragraph();
                    Font fontfirma2 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
                    Font fontcedula2 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                    firma2.add(new Phrase(cedulaPa, fontcedula2));

                    firma2.add(new Phrase("Firma del Paciente", fontfirma2));

                    firma2.setAlignment(Element.ALIGN_LEFT);

                    Image imageFirma = Image.getInstance(usuarios.getFirma());
                    Image imagenFirma2 = Image.getInstance(paciente.getImgFirma());
                    imagenFirma2.scaleAbsolute(120, 70);
                    imagenFirma2.setAlignment(Element.ALIGN_RIGHT);

                    imageFirma.scaleAbsolute(120, 70);
                    imageFirma.setAlignment(Element.ALIGN_LEFT);

                    PdfPTable table = new PdfPTable(2);
                    
                    PdfPCell celldatosUsu = new PdfPCell(firma);  
                    celldatosUsu.setBorderColor(BaseColor.WHITE);
                    celldatosUsu.setHorizontalAlignment(Element.ALIGN_LEFT);
                    
                    PdfPCell celldatosPa = new PdfPCell(firma2);  
                    celldatosPa.setBorderColor(BaseColor.WHITE);
                    celldatosPa.setHorizontalAlignment(Element.ALIGN_RIGHT);

                    table.addCell(celldatosUsu);
                    table.addCell(celldatosPa);
                    
                    PdfPCell cellImg1 = new PdfPCell(imageFirma);            
                    cellImg1.setBorderColor(BaseColor.WHITE);
                    cellImg1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell cellImg2 = new PdfPCell(imagenFirma2);       
                    cellImg2.setBorderColor(BaseColor.WHITE);
                    cellImg2.setHorizontalAlignment(Element.ALIGN_RIGHT);

                    table.addCell(cellImg1);
                    table.addCell(cellImg2);

                    documento.add(table);

                    documento.add(new Chunk(ls));
                    
                    SimpleDateFormat formateador2 = 
                            new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss", new Locale("es_ES"));
                    Date fechaImpresion = new Date();
                    String fechaImp = formateador2.format(fechaImpresion);
                    String date = "Impreso por: " + "                                           "
                            + "                                       nidiabenavidesmso@gmail.com\n"
                            + "Fecha y hora de impresión: " +fechaImp+ "\n"
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
