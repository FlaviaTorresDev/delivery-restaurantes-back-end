package flavia.dev.delivery_restaurantes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    private Long valor;
    private boolean avaliacoes;
    private boolean vegetariano;
    private boolean sazonal;
    
    @ManyToOne
    private Categoria comidaCategoria;


    @ElementCollection
    @Column(length = 1000)
    private List<String> imagem;

//    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
    
    
    @ManyToMany
    private List<IngredientsItem> ingredients=new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    
}
