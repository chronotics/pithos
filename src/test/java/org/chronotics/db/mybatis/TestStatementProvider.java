package org.chronotics.db.mybatis;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
public class TestStatementProvider {
    public ExpectedException exceptions = ExpectedException.none();

    @Resource(name = "mapperSimpleMySqlN")
    private MapperMySqlN mapper;

    // table name
    public static String TABLE1 = "table1";
    public static String TABLE2 = "table2";
    // public static variable matches with COLUMN in DB
    public static String CID = "c0";
    public static String CSTR1 = "c1";
    public static String CSTR2 = "c2";
    public static String CNUMBER = "c3";
    public static String CVARBINARY = "c4";
    public static String CBLOB = "c5";
    public static String CCLOB = "c6";
    public static String CDATE = "c7";
    public static String CTIME = "c8";
    public static String CTIMESTAMP = "c9";

    // for Table1
    public static List<Map<String,Object>> itemSet1 =
            new ArrayList<Map<String,Object>>();
    public static int itemCount = 100;

    // for Table2
    public static List<Map<String,Object>> itemSet2 =
            new ArrayList<Map<String,Object>>();

    private void createTables() {
//        dropTables();
//        {
//            String statement=
//                    "CREATE TABLE "+ TABLE1 +" (" +
//                            "	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," +
//                            "	c1 VARCHAR(255) NULL," +
//                            "	c2 VARCHAR(255) NULL," +
//                            "	c3 FLOAT NULL default '0'," +
//                            "	c4 VARBINARY(255) NULL," +
//                            "	c5 BLOB NULL," +
//                            "	c6 TEXT NULL," +
//                            "	c7 DATE NULL," +
//                            "	c8 TIME NULL," +
//                            "	c9 TIMESTAMP(6) NULL," +
//                            "	PRIMARY KEY (c0)" +
//                            ");";
//            Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//            sqlStatement.put(KEYWORD.STATEMENT,statement);
//            mapper.doStatement(sqlStatement);
//        }
//        {
//            String statement=
//                    "CREATE TABLE "+ TABLE2 +" (" +
//                            "	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," +
//                            "	c1 VARCHAR(255) NULL," +
//                            "	c2 VARCHAR(255) NULL," +
//                            "	c3 FLOAT NULL default '0'," +
//                            "	c4 VARBINARY(255) NULL," +
//                            "	c5 BLOB NULL," +
//                            "	c6 TEXT NULL," +
//                            "	c7 DATE NULL," +
//                            "	c8 TIME NULL," +
//                            "	c9 TIMESTAMP(6) NULL," +
//                            "	PRIMARY KEY (c0)" +
//                            ");";
//            Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//            sqlStatement.put(KEYWORD.STATEMENT,statement);
//            mapper.doStatement(sqlStatement);
//        }
    }

