package com.ISimpleLab.TestTask.util.workWithObjects;

import com.ISimpleLab.TestTask.entities.Task;
import com.ISimpleLab.TestTask.util.parsers.StringParser;
import com.ISimpleLab.TestTask.util.parsers.ToDoListParser;
import com.ISimpleLab.TestTask.util.parsers.xml.XMLParser;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.time.*;
import java.util.Map;

/**
 * Класс выполнения команд над объектом типа "Task"
 */

public class TaskHandler {

    private TaskSeeker taskSeeker = new TaskSeeker();
    private ToDoListParser toDoListParser = new XMLParser();


    /**
     * Получает список задач из xml файла, добавляет в него новую задачу и отправляет обновлённый список
     * на перезапись классу ToDoListParser
     * @param taskParameters набор пар вида "имя параметра - параметр введённый пользователем"
     */
    public void addNewTask(Map<String, String> taskParameters) {
        List<Task> taskList = taskSeeker.getTasksList();
        Task newTask = createNewTaskByParameters(taskParameters);
        newTask.setId(taskList.get(taskList.size() - 1).getId() + 1);
        taskList.add(newTask);
        toDoListParser.rewriteToDoList(taskList);
    }

    /**
     * Создаёт объект Task на основе параметров, полученных от пользователя
     * @param taskParameters набор пар вида "имя параметра - параметр введённый пользователем"
     * @return объект Task, сформированный по параметрам, введёнными пользователем
     */
    private Task createNewTaskByParameters(Map<String, String> taskParameters) {
        byte priority = Byte.parseByte(taskParameters.get("priority"));
        try {
            LocalDate localDate = LocalDate.parse(taskParameters.get("deadline"));
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            xmlGregorianCalendar.setYear(gregorianCalendar.get(GregorianCalendar.YEAR));
            xmlGregorianCalendar.setMonth(gregorianCalendar.get(GregorianCalendar.MONTH) + 1);
            xmlGregorianCalendar.setDay(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
        } catch (DatatypeConfigurationException exception) {
            exception.printStackTrace();
        }
        return new Task(taskParameters.get("caption"), taskParameters.get("description"),
                priority, new StringParser().parseStringDateToXMLGregorianCalendar(taskParameters.get("deadline")));
    }

    /**
     * Редактирует задачу из списка по введённым пользователем параметрам и идентификатору
     * @param stringId идентификатор задачи
     * @param taskParameters набор пар вида "имя параметра - параметр введённый пользователем"
     */
    public void editTask(String stringId, Map<String, String> taskParameters) {
        List<Task> taskList = taskSeeker.getTasksList();
        int taskIndex = getTaskListIndexById(taskList, stringId);
        Task taskToEdit = taskList.get(taskIndex);

        if (!taskParameters.get("caption").isEmpty()) {
            taskToEdit.setCaption(taskParameters.get("caption"));
        }
        if (!taskParameters.get("description").isEmpty()) {
            taskToEdit.setDescription(taskParameters.get("description"));
        }
        if (!taskParameters.get("priority").isEmpty()) {
            taskToEdit.setPriority(Byte.parseByte(taskParameters.get("priority")));
        }
        if (!taskParameters.get("deadline").isEmpty()) {
            taskToEdit.setDeadline(
                    new StringParser().parseStringDateToXMLGregorianCalendar(taskParameters.get("deadline")));
        }
        taskList.set(taskIndex, taskToEdit);
        toDoListParser.rewriteToDoList(taskList);
    }

    /**
     * Удаляет из списка задач задачу, по введённому пользователем идентификатору
     * @param stringId идентификатор задачи
     */
    public void removeTask(String stringId) {
        List<Task> taskList = taskSeeker.getTasksList();
        int indexRemovingElement = getTaskListIndexById(taskList, stringId);
        taskList.remove(indexRemovingElement);
        toDoListParser.rewriteToDoList(taskList);
    }

    /**
     * Редактирует статус задачи на статус, введённый пользователем
     * @param stringId идентификатор задачи
     * @param status статус задачи
     */
    public void setTaskStatus(String stringId, String status) {
        List<Task> taskList = taskSeeker.getTasksList();
        int index = getTaskListIndexById(taskList, stringId);
        taskList.get(index).setStatus(status);
        taskList.get(index).setComplete(new StringParser().parseStringDateToXMLGregorianCalendar(String.valueOf(LocalDate.now())));
        toDoListParser.rewriteToDoList(taskList);
    }

    /**
     * Возвращает индекс в списке задач по идентификатору задачи
     * @param taskList список задач
     * @param stringId идентификатор задачи
     * @return индекс задачи из списка
     */
    private int getTaskListIndexById(List<Task> taskList, String stringId) {
        int id = Integer.parseInt(stringId);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
