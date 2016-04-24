package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.FeedbackDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Feedback;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Рылеев on 24.04.2016.
 */
public class MySqlFeedbackDao implements FeedbackDao {
    private static final String SQL_SELECT_ALL = "SELECT id, client_id, text FROM client_feedback";

    private static final String COLUMN_NAME_FEEDBACK_ID = "id";
    private static final String COLUMN_NAME_TEXT = "text";
    private static final String COLUMN_NAME_USER_ID = "client_id";
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
        return null;
    }

    private Feedback fillFeedback(ResultSet resultSet) throws SQLException, DaoException{
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getLong(COLUMN_NAME_FEEDBACK_ID));
        feedback.setText(resultSet.getString(COLUMN_NAME_TEXT));
        MySqlUserDao userDao = MySqlUserDao.getInstance();
        feedback.setUser(userDao.findById(resultSet.getLong(COLUMN_NAME_USER_ID)));
        return feedback;
    }
}