    private void dropTables() {
//        {
//            String statement=
//                    "DROP TABLE IF EXISTS " + TABLE1;
//            Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//            sqlStatement.put(KEYWORD.STATEMENT,statement);
//            mapper.doStatement(sqlStatement);
//        }
//        {
//            String statement=
//                    "DROP TABLE IF EXISTS " + TABLE2;
//            Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//            sqlStatement.put(KEYWORD.STATEMENT,statement);
//            mapper.doStatement(sqlStatement);
//        }
    }

//    private int insertMultipleItems(String _tableName) throws Exception {
//        List<Map<String,Object>> itemSet;
//        if(_tableName == TABLE1) {
//            itemSet = itemSet1;
//        } else if (_tableName == TABLE2) {
//            itemSet = itemSet2;
//        } else {
//            assert(false);
//            return 0;
//        }
//
//        List<Object> colNames = new ArrayList<Object>();
//        colNames.add(CSTR1);
//        colNames.add(CSTR2);
//        colNames.add(CNUMBER);
//        // binary
//        // blob
//        // clob
//        colNames.add(CDATE);
//        colNames.add(CTIME);
//        colNames.add(CTIMESTAMP);
//
//        List<List<Object>> records = new ArrayList<List<Object>>();
//        for(Map<String,Object> entry : itemSet) {
//            List<Object> variables = new ArrayList<Object>();
//
//            String str1 = (String) entry.get(CSTR1);
//            String str2 = (String) entry.get(CSTR2);
//            Object number = entry.get(CNUMBER);
//            // binary
//            // blob
//            // clob
//            Object date = entry.get(CDATE);
//            Object time = entry.get(CTIME);
//            Object timestamp = entry.get(CTIMESTAMP);
//
//            variables.add(str1);
//            variables.add(str2);
//            variables.add(number);
//            // binary
//            // blob
//            // clob
//            variables.add(date);
//            variables.add(time);
//            variables.add(timestamp);
//
//            records.add(variables);
//        }
//
//        Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//
//        List<Object> insert = new ArrayList<Object>();
//        insert.add(_tableName);
//
//        sqlStatement.put(COMMAND.INSERT, insert);
//        sqlStatement.put(KEYWORD.COLNAMES, colNames);
//        sqlStatement.put(KEYWORD.RECORDS, records);
//
//        return mapper.insertMultipleItems(sqlStatement);
//    }
//
//    private int insertItemsOneByOne(String _tableName) throws Exception {
//        List<Map<String,Object>> itemSet;
//        if(_tableName == TABLE1) {
//            itemSet = itemSet1;
//        } else if (_tableName == TABLE2) {
//            itemSet = itemSet2;
//        } else {
//            return 0;
//        }
//
//        int totalInsertion = 0;
//        // insert
//        for(Map<String,Object> entry : itemSet) {
//            String str1 = (String) entry.get(CSTR1);
//            String str2 = (String) entry.get(CSTR2);
//            Object number = entry.get(CNUMBER);
//            // binary
//            // blob
//            // clob
//            Object date = entry.get(CDATE);
//            Object time = entry.get(CTIME);
//            Object timestamp = entry.get(CTIMESTAMP);
//
//            // old method
//            Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//
//            List<Object> insert = new ArrayList<Object>();
//            insert.add(_tableName);
//
//            List<Object> colNames = new ArrayList<Object>();
//            colNames.add(CSTR1);
//            colNames.add(CSTR2);
//            colNames.add(CNUMBER);
//            // binary
//            // blob
//            // clob
//            colNames.add(CDATE);
//            colNames.add(CTIME);
//            colNames.add(CTIMESTAMP);
//
//            List<Object> colValues = new ArrayList<Object>();
//            colValues.add(str1);
//            colValues.add(str2);
//            colValues.add(number);
//            // binary
//            // blob
//            // clob
//            colValues.add(date);
//            colValues.add(time);
//            colValues.add(timestamp);
//
//            sqlStatement.put(COMMAND.INSERT, insert);
//            sqlStatement.put(KEYWORD.COLNAMES, colNames);
//            sqlStatement.put(KEYWORD.COLVALUES, colValues);
//
//            int count = mapper.insert(sqlStatement);
//            totalInsertion += count;
//        }
//
//        return totalInsertion;
//    }
//
//    private int deleteLikeName(String _tableName) throws Exception {
//        Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
//
//        List<Object> delete = new ArrayList<Object>();
//        delete.add(_tableName);
//
//        List<Object> where = new ArrayList<Object>();
//        where.add(CSTR1);
//        where.add(OPERATOR.LIKE);
//        where.add(SqlStatement.toVV("%"));
//
//        Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
//        whereCondition.put(COMMAND.WHERE, where);
//
//        sqlStatement.put(COMMAND.DELETE, delete);
//        sqlStatement.put(COMMAND.WHERECONDITION, whereCondition);
//
//        int result = mapper.delete(sqlStatement);
//
//        return result;
//    }

    @BeforeClass
    public static void setup() {
        for(int i=0; i<itemCount; i++) {
            Map<String,Object> item =
                    new LinkedHashMap<String,Object>();
            item.put(CSTR1, Integer.toString(i));
            item.put(CSTR2, Integer.toString(i));
            item.put(CNUMBER, (double)i);
            // BINARY
            // BLOB
            // CLOB
            item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
            item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
            item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
            itemSet1.add(item);
        }

        for(int i=0; i<itemCount; i++) {
            Map<String,Object> item =
                    new LinkedHashMap<String,Object>();
            item.put(CSTR1, Integer.toString(i));
            item.put(CSTR2, Integer.toString(i));
            item.put(CNUMBER, (double)i);
            // BINARY
            // BLOB
            // CLOB
            item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
            item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
            item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
            itemSet2.add(item);
        }
    }
//    @Test
//    public void testSingleInsert() throws Exception {
////        createTables();
////
//////        int resultCount = 0;
//////        resultCount = insertItemsOneByOne(TABLE1);
//////        assertEquals(itemCount, resultCount);
//////
//////        resultCount = insertItemsOneByOne(TABLE2);
//////        assertEquals(itemCount, resultCount);
//////
//////        this.deleteLikeName(TABLE1);
//////        this.deleteLikeName(TABLE2);
////
////        dropTables();
//    }

    @Test
    public void testCustomSelect() throws Exception {
//        createTables();

//        int resultCount = 0;
//        resultCount = insertItemsOneByOne(TABLE1);
//        assertEquals(itemCount, resultCount);
//
////        resultCount = insertItemsOneByOne(TABLE2);
////        assertEquals(itemCount, resultCount);
//
////        String statement = "SELECT * FROM table1.user";
////        Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
////        sqlStatement.put(KEYWORD.STATEMENT,statement);
////
////        mapper.doStatement(sqlStatement);

        Integer i = new Integer(1);
        StatementProvider provider = new StatementProvider
                .Builder()
                .select(i)
                .from("user")
                .build();
        Map<Object,Object> statementMap = provider.getStatementMap();

        List<Map<String,Object>> result = mapper.selectList(statementMap);
        System.out.println(result);


        dropTables();
    }

}
