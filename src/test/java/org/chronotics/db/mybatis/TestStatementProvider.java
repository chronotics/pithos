package org.chronotics.db.mybatis;

import com.google.common.primitives.Ints;
import oracle.sql.BLOB;
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

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
public class TestStatementProvider {
    public ExpectedException exceptions = ExpectedException.none();

    @Resource(name = "mapperSimpleStatementProvider")
    private MapperStatementProvider mapper;

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
    public static List<Map<String, Object>> itemSet1 =
            new ArrayList<Map<String, Object>>();
    public static int itemCount = 100;

    // for Table2
    public static List<Map<String, Object>> itemSet2 =
            new ArrayList<Map<String, Object>>();

    private void createTables() {
        dropTables();
        {
            String statement =
                    "CREATE TABLE " + TABLE1 + " (" +
                            "	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," +
                            "	c1 VARCHAR(255) NULL," +
                            "	c2 VARCHAR(255) NULL," +
                            "	c3 FLOAT NULL default '0'," +
                            "	c4 VARBINARY(255) NULL," +
                            "	c5 BLOB NULL," +
                            "	c6 TEXT NULL," +
                            "	c7 DATE NULL," +
                            "	c8 TIME NULL," +
                            "	c9 TIMESTAMP(6) NULL," +
                            "	PRIMARY KEY (c0)" +
                            ");";
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(StatementProvider.BUILDTYPE.MYSQL);
            Map<Object, Object> statementMap = provider.getStatementMap();
            mapper.doStatement(statementMap);
        }
        {
            String statement =
                    "CREATE TABLE " + TABLE2 + " (" +
                            "	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," +
                            "	c1 VARCHAR(255) NULL," +
                            "	c2 VARCHAR(255) NULL," +
                            "	c3 FLOAT NULL default '0'," +
                            "	c4 VARBINARY(255) NULL," +
                            "	c5 BLOB NULL," +
                            "	c6 TEXT NULL," +
                            "	c7 DATE NULL," +
                            "	c8 TIME NULL," +
                            "	c9 TIMESTAMP(6) NULL," +
                            "	PRIMARY KEY (c0)" +
                            ");";
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(StatementProvider.BUILDTYPE.MYSQL);
            Map<Object, Object> statementMap = provider.getStatementMap();
            mapper.doStatement(statementMap);
        }
    }

    private void dropTables() {
        {
            String statement =
                    "DROP TABLE IF EXISTS " + TABLE1;
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(StatementProvider.BUILDTYPE.MYSQL);
            Map<Object, Object> statementMap = provider.getStatementMap();
            mapper.doStatement(statementMap);
        }
        {
            String statement =
                    "DROP TABLE IF EXISTS " + TABLE2;
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(StatementProvider.BUILDTYPE.MYSQL);
            Map<Object, Object> statementMap = provider.getStatementMap();
            mapper.doStatement(statementMap);
        }
    }

    @BeforeClass
    public static void setup() {
        for (int i = 0; i < itemCount; i++) {
            Map<String, Object> item =
                    new LinkedHashMap<String, Object>();
            item.put(CSTR1, Integer.toString(i));
            item.put(CSTR2, Integer.toString(i));
            item.put(CNUMBER, (double) i);
            // BINARY
            item.put(CVARBINARY, new Integer(i).byteValue());
            // BLOB
            item.put(CBLOB, Ints.toByteArray(i));
            // CLOB
            item.put(CCLOB, Integer.toBinaryString(i));
            item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
            item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
            item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
            itemSet1.add(item);
        }

        for (int i = 0; i < itemCount; i++) {
            Map<String, Object> item =
                    new LinkedHashMap<String, Object>();
            item.put(CSTR1, Integer.toString(i));
            item.put(CSTR2, Integer.toString(i));
            item.put(CNUMBER, (double) i);
            // BINARY
            item.put(CVARBINARY, new Integer(i).byteValue());
            // BLOB
            item.put(CBLOB, Ints.toByteArray(i));
            // CLOB
            item.put(CCLOB, Integer.toBinaryString(i));
            item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
            item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
            item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
            itemSet2.add(item);
        }
    }

    @Test
    public void testCustomStatement() throws Exception {
        createTables();

        dropTables();
    }

    private int insertItem(String _tableName) throws Exception {
        List<Map<String,Object>> itemSet;
        if(_tableName == TABLE1) {
            itemSet = itemSet1;
        } else if (_tableName == TABLE2) {
            itemSet = itemSet2;
        } else {
            assert(false);
            return 0;
        }

        int result = 0;
        List<Object> record = new ArrayList<>();
        for (Map<String, Object> entry : itemSet) {
            List<Object> values = new ArrayList<>();

            String str1 = (String) entry.get(CSTR1);
            String str2 = (String) entry.get(CSTR2);
            Object number = entry.get(CNUMBER);
            byte binary = (byte) entry.get(CVARBINARY);
            byte[] blob = (byte[]) entry.get(CBLOB);
            String clob = (String)entry.get(CCLOB);
            Object date = entry.get(CDATE);
            Object time = entry.get(CTIME);
            Object timestamp = entry.get(CTIMESTAMP);

            values.add(str1);
            values.add(str2);
            values.add(number);
//            values.add(binary);
//            values.add(blob);
//            values.add(clob);
            values.add(date);
            values.add(time);
            values.add(timestamp);

            StatementProvider provider = new StatementProvider
                    .Builder()
                    .insertInto(TABLE1)
                    .insertColumns(
                            "C1",
                            "C2",
                            "C3",
//                            "C4",
//                            "C5",
//                            "C6",
                            "C7",
                            "C8",
                            "C9"
                    )
                    .values(values)
                    .build(StatementProvider.BUILDTYPE.MYSQL);
            Map<Object, Object> statementMap = provider.getStatementMap();

            result += mapper.doStatement(statementMap);
        }

        return result;
    }

    @Test
    public void testInsert() throws Exception {
        createTables();
        int result = this.insertItem(TABLE1);
        assertTrue(result != 0);
        dropTables();
    }

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

//        SqlObject eqCondition = new SqlObjectOperator(SqlObjectOperator.EQ);
//        eqCondition.addChildObject("hbp",120);
        Integer i = new Integer(1);
        StatementProvider provider = new StatementProvider
                .Builder()
                .select("hbp", "lbp")
                .from("bp")
                .where(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .put("hbp", 120))
                .and(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .put("lbp", 90))
                .orderby("hbp", "lbp")
                .desc()
                .limit(1)
                .build(StatementProvider.BUILDTYPE.MYSQL);
        Map<Object, Object> statementMap = provider.getStatementMap();

        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertTrue(result.size() != 0);
        System.out.println(result);


        dropTables();
    }

}
