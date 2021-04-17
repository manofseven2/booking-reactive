package info.eberry.demo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@NoArgsConstructor
@Table
@EqualsAndHashCode
@ToString
public class InitialCreditLimit {
    @Id
    protected Long id;
    private Long amount;
    @Column("USER_ID")
    private Long userId;
}
