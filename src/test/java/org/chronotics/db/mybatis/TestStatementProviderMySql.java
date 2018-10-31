package org.chronotics.db.mybatis;

import com.google.common.primitives.Ints;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.security.util.Cache;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
public class TestStatementProviderMySql {
    public ExpectedException exceptions = ExpectedException.none();

    private static StatementProvider.BUILDTYPE buildType = StatementProvider.BUILDTYPE.MYSQL;
    @Resource(name = "mapperSimpleMySql")
    private Mapper mapper;

    // table name
    public static String TABLE1 = "TABLE1";
    public static String TABLE2 = "TABLE2";
    // public static variable matches with COLUMN in DB
    public static String CID = "C0";
    public static String CSTR1 = "C1";
    public static String CSTR2 = "C2";
    public static String CNUMBER = "C3";
    public static String CVARBINARY = "C4";
    public static String CBLOB = "C5";
    public static String CCLOB = "C6";
    public static String CDATE = "C7";
    public static String CTIME = "C8";
    public static String CTIMESTAMP = "C9";

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
                            "	C0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," +
                            "	C1 VARCHAR(255) NULL," +
                            "	C2 VARCHAR(255) NULL," +
                            "	C3 FLOAT NULL default '0'," +
                            "	C4 VARBINARY(255) NULL," +
                            "	C5 BLOB NULL," +
                            "	C6 TEXT NULL," +
                            "	C7 DATE NULL," +
                            "	C8 TIME NULL," +
                            "	C9 TIMESTAMP(6) NULL," +
                            "	PRIMARY KEY (C0)" +
                            ");";
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();
            mapper.doStatement(statementMap);
        }
        {
            String statement =
                    "CREATE TABLE " + TABLE2 + " (" +
                            "	X0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," +
                            "	C1 VARCHAR(255) NULL," +
                            "	X2 VARCHAR(255) NULL," +
                            "	X3 FLOAT NULL default '0'," +
                            "	X4 VARBINARY(255) NULL," +
                            "	X5 BLOB NULL," +
                            "	X6 TEXT NULL," +
                            "	X7 DATE NULL," +
                            "	X8 TIME NULL," +
                            "	X9 TIMESTAMP(6) NULL," +
                            "	PRIMARY KEY (X0)" +
                            ");";
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(buildType);
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
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();
            mapper.doStatement(statementMap);
        }
        {
            String statement =
                    "DROP TABLE IF EXISTS " + TABLE2;
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .doStatement(statement)
                    .build(buildType);
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
            if(i%2 == 1) {
                continue;
            }

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
    public void testCustomStatement() {
        createTables();

        dropTables();
    }

    private int insertItemOneByOne(String _tableName) {
        String prefix="";
        List<Map<String,Object>> itemSet;
        if(_tableName == TABLE1) {
            itemSet = itemSet1;
            prefix="C";
        } else if (_tableName == TABLE2) {
            itemSet = itemSet2;
            prefix="X";
        } else {
            assert(false);
            return 0;
        }

        int result = 0;
        for (Map<String, Object> entry : itemSet) {

            String str1 = (String) entry.get(CSTR1);
            String str2 = (String) entry.get(CSTR2);
            Object number = entry.get(CNUMBER);
            byte binary = (byte) entry.get(CVARBINARY);
            byte[] blob = (byte[]) entry.get(CBLOB);
            String clob = (String)entry.get(CCLOB);
            Object date = entry.get(CDATE);
            Object time = entry.get(CTIME);
            Object timestamp = entry.get(CTIMESTAMP);

            List<Object> columns = new ArrayList<>();
            if(_tableName != TABLE2) {
                columns.add(prefix + "1");
            } else {
                columns.add("C1");
            }
            columns.add(prefix+"2");
            columns.add(prefix+"3");
            columns.add(prefix+"4");
            columns.add(prefix+"5");
            columns.add(prefix+"6");
            columns.add(prefix+"7");
            columns.add(prefix+"8");
            columns.add(prefix+"9");

            List<Object> values = new ArrayList<>();
            values.add(str1);
            values.add(str2);
            values.add(number);
            values.add(binary);
            values.add(blob);
            values.add(clob);
            values.add(date);
            values.add(time);
            values.add(timestamp);

            StatementProvider provider = new StatementProvider
                    .Builder()
                    .insertInto(_tableName)
                    .values(columns, values)
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();

            result += mapper.insert(statementMap);
        }
        return result;
    }

    private int insertItemMulti(String _tableName) {
        String prefix="";
        List<Map<String,Object>> itemSet;
        if(_tableName == TABLE1) {
            itemSet = itemSet1;
            prefix="C";
        } else if (_tableName == TABLE2) {
            itemSet = itemSet2;
            prefix="X";
        } else {
            assert(false);
            return 0;
        }

        List<Object> columns = new ArrayList<>();
        if(_tableName != TABLE2) {
            columns.add(prefix + "1");
        } else {
            columns.add("C1");
        }
        columns.add(prefix+"2");
        columns.add(prefix+"3");
        columns.add(prefix+"4");
        columns.add(prefix+"5");
        columns.add(prefix+"6");
        columns.add(prefix+"7");
        columns.add(prefix+"8");
        columns.add(prefix+"9");

        List<List<Object>> valuesList = new ArrayList<>();

        for (Map<String, Object> entry : itemSet) {
            String str1 = (String) entry.get(CSTR1);
            String str2 = (String) entry.get(CSTR2);
            Object number = entry.get(CNUMBER);
            byte binary = (byte) entry.get(CVARBINARY);
            byte[] blob = (byte[]) entry.get(CBLOB);
            String clob = (String)entry.get(CCLOB);
            Object date = entry.get(CDATE);
            Object time = entry.get(CTIME);
            Object timestamp = entry.get(CTIMESTAMP);


            List<Object> values = new ArrayList<>();
            values.add(str1);
            values.add(str2);
            values.add(number);
            values.add(binary);
            values.add(blob);
            values.add(clob);
            values.add(date);
            values.add(time);
            values.add(timestamp);

            valuesList.add(values);
        }

        StatementProvider provider = new StatementProvider
                .Builder()
                .insertMulti(_tableName, columns, valuesList)
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();

       return mapper.insert(statementMap);
    }

    @Test
    public void testInsertOneByOne() {
        createTables();
        int result = this.insertItemOneByOne(TABLE1);
        assertEquals(itemCount, result);
        dropTables();
    }

    @Test
    public void testInsertMulti() {
        createTables();
        int result = this.insertItemMulti(TABLE1);
        assertEquals(itemCount, result);
        dropTables();
    }

    @Test
    public void testCustomSelect() {
        createTables();

        insertItemMulti(TABLE1);

        for(int i = 0; i < itemCount; i++) {
            String strI = String.valueOf(i);
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .select("*")
                    .from(TABLE1)
                    .where(new SqlObjectOperator(SqlObjectOperator.EQ)
                            .setLeftOperand("C2")
                            .addRightOperand(strI))
                    .and(new SqlObjectOperator(SqlObjectOperator.EQ)
                            .setLeftOperand("C3")
                            .addRightOperand(i))
                    .orderby("C1")
                    .desc()
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();

            List<Map<String, Object>> result = mapper.selectList(statementMap);
            assertEquals(1, result.size());

            java.sql.Timestamp timestampResult = (java.sql.Timestamp) (result.get(0).get(CTIMESTAMP));
            java.sql.Timestamp timestampOrg = (java.sql.Timestamp) (itemSet1.get(i).get(CTIMESTAMP));
            assertEquals(timestampOrg.getTime(), timestampResult.getTime());

            assertEquals(itemSet1.get(i).get(CSTR2), (String) result.get(0).get(CSTR2));
        }

        dropTables();
    }

    @Test
    public void testUpdate() {
        createTables();
        this.insertItemMulti(TABLE1);

        final double offset = 200.0;
        final double EPSILON = 0.0001;

        for(int i = 0; i < itemCount; i++) {
            String strI = String.valueOf(i);
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .update(TABLE1)
                    .set(
                            new SqlObjectOperator(SqlObjectOperator.EQ)
                                    .setLeftOperand("C3")
                                    .addRightOperand(i+offset),
                            new SqlObjectOperator(SqlObjectOperator.EQ)
                                    .setLeftOperand("C2")
                                    .addRightOperand(String.valueOf(i+offset))
                    )
                    .where(new SqlObjectOperator(SqlObjectOperator.EQ)
                            .setLeftOperand("C2")
                            .addRightOperand(strI))
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();

            int count = mapper.update(statementMap);
            assertEquals(1, count);
        }

        for(int i = 0; i < itemCount; i++) {
            String strI = String.valueOf(i+offset);
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .select("*")
                    .from(TABLE1)
                    .where(new SqlObjectOperator(SqlObjectOperator.EQ)
                            .setLeftOperand("C2")
                            .addRightOperand(strI))
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();

            List<Map<String, Object>> result = mapper.selectList(statementMap);
            assertEquals(1, result.size());

            java.sql.Timestamp timestampResult = (java.sql.Timestamp) (result.get(0).get(CTIMESTAMP));
            java.sql.Timestamp timestampOrg = (java.sql.Timestamp) (itemSet1.get(i).get(CTIMESTAMP));
            assertEquals(timestampOrg.getTime(), timestampResult.getTime());

            double actual = ((Number)result.get(0).get(CNUMBER)).doubleValue();
            double expected = ((Number)itemSet1.get(i).get(CNUMBER)).doubleValue();

            assertTrue(Math.abs(actual - expected - offset) < EPSILON);
        }

        dropTables();
    }

    @Test
    public void testDelete() {
        createTables();
        this.insertItemMulti(TABLE1);

        for(int i = 0; i < itemCount; i++) {
            String strI = String.valueOf(i);
            StatementProvider provider = new StatementProvider
                    .Builder()
                    .deleteFrom(TABLE1)
                    .where(new SqlObjectOperator(SqlObjectOperator.EQ)
                            .setLeftOperand("C2")
                            .addRightOperand(strI))
                    .build(buildType);
            Map<Object, Object> statementMap = provider.getStatementMap();

            int count = mapper.update(statementMap);
            assertEquals(1, count);
        }

        StatementProvider provider = new StatementProvider
                .Builder()
                .select("*")
                .from(TABLE1)
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();
        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(0, result.size());

        dropTables();
    }

    @Test
    public void testJoin() {
        createTables();
        this.insertItemMulti(TABLE1);
        this.insertItemMulti(TABLE2);

        StatementProvider provider = new StatementProvider
                .Builder()
                .select(TABLE1+".C1",TABLE2+".X7")
                .from(TABLE1)
                .innerjoin(TABLE2)
                .on(new SqlObjectOperator(SqlObjectOperator.EQ)
                    .setLeftOperand(TABLE1+".C1")
                    .setRightOperand(TABLE2+".C1",false))
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();
        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(50, result.size());

        dropTables();
    }

    @Test
    public void testLeftJoin() {
        createTables();
        this.insertItemMulti(TABLE1);
        this.insertItemMulti(TABLE2);

        StatementProvider provider = new StatementProvider
                .Builder()
                .select(TABLE1+".C1",TABLE2+".X7")
                .from(TABLE1)
                .leftjoin(TABLE2)
                .on(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .setLeftOperand(TABLE1+".C1")
                        .setRightOperand(TABLE2+".C1",false))
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();
        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(100, result.size());

        dropTables();
    }

    @Test
    public void testRightJoin() {
        createTables();
        this.insertItemMulti(TABLE1);
        this.insertItemMulti(TABLE2);

        StatementProvider provider = new StatementProvider
                .Builder()
                .select(TABLE1+".C1",TABLE2+".X7")
                .from(TABLE1)
                .rightjoin(TABLE2)
                .on(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .setLeftOperand(TABLE1+".C1")
                        .setRightOperand(TABLE2+".C1",false))
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();
        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(50, result.size());

        dropTables();
    }

    /**
     * This test is for MySql
     */
    @Test
    public void testOuterJoin() {
        createTables();
        this.insertItemMulti(TABLE1);
        this.insertItemMulti(TABLE2);

        StatementProvider provider = new StatementProvider
                .Builder()
                .select(TABLE1+".C1",TABLE2+".X7")
                .from(TABLE1)
                .leftjoin(TABLE2)
                .on(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .setLeftOperand(TABLE1+".C1")
                        .setRightOperand(TABLE2+".C1",false))
                .union()
                .select(TABLE1+".C1",TABLE2+".X7")
                .from(TABLE1)
                .rightjoin(TABLE2)
                .on(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .setLeftOperand(TABLE1+".C1")
                        .setRightOperand(TABLE2+".C1",false))
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();
        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(100, result.size());

        dropTables();
    }

    @Test
    public void testParenthesisJoin() {
        createTables();
        this.insertItemMulti(TABLE1);
        this.insertItemMulti(TABLE2);

        SqlObject condition = new SqlObjectOperator(SqlObjectOperator.EQ)
                                        .setLeftOperand(TABLE1+".C1")
                                        .setRightOperand(TABLE2+".C1",false);
        SqlObject and= new SqlObjectCommand(SqlObjectCommand.AND);
        and.addChild(condition, false);

        SqlObject parenthesis = new SqlObjectFunction(SqlObjectFunction.PARENTHESIS,false);
        parenthesis.addChild(condition, false);
        parenthesis.addChild(and, false);

        StatementProvider provider = new StatementProvider
                .Builder()
                .select(TABLE1+".C1",TABLE2+".X7")
                .from(TABLE1)
                .innerjoin(TABLE2)
                .on(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .setLeftOperand(TABLE1+".C1")
                        .setRightOperand(TABLE2+".C1",false))
                .and(parenthesis)
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();
        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(50, result.size());

        dropTables();
    }

    @Test
    public void testFunction() {
        createTables();

        insertItemMulti(TABLE1);

        SqlObject function = new SqlObjectFunction(SqlObjectFunction.COUNT,false);
        function.addChild("C1",false);
        String strI = String.valueOf(2);
        StatementProvider provider = new StatementProvider
                .Builder()
                .select(function)
                .from(TABLE1)
                .where(new SqlObjectOperator(SqlObjectOperator.EQ)
                        .setLeftOperand("C2")
                        .addRightOperand(strI))
                .build(buildType);
        Map<Object, Object> statementMap = provider.getStatementMap();

        List<Map<String, Object>> result = mapper.selectList(statementMap);
        assertEquals(1, result.size());

        dropTables();
    }
}
