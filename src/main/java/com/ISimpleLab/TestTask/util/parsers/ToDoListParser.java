package com.ISimpleLab.TestTask.util.parsers;

import com.ISimpleLab.TestTask.entities.Task;
import com.ISimpleLab.TestTask.entities.ToDoList;

import java.util.List;

/**
 * Интерфейс форматирования xml файла
 */

public interface ToDoListParser {

    /**
     * Возвращает список задач из файла
     * @param pathToFile путь к файлу
     * @return список задач в виде объекта ToDoList
     */
    ToDoList getToDoListFromFile(String pathToFile);

    /**
     * Перезаписывает файл списка задач на основе полученного списка
     * @param taskList список задач
     */
    void rewriteToDoList(List<Task> taskList);

}
