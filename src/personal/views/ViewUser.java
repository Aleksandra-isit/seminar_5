package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;
        int typeWriteDown = typeToSave();
        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone), typeWriteDown);
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");

                        User user = userController.readUser(id, typeWriteDown);
                        System.out.println(user);


                        break;
                    case LIST:
                        List<User> users = userController.readList(typeWriteDown);
                        for (User item : users) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case UPDATE:
                        String numId = prompt("Какой контакт вы хотите изменить? Введите ID: ");
                        userController.validateExistingUser(numId, typeWriteDown);
                        userController.updateUser(numId, CreateGuy(), typeWriteDown);
                        break;
                    case DELETE:
                        String numIdToDelete = prompt("Какой контакт вы хотите изменить? Введите ID: ");
                        userController.validateExistingUser(numIdToDelete, typeWriteDown);
                        userController.deleteUser(numIdToDelete, typeWriteDown);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

        private String prompt(String message){
            Scanner in = new Scanner(System.in);
            System.out.print(message);
            return in.nextLine();
        }

        private int typeToSave(){
            String typeToSave = prompt("Выберите в каком формате вы хотите сохранить новые данные: 1 - <<,>> 2 - <<;>>\n");
            if (typeToSave.equals("1") || typeToSave.equals("2")){
                return Integer.parseInt(typeToSave);
            }
            return typeToSave();
        }

        private User CreateGuy(){
            String firstName = prompt("Имя: ");
            String lastName = prompt("Фамилия: ");
            String phone = prompt("Номер телефона: ");
            User newGuy = new User(firstName, lastName, phone);
            return newGuy;
        }
    }



























