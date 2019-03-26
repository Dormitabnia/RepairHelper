package com.ss.rh.service;

import com.ss.rh.entity.Journal;

import java.util.List;

public interface JournalService {

    List<Journal> getJournalsByRid(int rid);

    boolean insertJournal(Journal journal);

    boolean deleteJournalById(int id);

    Journal getJournalById(int id);

    boolean updateJournal(Journal journal);

}
