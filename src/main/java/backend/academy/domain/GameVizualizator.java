package backend.academy.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GameVizualizator {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {

        writer.write("Здравствуй! Ты зашел в игру 'Виселица', " +
            "укажи категорию слов, которая нравится больше всего из следующих:");
        writer.flush();
        writer.write("Математика   Медицина   Города мира   Штаты США   Страны");
        writer.flush();
        writer.write("Просто нажми 'Enter' для выбора случайной категории.");
        writer.flush();
        String category = reader.readLine();

        writer.write("Теперь выбери уровень сложности из трех, " +
            "число справа обозначает количество допустимых ошибок");
        writer.flush();
        writer.write("Легкий - 7 ошибок   Средний - 6 ошибок  Сложный - 5 ошибок");
        writer.flush();
        writer.write("Просто нажми 'Enter' для выбора случайного уровня сложности.");
        writer.flush();
        String level = reader.readLine();
        writer.write(String.valueOf(level));
        writer.flush();
    }


}
