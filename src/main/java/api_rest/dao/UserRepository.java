package api_rest.dao;

import api_rest.model.User;

import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by ferlopez on 02/06/2017.
 */


public interface UserRepository {
    User get(@NotNull Long id)throws NotFoundException;
    void add(@NotNull User user);
    void remove(@NotNull Long id) throws NotFoundException;
    User update(@NotNull User user) throws NotFoundException;
    List<User> query(@NotNull SqlSpecification sqlSpecification);
}
