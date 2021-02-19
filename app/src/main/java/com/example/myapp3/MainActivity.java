package com.example.myapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapp3.Network.RequestSchedule.RequestSchedule;
import com.example.myapp3.Network.RequestSchedule.ResponseCourses;
import com.example.myapp3.Network.RequestSchedule.ResponseDates;
import com.example.myapp3.Network.RequestSchedule.ResponseGroups;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;
import com.example.myapp3.Network.RequestSchedule.ResponseSubgroups;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.example.myapp3.Network.Service;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Пример заполнения входных данных, представленны все поля
        RequestSchedule requestSchedule = new RequestSchedule();
        requestSchedule.setContractId(1); // форма обучения
        requestSchedule.setCourseNumber(1); // номер курса, нумерация с еденицы
        requestSchedule.setGroupNumber(1); // номер группы, нумерация с еденицы
        requestSchedule.setSubgroupNumber(0); // номер подгруппы, нумерация с еденицы
        requestSchedule.setGroupId(0); // ИД группы в БД, нужно для упрощения запросов, в мейне не нужен
        requestSchedule.setYear(2021); // УЧЕБНЫЙ год
        requestSchedule.setSemester(1); // семестр (1 - первый, 2 - второй)
        requestSchedule.setDate("2021-02-08"); // понедельник запрашиваемой недели

        // RequestSchedule.Connect() содержит в себе все HTTP запросы для RequestSchedule
        // входные данные для запросов автоматом собираются из верхнего класса
        // т.к. get обявлен внутри requestCourses, все данные для отправки автоматом цепляются из requestCourses
        // Осталось оформить нормальную обратоку ошибок
        // каждый response содержит перемнную error куда в будущем будет нормально помещаться текст ошибки в случае проблем
        RequestSchedule.Connect get = requestSchedule.new Connect();

        // Запрос 1: GetCourses
        // Результат requestSchedule.сourseNumber = 2; в responseCourses.courses содержатся все доступные курсы для выбранной формы обучения в виде массива
        ResponseCourses responseCourses = get.getCourses();
        requestSchedule.setCourseNumber(responseCourses.getCourses().get(0));

        // Запрос 2: GetGroups
        // Результат requestSchedule.groupNumber = 6; в responseGroups.groups содержатся все доступные группы для выбранного курса в виде массива
        ResponseGroups responseGroups = get.getGroups();
        requestSchedule.setGroupNumber(responseGroups.getGroups().get(0));

        // Запрос 3: GetSubGroups
        // Результат requestSchedule.subgroupNumber = 1; в responseSubgroups.subgroups содержатся все доступные подгруппы для выбранной группы в виде массива
        ResponseSubgroups responseSubgroups = get.getSubgroups();
        requestSchedule.setSubgroupNumber(responseSubgroups.getSubgroups().get(0));

        // Запрос 4: GetSemesters
        // В ResponseSemesters.Year.Semesters значения такие: 0 - доступных семемтров нет, что является ошибкой в БД (мои кривые руки)
        //                                                    1 - доступен первый семестр
        //                                                    2 - доступен второй семестр
        //                                                    !!!3 - доступно оба семестра!!!
        // Результат requestSchedule.year = 2020, requestSchedule.semester = 2; в responseSemesters.group_id лежит ид группы (из БД) для упрощения дальнейших запросов
        ResponseSemesters responseSemesters = get.getSemesters();
        requestSchedule.setYear(responseSemesters.getYears().get(0).getYear());
        requestSchedule.setSemester(responseSemesters.getYears().get(0).getSemesters());

        // Запрос 5: GetDates
        // В ResponseDates.Dates[0] = первый доступный понедельник
        // В ResponseDates.Dates[1] = последний доступный понедельник
        // Результат date_begin = 2021-02-08, date_end = 2021-05-31
        ResponseDates responseDates = get.getDates();
        String date_begin = responseDates.getDates().get(0);
        String date_end = responseDates.getDates().get(1);

        // Общий запрос
        ResponseSchedule responseSchedule = get.getSchedule();

    }
}