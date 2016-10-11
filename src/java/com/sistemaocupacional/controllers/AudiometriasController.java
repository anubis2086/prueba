package com.sistemaocupacional.controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sistemaocupacional.entities.Audiometrias;
import com.sistemaocupacional.controllers.util.JsfUtil;
import com.sistemaocupacional.controllers.util.JsfUtil.PersistAction;
import com.sistemaocupacional.sessions.AudiometriasFacade;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("audiometriasController")
@SessionScoped
public class AudiometriasController implements Serializable {

    @EJB
    private com.sistemaocupacional.sessions.AudiometriasFacade ejbFacade;
    private List<Audiometrias> items = null;
    private Audiometrias selected;
    private StreamedContent imagen;

    public AudiometriasController() {
    }

    public Audiometrias getSelected() {
        return selected;
    }

    public void setSelected(Audiometrias selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AudiometriasFacade getFacade() {
        return ejbFacade;
    }

    public Audiometrias prepareCreate() {
        selected = new Audiometrias();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void crearPdf() throws DocumentException{
        
          Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
                    documento.addAuthor("Nidia");
                    documento.addTitle("Certificado médico de aptitud laboral");
                    OutputStream output = null;
                
                    PdfWriter.getInstance(documento, output);
                    documento.open();
                 
               
                    
                    LineSeparator ls = new LineSeparator();

                    String date = new Date().toString();
                    Paragraph fecha;
                    fecha = new Paragraph();
                    Font fontfecha = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                    fecha.add(new Phrase(date, fontfecha));
                    fecha.setAlignment(Element.ALIGN_LEFT);
                    fecha.add(new Phrase(Chunk.NEWLINE));
                    fecha.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Chunk(ls));
                    documento.add(fecha);

                    documento.close();
        
        
    }

    public StreamedContent getImagen() {
        InputStream input = new ByteArrayInputStream(selected.getIdPaciente().getFoto());

        imagen = new DefaultStreamedContent(input, "image/jpg", "fileName.jpg");

        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public String prepareEdit() {
        return "/audiometrias/Edit";

    }

    public String prepareView() {
        return "/audiometrias/View";

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("AudiometriasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public String update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("AudiometriasUpdated"));
        return "/audiometrias/List";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("AudiometriasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void recargarListaAudiometria() {

        items = null;
    }

    public List<Audiometrias> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public Audiometrias getAudiometrias(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Audiometrias> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Audiometrias> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Audiometrias.class)
    public static class AudiometriasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AudiometriasController controller = (AudiometriasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "audiometriasController");
            return controller.getAudiometrias(getKey(value));
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
            if (object instanceof Audiometrias) {
                Audiometrias o = (Audiometrias) object;
                return getStringKey(o.getIdAudiometria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Audiometrias.class.getName()});
                return null;
            }
        }

    }

}
