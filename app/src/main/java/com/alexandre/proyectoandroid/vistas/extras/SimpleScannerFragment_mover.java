package com.alexandre.proyectoandroid.vistas.extras;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerFragment_mover extends Fragment implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private Vibrator vibrator;
    private MediaPlayer mp;
    private EnviarData2 ed;
    private Boolean estadoFlash = false;


    public interface EnviarData2
    {
        void enviarCodigo(String codigo,String tipo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        mScannerView = new ZXingScannerView(getActivity());
        mScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadoFlash == true){
                    mScannerView.setFlash(false);
                    estadoFlash = false;
                }else{
                    mScannerView.setFlash(true);
                    estadoFlash = true;
                }
            }
        });
        mScannerView.setFlash(estadoFlash);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.bip);
        ed = (EnviarData2)getActivity();

        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }



    @Override
    public void handleResult(Result rawResult) {
        vibrator.vibrate(100);
        mp.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerFragment_mover.this);
            }
        }, 2000);

        if(rawResult.getBarcodeFormat().equals(BarcodeFormat.CODE_128)){
            ActivoDAO dao = new ActivoDAO(getActivity());
            ActivoDTO mobiliario = dao.getActivoDTO(rawResult.getText().toString().trim());

            if(mobiliario.getMarca()==null){
                Toast.makeText(getActivity(),"No existe ningún mobiliario con ese codigo",Toast.LENGTH_LONG).show();
            }else {
                if (mobiliario.getEstado().toString().equals("En almacén")) {
                    ed.enviarCodigo(rawResult.getText(),"salida");
                } else {
                    ed.enviarCodigo(rawResult.getText(),"entrada");
                }
                mScannerView.setFlash(false);
                mScannerView.stopCamera();
            }
        }else if(rawResult.getBarcodeFormat().equals(BarcodeFormat.QR_CODE)){
            Toast.makeText(this.getContext(), rawResult.getText(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.getContext(), "Formato incorrecto de codigo." ,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

}
