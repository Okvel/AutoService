package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.FeedbackDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Feedback;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Рылеев on 24.04.2016.
 */
public class MySqlFeedbackDao implements FeedbackDao {
    private static final String SQL_SELECT_ALL = "SELECT id, client_id, text FROM client_feedback";
    private static final String SQL_INSERT = "INSERT INTO client_feedback(last_name, first_name, text) VALUES(?,?,?)";

    private static final String COLUMN_NAME_FEEDBACK_ID = "id";
    private static final String COLUMN_NAME_TEXT = "text";
    private static final String COLUMN_NAME_LAST_NAME = "last_name";
    private static final String COLUMN_NAME_FIRST_NAME = "first_name";
    private static MySqlFeedbackDao instance = new MySqlFeedbackDao();

    private MySqlFeedbackDao(){}

    public static MySqlFeedbackDao getInstance() {
        return instance;
    }

    @Override
    public Feedback findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<Feedback> findAll() throws DaoException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()
                ){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                feedbacks.add(fillFeedback(resultSet));
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return feedbacks;
    }

    @Override
    public Long save(Feedback entity) throws DaoException {
        Long id = null;
        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                        PreparedStatement.RETURN_GENERATED_KEYS)
                ){
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getText());
            if (statement.executeUpdate() == 1){
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                id = resultSet.getLong(1);
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return id;
    }

    private Feedback fillFeedback(ResultSet resultSet) throws SQLException, DaoException{
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getLong(COLUMN_NAME_FEEDBACK_ID));
        feedback.setText(resultSet.getString(COLUMN_NAME_TEXT));
        feedback.setLastName(resultSet.getString(COLUMN_NAME_LAST_NAME));
        feedback.setFirstName(resultSet.getString(COLUMN_NAME_FIRST_NAME));
        return feedback;
    }
}
