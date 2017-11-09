package com.course.ums;

import com.course.ums.ws.AddUser;
import com.course.ums.ws.ListUsers;
import org.json.JSONObject;
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

        JSONObject test = new JSONObject();
        test.put("id", 0);
        test.put("text", "hello world");

        System.out.println(test);

        test = new JSONObject("{hello: world, bla : blabla}");
        System.out.println(test.get("hello"));
        System.out.println(test.get("bla"));
    }
}
