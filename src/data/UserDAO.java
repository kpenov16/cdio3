package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO {
    private List<UserDTO> userDTOList = new ArrayList<UserDTO>();
    private static UserDAO instance;

    private UserDAO(){
        //Static dataset
        List<String> roles = new ArrayList<String>();
        roles.add("abe");
        UserDTO tempUser = new UserDTO(1, "Bent", "Jensen", "benjen", "bj", "123123123", "sjovabe", roles, true);
        this.createUser(tempUser);
        UserDTO tempUser2 = new UserDTO(2, "Kent", "Jensen", "kenjen", "bj", "123123123", "sjovabe", roles, true);
        this.createUser(tempUser2);
        UserDTO tempUser3 = new UserDTO(3, "Lent", "Jensen", "lenjen", "bj", "123123123", "sjovabe", roles, true);
        this.createUser(tempUser3);
    }

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
