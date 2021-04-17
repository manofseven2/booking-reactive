package info.eberry.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table
@ToString(includeFieldNames = true, exclude = {"reservations", "initialCreditLimit"})
public class User {
    @Id
    protected Long id;
    private String name;
    private String family;
    private String email;

}
