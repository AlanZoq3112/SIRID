package mx.edu.utez.sirid.model.Type;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
    boolean existsById(Long id);
=======

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
    boolean findById(long Id);
    List<Type> findAll();
>>>>>>> bda133956100d56c6e15099595aa72cdf3599592
}
