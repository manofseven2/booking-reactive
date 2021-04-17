package info.eberry.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//import javax.persistence.*;

@Data
@NoArgsConstructor
//@Entity
@Table
public class InitialCreditLimit {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
//    @Basic
    private Long amount;
//    @OneToOne
    @Column("USER_ID")
    private Long userId;
}
