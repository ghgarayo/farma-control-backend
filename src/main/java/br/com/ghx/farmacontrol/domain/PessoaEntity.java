package br.com.ghx.farmacontrol.domain;

import br.com.ghx.farmacontrol.enumeration.EnumPersonType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Pessoa")
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class PessoaEntity extends BaseEntity {

    @Id
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "document_number", unique = true)
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "person_type", nullable = false)
    private EnumPersonType personType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private UsuarioEntity user;
}