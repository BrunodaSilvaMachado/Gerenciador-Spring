package br.com.adaca.util;

import java.io.Serializable;

@FunctionalInterface
public interface EntityModel extends Serializable {

    Serializable getId();
}