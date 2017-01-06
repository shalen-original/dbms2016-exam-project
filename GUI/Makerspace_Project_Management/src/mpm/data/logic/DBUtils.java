/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mpm.main.ProjectConstants;

/**
 * Contains various database utilities.
 * @author Matteo Nardini
 */
public class DBUtils {
    
    /**
     * Allows to perform a generic operation on the database.
     * This methods creates a connection for the requested operation and closes it
     * after such operation completed. This method also catches any SQLException, in
     * order to avoid filling the code with try/catches.
     * @param operation 
     */
    public static void performOperation(IDatabaseOperation operation)
    {
        try (Connection conn = DriverManager.getConnection(ProjectConstants.DB_URL,
                ProjectConstants.DB_USERNAME,
                ProjectConstants.DB_PASSWORD)) 
        {
            
            operation.accept(conn);
            
        }catch (SQLException ex){
            //TODO
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Performs a generic UID (Update, Insert or Delete) operation on the database.
     * @param sql The string containing the sql of the query
     * @param filler The functions that sets all the parameters of the prepared statement
     */
    public static void performUID(String sql, IPreparedStatementFiller filler)
    {
        performOperation(conn -> {
            PreparedStatement s = conn.prepareStatement(sql);
            filler.accept(s);
            s.executeUpdate();    
        });
    }
    
    public static <T> List<T> performSelect(String sql, IPreparedStatementFiller filler,
                                            ISQLResultParser<T> parser)
    {
        
        List<T> ans = new ArrayList<>();
        
        DBUtils.performOperation(conn -> {
            PreparedStatement s = conn.prepareStatement(sql);
            filler.accept(s);
            ResultSet r = s.executeQuery();
            
            while (r.next())
            {
                ans.add(parser.accept(r));
            }
        });
        
        return ans;
    }

}