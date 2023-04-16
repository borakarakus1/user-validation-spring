package com.bora.validatetest.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Request")
public class Request {
    @SequenceGenerator(name = "request_sequence"
                      ,sequenceName = "request_sequence"
                      ,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "request_sequence")
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_name")
    @NotNull
    private User author;
    @Enumerated(EnumType.STRING)
    @NotNull
    private ERequest type;
    @NotNull
    @Column(name = "content")
    @Size(min = 30)
    private String content;
    @CreationTimestamp
    @Column(updatable = false,name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "approved")
    private boolean approved = false;
    @Column(name = "denied")
    private boolean denied = true;


}
