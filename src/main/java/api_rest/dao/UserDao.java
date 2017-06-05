package api_rest.dao;

import api_rest.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by ferlopez on 02/06/2017.
 */
@ApplicationScoped
public class UserDao implements UserRepository {

    @PersistenceContext(unitName = "primary2")
    EntityManager em;

    @Override
    public User get(Long id) throws NotFoundException {
        User user = em.find(User.class, id);
        if (user == null){
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void remove(Long id) throws NotFoundException {
        User persisted = get(id);
        if (persisted != null) {
            em.remove(persisted);
        }
    }

    @Override
    public User update(User user) throws NotFoundException {
        User persisted = get(user.getId());
        if (persisted != null){
            if (user.getFirstName() != null){
                persisted.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null){
                persisted.setLastName(user.getLastName());
            }
        }
        return persisted;
    }

    @Override
    public List<User> query(SqlSpecification sqlSpecification) {
        return em.createQuery(sqlSpecification.toSqlClauses(), User.class).getResultList();
    }
}
