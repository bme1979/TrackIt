package trackit.DAL;

import java.sql.*;
import java.util.*;

/**
 * DAL Layer: Interface for all classes that deal with database access.
 *
 * @author Bond
 * @param <T> The object that needs to interact with the database.
 */
public abstract interface ISQLHelper<T> {

    /**
     * Gets all T from database.
     *
     * @return A list of T.
     * @throws SQLException
     */
    public abstract ArrayList<T> selectAll()
            throws SQLException, Exception;

    /**
     * Gets a single T from database.
     *
     * @param primaryKey The primary key of T.
     * @return T
     * @throws SQLException
     */
    public abstract T selectOne(Integer primaryKey)
            throws SQLException, Exception;

    /**
     * Inserts a list of T's into the database.
     *
     * @param aList A list of all the T's to be inserted.
     * @return A list of all the primary keys generated by the inserts.
     * @throws SQLException
     */
    public abstract List<Integer> insertAll(List<T> aList)
            throws SQLException, Exception;

    /**
     * Inserts a single T into the database.
     *
     * @param anObject The T to be inserted.
     * @return The primary key generated by the insert.
     * @throws SQLException
     */
    public abstract Integer insert(T anObject)
            throws SQLException, Exception;

    /**
     * Updates the database with values from a list of T's.
     *
     * @param aList A list of all the T's to be updated.
     * @throws SQLException
     */
    public abstract void updateAll(List<T> aList)
            throws SQLException, Exception;

    /**
     * Updates the database with values from a single T.
     *
     * @param anObject The T to be updated.
     * @throws SQLException
     */
    public abstract void update(T anObject)
            throws SQLException, Exception;

    /**
     * Deletes from the database all T's in the list.
     *
     * @param aList The list of T's to be deleted.
     * @throws SQLException
     */
    public abstract void deleteAll(List<Integer> aList)
            throws SQLException, Exception;

    /**
     * Deletes from the database a single T.
     *
     * @param primaryKey The primary key of the T to be deleted.
     * @throws SQLException
     */
    public abstract void delete(Integer primaryKey)
            throws SQLException, Exception;
}
