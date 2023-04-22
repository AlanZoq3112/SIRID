package mx.edu.utez.sirid.model.Type;



import mx.edu.utez.sirid.model.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
    boolean findById(long Id);
    boolean existsByName(String name);
    List<Type> findAll();

    //Tare todos los tipos de aulas ordenados alfabeticamente
    @Query(
            value = " select * from types  ORDER BY name ASC;",
            nativeQuery = true
    )
    List<Type> selectTypes();
}
