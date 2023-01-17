package personal.model;

public class UserMapper {
    public String map(User user) {
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User map(String line) {
        String[] lines = splitLines(line); //line.split(",");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

    public String mapByPointComma(User user){
        return String.format("%s;%s;%s;%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User mapByPointComma(String line){
        String[] lines = splitLines(line); //line.split(";");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

    private String[] splitLines(String line){
        String[] lines;
        if (line.contains(",")){
            lines = line.split(",");
            return lines;
        }
        lines = line.split(";");
        return lines;
    }
}
