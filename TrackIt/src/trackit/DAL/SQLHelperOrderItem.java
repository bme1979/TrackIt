package trackit.DAL;

import java.sql.*;
import java.util.*;

/**
 * DAL Layer: Converts a row in database table OrderItems into an AnOrderItem
 * object and vice versa.
 *
 * @author Bond
 */
public class SQLHelperOrderItem
        extends SQLHelper<AnOrderItem> {

    //SQLHelperItem itemHelper = new SQLHelperItem();
    // <editor-fold defaultstate="collapsed" desc="Database Columns">
    /**
     *
     */
    public static final String COLUMN_ORDERID = "orderId";
    //public static final String COLUMN_ITEMID = "itemId";

    /**
     *
     */
    public final String COLUMN_QUANTITYORDERED = "quantityOrdered";

  /**
  *
  */
   public static final String COLUMN_QUANTITYCHECKEDIN = "quantityCheckedIn";
    /**
     *
     */
    public static final String COLUMN_PRICE = "price";

    /**
     *
     */
    public static final String COLUMN_EXTENDEDPRICE = "extendedPrice";

    // </editor-fold>  // <editor-fold defaultstate="collapsed" desc="Constructors">
<<<<<<< HEAD
    static {
=======

    /**
     *order item for dbase
     */
    public SQLHelperOrderItem() {
>>>>>>> c325af60196bf4417f5a1e7e174602122f1e22a0
        COLUMN_PK = "orderItemId";
    }

    public SQLHelperOrderItem() {

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    @Override
    protected AnOrderItem convertResultSetToObject(ResultSet rs)
            throws SQLException {
        AnOrderItem anObj = new AnOrderItem();
        anObj.setPrimaryKey(rs.getInt(COLUMN_PK));
        anObj.setOrderId(rs.getInt(COLUMN_ORDERID));
        //TODO
        //anObj.setItemId(rs.getInt(itemHelper.COLUMN_PK));
        anObj.setQuantityOrdered(rs.getInt(COLUMN_QUANTITYORDERED));
        anObj.setQuantityCheckedIn(rs.getInt(COLUMN_QUANTITYCHECKEDIN));
        anObj.setPrice(rs.getDouble(COLUMN_PRICE));
        return anObj;
    }

    @Override
    protected ArrayList<AnOrderItem> execSproc(String sprocName, HashMap<Integer, SprocParameter> parameters)
            throws SQLException, Exception {
        ArrayList<AnOrderItem> results = new ArrayList<>();

        String sql = buildSprocSyntax(sprocName, parameters.size());
        System.out.println("execSproc's sql = " + sql);

        try (Connection myConn = sqlConn.getConnection();
                CallableStatement stmt = myConn.prepareCall(sql)) {

            final ArrayList<Integer> outParams = setParameters(stmt, parameters);

            //Get the result set, if any, and add to results (returned variable).
            if (stmt.execute()) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    results.add(convertResultSetToObject(rs));
                }
            }

            //Retrieve OUT parameters.
            for (int aParamIndex : outParams) {
                SprocParameter aParam = parameters.get(aParamIndex);
                aParam.setValue(getOutParameterValue(stmt, aParam));
            }
        }

        return results;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public ArrayList<AnOrderItem> selectAll()
            throws SQLException, Exception {
        HashMap<Integer, SprocParameter> params = new HashMap<>();

        ArrayList<AnOrderItem> results = execSproc("sp_OrderItems_SelectAll", params);
        return results;
    }

    @Override
    public AnOrderItem selectOne(Integer primaryKey)
            throws SQLException, Exception {
        HashMap<Integer, SprocParameter> params = new HashMap<>();
        params.put(0, new SprocParameterInteger(COLUMN_PK, primaryKey.toString(), ParameterDirection.IN));

        ArrayList<AnOrderItem> results = execSproc("sp_OrderItems_Select", params);
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public Integer insert(AnOrderItem anObject)
            throws SQLException, Exception {
        HashMap<Integer, SprocParameter> params = new HashMap<>();
        SprocParameterInteger outParam = new SprocParameterInteger(COLUMN_PK, anObject.getPrimaryKey().toString(), ParameterDirection.OUT);
        params.put(0, outParam);
        //TODO:
        params.put(1, new SprocParameterInteger(COLUMN_ORDERID, anObject.getOrderId().toString(), ParameterDirection.IN));
        //params.put(2, new SprocParameterInteger(itemHelper.COLUMN_PK, anObject.getItemId().toString(), ParameterDirection.IN));
        params.put(3, new SprocParameterInteger(COLUMN_QUANTITYORDERED, anObject.getQuantityOrdered().toString(), ParameterDirection.IN));
        params.put(4, new SprocParameterDouble(COLUMN_PRICE, anObject.getPrice().toString(), ParameterDirection.IN));

        execSproc("sp_OrderItems_Insert", params);
        Integer primaryKey = Integer.parseInt(outParam.getValue());
        anObject.setPrimaryKey(primaryKey);
        return primaryKey;
    }

    @Override
    public void update(AnOrderItem anObject)
            throws SQLException, Exception {
        HashMap<Integer, SprocParameter> params = new HashMap<>();
        params.put(0, new SprocParameterInteger(COLUMN_PK, anObject.getPrimaryKey().toString(), ParameterDirection.IN));
        //TODO:
        //params.put(1, new SprocParameterVarchar("nickname", anObject.getNickname(), ParameterDirection.IN));
        //params.put(2, new SprocParameterVarchar("url", anObject.getUrl(), ParameterDirection.IN));
        params.put(3, new SprocParameterInteger(COLUMN_QUANTITYORDERED, anObject.getQuantityOrdered().toString(), ParameterDirection.IN));
        params.put(4, new SprocParameterDouble(COLUMN_PRICE, anObject.getPrice().toString(), ParameterDirection.IN));

        execSproc("sp_OrderItems_Update", params);
    }

    @Override
    public void delete(Integer primaryKey)
            throws SQLException, Exception {
        HashMap<Integer, SprocParameter> params = new HashMap<>();
        params.put(0, new SprocParameterInteger(COLUMN_PK, primaryKey.toString(), ParameterDirection.IN));

        execSproc("sp_OrderItems_Delete", params);
    }

    @Override
    public java.sql.Date doNullCheck(String columnName, java.sql.Date aValue)
            throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double doNullCheck(String columnName, Double aValue)
            throws SQLException {
        if (aValue == null
                && (columnName.equalsIgnoreCase(COLUMN_PRICE)
                || columnName.equalsIgnoreCase(COLUMN_EXTENDEDPRICE))) {
            throw new NonNullableValueException();
        } else {
            return aValue;
        }
    }

    @Override
    public Integer doNullCheck(String columnName, Integer aValue)
            throws SQLException {
        if (aValue == null
                && (columnName.equalsIgnoreCase(COLUMN_PK)
                || columnName.equalsIgnoreCase(COLUMN_ORDERID)
                || columnName.equalsIgnoreCase(SQLHelperItem.COLUMN_PK)
                || columnName.equalsIgnoreCase(COLUMN_QUANTITYORDERED)
                || columnName.equalsIgnoreCase(COLUMN_QUANTITYCHECKEDIN))) {
            throw new NonNullableValueException();
        } else {
            return aValue;
        }
    }

    @Override
    public String doNullCheck(String columnName, String aValue)
            throws SQLException {
        if (aValue == null
                && (columnName.equalsIgnoreCase(SQLHelperItem.COLUMN_DESCRIPTION)
                || columnName.equalsIgnoreCase(SQLHelperItem.COLUMN_SKU)
                || columnName.equalsIgnoreCase(SQLHelperItem.COLUMN_SIZEUNIT)
                || columnName.equalsIgnoreCase(SQLHelperItem.COLUMN_ITEMSTATUS))) {
            throw new NonNullableValueException();
        } else {
            return aValue;
        }
    }
    // </editor-fold>
}
