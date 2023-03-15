package mx.edu.utez.sirid.model.Type;


import mx.edu.utez.sirid.enums.TiposDeSalones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
    boolean findById(long Id);
    boolean existsByName(TiposDeSalones name);
    List<Type> findAll();
}
