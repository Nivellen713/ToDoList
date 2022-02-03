package com.ISimpleLab.TestTask.userCommunication;

import com.ISimpleLab.TestTask.entities.Facade;
import com.ISimpleLab.TestTask.util.parsers.StringParser;

import java.util.Scanner;

/**
 * Слой коммуникации с пользователем, а именно:
 * Вывод задач по тегам,
 * Вывод справки по командам,
 * Приём запросов и вывод результата их выполнения
 */

public class Questioner {

    /**
     * Приём, передача другим классам и вывод результата выполнения пользовательских команд
     */
    public void userActionRequest() {
        Scanner scanner = new Scanner(System.in);
        StringParser stringParser = new StringParser();
        demonstrateProgramFunctionality();
        String userAction;
        userAction = scanner.nextLine();

        String stringId = stringParser.getNumbersFromString(userAction);
        userAction = stringParser.removeNumbersFromString(userAction);

        switch (userAction.trim()) {
            case "help":
                demonstrateProgramFunctionality();
                break;
            case "list -s new":
                System.out.println(new Facade().getTasksListByStatus("new"));
                break;
            case "list -s done":
                System.out.println(new Facade().getTasksListByStatus("done"));
                break;
            case "list":
                System.out.println(new Facade().getTasksList());
                break;
            case "new":
                new Facade().transferNewTaskParameters(new StringParser().requestTaskParameters());
                break;
            case "edit":
                new Facade().transferEditableTaskParameters(stringId, new StringParser().requestTaskParameters());
                break;
            case "complete":
                new Facade().transferTaskStatus(stringId, "done");
                break;
            case "remove":
                new Facade().transferRemovedTaskId(stringId);
                break;
            default:
                System.out.println("Нет такой команды как " + userAction +
                        "\nИспользуйте help");
        }


    }

    /**
     * Вывод в консоль консольных команд программы
     */
    private void demonstrateProgramFunctionality() {
        System.out.println("Вывод справки по командам: help\n" +
                "Вывод новых задач: list -s new\n" +
                "Вывод выполненных задач: list -s done\n" +
                "Вывод всех задач: list\n" +
                "Добавить новую задачу: new\n" +
                "Редактировать задачу: edit \"идентификатор\"\n" +
                "Пометить задачу как выполненную: complete \"идентификатор\"\n" +
                "Удалить задачу: remove \"идентификатор\"\n");
    }


}
