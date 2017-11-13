package com.course.ums.ws.group;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GroupStudentRemove extends JSONRoute{
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT id FROM group_students WHERE groups_id = ? AND students_id = ? AND semesters_id = ?");
        ps.setInt(1, request.getInt("group_id"));
        ps.setInt(2, request.getInt("students_id"));
        ps.setInt(2, request.getInt("semesters_id"));

        ResultSet rs = ps.executeQuery();
        rs.next();
        int id = rs.getInt(1);


        ps = DBManager.getConnection().prepareStatement("DELETE * FROM group_students WHERE groups_id = ? AND students_id = ? AND semesters_id = ?");
        ps.setInt(1, request.getInt("group_id"));
        ps.setInt(2, request.getInt("students_id"));
        ps.setInt(2, request.getInt("semesters_id"));
        ps.execute();

        JSONObject result = new JSONObject();

        result.put("id", id);

        return result;
    }
}
