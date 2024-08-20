package flavia.dev.delivery_restaurantes.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private User proprietário;
    
    private String nome;
    private String descricao;
    private String cozinhaTipo;
    private int avaliacoes;
    private String horarioAbertura;
    private LocalDateTime dataRegistro;
    private boolean aberto;
    
    @ElementCollection
    @Column(length = 1000)
    private List<String> imagens;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @Embedded
    private InformacaoContato informacaoContato;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review>reviews=new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Pedido> orders=new ArrayList<>();
    

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Comida> comidas=new ArrayList<>();
    
    
}

