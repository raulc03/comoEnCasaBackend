package com.hb.comoencasa.domain;

import javax.persistence.*;


@Entity
@Table(name = "Lista_Producto")
public class Lista_Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne()
    @JoinColumn(name = "Producto_Id")
    private Producto producto;

    @ManyToOne()
    @JoinColumn(name = "Comprador_Id")
    private Comprador comprador;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}
