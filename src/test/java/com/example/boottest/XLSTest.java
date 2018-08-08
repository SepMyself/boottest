package com.example.boottest;

import com.coamc.xlsunit.*;
import com.example.boottest.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class XLSTest {
    XLSParser workflowParser = null;


    @Autowired
    UserService userService;

    @Autowired
    protected SQLManager sqlManager;

    @Before
    public void init() {
        //super.init();
        DBAccess dbAccess = new BeetlSqlDBAccess(sqlManager);
        XLSFileLoader loader = new XLSFileLoader("e:/");
        workflowParser = new XLSParser(loader, "testCase.xlsx", dbAccess,
                new RowHolderFacotoy.RowBeetlSQLHolderFactory());
    }

    @Test
    public void testPasswordChange() {
        VariableTable vars = new VariableTable();
        workflowParser.init(vars);

        workflowParser.prepare("password change", vars);

        

        workflowParser.test("修改密码", vars);
    }
}
