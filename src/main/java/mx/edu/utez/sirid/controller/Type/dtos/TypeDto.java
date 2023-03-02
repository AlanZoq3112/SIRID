package mx.edu.utez.sirid.controller.Type.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.sirid.model.Type.Type;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TypeDto {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 45)
    private String name_aula;

    public Type getType(){
        return new Type(
                getId(),
                getName_aula()
        );
    }
}
