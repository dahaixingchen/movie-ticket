package com.fei.add.ticket.service.impl;

import com.fei.add.ticket.mapper.SaveDataMapper;
import com.fei.add.ticket.service.SaveDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: SaveData2MysqlServiceImpl
 * @Author chengfei
 * @Date 2020/11/4 10:26
 * @Description: TODO
 **/
@Service
public class SaveData2MysqlServiceImpl implements SaveDataService {

    @Resource
    SaveDataMapper saveDataMapper;

    @Override
    public void savaData(String line) {
        saveDataMapper.saveData(line);
    }
}
