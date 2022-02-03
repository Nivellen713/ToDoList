package com.ISimpleLab.TestTask.util.parsers;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Форматирование строковых данных по обозначенным параметрам
 */
public class StringParser {

    /**
     * Удаляет не числовые символы из входящей строки
     * @param inputString произовальная строка
     * @return строка чисел
     */
    public String getNumbersFromString(String inputString) {
        String[] stringArrayOfNumbers = inputString.split("\\D+");
        StringBuilder stringBuilder = new StringBuilder();
        for (String stringNumber : stringArrayOfNumbers) {
            stringBuilder.append(stringNumber);
        }
        return stringBuilder.toString();
    }

    /**
     * Удаляет числовые символы из входящей строки
     * @param inputString произвольная строка
     * @return строка нечисловых символов
     */
    public String removeNumbersFromString(String inputString) {
        return inputString.replaceAll("\\d+", "");
    }

    /**
     * Форматирует введённые пользователем параметры задачи
     * @return введёные пользователем параметры задачи
     * в виде пары "название параметра - значение параметра, введённое пользователем"
     */
    public Map<String, String> requestTaskParameters() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> parametersMap = new HashMap<>();
        System.out.println("Заголовок\n");
        parametersMap.put("caption", scanner.nextLine());
        System.out.println("\nОписание\n");
        parametersMap.put("description", scanner.nextLine());
        System.out.println("\nВажность\n");
        parametersMap.put("priority", scanner.nextLine());
        System.out.println("\nСрок\n \"yyyy-MM-dd\"");
        parametersMap.put("deadline", scanner.nextLine());
        return parametersMap;
    }

    /**
     * Форматирует строковую дату в формат XMLGregorianCalendar
     * @param stringDate дата в строковом виде
     * @return дата формата XMLGregorianCalendar
     */
    public XMLGregorianCalendar parseStringDateToXMLGregorianCalendar(String stringDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(stringDate);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (ParseException | DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return new XMLGregorianCalendarImpl();
    }
}
