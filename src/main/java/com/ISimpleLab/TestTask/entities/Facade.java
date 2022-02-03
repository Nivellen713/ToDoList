package com.ISimpleLab.TestTask.entities;

import com.ISimpleLab.TestTask.util.workWithObjects.TaskHandler;
import com.ISimpleLab.TestTask.util.workWithObjects.TaskSeeker;

import java.util.List;
import java.util.Map;

/**
 * Распределяет полученные от класса Questioner задачи по классам - обработчикам
 */

public class Facade {

    private TaskSeeker taskSeeker = new TaskSeeker();
    private TaskHandler taskHandler = new TaskHandler();

    /**
     * Возвращает список задач, полученных от класса TaskSeeker
     * @return список задач
     */
    public List<Task> getTasksList() {
        return taskSeeker.getTasksList();
    }

    /**
     * Возвращает список задач, полученных от класса TaskSeeker и соответствующих параметру status
     * @param status статус задачи
     * @return список задач
     */
    public List<Task> getTasksListByStatus(String status){
        return taskSeeker.getTaskListByStatus(status);
    }

    /**
     * Передаёт список пар параметров классу TaskSeeker для добавления новой задачи
     * @param taskParameters список пар параметров вида "имя параметра - значение параметра"
     */
    public void transferNewTaskParameters(Map<String, String> taskParameters){
        taskHandler.addNewTask(taskParameters);
    }

    /**
     * Передаёт список пар параметров классу TaskSeeker для редактирования задачи
     * @param taskParameters список пар параметров вида "имя параметра - значение параметра"
     */
    public void transferEditableTaskParameters(String id, Map<String, String> taskParameters) {
        taskHandler.editTask(id, taskParameters);
    }

    /**
     * Передаёт паратетры "идентификатор" и "статус" классу TaskSeeker для обновления статуса задачи
     * @param id идентификатор задачи
     * @param status статус задачи
     */
    public void transferTaskStatus(String id, String status){
        taskHandler.setTaskStatus(id, status);
    }

    /**
     * Передаёт идентификатор задачи для её удаления из списка
     * @param id идентификатор задачи
     */
    public void transferRemovedTaskId(String id) {
        taskHandler.removeTask(id);
    }
}
