package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.UserBO;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.UserDAO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.dto.UserDTO;
import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkUser(String userName, String password, String password1) {
        return userDAO.check(userName,password,password1);
    }

    @Override
    public String generatenextUserId() {
        return userDAO.generateNextId();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail() , userDTO.getPassword(), userDTO.getPasswordHint()));
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> userDTOArrayList = new ArrayList<>();
        List<User> userArrayList = userDAO.getAll();
        for (User user : userArrayList) {
            userDTOArrayList.add(new UserDTO(user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPassword_hint()
            ));
        }
        return userDTOArrayList;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getPasswordHint()));
    }

    @Override
    public boolean deleteUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        return userDAO.delete(user);
    }
}
