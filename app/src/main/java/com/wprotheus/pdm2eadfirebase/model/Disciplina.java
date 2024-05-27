package com.wprotheus.pdm2eadfirebase.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Disciplina implements Serializable {
    private String nome;
    private String nota;
}