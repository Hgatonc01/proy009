package es.cic25.proy009.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Rama {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numHojas;
    private boolean hojaCompuesta;
    private double grosor;
    private double longitud;

    @ManyToOne
    @JsonIgnore
    private Arbol arbol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumHojas() {
        return numHojas;
    }

    public void setNumHojas(int numHojas) {
        this.numHojas = numHojas;
    }

    public boolean isHojaCompuesta() {
        return hojaCompuesta;
    }

    public void setHojaCompuesta(boolean hojaCompuesta) {
        this.hojaCompuesta = hojaCompuesta;
    }

    public double getGrosor() {
        return grosor;
    }

    public void setGrosor(double grosor) {
        this.grosor = grosor;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rama other = (Rama) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rama [id=" + id + ", numHojas=" + numHojas + ", hojaCompuesta=" + hojaCompuesta + ", grosor=" + grosor
                + ", longitud=" + longitud + ", arbol=" + arbol + "]";
    }

}