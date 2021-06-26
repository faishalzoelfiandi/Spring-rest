/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.dao.impl;

import com.je.spring.rest.model.Karyawan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.je.spring.rest.dao.KaryawanDao;
/**
 *
 * @author user
 */
@Service("karyawanDao")
public class KaryawanDaoImpl implements KaryawanDao{
    private static final String SQL_SELECT_KARYAWAN_BY_ID = "SELECT id,nama_karyawan,jenis_kelamin,jabatan,alamat FROM karyawan WHERE id = ?";
    private static final String SQL_SELECT_KARYAWAN_ALL = "SELECT id,nama_karyawan,jenis_kelamin,jabatan,alamat FROM karyawan";
    private static final String SQL_COUNT_KARYAWAN = "SELECT COUNT(*) FROM karyawan ";
    private static final String SQL_UPDATE_KARYAWAN = "UPDATE karyawan set nama_karyawan=?,jenis_kelamin=?,jabatan=?,alamat=? WHERE ID=?";
    private static final String SQL_DELETE_KARYAWAN = "DELETE FROM karyawan WHERE id=?";
    private static final String SQL_INSERT_KARYAWAN = "INSERT INTO karyawan (nama_karyawan,jenis_kelamin,jabatan,alamat) VALUES (?,?,?,?)";
    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Karyawan> getAll() {
        List<Karyawan> karyawanList = null;
        try{
            karyawanList = jdbcTemplate.query(SQL_SELECT_KARYAWAN_ALL, new Object[]{}, new BeanPropertyRowMapper(Karyawan.class));
        }catch(Exception e){
            e.printStackTrace();
    	} 
        return karyawanList;
    }

    public Karyawan getById(int id) {
        Karyawan karyawan = null;
        try{
            karyawan = (Karyawan) jdbcTemplate.queryForObject(SQL_SELECT_KARYAWAN_BY_ID, new Object[]{id}, new RowMapper<Karyawan>(){
                public Karyawan mapRow(ResultSet rs, int rowNum)throws SQLException{
                    Karyawan karyawan = new Karyawan();
                    karyawan.setId(rs.getInt("id"));
                    karyawan.setNama_karyawan(rs.getString("nama_karyawan"));
                    karyawan.setJenis_kelamin(rs.getString("jenis_kelamin"));
                    karyawan.setJabatan(rs.getString("jabatan"));
                    karyawan.setAlamat(rs.getString("alamat"));
                    return karyawan;
                }
            });
        }catch(Exception e){
    		e.printStackTrace();
    	}
        return karyawan;
    }

    @Override
    public void insert(Karyawan karyawan) {
        try{
            jdbcTemplate.update(SQL_INSERT_KARYAWAN, new Object[] {karyawan.getNama_karyawan(),karyawan.getJenis_kelamin(),karyawan.getJabatan(),karyawan.getAlamat()});
           
        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    @Override
    public void update(Karyawan karyawan) {
        try{
            jdbcTemplate.update(SQL_UPDATE_KARYAWAN, new Object[] {karyawan.getNama_karyawan(),karyawan.getJenis_kelamin(),karyawan.getJabatan(),karyawan.getAlamat(),karyawan.getId()});
           
        }catch(Exception e){
            e.printStackTrace();
        } 
    }

    @Override
    public void delete(Karyawan karyawan) {
        try{
            jdbcTemplate.update(SQL_UPDATE_KARYAWAN, new Object[] {
                karyawan.getId()
            });
           
        }catch(Exception e){
            e.printStackTrace();
        } 
    }

    public long count() {
        long count = 0;
            try{

              count = jdbcTemplate.queryForObject(SQL_COUNT_KARYAWAN, null,Long.class);

            }catch(Exception e){
                    e.printStackTrace();
            }
            return count;
    }
}
