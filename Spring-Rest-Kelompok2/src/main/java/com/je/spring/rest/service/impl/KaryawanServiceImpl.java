/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.service.impl;

import com.je.spring.rest.model.Karyawan;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.je.spring.rest.dao.KaryawanDao;
import com.je.spring.rest.service.KaryawanService;

/**
 *
 * @author user
 */
@Service("karyawanService")
public class KaryawanServiceImpl implements KaryawanService{
    
    @Autowired
    KaryawanDao karyawanDao;

    public List<Karyawan> getAll() {
        List<Karyawan> karyawanList = new ArrayList<Karyawan>();
        karyawanList = karyawanDao.getAll();
        return karyawanList;
    }

    public Karyawan getById(int id) {
        Karyawan karyawan = new Karyawan();
        karyawan = karyawanDao.getById(id);
        return karyawan;
    }

    public void insert(Karyawan karyawan) {
        karyawanDao.insert(karyawan);
    }

    public void update(Karyawan karyawan) {        
        karyawanDao.update(karyawan);
    }
    
    public void delete(Karyawan karyawan) {        
        karyawanDao.delete(karyawan);
    }

    public long count() {        
        return karyawanDao.count();
    }
}
