package com.ss.rh.service.impl;

import com.ss.rh.dao.JournalMapper;
import com.ss.rh.entity.Journal;
import com.ss.rh.entity.JournalExample;
import com.ss.rh.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    JournalMapper journalMapper;

    @Override
    public List<Journal> getJournalsByRid(int rid) {
        JournalExample je = new JournalExample();
        je.createCriteria().andOrderIdEqualTo(rid);
        je.setOrderByClause("createTime");

        return journalMapper.selectByExample(je);
    }

    @Override
    public boolean insertJournal(Journal journal) {
        return journalMapper.insert(journal) > 0;
    }

    @Override
    public boolean deleteJournalById(int id) {
        return journalMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Journal getJournalById(int id) {
        return journalMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateJournal(Journal journal) {
        return journalMapper.updateByPrimaryKey(journal) > 0;
    }
}
