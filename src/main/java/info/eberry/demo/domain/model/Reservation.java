package info.eberry.demo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import java.util.Date;

@Data
@NoArgsConstructor
@Table
@ToString(includeFieldNames = true)
@EqualsAndHashCode
public class Reservation {
    @Id
    protected Long id;
    private Long cost;
    private String transactionNumber;
    private Boolean successful;
    @Column("USER_ID")
    private Long userId;
}
