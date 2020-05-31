package com.salon.dao.impl;


import com.salon.dao.CrudDao;
import com.salon.dao.DataSource;
import com.salon.exception.SqlQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrudDaoImpl.class);

    protected static final BiConsumer<PreparedStatement, Integer> SET_STATEMENT_INT_PARAM = ((preparedStatement, integer) -> {
        try {
            preparedStatement.setInt(1, integer);
        } catch (SQLException e) {
            LOGGER.error("Setting integer for preparedStatement went wrong in static BiConsumer");
        }
    });

    protected static final BiConsumer<PreparedStatement, Integer> SET_STATEMENT_PARAM_INT_TWO = ((preparedStatement, integer) ->
    {
        try {
            preparedStatement.setInt(2, integer);
        } catch (SQLException e) {
            LOGGER.error("Setting int for preparedStatement went wrong in static BiConsumer");
        }
    });

    protected static BiConsumer<PreparedStatement, String> SET_STATEMENT_STRING_PARAM = (((preparedStatement, str) ->
    {
        try {
            preparedStatement.setString(1, str);
        } catch (SQLException e) {
            LOGGER.error("Setting string for preparedStatement went wrong in static BiConsumer");
        }
    }));

    public void update(E entity, String query) {

        try (PreparedStatement st = DataSource.getConnection().prepareStatement(query)) {
            setStatementParamsWithId(st, entity);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(String.format("updating went wrong .Query - %s", query));
        }
    }

    public void save(E entity, String query) {

        try (PreparedStatement st = DataSource.getConnection().prepareStatement(query)) {
            setStatementParams(st, entity);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(String.format("adding went wrong .Query - %s", query));
            throw new SqlQueryException(query);
        }
    }

    protected <P> List<E> findListByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {

        List<E> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(findByParam)) {
            designatedParamSetter.accept(preparedStatement, param);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(mapResultSetToEntity(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("searching List by param %s in query %s went wrong", param, findByParam));
        }
        return Collections.emptyList();
    }

    protected List<Integer> findIdsByParam(Integer id, String query, String columnName) {

        List<Integer> userIdList = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(query)) {
            SET_STATEMENT_INT_PARAM.accept(preparedStatement, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userIdList.add(resultSet.getInt(columnName));
                }
                return userIdList;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Searching list of user_id by param [%s] went wrong. Query : [%s]",id,query));

        }
        return Collections.emptyList();
    }

    protected <P> Optional<E> findByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {

        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(findByParam)) {
            designatedParamSetter.accept(preparedStatement, param);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.ofNullable(mapResultSetToEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Search by param [%s] went wrong. Query : [%s]",param,findByParam));
            throw new SqlQueryException("Search by param " + param + " went wrong. Query :" + findByParam);
        }
        return Optional.empty();
    }

    public List<E> findAll(int page, int itemsPerPage, String query) {

        List<E> pageItems = new ArrayList<>();
        try (PreparedStatement st = DataSource.getConnection().prepareStatement(query)) {
            st.setInt(1, itemsPerPage);
            st.setInt(2, (page - 1) * itemsPerPage);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    pageItems.add(mapResultSetToEntity(resultSet));
                }
                return pageItems;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Exception when finding page number [%o] with [%o] items per page", page, itemsPerPage));
            throw new SqlQueryException(String.format("Exception when finding page number [%o] with [%o] items per page", page, itemsPerPage));
        }
    }

    public List<E> findAll(String query){
        List<E> items = new ArrayList<>();
        try (Statement st = DataSource.getConnection().createStatement()) {
            try (ResultSet resultSet = st.executeQuery(query)) {
                while (resultSet.next()) {
                    items.add(mapResultSetToEntity(resultSet));
                }
                return items;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception when extracting all rows of table");
            throw new SqlQueryException("Exception when extracting all rows of table");
        }
    }


    public <P> List<E> getListById(P param, String query, BiConsumer<PreparedStatement, P> designatedParamSetter) { //need to be tested

        List<E> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(query)) {

            designatedParamSetter.accept(preparedStatement, param);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    result.add(mapResultSetToEntity(resultSet));
                }
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Query %s with param [%s] has thrown SqlException", query, param));

            throw new SqlQueryException(String.format("Query %s with param [%s] has thrown SqlException", query, param));
        }
    }

    public int count(String query) {
        int count = 0;
        try (Statement st = DataSource.getConnection().createStatement()) {
            st.execute(query);

            try (ResultSet set = st.getResultSet()) {

                if (set.next()) {
                    count = set.getInt("total");
                }

                return count;
            }
        } catch (SQLException e) {
            LOGGER.error("error when selecting count");
        }

        return 0;
    }

    protected abstract void setStatementParams(PreparedStatement statement, E entity) throws SQLException;

    protected abstract void setStatementParamsWithId(PreparedStatement statement, E entity) throws SQLException;

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
