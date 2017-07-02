package com.alexandre.proyectoandroid;


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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerFragment extends Fragment implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    Vibrator vibrator;
    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        mp = MediaPlayer.create(getActivity().getBaseContext(),R.raw.bip);
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
        Toast.makeText(getActivity(), "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerFragment.this);
            }
        }, 2000);

        Toast.makeText(this.getContext(), "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        if(rawResult.getBarcodeFormat().equals(BarcodeFormat.CODE_128)){
            Toast.makeText(this.getContext(), "Buscando en la base de datos",Toast.LENGTH_SHORT).show();

            /*
            ActivoServicio a = new ActivoServicio();
            int ok = a.getActivo();
            if(ok>0){

            }else{
                Activo no encontrado.
            }
            * */

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
