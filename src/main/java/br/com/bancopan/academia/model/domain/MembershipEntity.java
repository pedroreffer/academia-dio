package br.com.bancopan.academia.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "matricula")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MembershipEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private GymMemberEntity aluno;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataMatricula;

    @Column(nullable = false)
    private Boolean ativa = Boolean.TRUE;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime dataDesmatricula = null;
}
