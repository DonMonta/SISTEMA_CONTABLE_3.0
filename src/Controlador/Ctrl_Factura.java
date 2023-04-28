/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;
import Modelo.Cliente;
import Modelo.ClsConsultaClientes;
import Modelo.ClsConsultaFactura;
import Modelo.ClsConsultaProveedor;
import Modelo.ClsCuentasPorCobrar;
import Modelo.ClsFactura;
import Modelo.ClsProveedor;
import Vista.FrmFACTURA;
import Vista.frmFacturas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author joel
 */

public class Ctrl_Factura implements ActionListener{
     ClsFactura em;
    ClsConsultaFactura sqlemp;
    frmFacturas frm;
    public Ctrl_Factura(ClsFactura em, ClsConsultaFactura sqlemp, frmFacturas frm){
        this.em = em;
        this.sqlemp = sqlemp;
        this.frm = frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btngenerar.addActionListener(this);
        this.frm.cmbcliente.addActionListener((ActionListener)this);
        this.frm.cmbprovee.addActionListener((ActionListener)this);
        
        
        this.frm.tbfactura.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                DefaultTableModel modelo = (DefaultTableModel) frm.tbfactura.getModel();
                int fila;
                fila=frm.tbfactura.getSelectedRow();
                
                frm.txtid.setText(modelo.getValueAt(fila, 0).toString());
                frm.txtnumero.setText(modelo.getValueAt(fila, 1).toString());
                frm.txtimporte.setText(modelo.getValueAt(fila, 2).toString());
                
                String fecha=String.valueOf(modelo.getValueAt(fila, 3));
                frm.cmbcliente.setSelectedItem(modelo.getValueAt(fila, 4).toString());
                frm.cmbprovee.setSelectedItem(modelo.getValueAt(fila, 5).toString());
             
                SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechasa;
                try {
                    fechasa=(java.util.Date) s.parse(fecha);
                    
                    frm.jDateChooser1.setDate(fechasa);
                  
                } catch (ParseException ex) {
                    Logger.getLogger(Ctrl_Factura.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
               
              
            }
        });
    }
    public void ListarCliente() throws Exception{
         ClsConsultaClientes conse = new ClsConsultaClientes(); 
         List objList = conse.MostrarCliente();
         Iterator iterador = objList.iterator();
         while (iterador.hasNext()) {
                Cliente eml = (Cliente) iterador.next();
                frm.cmbcliente.addItem(eml);
            }
    }
    public void ListarProveedor() throws Exception{
         ClsConsultaProveedor consb = new ClsConsultaProveedor();
         List objList2 = consb.MostrarProveedor();
          Iterator iterador2 = objList2.iterator();
         while (iterador2.hasNext()) {
                 ClsProveedor pro = (ClsProveedor) iterador2.next();
                frm.cmbprovee.addItem(pro);
            }
    }
    
    public void Iniciar() throws Exception{
        
            frm.txtid.setVisible(false);
            
            ListarCliente();
            ListarProveedor();
            Mostrar();
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==frm.btnactualizar){
            
            Mostrar();
            Limpiar();
           
           
        }
         if(e.getSource()==frm.cmbcliente){   
            
         
        }
         if(e.getSource()==frm.cmbprovee){   
           
         
        }
         
         if(e.getSource()==frm.btnagregar){
             
            if(Validar()){
                Cliente iol = (Cliente)frm.cmbcliente.getSelectedItem();
                em.setCliente(iol.getId()); 
                ClsProveedor bote = (ClsProveedor)frm.cmbprovee.getSelectedItem();
                em.setProveedor(bote.getId()); 
                String fecha;
                java.util.Date date = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fecha=f.format(frm.jDateChooser1.getDate());
         
           
                try {       
                
                    em.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
                } catch (ParseException ex) {
                    Logger.getLogger(Ctrl_Factura.class.getName()).log(Level.SEVERE, null, ex);
                }
                em.setImporte(Double.parseDouble(frm.txtimporte.getText()));
               
                
                if(sqlemp.Guardar(em)){
                      
                    JOptionPane.showMessageDialog(null, "Guardado");
                    Mostrar();
                    Limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "No se guardó la informacion");Limpiar();
                }
            }
                  
                  
        }
    }
 
     public void Mostrar()
    {  
        List notas; 
       ClsFactura cls;
       ClsProveedor proveedor;
       Cliente cliente;
                 String[] columnas ={"ID","Numero Factura","Fecha","Clientes","Importe Cliente","Proveedores","Importe Proveedores","Importe Total"};
           Object[] datos = new Object[8];
           DefaultTableModel tabla = new DefaultTableModel(null,columnas){
             @Override
             public boolean isCellEditable(int i, int j)
             { if(i==8){return true;} else {return false;}}
           };
         
         Cliente obj =(Cliente) frm.cmbcliente.getSelectedItem();
           try {
              
                notas= sqlemp.Mostrar();
                if(!notas.isEmpty())
                {
                    for (int i = 0; i < notas.size(); i++) {

                       cls = (ClsFactura) notas.get(i);
                      datos[0] = cls.getId();
                       datos[1]= cls.getNumero();

                       datos[2]= cls.getFecha();
                        datos[3]= cls.getFecha_pag();
                       factura = sqlest.BuscarFactura(cls);
                       datos[4]= factura.getNumero();
                       tabla.addRow(datos);
                     }  
                    frm.tbtransacc.setModel(tabla);


                    DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
                    Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
                    for(int i=4; i<7;i++)
                    {  frm.tbtransacc.getColumnModel().getColumn(i).setCellRenderer(Alinear);}
                }
                else
                {
                   frm.txtbuscar.setEnabled(false);
                   JOptionPane.showMessageDialog(null, "No encontro información"); Limpiar();frm.txtbuscar.setText(null);}
               
           } catch (Exception ex) {
               Logger.getLogger(Ctrl_ReportCuentaCobrar.class.getName()).log(Level.SEVERE, null, ex);
           }  
    }  
    public void ReportesCuentasCobrar() throws Exception {
         List notas; 
       ClsCuentasPorCobrar cls;
       ClsFactura factura;
        Document documento = new Document();
        try {
           String rutaArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Reporte Cuentas por Cobrar.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            Image header = Image.getInstance("src/imagenes/header.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nGrupo #1\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Cuentas por Cobrar \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            PdfPCell celda;
            Font fuenteCabecera = new Font(BaseFont.createFont(), 12, Font.BOLD, BaseColor.WHITE);
            celda = new PdfPCell(new Phrase("ID", fuenteCabecera));
            celda.setBackgroundColor(new BaseColor(0,75,159));
            tabla.addCell(celda);
            celda = new PdfPCell(new Phrase("Importe", fuenteCabecera));
            celda.setBackgroundColor(new BaseColor(0,75,159));
            tabla.addCell(celda);
            celda = new PdfPCell(new Phrase("Fecha de Vencimiento", fuenteCabecera));
            celda.setBackgroundColor(new BaseColor(0,75,159));
            tabla.addCell(celda);
            celda = new PdfPCell(new Phrase("Fecha de Pago", fuenteCabecera));
            celda.setBackgroundColor(new BaseColor(0,75,159));
            tabla.addCell(celda);
            celda = new PdfPCell(new Phrase("Numero de Factura", fuenteCabecera));
            celda.setBackgroundColor(new BaseColor(0,75,159));
            tabla.addCell(celda);

               notas= sqlest.Mostrar();
                if(!notas.isEmpty()){
                     for (int i = 0; i < notas.size(); i++) {

                       cls = (ClsCuentasPorCobrar) notas.get(i);
                       tabla.addCell( String.valueOf(cls.getId()));
                       
                        tabla.addCell(String.valueOf(cls.getImporte()));
                        tabla.addCell(String.valueOf(cls.getFecha_venci()));
                        tabla.addCell(String.valueOf(cls.getFecha_pag()));
                        //factura = sqlest.BuscarFactura(cls);
                        tabla.addCell(String.valueOf(factura.getNumero()));
                        
                     }
                     documento.add(tabla);
                }
                 
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
        
    public void Limpiar()
    {
        
         frm.txtid.setText(null);
         frm.txtimporte.setText(null);
       
         frm.jDateChooser1.setDate(null);
    }
     private boolean Validar(){
         
         if("".equals(frm.txtnumero.getText())){
             
             JOptionPane.showMessageDialog(null,"Debe ingresar datos");
             return  false;
            
             }
         if("".equals(frm.txtimporte.getText())){
             JOptionPane.showMessageDialog(null,"Debe ingresar datos");
            
             return  false;
            
             }
        
         

         return true;
     }
     
}

