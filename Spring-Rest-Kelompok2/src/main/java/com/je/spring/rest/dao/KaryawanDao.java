/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.dao;

import com.je.spring.rest.model.Karyawan;
import java.util.List;

/**
 *
 * @author maulana
 */
public interface KaryawanDao {
    public List<Karyawan> getAll();
    public Karyawan getById(int id);
    public void insert(Karyawan karyawan);
    public void update(Karyawan karyawan);
    public void delete(Karyawan karyawan);
    public long count();
}
