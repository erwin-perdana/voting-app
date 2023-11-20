package com.voting.voting_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voting.voting_app.constant.Status;
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
@Table(name = "m_voting")
public class Voting {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "voting_date")
    private LocalDateTime votingDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "voting")
    @JsonManagedReference
    private List<VotingDetail> votingDetails;

    @OneToMany(mappedBy = "voting")
    @JsonManagedReference
    private List<VotingAnswer> votingAnswers;
}
