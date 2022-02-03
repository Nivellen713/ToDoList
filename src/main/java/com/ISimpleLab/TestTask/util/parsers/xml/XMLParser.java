package com.ISimpleLab.TestTask.util.parsers.xml;

import com.ISimpleLab.TestTask.entities.Task;
import com.ISimpleLab.TestTask.entities.ToDoList;
import com.ISimpleLab.TestTask.util.ConfigurationDataUsage;
import com.ISimpleLab.TestTask.util.parsers.ToDoListParser;
import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.List;

/**
 * Произодит ввод/вывод данных в/из xml файла
 */

public class XMLParser implements ToDoListParser {


    /**
     * Возвращает список задач в виде объекта ToDoList из xml файла
     * @param pathToFile путь к xml файлу
     * @return список задач в виде объекта ToDoList
     */
    @Override
    public ToDoList getToDoListFromFile(String pathToFile) {
        try {
            File file = new File(pathToFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(ToDoList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (ToDoList) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ToDoList();
    }

    /**
     * Перезаписывает xml файл на основе полученного списка задач
     * @param taskList список задач
     */
    @Override
    public void rewriteToDoList(List<Task> taskList) {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element toDoList = document.createElement("ToDoList");
            document.appendChild(toDoList);

            Element newTask = document.createElement("Task");
            toDoList.appendChild(newTask);

            for (Task task : taskList) {

                // set an attribute to newTask element
                Attr idAttr = document.createAttribute("id");
                idAttr.setValue(String.valueOf(task.getId()));
                newTask.setAttributeNode(idAttr);

                Attr captionAttr = document.createAttribute("caption");
                captionAttr.setValue(task.getCaption());
                newTask.setAttributeNode(captionAttr);

                // Description element
                Element description = document.createElement("Description");
                description.appendChild(document.createTextNode(task.getDescription()));
                newTask.appendChild(description);

                // Priority element
                Element priority = document.createElement("Priority");
                priority.appendChild(document.createTextNode(String.valueOf(task.getPriority())));
                newTask.appendChild(priority);

                // Deadline element
                Element deadline = document.createElement("Deadline");
                deadline.appendChild(document.createTextNode(String.valueOf(task.getDeadline())));
                newTask.appendChild(deadline);

                // Status elements
                Element status = document.createElement("Status");
                status.appendChild(document.createTextNode(task.getStatus()));
                newTask.appendChild(status);

                // Complete elements
                Element complete = document.createElement("Complete");
                complete.appendChild(document.createTextNode(String.valueOf(task.getComplete())));
                newTask.appendChild(complete);
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(ToDoList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ToDoList toDoList1 = new ToDoList();
            toDoList1.setTask(taskList);
            jaxbMarshaller.marshal(toDoList1, new File(new ConfigurationDataUsage().getXMLToDoListPath()));
        } catch (ParserConfigurationException | JAXBException e) {
            e.printStackTrace();
        }
    }

}
