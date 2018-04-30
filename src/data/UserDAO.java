package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO implements IUserDAO{
    private List<UserDTO> userList;

    public UserDAO(){
        userList = new ArrayList<UserDTO>();

        UserDTO tempUser = new UserDTO(); //Hardcoded user as given in the problem description
        tempUser.setUserId(12);
        tempUser.setUserName("Anders And");

        try {
            createUser(tempUser);
        } catch(DALException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        Iterator<UserDTO> iterator = userList.iterator();
        while (iterator.hasNext()) {
            UserDTO tempUser = iterator.next();
            if (tempUser.getUserId() == userId) {
                return tempUser;
            }
        }

        throw new DALException("Fejl: Bruger ikke fundet!");
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            if (((UserDTO) iterator.next()).getUserId() == user.getUserId()) {
                throw new DALException("Fejl: Bruger eksisterer allerede!");
            }
        }
        UserDTO tempUser = new UserDTO();
        tempUser.setUserId(user.getUserId());
        tempUser.setUserName(user.getUserName());
        userList.add(tempUser);
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        Iterator iterator = userList.iterator();
        boolean j = false;
        while (iterator.hasNext()) {
            if (((UserDTO) iterator.next()).getUserId() == userId) {
                iterator.remove();
                j = true;
            }
        }
        if (!j) {
            throw new DALException("Fejl: Bruger ikke fundet!");
        }
    }
}