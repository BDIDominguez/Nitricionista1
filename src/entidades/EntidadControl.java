/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author Dario
 */
public class EntidadControl {
    private int idControl;
    private int idPaciente;
    private LocalDate fecha;
    private double peso;
    private double altura;
    private double cintura;
    private double gasenergetico;
    private double IMC;
    private LocalDate proximacita;
    private boolean estado;
    private String obs;

    public EntidadControl() {
    }

    public EntidadControl(int idPaciente, LocalDate fecha, double peso, double altura, double cintura, double gasenergetico, double IMC, LocalDate proximacita, boolean estado, String obs) {
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.peso = peso;
        this.altura = altura;
        this.cintura = cintura;
        this.gasenergetico = gasenergetico;
        this.IMC = IMC;
        this.proximacita = proximacita;
        this.estado = estado;
        this.obs = obs;
    }

    public EntidadControl(int idControl, int idPaciente, LocalDate fecha, double peso, double altura, double cintura, double gasenergetico, double IMC, LocalDate proximacita, boolean estado, String obs) {
        this.idControl = idControl;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.peso = peso;
        this.altura = altura;
        this.cintura = cintura;
        this.gasenergetico = gasenergetico;
        this.IMC = IMC;
        this.proximacita = proximacita;
        this.estado = estado;
        this.obs = obs;
    }

    public int getIdControl() {
        return idControl;
    }

    public void setIdControl(int idControl) {
        this.idControl = idControl;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getGasenergetico() {
        return gasenergetico;
    }

    public void setGasenergetico(double gasenergetico) {
        this.gasenergetico = gasenergetico;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public LocalDate getProximacita() {
        return proximacita;
    }

    public void setProximacita(LocalDate proximacita) {
        this.proximacita = proximacita;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    
}
