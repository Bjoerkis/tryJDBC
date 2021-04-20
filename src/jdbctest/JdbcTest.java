package jdbctest;

import java.sql.*;
import java.util.Scanner;


public class JdbcTest {

    static Connection connection;
    static Statement statement;
    static Scanner input = new Scanner(System.in);
    static boolean loop = true;
    static String url = "jdbc:mysql://localhost:3306/stuff";
    static String username = "root";
    static String password = "root";

    public static void main(String[] args) throws SQLException {

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        while (loop) {
            menu();
        }

    }

    private static void menu() throws SQLException {

        JdbcTest jdbcTest = new JdbcTest();

        System.out.println("1. Create new Student");
        System.out.println("2. Update a Students name");
        System.out.println("3. Update a Student age");
        System.out.println("4. Delete a Student");
        System.out.println("5. Show Student information");
        System.out.println("6. Show all Students");
        System.out.println("0. Exit Application");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {

            case 0:
                loop = false;
                break;

            case 1:
                jdbcTest.createStudent();
                break;

            case 2:
                jdbcTest.updateName();
                break;

            case 3:
                jdbcTest.updateAge();
                break;

            case 4:
                jdbcTest.deleteStudent();
                break;

            case 5:
                jdbcTest.showStudent();
                break;

            case 6:
                jdbcTest.showAll();
                break;

            default:
                System.out.println("Invalid Input");

        }

    }



    public void createStudent() throws SQLException {

        PreparedStatement createStudent = connection.prepareStatement("INSERT into registration(name, age, socialSecNr) values(?,?,?)");

        System.out.println("Input name: ");
        String name = input.nextLine();

        System.out.println("Input age: ");
        int age = input.nextInt();
        input.nextLine();

        System.out.println("Input Social Security number: ");
        var socNr = input.nextInt();
        input.nextLine();

        createStudent.setString(1, name);
        createStudent.setInt(2, age);
        createStudent.setInt(3,socNr);

        createStudent.executeUpdate();


    }

    private void showAll() throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT * from registration");
        while (rs.next()) {

            System.out.println("ID: " + rs.getInt("ID")
                    + "\nName: " + rs.getString("Name")
                    + "\nAge: " + rs.getInt("Age")
                    + "\nSocial Security Number: " + rs.getString("SocialSecNr") + "\n");

        }

    }

    private void updateName() throws SQLException {

        PreparedStatement updateName = connection.prepareStatement("UPDATE registration set name=? where id=?");

        System.out.println("Enter the ID of the student you'd like to update: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("New name: ");
        String name = input.nextLine();

        updateName.setInt(2, id);
        updateName.setString(2, name);

        updateName.executeUpdate();

    }

    private void updateAge() throws SQLException {

        PreparedStatement updateSalary = connection.prepareStatement("update registration set age=?");

        System.out.println("Enter the ID of the student you'd like to update: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Enter new value for AGE: ");
        int age = input.nextInt();
        input.nextLine();

        updateSalary.setInt(id, age);
        updateSalary.executeUpdate();

    }

    private void deleteStudent() throws SQLException {

        PreparedStatement deleteStatement = connection.prepareStatement("delete from registration where id=?");

        System.out.println("Enter the ID of the students records you'd like to remove: ");
        int id = input.nextInt();
        input.nextLine();

        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();

    }

    private void showStudent() throws SQLException {

        PreparedStatement showStudent = connection.prepareStatement("select * from registration where id=?");

        System.out.println("Enter the ID of the student you'd like to display additional information about: ");
        int id = input.nextInt();
        input.nextLine();

        showStudent.setInt(1, id);

        ResultSet rs = showStudent.executeQuery();

        while (rs.next()) {

            System.out.println("ID: " + rs.getInt("ID")
                    + "\nName: " + rs.getString("Name")
                    + "\nAge: " + rs.getInt("Age")
                    + "\nSocial security number: " + rs.getString("SocialSecNr")+"\n");
        }

    }

}




