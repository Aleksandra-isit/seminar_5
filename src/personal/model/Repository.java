package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers(int typeToSave);
    String CreateUser(User user, int typeToSave);
    void updateUser(User user, int typeToSave);
    void deleteUser(String idUserToDelete, int typeToSave);
}
