package com.course.ums.ws;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ExamAdd extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_TEACHER)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps =DBManager.getConnection().prepareStatement("SELECT * from teachers_courses WHERE teachers_id = ? AND courses_id = ?");
        ResultSet rs = ps.executeQuery();
        if(rs.next());

        ps = DBManager.getConnection().prepareStatement("INSERT INTO exams(date,semesters_id,teachers_courses_id,groups_id) VALUES(?,?,?,?)");
        java.util.Date utilDate = new SimpleDateFormat("yyyyy-MM-dd").parse(request.getString("date"));

        ps.setDate(1, new java.sql.Date(utilDate.getTime()));
        ps.setInt(2, request.getInt("semester_id"));

        ps.setInt(3, request.getInt("course_id"));

        ps.execute();

        JSONObject result = new JSONObject();
        ResultSet res = ps.getGeneratedKeys();
        res.next();

        result.put("id", res.getInt(1));

        return result;
    }
}
/*
date
semester_id
course_id

CREATE TABLE IF NOT EXISTS `ums`.`exams` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  //`date` DATETIME NOT NULL,
  //`semesters_id` INT(11) NOT NULL,
  `teachers_courses_id` INT(11) NOT NULL,
  `groups_id` INT(11) NOT NULL,

 */