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
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sistemaocupacional.entities.CuentaCobro;
import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.entities.Usuarios;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
@WebServlet(name = "CuentaDeCobro", urlPatterns = {"/CuentaDeCobro"})
public class CuentaDeCobro extends HttpServlet {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private CuentaCobro cuenta;
    private Empresas empresa;
    private Usuarios usuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "application/pdf");
        OutputStream output = response.getOutputStream();
        String numeroCuenta = request.getParameter("numeroCuenta");

        try {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaocupacional?user=root&password=Persefone2016");
                st = (Statement) con.createStatement();
                rs = st.executeQuery("SELECT c.fecha_cuenta, c.numero_cuenta, c.concepto, c.cantidad, c.valor_unitario,"
                        + " c.valor_total, c.banco, c.cuenta_ahorros, c.nombre, c.titular_cuenta, c.pago_efectivo, c.firma_encargado, e.nom_clem, "
                        + "e.nit_clem, u.nom, u.ape, u.tel, u.cel, u.correo, u.numero_doc, u.firma "
                        + "FROM cuenta_cobro AS c "
                        + "INNER JOIN empresas AS e ON c.id_clem = e.id_clem "
                        + "INNER JOIN usuarios AS u ON c.id_usuario = u.id_usuario "
                        + "WHERE c.id_cuenta ="
                        + numeroCuenta + ";");

                while (rs.next()) {
                    cuenta = new CuentaCobro();
                    empresa = new Empresas();
                    usuario = new Usuarios();

                    cuenta.setFechaCuenta(rs.getDate("c.fecha_cuenta"));
                    cuenta.setNumeroCuenta(rs.getString("c.numero_cuenta"));
                    cuenta.setConcepto(rs.getString("c.concepto"));
                    cuenta.setCantidad(rs.getInt("c.cantidad"));
                    cuenta.setValorUnitario(rs.getInt("c.valor_unitario"));
                    cuenta.setValorTotal(rs.getInt("c.valor_total"));
                    cuenta.setBanco(rs.getString("c.banco"));
                    cuenta.setCuentaAhorros(rs.getString("c.cuenta_ahorros"));
                    cuenta.setNombre(rs.getString("c.nombre"));
                    cuenta.setTitularCuenta(rs.getString("c.titular_cuenta"));
                    cuenta.setPagoEfectivo(rs.getString("c.pago_efectivo"));
                    cuenta.setFirmaEncargado(rs.getBytes("c.firma_encargado"));
                    empresa.setNomClem(rs.getString("e.nom_clem"));
                    empresa.setNitClem(rs.getString("e.nit_clem"));
                    usuario.setNom(rs.getString("u.nom"));
                    usuario.setApe(rs.getString("u.ape"));
                    usuario.setTel(rs.getString("u.tel"));
                    usuario.setCel(rs.getString("u.cel"));
                    usuario.setCorreo(rs.getString("u.correo"));
                    usuario.setNumeroDoc(rs.getString("u.numero_doc"));
                    usuario.setFirma(rs.getBytes("u.firma"));

                }
              
                con.close();

            } catch (ClassNotFoundException | SQLException e) {

            }
            try {
                if (rs != null) {
                    Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
                    documento.addAuthor("Nidia");
                    documento.addTitle("Cuenta de cobro para profesionales");

                    PdfStamper stamp;

                    PdfWriter writer = PdfWriter.getInstance(documento, output);
                    documento.open();

                   /* PdfContentByte cb = writer.getDirectContent();
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
                     cb.addTemplate(template, 3, 3, -1, 5, 150, 450);*/
                    LineSeparator ls = new LineSeparator();

                    Paragraph par1 = new Paragraph();
                    Font fonttitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
                    par1.add(new Phrase("CUENTA DE COBRO PARA PROFESIONALES", fonttitulo));
                    par1.setAlignment(Element.ALIGN_CENTER);
                    par1.add(new Phrase(Chunk.NEWLINE));

                    documento.add(par1);
                    documento.add(new Chunk(ls));

                    Paragraph par2 = new Paragraph();
                    Font fonttitulo2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

                    par2.add(new Phrase("(Personas Naturales no obligadas a facturar)", fonttitulo2));
                    par2.setAlignment(Element.ALIGN_CENTER);
                    par2.add(new Phrase(Chunk.NEWLINE));
                    par2.add(new Phrase(Chunk.NEWLINE));
                    par2.add(new Phrase(Chunk.NEWLINE));
                    par2.add(new Phrase(Chunk.NEWLINE));
                    documento.add(par2);

                    Paragraph parFecha;
                    String fecha = "Fecha: " + cuenta.getFechaCuenta().toString() + "                                        Cuenta de cobro No: "
                            + cuenta.getNumeroCuenta();
                    parFecha = new Paragraph();
                    Font fontdatos = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                    parFecha.add(new Phrase(fecha, fontdatos));
                    parFecha.setAlignment(Element.ALIGN_LEFT);
                    parFecha.add(new Phrase(Chunk.NEWLINE));
                    parFecha.add(new Phrase(Chunk.NEWLINE));

                    documento.add(parFecha);

                    Paragraph parlegal;
                    String legal = "Para efectos relacionados con las normas fiscales me permito informar: \n"
                            + "     1. Que soy persona natural no responsable de IVA REGIMEN COMUN. Servicios\n"
                            + "     excluidos de numeral 3 del Artículo 476 de E.T \n"
                            + "     2. Que no me encuentro dentro de las situaciones contempladas en el Art 1 del decreto \n"
                            + "     1165 de 1996, del Art 615 "
                            + "del Estatuto Tributario (obligados a facturar).";
                    parlegal = new Paragraph();
                    Font fontlegal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                    parlegal.add(new Phrase(legal, fontlegal));
                    parlegal.setAlignment(Element.ALIGN_LEFT);
                    parlegal.add(new Phrase(Chunk.NEWLINE));

                    documento.add(parlegal);
                    documento.add(new Chunk(ls));

                    Paragraph par3 = new Paragraph();
                    String par3String = "NOMBRE DE LA EMPRESA:               "
                            + empresa.getNomClem().toUpperCase();

                    par3.add(new Phrase(par3String, fontlegal));
                    par3.setAlignment(Element.ALIGN_LEFT);
                    par3.add(new Phrase(Chunk.NEWLINE));

                    documento.add(par3);

                    Paragraph par4 = new Paragraph();
                    String par4String = "NIT DE LA EMPRESA (con digito de verificación): "
                            + empresa.getNitClem();

                    par4.add(new Phrase(par4String, fontlegal));
                    par4.setAlignment(Element.ALIGN_LEFT);
                    par4.add(new Phrase(Chunk.NEWLINE));
                    par4.add(new Phrase(Chunk.NEWLINE));

                    documento.add(par4);

                    Paragraph parlegal2;
                    String legal2 = "En consecuencia no estoy obligado a expedir factura ni documento equivalente.";

                    parlegal2 = new Paragraph();
                    Font fontlegal2 = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                    parlegal2.add(new Phrase(legal2, fontlegal2));
                    parlegal2.setAlignment(Element.ALIGN_LEFT);
                    parlegal2.add(new Phrase(Chunk.NEWLINE));
                    parlegal2.add(new Phrase(Chunk.NEWLINE));
                    parlegal2.add(new Phrase(Chunk.NEWLINE));

                    documento.add(parlegal2);

                    Paragraph parconcepto;
                    String concepto = "CONCEPTO (Descripción de la prestación del servicio) \n";

                    parconcepto = new Paragraph();
                    Font fontconcepto = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
                    parconcepto.add(new Phrase(concepto, fontconcepto));
                    parconcepto.setAlignment(Element.ALIGN_LEFT);

                    documento.add(parconcepto);

                    Paragraph parconcepto2;
                    String concepto2 = cuenta.getConcepto();

                    parconcepto2 = new Paragraph();
                    Font fontconcepto2 = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                    parconcepto2.add(new Phrase(concepto2, fontconcepto2));
                    parconcepto2.setAlignment(Element.ALIGN_JUSTIFIED);
                    parconcepto2.add(new Phrase(Chunk.NEWLINE));
                    parconcepto2.add(new Phrase(Chunk.NEWLINE));

                    documento.add(parconcepto2);

                    Paragraph parDatos;
                    String datos = "Cantidad:   " + cuenta.getCantidad() + "                        (Ver reporte de servicios) \n"
                            + "Valor unitario:  $ " + cuenta.getValorUnitario() + "   Valor total: $ "
                            + cuenta.getValorTotal() + "     "
                            + "Banco:  " + cuenta.getBanco() + "\nCuenta de ahorros: " + cuenta.getCuentaAhorros() + "\n"
                            + "Nombre del titular de la cuenta:  " + cuenta.getTitularCuenta() + "       "
                            + "\nPago en efectivo: " + cuenta.getPagoEfectivo() + "";
                    parDatos = new Paragraph();
                    Font fontdata = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                    parDatos.add(new Phrase(datos, fontdatos));
                    parDatos.setAlignment(Element.ALIGN_LEFT);
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    parDatos.add(new Phrase(Chunk.NEWLINE));
                    documento.add(parDatos);

                    Image imageFirma = Image.getInstance(usuario.getFirma());
                    imageFirma.setBorderWidth((float) 15.0);
                    imageFirma.setBorderColorBottom(BaseColor.ORANGE);
                    imageFirma.scaleAbsolute(130, 80);
                    imageFirma.setAlignment(Element.ALIGN_LEFT);
                    //imageFirma.setAbsolutePosition(80, 150);

                    String nombre = usuario.getNom() + " " + usuario.getApe();
                    String numeroDoc = "CC " + usuario.getNumeroDoc();
                    Paragraph firma = new Paragraph();
                    Font fontfirma = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
                    Font fontnombre = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
                    firma.add(new Phrase(nombre, fontnombre));
                    firma.add(new Phrase(Chunk.NEWLINE));
                    firma.add(new Phrase(numeroDoc, fontfirma));
                    firma.add(new Phrase(Chunk.NEWLINE));
                    firma.add(new Phrase("Nombre y documento del profesional "
                            + "                                                       "
                            + "                                            "
                            + "Firma de recibido", fontfirma));

                    firma.add(new Phrase(Chunk.NEWLINE));

                    firma.setAlignment(Element.ALIGN_LEFT);

                    documento.add(firma);
                    documento.add(imageFirma);

                    Paragraph parlinea;
                    String linea = "_____________________________";

                    parlinea = new Paragraph();
                    Font fontlinea = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
                    parlinea.add(new Phrase(linea, fontlinea));
                    parlinea.setAlignment(Element.ALIGN_RIGHT);

                    documento.add(parlinea);

                    documento.add(new Chunk(ls));
                    
                      Paragraph parnota;
                    String nota = "DIRECCIÓN: Cra. 82 No. 45 e-39, Medellín \n"
                            + "Nota: Favor enviar al correo nidiabenavidesmso@gmail.com la constancia de la cancelación "
                            + "de la cuenta cobro informando el valor neto pagado y si se efectuó retención en la fuente.";

                    parnota = new Paragraph();
                    Font fontnota = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
                    parnota.add(new Phrase(nota, fontnota));
                    parnota.setAlignment(Element.ALIGN_JUSTIFIED);
                    documento.add(parnota);
                    
                    
                 
                    

                    documento.close();

                }

            } catch (DocumentException | IOException e) {

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
