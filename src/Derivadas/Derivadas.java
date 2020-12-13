/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.ParseException;
import org.w3c.dom.Node;

/**
 *
 * @author CINDY GONZALEZ
 */

    //Variable que almacena las funciones a derivar
public class Derivadas {
    private String funcion = "";
    // DJep es la clase encargada de la derivacion en su escencia
    DJep djep;
    org.nfunk.jep.Node nodoFuncion;
    org.nfunk.jep.Node nodoDerivada;

    public Derivadas() {
        //...
    }

    public void setFuncionADerivar(String funcion) {
        this.funcion = funcion;
    }

    public String getFuncionDerivada() {
        return this.funcion;
    }

    public void derivar() {

        try {

            this.djep = new DJep();
            // agrega funciones estandares cos(x), sin(x)
            this.djep.addStandardFunctions();

            // agrega constantes estandares, pi, e, etc
            this.djep.addStandardConstants();

            // por si existe algun numero complejo
            this.djep.addComplex();

            // permite variables no declarables
            this.djep.setAllowUndeclared(true);

            // permite asignaciones
            this.djep.setAllowAssignment(true);

            // regla de multiplicacion o para sustraccion y sumas
            this.djep.setImplicitMul(true);

            // regla de multiplicacion o para sustraccion y sumas
            this.djep.addStandardDiffRules();

            // coloca el nodo con una funcion preestablecida
            this.nodoFuncion = this.djep.parse(this.funcion);

            // deriva la funcion con respecto a x
            org.nfunk.jep.Node diff = this.djep.differentiate(nodoFuncion, "x");

            // Simplificamos la funcion con respecto a x
            this.nodoDerivada = this.djep.simplify(diff);

            // Convertimos el valor simplificado en un String
            this.funcion = this.djep.toString(this.nodoDerivada);

        } catch (ParseException e) {
            this.funcion = "NaN";
            System.out.println("Error: " + e.getErrorInfo());
        }

    }

}
