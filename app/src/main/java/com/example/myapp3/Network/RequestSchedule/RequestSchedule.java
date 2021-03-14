package com.example.myapp3.Network.RequestSchedule;

import android.util.Log;

import com.example.myapp3.Network.App;
import com.example.myapp3.Network.NetworkService;
import com.example.myapp3.Network.RequestSchedule.ResponseSemesters.ResponseSemesters;
import com.example.myapp3.Network.ResponseSchedule.ResponseSchedule;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RequestSchedule{

    @SerializedName("contractId")
    @Expose
    private Integer contractId;
    @SerializedName("courseNumber")
    @Expose
    private Integer courseNumber;
    @SerializedName("groupNumber")
    @Expose
    private Integer groupNumber;
    @SerializedName("subgroupNumber")
    @Expose
    private Integer subgroupNumber;
    @SerializedName("groupId")
    @Expose
    private Integer groupId;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getContractId() { return contractId; }

    public void setContractId(Integer contractId) { this.contractId = contractId; }

    public Integer getCourseNumber() { return courseNumber; }

    public void setCourseNumber(Integer courseNumber) { this.courseNumber = courseNumber; }

    public Integer getGroupNumber() { return groupNumber; }

    public void setGroupNumber(Integer groupNumber) { this.groupNumber = groupNumber; }

    public Integer getSubgroupNumber() { return subgroupNumber; }

    public void setSubgroupNumber(Integer subgroupNumber) { this.subgroupNumber = subgroupNumber; }

    public Integer getGroupId() { return groupId; }

    public void setGroupId(Integer groupId) { this.groupId = groupId; }

    public Integer getYear() { return year; }

    public void setYear(Integer year) { this.year = year; }

    public Integer getSemester() { return semester; }

    public void setSemester(Integer semester) { this.semester = semester; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public class Connect {

   /*     public ResponseCourses getCourses() {
            ResponseCourses response = new ResponseCourses();
            CompletableFuture<ResponseCourses> future = NetworkService.getInstance()
                    .getApi()
                    .getCourses(contractId);
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
            return response;
        }

        public ResponseGroups getGroups() {
            ResponseGroups response = new ResponseGroups();
            CompletableFuture<ResponseGroups> future = NetworkService.getInstance()
                    .getApi()
                    .getGroups(contractId, courseNumber);
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
            return response;
        }

        public ResponseSubgroups getSubgroups() {
            ResponseSubgroups response = new ResponseSubgroups();
            CompletableFuture<ResponseSubgroups> future = NetworkService.getInstance()
                    .getApi()
                    .getSubgroups(contractId,
                            courseNumber,
                            groupNumber);
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
            return response;
        }

        public ResponseSemesters getSemesters() {
            ResponseSemesters response = new ResponseSemesters();
            CompletableFuture<ResponseSemesters> future = NetworkService.getInstance()
                    .getApi()
                    .getSemesters(contractId,
                            courseNumber,
                            groupNumber,
                            subgroupNumber);
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
                groupId = response.getGroupId();
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
            return response;
        }

        public ResponseDates getDates() {
            ResponseDates response = new ResponseDates();
            CompletableFuture<ResponseDates> future = NetworkService.getInstance()
                    .getApi()
                    .getDates(groupId,
                            year,
                            semester);
            try {
                response = future.get();
                if (!response.getError().equals("0"))
                    throw new Exception(response.getError()); // На данный момент ошибка в запросе останавливает программу, можно сделать вывод сообщений пользователю об ошибке
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            } catch (Exception e) {
                Log.e("Ошибка запроса: ", String.valueOf(e));
            }
            return response;
        }

        public ResponseSchedule getSchedule() {
            ResponseSchedule response = new ResponseSchedule();
            CompletableFuture<ResponseSchedule> future = NetworkService.getInstance()
                    .getApi()
                    .getSchedule(groupId,
                            date);
            try {
                response = future.get();
                Log.e("Response: ", response.getError());
            } catch (ExecutionException e) {
                Log.e("ExecutionException: ", String.valueOf(e));
            } catch (InterruptedException e) {
                Log.e("InterruptedException: ", String.valueOf(e));
            }
            return response;
        } */
    }
}