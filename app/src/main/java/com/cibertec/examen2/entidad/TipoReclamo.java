package com.cibertec.examen2.entidad;

public class TipoReclamo {

    private int idTipoReclamo;
    private String descripcion;
    private String estado;
    private String fechaRegistro;

    public int getIdTipoReclamo() {
        return idTipoReclamo;
    }

    public void setIdTipoReclamo(int idTipoReclamo) {
        this.idTipoReclamo = idTipoReclamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
