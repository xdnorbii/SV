package com.course.ums;

import com.course.ums.ws.SemesterAdd;
import com.course.ums.ws.user.AddUser;
import com.course.ums.ws.user.ListUsers;
import com.course.ums.ws.course.CourseAdd;
import com.course.ums.ws.user.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by vh on 11/2/17.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        Spark.port(8080);
        Spark.post("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "world";
            }
        });
        Spark.post("/user/add", new AddUser());
        Spark.post("/user/list", new ListUsers());
        Spark.get("/user/list", new ListUsers());

        Spark.post("user/authenticate", new Authenticate());
        Spark.post("user/student/add", new StudentAdd());
        Spark.post("user/teacher/add", new TeacherAdd());
        Spark.post("/course/add",new CourseAdd());
        Spark.post("/semester/add", new SemesterAdd());
    }
}
