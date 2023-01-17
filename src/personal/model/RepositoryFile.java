package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers(int typeToSave) {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            //users.add(mapper.map(line));
            users.add(whichFormatRead(typeToSave, line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user, int typeToSave) {

        List<User> users = getAllUsers(typeToSave);
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        writeDown(users, typeToSave);
        return id;
    }

    @Override
    public void updateUser(User user, int typeToSave) {
        List<User> users = getAllUsers(typeToSave);
        User target = users.stream().filter(i -> i.getId().equals(user.getId())).findFirst().get();
        target.setFirstName(user.getFirstName());
        target.setLastName(user.getLastName());
        target.setPhone(user.getPhone());
        writeDown(users, typeToSave);
    }

    @Override
    public void deleteUser(String idUserToDelete, int typeToSave) {
        List<User> users = getAllUsers(typeToSave);
        users.removeIf(user -> user.getId().equals(idUserToDelete));
        writeDown(users, typeToSave);
    }

    private void writeDown(List<User> users, int saveFormat){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            //lines.add(mapper.map(item));
            lines.add(whichFormatWriteDown(saveFormat, item));
        }
        fileOperation.saveAllLines(lines);

    }

    private String whichFormatWriteDown(int type, User user){
        if (type == 1){
            return mapper.map(user);
        }
        return mapper.mapByPointComma(user);
    }

    private User whichFormatRead(int type, String line){
        if (type == 1){
            return mapper.map(line);
        }
        return mapper.mapByPointComma(line);

    }
}


























