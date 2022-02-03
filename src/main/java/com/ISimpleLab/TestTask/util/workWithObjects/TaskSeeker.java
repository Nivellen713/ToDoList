package com.ISimpleLab.TestTask.util.workWithObjects;

import com.ISimpleLab.TestTask.entities.Task;
import com.ISimpleLab.TestTask.util.ConfigurationDataUsage;
import com.ISimpleLab.TestTask.util.parsers.ToDoListParser;
import com.ISimpleLab.TestTask.util.parsers.xml.XMLParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Ищет задачу в списке по указанным пользователем параметрам
 */

public class TaskSeeker {

    /**
     * Возвращает список задач из xml файла
     * @return список задач
     */
    public List<Task> getTasksList() {
        ToDoListParser toDoListParser = new XMLParser();
        return toDoListParser.getToDoListFromFile(new ConfigurationDataUsage().getXMLToDoListPath()).getTask();
    }

    /**
     * Возвращает список задач из xml файла, учитывая параметр status
     * @param status статус задачи
     * @return список задач
     */
    public List<Task> getTaskListByStatus(String status) {
        List<Task> taskList = new ArrayList<>();
        for (Task taskStatusNew : getTasksList()) {
            if (taskStatusNew.getStatus().equals(status)) {
                taskList.add(taskStatusNew);
            }
        }
        return taskList;
    }

    /**
     * Возвращает задачу из xml файла по идентификатору
     * @param id идентификатор задачи
     * @return задача
     */
    public Task getTaskById(String id) {
        for (Task task : getTasksList()) {
            if (task.getId() == Integer.parseInt(id)) {
                return task;
            }
        }
        return new Task();
    }

}
