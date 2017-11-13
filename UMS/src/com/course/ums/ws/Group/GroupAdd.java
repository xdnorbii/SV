package com.course.ums.ws.group;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GroupAdd extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if(!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }


        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO groups(semesters_id) VALUES(?)");
        ps.setInt(1, request.getInt("semester_id"));
        ps.execute();

        JSONObject result = new JSONObject();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        result.put("id", rs.getInt(1));

        return result;
    }
}
