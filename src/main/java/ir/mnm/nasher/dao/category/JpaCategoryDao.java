package ir.mnm.nasher.dao.category;

import ir.mnm.nasher.dao.JpaDao;
import ir.mnm.nasher.entity.Category;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mghasemy on 12/9/16.
 */
public class JpaCategoryDao extends JpaDao<Category,Long> implements CategoryDao{

    public JpaCategoryDao(Class<Category> entityClass) {
        super(entityClass);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getall(){
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Category> criteriaQuery = builder.createQuery(Category.class);

        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.orderBy(builder.desc(root.get("date")));

        TypedQuery<Category> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

}
