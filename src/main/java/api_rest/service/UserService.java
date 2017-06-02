package api_rest.service;

import api_rest.dao.SqlSpecification;
import api_rest.dao.UserRepository;
import api_rest.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by ferlopez on 02/06/2017.
 */

@Stateless
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.query( () -> "SELECT u FROM User u");
    }

    public List<User> filter (SqlSpecification sqlSpecification){
        return userRepository.query(sqlSpecification);
    }

    public User get(Long id) throws NotFoundException{
        return userRepository.get(id);
    }

    public void add(User user){
        userRepository.add(user);
    }

    public void remove(Long id) throws NotFoundException{
        userRepository.remove(id);
    }

    public User update(User user) throws NotFoundException{
        return userRepository.update(user);
    }

}
