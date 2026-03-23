package Repository;

import Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Livro, Long> {
}
