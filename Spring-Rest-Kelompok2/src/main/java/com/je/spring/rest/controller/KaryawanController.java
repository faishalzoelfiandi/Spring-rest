/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.controller;

import com.je.spring.rest.model.Karyawan;
import com.je.spring.rest.util.Constants;
import static com.je.spring.rest.util.Constants.KARYAWAN_KEY;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.je.spring.rest.service.KaryawanService;
/**
 *
 * @author user
 */
@Controller
public class KaryawanController {
    
    @Autowired
    private KaryawanService karyawanService;
    
    @RequestMapping(value = "/master/karyawan", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getAll() {
        
        Map<String, Object> response = new HashMap<String, Object>();
        try{
            List<Karyawan> karyawanList = karyawanService.getAll();
            long count = karyawanService.count();
            
            response.put(Constants.LIST, karyawanList);
            response.put(Constants.TOTAL, count);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/karyawan/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getById(@PathVariable("id") final int id) {
        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            Karyawan karyawan = karyawanService.getById(id);
                
            response.put(Constants.KARYAWAN_KEY, karyawan);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/karyawan", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> insert(@RequestBody final Map<String, Object> request) {
        
        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> karyawanMap = (Map<String, Object>) request.get(Constants.KARYAWAN_KEY);
        Karyawan karyawan = new Karyawan();
        
        try{
            System.out.println("Test Karyawan");
            
            karyawan.setNama_karyawan((String) karyawanMap.get("nama_karyawan"));
            karyawan.setJenis_kelamin((String) karyawanMap.get("jenis_kelamin"));
            karyawan.setJabatan((String) karyawanMap.get("jabatan"));
            karyawan.setAlamat((String) karyawanMap.get("alamat"));
            
            
            karyawanService.insert(karyawan);
                
            response.put(Constants.STATUS, Constants.OK);
        } catch(Exception e){
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/karyawan/{id}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody Map<String, Object> update(@PathVariable("id") final int id,@RequestBody final Map<String, Object> request) {
        
        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> karyawanMap = (Map<String, Object>) request.get(Constants.KARYAWAN_KEY);
        Karyawan karyawan = new Karyawan();
        
        try{            
            karyawan.setId(id);
            karyawan.setNama_karyawan((String) karyawanMap.get("nama_karyawan"));
            karyawan.setJenis_kelamin((String) karyawanMap.get("jenis_kelamin"));
            karyawan.setJabatan((String) karyawanMap.get("jabatan"));
            karyawan.setAlamat((String) karyawanMap.get("alamat")); 
            karyawanService.update(karyawan);
                
            response.put(Constants.STATUS, Constants.OK);
        } catch(Exception e){
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/karyawan/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id") final int id) {
        
        Map<String, Object> response = new HashMap<String, Object>();
        Karyawan karyawan = new Karyawan();
        
        try{
            karyawan.setId(id);
            
            karyawanService.delete(karyawan);
                
            response.put(Constants.STATUS, Constants.OK);
        } catch(Exception e){
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
        }
        
        return response;
    }
    
}
