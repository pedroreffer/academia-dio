package br.com.bancopan.academia.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvaliationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private GymMemberEntity aluno;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataAvaliacao;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double altura;

}
