package info.eberry.demo.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@ToString
@EqualsAndHashCode
public class User {
    @Id
    protected Long id;
    private String name;
    private String family;
    private String email;

}
