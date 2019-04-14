package com.example.api.controller;

import com.example.api.bean.ApiBean;
import com.example.api.dao.APRepository;
import com.example.api.dao.ApiRepository;
import com.example.api.dao.ParmRepository;
import com.example.api.entity.Addr_Parm;
import com.example.api.entity.Address;
import com.example.api.entity.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ParmRepository parmRepository;

    @Autowired
    private APRepository apRepository;

    @GetMapping("")
    public ModelAndView queryAll(Map<String, Object> map){
        List<Address> addrList = apiRepository.findAll();
        System.out.println(addrList);
        map.put("addrList",addrList);
        return new ModelAndView("",map);
    }

    @GetMapping("/query/{userId}")
    public ModelAndView queryAllByUserId(@PathVariable Integer userId,
                                         Map<String, Object> map){
        List<Address> addrList = apiRepository.findAllByUserId(userId);
        System.out.println(addrList);
        map.put("MyAddrList",addrList);
        return new ModelAndView("",map);
    }

    @GetMapping("/query/{userId}/{apiId}")
    public ModelAndView queryByAddrId(@PathVariable(value = "apiId") Integer apiId,
                              Map<String, Object> map){
        Address address = apiRepository.findByApiId(apiId);
        List<Addr_Parm> addrParms = apRepository.findAllParmId(apiId);
        List<Parameter> parameters = new ArrayList<>();
        for(Addr_Parm addrParm : addrParms){
            parameters.add(parmRepository.findByParmId(addrParm.getParmId()));
        }
        ApiBean apiBean = new ApiBean(address,parameters);
        map.put("apiInfo",apiBean);
        return new ModelAndView("",map);
    }

    @PostMapping("/addApi")
    public ModelAndView add(@RequestParam(value = "address") String address,
                    @RequestParam(value = "userId") Integer userId,
                    @RequestParam(value = "parmName") String[] parmName,
                   @RequestParam(value = "parmType") String[] parmType,
                   @RequestParam(value = "parmIsmust") String[] parmIsmust,
                   @RequestParam(value = "parmDescribe") String[] parmDescribe){
        if(apiRepository.findByAddress(address) != null){
            return new ModelAndView("");
        }
        Address address1 = new Address(address,userId);
        apiRepository.save(address1);
        saveParm(address1.getApiId(),parmName,parmType,parmIsmust,parmDescribe);
        return new ModelAndView("");
    }

    @PutMapping("/update/{userId}/{apiId}")
    public ModelAndView updateById(@PathVariable Integer userId,
                          @PathVariable Integer apiId,
                           @RequestParam(value = "address") String address,
                           @RequestParam(value = "parmName") String[] parmName,
                           @RequestParam(value = "parmType") String[] parmType,
                           @RequestParam(value = "parmIsmust") String[] parmIsmust,
                           @RequestParam(value = "parmDescribe") String[] parmDescribe){
        Address address1 = apiRepository.findByApiId(apiId);
        address1.setAddress(address);
        apiRepository.saveAndFlush(address1);
        List<Addr_Parm> addrParms = apRepository.findAllParmId(apiId);
        for(Addr_Parm addrParm : addrParms){
            parmRepository.deleteById(addrParm.getParmId());
            apRepository.delete(addrParm);
        }
        saveParm(apiId,parmName,parmType,parmIsmust,parmDescribe);
        return new ModelAndView("");
    }

    @DeleteMapping("/delete/{apiId}")
    public ModelAndView deleteById(@PathVariable Integer apiId){
        List<Addr_Parm> addrParms = apRepository.findAllParmId(apiId);
        apiRepository.deleteById(apiId);
        if(addrParms != null && addrParms.size() > 0){
            for(Addr_Parm addrParm : addrParms){
                parmRepository.deleteById(addrParm.getParmId());
                apRepository.delete(addrParm);
            }
        }
        return new ModelAndView("");
    }

    @DeleteMapping("/deleteAll/{userId}")
    public ModelAndView deleteAll(@PathVariable Integer userId){
        List<Address> addressList = apiRepository.findAllByUserId(userId);
        for(Address address : addressList){
            deleteById(address.getApiId());
        }
        return new ModelAndView("");
    }

    private boolean saveParm(Integer apiId,String[] parmName,String[] parmType,String[] parmIsmust,String[] parmDescribe){
        if(parmName != null && parmType != null && parmIsmust != null && parmDescribe != null) {
            System.out.println(parmName.toString());
            System.out.println(parmType.toString());
            System.out.println(parmIsmust.toString());
            System.out.println(parmDescribe.toString());
            for (int i = 0;i < parmName.length;i++) {
                Integer must;
                if(parmIsmust.equals("是")){
                    must = Integer.valueOf(1);
                }else{
                    must = Integer.valueOf(0);
                }
                Parameter parameter = new Parameter(parmName[i],parmType[i],must,parmDescribe[i]);
                parmRepository.save(parameter);
                apRepository.save(new Addr_Parm(apiId, parameter.getParmId()));
            }
            return true;
        }
        return false;
    }

}
