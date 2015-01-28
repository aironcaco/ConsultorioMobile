package com.oliveira.airon.consultoriomobile;

/**
 * Created by Airon on 24/01/2015.
 */
public enum Sexo {
    feminino(0),
    masculino(1);

    private int sexo;

    Sexo(int sexo){
        this.sexo = sexo;
    }

    public int getSexo(){
        return this.sexo;
    }
}
