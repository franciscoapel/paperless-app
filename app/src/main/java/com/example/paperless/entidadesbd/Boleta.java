package com.example.paperless.entidadesbd;

public class Boleta {
    private String folio;
    private String nombre_comercio;
    private String rut_comercio;
    private String descripcion_comercio;
    private String direccion_comercio;
    private String contacto_comercio;
    private String fecha;
    private String detalle;
    private String descuento;
    private String total;

    public static final String FOLIO = "Folio";
    public static final String NOMBRE = "Nombre";
    public static final String RUT = "Rut";
    public static final String DESCRIPCION = "Descripción";
    public static final String DIRECCION = "Dirección";
    public static final String CONTACTO = "Contacto";
    public static final String FECHA = "Fecha";
    public static final String DETALLE = "Detalle";
    public static final String DESCUENTO = "Descuento";
    public static final String TOTAL = "Total";
    public static final String[] CAMPOS = {FOLIO, NOMBRE, RUT, DESCRIPCION, DIRECCION, CONTACTO,
            FECHA, DETALLE, DESCUENTO, TOTAL};
    public static final String INFO = "info";

    public Boleta(String folio, String nombre_comercio, String rut_comercio,
                  String descripcion_comercio, String direccion_comercio, String contacto_comercio,
                  String fecha, String detalle, String descuento, String total) {
        this.folio = folio;
        this.nombre_comercio = nombre_comercio;
        this.rut_comercio = rut_comercio;
        this.descripcion_comercio = descripcion_comercio;
        this.direccion_comercio = direccion_comercio;
        this.contacto_comercio = contacto_comercio;
        this.fecha = fecha;
        this.detalle = detalle;
        this.descuento = descuento;
        this.total = total;
    }

    public String getInfoBoleta() {
        StringBuilder infoBoleta = new StringBuilder();
        String[] listaCampos = {folio, nombre_comercio, rut_comercio, descripcion_comercio,
                direccion_comercio, contacto_comercio, fecha, detalle, descuento, total};
        for (String campo: listaCampos) {
            infoBoleta.append(campo);
            infoBoleta.append(";");
        }
        return infoBoleta.toString();
    }

    public String getFolio() {return folio;}
    public String getNombreComercio() {return nombre_comercio;}
    public String getFecha() {return fecha;}

}
