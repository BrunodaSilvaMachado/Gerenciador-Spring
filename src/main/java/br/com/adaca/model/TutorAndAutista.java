package br.com.adaca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TutorAndAutista implements Serializable {
    private Integer idtutor;
    private Integer idautista;
}
