package fpt.t2010a.assignmentsprongbootapi.repository;

import fpt.t2010a.assignmentsprongbootapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
