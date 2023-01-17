package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user, int typeToSave) throws Exception{
        validateUser(user);
        repository.CreateUser(user, typeToSave);
    }

    public User readUser(String userId, int typeToSave) throws Exception {
        List<User> users = repository.getAllUsers(typeToSave);
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> readList(int typeToSave){
        List<User> users = repository.getAllUsers(typeToSave);
        return users;
    }

    public void updateUser(String idNumber, User newGuy, int typeToSave) throws Exception {
        validateExistingUser(idNumber, typeToSave);
        newGuy.setId(idNumber);
        validateUserId(newGuy);
        repository.updateUser(newGuy, typeToSave);
    }

    public void deleteUser(String IdNumber, int typeToSave) throws Exception {
        repository.deleteUser(IdNumber, typeToSave);
    }

    private void validateUser(User user) throws Exception {
        if (user.getFirstName().contains(" ")){
            throw new Exception("Users name has unacceptable characters");
        }
        if (user.getLastName().contains(" ")){
            throw new Exception("Users lastname has unacceptable characters");
        }
    }

    private void validateUserId(User user) throws Exception{
        if (user.getId().isEmpty()){
            throw new Exception("User has no id");
        }
        validateUser(user);
    }

    public void validateExistingUser(String inputId, int typeToSave) throws Exception{
        List<User> users = repository.getAllUsers(typeToSave);
        for (User u: users) {
            if (u.getId().equals(inputId)){
                return;
            }
        }
        throw new Exception("No such ID");
    }
}































