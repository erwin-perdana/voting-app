package com.voting.voting_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_voting_detail")
public class VotingDetail {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    @JsonBackReference
    private Voting voting;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
