package ro.teamnet.zth.dao;

import ro.teamnet.zth.domain.Employee;
import ro.teamnet.zth.domain.Job;
import ro.teamnet.zth.utils.ResultSetToPojoConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hangan on 11/4/2014.
 */
public class EmployeeDao {
    public ArrayList<Employee> getAllEmployees(Connection con) {
        String selectAllFromTableString = "SELECT employee_id,first_name,last_name,email,phone_number,hire_date,salary,comission_pct,manager_id,department_id,job_id FROM employees";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(selectAllFromTableString);
            ResultSet rs = stmt.executeQuery();
            return ResultSetToPojoConverter.covertToEmployee(rs, con);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<Employee>();

    }

    public Employee getEmployeeByID(Connection con, Long id) {
        String selectAllFromTableString = "SELECT employee_id,first_name,last_name,email,phone_number,hire_date,salary,comission_pct,manager_id,department_id,job_id FROM Employees WHERE employee_id = ?";
        PreparedStatement stmt = null;
        ArrayList<Employee> employees = null;
        try {
            stmt = con.prepareStatement(selectAllFromTableString);
            stmt.setLong(1,id);
            stmt.setMaxRows(5);
            ResultSet rs = stmt.executeQuery();
            employees = ResultSetToPojoConverter.covertToEmployee(rs, con);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees.size() > 0 ? employees.get(0) : null;
    }

    public void saveEmployee(Employee employee, Connection con) {
        HashMap<String, String> insertIntoTableEmployees = new HashMap<String, String> ();
        String tableName = "employess";

        insertIntoTableEmployees.put("employee_id", employee.getId().toString());
        insertIntoTableEmployees.put("first_name", employee.getFirstName());
        insertIntoTableEmployees.put("last_name", employee.getLastName());
        insertIntoTableEmployees.put("email", employee.getEmail());
        insertIntoTableEmployees.put("phone_number", employee.getPhoneNumber());
        insertIntoTableEmployees.put("hire_date", "TO_DATE('" + employee.getHireDate().toString() + "','yyyy-mm-dd')");
        insertIntoTableEmployees.put("job_id",employee.getJob().getId().toString());
        insertIntoTableEmployees.put("salary",employee.getSalary().toString());
        insertIntoTableEmployees.put("comission_pct",employee.getCommissionPoints().toString());
        insertIntoTableEmployees.put("manager_id",employee.getManager().toString());

        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("");
            String createTableString = "INSERT INTO " + tableName + " ( ";
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append(createTableString);
            Integer valuesCount = insertIntoTableEmployees.keySet().size();
            for (String valueName : insertIntoTableEmployees.keySet()) {
                valuesCount--;
                String columnString = valueName + (valuesCount != 0 ? " , " : ")");
                sqlStatement.append(columnString);
            }
            valuesCount = insertIntoTableEmployees.keySet().size();
            sqlStatement.append(" VALUES ( '");
            for (String valueName : insertIntoTableEmployees.keySet()) {
                valuesCount--;

                String columnString;
                if (valueName.equals("hire_date")) {
                    columnString = insertIntoTableEmployees.get(valueName) + (valuesCount != 0 ? " , '" : "')");
                }else if (valueName.equals("phone_number")) {
                    columnString = insertIntoTableEmployees.get(valueName) + (valuesCount != 0 ? "' , " : "')");
                }else
                {
                    columnString = insertIntoTableEmployees.get(valueName) + (valuesCount != 0 ? "' , '" : "')");
                }
                sqlStatement.append(columnString);
            }
            System.out.println(sqlStatement);
            stmt = con.prepareStatement(sqlStatement.toString());
            stmt.executeUpdate();
            System.out.println("Inserted into table " + tableName + "...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public void deleteEmployee(Employee employee, Connection con){
        PreparedStatement stmt = null;
        String tableName = "employees";
        try {
            String deleteTableString ="DELETE FROM"+tableName+"WHERE employee_id=?";
            stmt=con.prepareStatement(deleteTableString);
            stmt.setLong(1,employee.getId());
            stmt.executeUpdate();
            System.out.println("Delete employee from table" + tableName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateEmployee(Employee employee, Connection con) {
        HashMap<String, String> insertIntoTableEmployees = new HashMap<String, String>();
        insertIntoTableEmployees.put("employee_id", employee.getId().toString());
        insertIntoTableEmployees.put("first_name", employee.getFirstName());
        insertIntoTableEmployees.put("last_name", employee.getLastName());
        insertIntoTableEmployees.put("email", employee.getEmail());
        insertIntoTableEmployees.put("phone_number", employee.getPhoneNumber());
        insertIntoTableEmployees.put("hire_date", "TO_DATE('" + "1999-12-06" + "','yyyy-mm-dd')");
        insertIntoTableEmployees.put("job_id", employee.getJob().getId().toString());
        insertIntoTableEmployees.put("salary", employee.getSalary().toString());
        insertIntoTableEmployees.put("comission_pct", employee.getCommissionPoints().toString());
        insertIntoTableEmployees.put("manager_id", employee.getManager().getId().toString());
        insertIntoTableEmployees.put("department_id", employee.getDepartment().getId().toString());
        String tableName = "employees";

        PreparedStatement stmt = null;
        try {
            String createTableString = "UPDATE " + tableName + " SET ";
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append(createTableString);
            Integer columnsCount = insertIntoTableEmployees.keySet().size();
            for (String columnName : insertIntoTableEmployees.keySet()) {
                columnsCount--;
                String columnString;
                if (columnName.equals("hire_date")) {
                    columnString = columnName + " = " + insertIntoTableEmployees.get(columnName) + (columnsCount != 0 ? " , " : "' ");
                } else {
                    columnString = columnName + " = '" + insertIntoTableEmployees.get(columnName) + (columnsCount != 0 ? "' , " : "' ");
                }
                sqlStatement.append(columnString);
            }
            sqlStatement.append("WHERE employee_id = ?");
            stmt = con.prepareStatement(sqlStatement.toString());
            stmt.setLong(1, employee.getId());
            stmt.executeUpdate();
            System.out.println("Updated employee in table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
