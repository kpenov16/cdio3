package data;

import java.util.Iterator;
import java.util.List;

public class UserDAO {
    private List<UserDTO> userDTOList;
    private static UserDAO instance;

    private UserDAO(){}

    public static UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public UserDTO getUser(int userId) {
        Iterator iterator = userDTOList.iterator();
        while (iterator.hasNext()) {
            UserDTO tempUser = (UserDTO) iterator.next();
            if (tempUser.getUserId() == userId) {
                return tempUser;
            }
        }

        return null;
    }

    public void createUser(UserDTO user) {
        UserDTO tempUser = new UserDTO();
        tempUser.setUserId(user.getUserId());
        tempUser.setIni(user.getIni());
        tempUser.setRoles(user.getRoles());
        tempUser.setUserName(user.getUserName());
        tempUser.setCpr(user.getCpr());
        tempUser.setPassword(user.getPassword());
        userDTOList.add(tempUser);
    }

    public void updateUser(UserDTO user) {
        Iterator iterator = userDTOList.iterator();
        while (iterator.hasNext()) {
            UserDTO tempUser = (UserDTO) iterator.next();
            if (tempUser.getUserId() == user.getUserId()) {
                tempUser.setIni(user.getIni());
                tempUser.setRoles(user.getRoles());
                tempUser.setUserName(user.getUserName());
                tempUser.setCpr(user.getCpr());
                tempUser.setPassword(user.getPassword());
                return;
            }
        }
    }

    public void deleteUser(int userId) {
        userDTOList.removeIf(o -> ((UserDTO) o).getUserId() == userId);
    }

}
