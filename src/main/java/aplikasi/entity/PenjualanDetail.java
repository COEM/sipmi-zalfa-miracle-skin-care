/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.entity;

/**
 *
 * @author dimmaryanto
 */
public class PenjualanDetail {

    private Integer id;
    private Penjualan penjualan;
    private Barang barang;
    private Double harga;
    private Double diskon;
    private Integer jumlah;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getDiskon() {
        return diskon;
    }

    public void setDiskon(Double diskon) {
        this.diskon = diskon;
    }

}
