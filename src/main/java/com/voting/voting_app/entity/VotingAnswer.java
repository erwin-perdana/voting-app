package com.voting.voting_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_voting_answer")
public class VotingAnswer {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "vote_date")
    private LocalDateTime voteDate;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    @JsonBackReference
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    @JsonBackReference
    private Voting voting;
}
