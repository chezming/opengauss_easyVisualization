package com.huawei.datashow.controller;

import com.huawei.datashow.bean.ParamDataSourceEditBean;
import com.huawei.datashow.service.HandleDataSourceService;
import com.huawei.datashow.util.MyException;
import com.huawei.datashow.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@RestController
@RequestMapping("/handle-data-source")
public class HandleDataSourceController {
    @Autowired
    private HandleDataSourceService handleDataSourceServiceImpl;

    @PostMapping("/save-data-source")
    public Result<Object> saveDataSource(@RequestParam("pollName") String pollName,
                                         @RequestParam("sql") String sql,
                                         @RequestParam("dataSourceName") String dataSourceName)
    {
        try {
            handleDataSourceServiceImpl.saveDataSource(pollName, sql, dataSourceName);
            return Result.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("error");
        }
    }

    @GetMapping("/read-data-source")
    public Result<Object> readDataSource(@RequestParam("dataSourceName") String dataSourceName,
                                         @RequestParam("startIndex") int startIndex,
                                         @RequestParam("limit") int limit)
    {
        try {
            String data = handleDataSourceServiceImpl.readDataSource(dataSourceName, startIndex, limit);
            return Result.OK(data);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        }
    }

    @GetMapping("/get-data-source-size")
    public Result<Object> getDataSourceSize(@RequestParam("dataSourceName") String dataSourceName){
        try {
            int dataSourceSize = handleDataSourceServiceImpl.getDataSourceSize(dataSourceName);
            return Result.OK(dataSourceSize);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        }
    }

    @GetMapping("/remove-data-source")
    public Result<Object> removeDataSource(@RequestParam("dataSourceName") String dataSourceName){
        try {
            handleDataSourceServiceImpl.removeDataSource(dataSourceName);
            return Result.OK();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        }
    }

    @GetMapping("/get-data-source-list")
    public Result<Object> getDataSourceList(){
        return Result.OK(handleDataSourceServiceImpl.getDataSourceList());
    }

    @PostMapping("/edit-data-source")
    public Result<Object> editDataSource(@RequestBody ParamDataSourceEditBean paramDataSourceEditBean) {
        try {
            handleDataSourceServiceImpl.editDataSource(paramDataSourceEditBean.getDataSourceName(), paramDataSourceEditBean.getDataSourceEditBean());
            return Result.OK();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        }
    }

    @GetMapping("/reload-data-source")
    public Result<Object> reloadDataSource(@RequestParam("dataSourceName") String dataSourceName) {
        try {
            handleDataSourceServiceImpl.reloadDataSource(dataSourceName);
            return Result.OK();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        }
    }

    @GetMapping("/save-edit-data-source")
    public Result<Object> saveEditDataSource(@RequestParam("dataSourceName") String dataSourceName) {
        try {
            handleDataSourceServiceImpl.saveEditDataSource(dataSourceName);
            return Result.OK();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        } catch (MyException myException) {
            return Result.error("Data source is empty! Please Delete Instead.");
        }
    }

    @GetMapping("/get-edit-status")
    public Result<Object> getEditStatus(@RequestParam("dataSourceName") String dataSourceName) {
        try {
            boolean editStatus = handleDataSourceServiceImpl.getEditStatus(dataSourceName);
            return Result.OK(editStatus);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return Result.error("error");
        }
    }

}
