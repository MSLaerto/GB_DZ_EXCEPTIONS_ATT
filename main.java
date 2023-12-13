import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в одну строку (Фамилия Имя Отчество датарождения номертелефона пол):");
        String input = scanner.nextLine();
        scanner.close();
        String[] data = input.split(" ");
        if (data.length != 6) {
            System.err.println("Ошибка: количество введенных данных не совпадает с требуемым");
            return;
        }

        String surname = data[0];
        String name = data[1];
        String patronymic = data[2];
        String birthDate = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        try {
            validateData(surname, name, patronymic, birthDate, phoneNumber, gender);
            String fileName = surname + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            String fileContent = surname + " "+ name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender + "\n";
            fileWriter.write(fileContent);
            fileWriter.close();
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (InvalidDataFormatException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }

    private static void validateData(String surname, String name, String patronymic, String birthDate,
                                     String phoneNumber, String gender) throws InvalidDataFormatException {
        if (!surname.matches("[а-яА-Яa-zA-Z]+")) {
            throw new InvalidDataFormatException("Некорректная фамилия");
        }
        if (!name.matches("[а-яА-Яa-zA-Z]+")) {
            throw new InvalidDataFormatException("Некорректное имя");
        }
        if (!patronymic.matches("[а-яА-Яa-zA-Z]+")) {
            throw new InvalidDataFormatException("Некорректное отчество");
        }
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDataFormatException("Некорректная дата рождения. Введите в формате dd.mm.yyyy");
        }
        if (!phoneNumber.matches("\\d+")) {
            throw new InvalidDataFormatException("Некорректный номер телефона");
        }
        if (!gender.matches("[mf]")) {
            throw new InvalidDataFormatException("Некорректный пол. Введите 'f' или 'm'");
        }
    }

    private static class InvalidDataFormatException extends Exception {
        public InvalidDataFormatException(String message) {
            super(message);
        }
    }
}