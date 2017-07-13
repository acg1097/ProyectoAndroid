package com.alexandre.proyectoandroid.vistas.registrar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.alexandre.proyectoandroid.vistas.cotejar.Paso1_cotejar_carro;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class RegistrarActivo extends AppCompatActivity implements PasoPrincipal_registrar_Fragment.Fragment_principal_registrar, Paso1_registrar_mobiliario.Fragment_mobiliario_registrar,Paso1_registrar_vehiculo.Fragment_vehiculo_registrar {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_activo);

        toolbar = (Toolbar) findViewById(R.id.toolbar_registrarActivo);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Registrar activo");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        PasoPrincipal_registrar_Fragment f1 = new PasoPrincipal_registrar_Fragment();
        transaction.replace(R.id.fragment_contenedor_registrar, f1).commit();

    }

    @Override
    public void pasar_siguiente_1(int tipo) {
        try {
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            if (tipo == 0) {
                Paso1_registrar_mobiliario f1 = new Paso1_registrar_mobiliario();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.fragment_contenedor_registrar, f1).commit();
            } else {
                Paso1_registrar_vehiculo f1 = new Paso1_registrar_vehiculo();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.fragment_contenedor_registrar, f1).commit();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void pasar_siguiente_registrar(ActivoDTO m) {
        ActivoDAO dao = new ActivoDAO(RegistrarActivo.this);
        ProgressDialog pd = new ProgressDialog(RegistrarActivo.this);
        pd.setMessage("Registrando activo");
        pd.setCancelable(false);
        pd.show();

        ActivoDTO a = dao.agregarActivo(m);

        pd.dismiss();

        if (a == null) {
            Toast.makeText(RegistrarActivo.this, "No se pudo registrar el mobiliario", Toast.LENGTH_LONG).show();
            finish();
        } else {
            try {
                generarPDF_QR_BD(a);
            }catch (Exception e){}
        }
        setResult(RESULT_OK);
        Toast.makeText(RegistrarActivo.this, "Mobiliario registrado correctamente", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void volver_anterior_mobiliario() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        PasoPrincipal_registrar_Fragment f1 = new PasoPrincipal_registrar_Fragment();
        transaction.replace(R.id.fragment_contenedor_registrar, f1).commit();
    }

    public void generarPDF_QR_BD(ActivoDTO activo){
        String NOMBRE_CARPETA = "Logística Cibertec";

        String direccion = Environment.getExternalStorageDirectory().toString();

        File pdfDir = new File(direccion + File.separator+ NOMBRE_CARPETA);
        if (!pdfDir.exists()) {
            pdfDir.mkdir();
        }
        Rectangle rect = new Rectangle(85, 200);
        Document doc = new Document(rect,40,40,0,0);

        PdfWriter docWriter = null;

        String path = null;

        try {
            /**CONFIGURACIONES DEL DOCUMENTO*/
            path = pdfDir+ File.separator + "Activo - "+activo.getId()+".pdf";
            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
            doc.addAuthor("ALEXANDRE");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("ALEXANDRE.COM");
            doc.addTitle("ACTIVO - "+activo.getId());
            doc.setPageSize(PageSize.A4);

            /**ABRIR PDF*/
            doc.open();

            PdfContentByte cb = docWriter.getDirectContent();

            Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 60, Font.BOLD, BaseColor.BLACK);
            Chunk titulo = new Chunk(activo.getTipo().toUpperCase(), fuenteTitulo);
            Paragraph paragraph1 = new Paragraph(titulo);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            doc.add(paragraph1);

            Font fuenteBasica;

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            if(activo.getTipo().equals("Mobiliario")) {
                fuenteBasica = new Font(Font.FontFamily.HELVETICA, 30, Font.NORMAL, BaseColor.BLACK);
                Chunk id = new Chunk("ID: "+activo.getId(), fuenteBasica);
                Paragraph paragraph2 = new Paragraph(id);
                doc.add(paragraph2);

                doc.add(new Paragraph(" "));
                Chunk descripcion = new Chunk("DESCRIPCIÓN: "+activo.getDescripcion(), fuenteBasica);
                Paragraph paragraph3 = new Paragraph(descripcion);
                doc.add(paragraph3);
            }else{
                fuenteBasica = new Font(Font.FontFamily.HELVETICA, 40, Font.NORMAL, BaseColor.BLACK);

                Chunk id = new Chunk("NRO. PLACA: "+activo.getId(), fuenteBasica);
                Paragraph paragraph2 = new Paragraph(id);
                doc.add(paragraph2);
            }

            doc.add(new Paragraph(" "));
            Chunk marca = new Chunk("MARCA: "+activo.getMarca(), fuenteBasica);
            Paragraph paragraph4 = new Paragraph(marca);
            doc.add(paragraph4);

            doc.add(new Paragraph(" "));
            Chunk modelo = new Chunk("MODELO: "+activo.getModelo(), fuenteBasica);
            Paragraph paragraph5 = new Paragraph(modelo);
            doc.add(paragraph5);

            doc.add(new Paragraph(" "));
            Chunk valor = new Chunk("VALOR: "+activo.getValor(), fuenteBasica);
            Paragraph paragraph6 = new Paragraph(valor);
            doc.add(paragraph6);

            doc.add(new Paragraph(" "));
            Chunk ubicacion = new Chunk("UBICACIÓN: "+activo.getUbicacion(), fuenteBasica);
            Paragraph paragraph7 = new Paragraph(ubicacion);
            doc.add(paragraph7);

            if(activo.getTipo().toString().toLowerCase().equals("mobiliario")){
                /** GENERAR CODIGO DE BARRA */
                Barcode128 code128 = new Barcode128();
                code128.setCode(activo.getId());
                code128.setCodeType(Barcode128.CODE128);
                Image code128Image = code128.createImageWithBarcode(cb, null, null);
                code128Image.setAbsolutePosition(90,130);
                code128Image.scalePercent(200);

            /*añadir codigo de barra a documento*/
                doc.add(code128Image);

                /** GENERAR CODIGO QR*/
                BarcodeQRCode qrcode = new BarcodeQRCode("ID:"+activo.getId()+"-DESC:"+activo.getDescripcion()+"-UBIC:"+activo.getUbicacion(), 1, 1, null);
                Image qrcodeImage = qrcode.getImage();
                qrcodeImage.setAbsolutePosition(370,105);
                qrcodeImage.scalePercent(400);

            /*añadir qr a documento*/
                doc.add(qrcodeImage);
            }

        }
        catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null)
            {
                doc.close();
            }
            if (docWriter != null)
            {
                docWriter.close();
            }
        }

        File uri = new File(path);
        sendEmail(Uri.fromFile(uri));

    }

    private void sendEmail(Uri URI) {
        try {

            String email = "acg1097@hotmail.com";//correo de italo

            String subject = "Etiqueta equipo";

            String message = "Mediante este mensaje le adjunto el equipo.";

            final Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("application/pdf");
            i.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { email });
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);

            if (URI != null) {
                i.putExtra(Intent.EXTRA_STREAM, URI);
            }

            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(Intent.createChooser(i,null));

        } catch (Throwable t) {
            Toast.makeText(this,
                    "Request failed try again: " + t.toString(),
                    Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void pasar_siguiente_registrar_v(ActivoDTO v) {
        ActivoDAO dao = new ActivoDAO(RegistrarActivo.this);
        ProgressDialog pd = new ProgressDialog(RegistrarActivo.this);
        pd.setMessage("Registrando activo");
        pd.setCancelable(false);
        pd.show();

        ActivoDTO a = dao.agregarActivo(v);

        pd.dismiss();

        if (a == null) {
            Toast.makeText(RegistrarActivo.this, "No se pudo registrar el vehiculo", Toast.LENGTH_LONG).show();
            finish();
        } else {
            try {
                generarPDF_QR_BD(a);
            }catch (Exception e){}
        }
        setResult(RESULT_OK);
        Toast.makeText(RegistrarActivo.this, "Vehiculo registrado correctamente", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void volver_anterior_vehiculo() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        PasoPrincipal_registrar_Fragment f1 = new PasoPrincipal_registrar_Fragment();
        transaction.replace(R.id.fragment_contenedor_registrar, f1).commit();
    }
}
