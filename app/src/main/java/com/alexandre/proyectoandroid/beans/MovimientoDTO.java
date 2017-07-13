package com.alexandre.proyectoandroid.beans;

/**
 * Created by ITALO on 06/07/2017.
 */

public class MovimientoDTO {

    private int nroMovimiento;
    private String idActivo;
    private String tipoActivo;
    private String tipoMovimiento;
    private String fechaMovimiento;
    private String areaMovimiento;
    private String horaMovimiento;

    public int getNroMovimiento() {
        return nroMovimiento;
    }

    public void setNroMovimiento(int nroMovimiento) {
        this.nroMovimiento = nroMovimiento;
    }

    public String getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(String idActivo) {
        this.idActivo = idActivo;
    }

    public String getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(String tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getAreaMovimiento() {
        return areaMovimiento;
    }

    public void setAreaMovimiento(String areaMovimiento) {
        this.areaMovimiento = areaMovimiento;
    }

    public String getHoraMovimiento() {
        return horaMovimiento;
    }

    public void setHoraMovimiento(String horaMovimiento) {
        this.horaMovimiento = horaMovimiento;
    }
}